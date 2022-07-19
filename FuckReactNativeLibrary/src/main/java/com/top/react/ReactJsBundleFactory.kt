package com.top.react

import android.content.Context
import com.top.react.utils.FileUtils
import java.io.File
import java.io.IOException
import java.util.*

class ReactJsBundleFactory(context: Context?, options: ReactNativeOptions?) {

    private var mContext: Context? = context
    private var mOptions: ReactNativeOptions? = options



    @Throws(IOException::class)
    fun install(jsBundleZipPath: String?, destJsBundleFileName: String?): String? {

        val binFile = File(jsBundleZipPath)
        val dist = File(mOptions?.jsBundleInstallDir, destJsBundleFileName).absolutePath
        val packageFile = File(dist)
        if (!packageFile.exists()) packageFile.mkdirs()
        val unzippedFolderPath: String = getUnzippedFolderPath()
        FileUtils.unzipFile(binFile, unzippedFolderPath)
        FileUtils.deleteFileOrFolderSilently(binFile)
        FileUtils.copyDirectoryContents(unzippedFolderPath, dist)
        FileUtils.deleteFileAtPathSilently(unzippedFolderPath)

        return dist
    }

    private fun getUnzippedFolderPath(): String {
        val temp = mContext!!.cacheDir.absolutePath
        return FileUtils.appendPathComponent(
            temp,
            UUID.randomUUID().toString().replace("-".toRegex(), "")
        )
    }
}