package com.top.react.download

import android.content.Context
import android.os.Build
import android.text.TextUtils
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class Downloader(private var mContext: Context) {

    private var DOWNLOAD_BUFFER_SIZE = 1024 * 256
    private lateinit var mRequest: Request
    private val EXECUTOR_SERVICE = Executors.newFixedThreadPool(5)

    fun request(request: Request): Downloader {
        this.mRequest = request
        return this
    }

    fun execute(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (Exception) -> Unit,
        onProcess: () -> Unit
    ) {
        EXECUTOR_SERVICE.execute {
            var connection: HttpURLConnection? = null
            var bin: BufferedInputStream? = null
            var fos: FileOutputStream? = null
            var bout: BufferedOutputStream? = null
            val downloadFile: File

            try {
                val url = URL(mRequest.url)
                connection = url.openConnection() as HttpURLConnection?
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && url.toString().startsWith("https")) {
                    (connection as HttpsURLConnection).sslSocketFactory = TLSSSocketFactory()
                }
                connection?.setRequestProperty("Accept-Encoding", "identity")
                bin = BufferedInputStream(connection?.inputStream)

                val totalBytes = connection?.contentLength
                var receivedBytes = 0
                val downloadFolder = File(mContext.cacheDir, "")
                val canMkdir: Boolean = downloadFolder.mkdirs()
                val path: String = url.path
                val fileName = path.substring(path.lastIndexOf('/'))

                downloadFile = File(
                    downloadFolder,
                    if (TextUtils.isEmpty(fileName)) "" else fileName
                )

                fos = FileOutputStream(downloadFile)
                bout = BufferedOutputStream(fos, DOWNLOAD_BUFFER_SIZE)
                val data = ByteArray(DOWNLOAD_BUFFER_SIZE)

                var numBytesRead: Int

                while ((bin.read(data, 0, DOWNLOAD_BUFFER_SIZE).also { numBytesRead = it }) >= 0) {
                    receivedBytes += numBytesRead
                    bout.write(data, 0, numBytesRead)
                }

                fos.flush()
                bout.flush()

                if (totalBytes == receivedBytes) {
                    //callback.onComplete(downloadFile.absolutePath)
                } else {
                    //callback.onError(RuntimeException("Received $receivedBytes bytes, expected $totalBytes"))
                }

            } catch (e: Exception) {
                onError.invoke(e)
            } finally {
                try {
                    bout?.close()
                    fos?.close()
                    bin?.close()
                    connection?.disconnect()
                } catch (e: IOException) {
                    throw RuntimeException("Error closing IO resources.", e)
                }
            }

        }
    }
}