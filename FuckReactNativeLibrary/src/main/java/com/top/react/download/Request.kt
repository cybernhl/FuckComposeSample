package com.top.react.download

class Request(val url: String?) {

    class Builder {
        private var url: String? = null
        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun build(): Request {
            return Request(url)
        }
    }
}