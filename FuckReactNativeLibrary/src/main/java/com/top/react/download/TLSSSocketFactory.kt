package com.top.react.download

import java.net.InetAddress
import java.net.Socket
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class TLSSSocketFactory : SSLSocketFactory() {
    private var delegate: SSLSocketFactory? = null


    @Throws(KeyManagementException::class, NoSuchAlgorithmException::class)
    fun TLSSocketFactory() {
        val context = SSLContext.getInstance("TLS")
        context.init(null, null, null)
        delegate = context.socketFactory
    }

    override fun createSocket(): Socket? {
        return delegate?.createSocket()?.let { enableTLSOnSocket(it) }
    }

    override fun createSocket(
        socket: Socket,
        host: String,
        port: Int,
        autoClose: Boolean
    ): Socket? {
        return delegate?.createSocket(socket, host, port, autoClose)?.let { enableTLSOnSocket(it) }
    }

    override fun createSocket(host: String?, port: Int): Socket? {
        return delegate?.createSocket(host, port)?.let { enableTLSOnSocket(it) }
    }

    override fun createSocket(
        host: String?,
        port: Int,
        localHost: InetAddress?,
        localPort: Int
    ): Socket? {
        return delegate?.createSocket(host, port, localHost, localPort)
            ?.let { enableTLSOnSocket(it) }
    }

    override fun createSocket(localHost: InetAddress?, port: Int): Socket? {
        return delegate?.createSocket(localHost, port)?.let { enableTLSOnSocket(it) }
    }

    override fun createSocket(
        address: InetAddress?,
        port: Int,
        localAddress: InetAddress?,
        localPort: Int
    ): Socket? {
        return delegate?.createSocket(address, port, localAddress, localPort)
            ?.let { enableTLSOnSocket(it) }
    }

    override fun getDefaultCipherSuites(): Array<out String>? {
        return delegate?.defaultCipherSuites
    }

    override fun getSupportedCipherSuites(): Array<out String>? {
        return delegate?.supportedCipherSuites
    }


    private fun enableTLSOnSocket(socket: Socket): Socket {
        if (socket is SSLSocket) {
            socket.enabledProtocols =
                arrayOf("TLSv1.1", "TLSv1.2")

        }
        return socket
    }

}
