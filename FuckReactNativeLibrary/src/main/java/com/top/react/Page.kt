package com.top.react

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable


class Page : Parcelable {
    private var name: String? = null
    private var url: String? = null
    private var extras: Bundle? = null
    fun url(): String? {
        return url
    }

    constructor() {}

    fun filePath(): String {
        val start = url!!.lastIndexOf("://") + 3
        return url!!.substring(start)
    }

    fun url(url: String?): Page {
        this.url = url
        return this
    }

    fun name(): String? {
        return name
    }

    fun name(name: String?): Page {
        this.name = name
        return this
    }

    fun extras(): Bundle? {
        return extras
    }

    fun extras(extras: Bundle?): Page {
        this.extras = extras
        return this
    }

    fun extras(key: String?, value: String?): Page {
        if (null == extras) {
            extras = Bundle()
        }
        extras!!.putString(key, value)
        return this
    }

    fun extras(key: String?, value: Int?): Page {
        if (null == extras) {
            extras = Bundle()
        }
        extras!!.putInt(key, value!!)
        return this
    }

    fun extras(key: String?, value: Byte): Page {
        if (null == extras) {
            extras = Bundle()
        }
        extras!!.putByte(key, value)
        return this
    }

    fun extras(key: String?, value: Long): Page {
        if (null == extras) {
            extras = Bundle()
        }
        extras!!.putLong(key, value)
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(url)
        dest.writeBundle(extras)
    }

    fun readFromParcel(source: Parcel) {
        name = source.readString()
        url = source.readString()
        extras = source.readBundle(javaClass.classLoader)
    }

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        url = `in`.readString()
        extras = `in`.readBundle(javaClass.classLoader)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Page?> = object : Parcelable.Creator<Page?> {
            override fun createFromParcel(source: Parcel): Page? {
                return Page(source)
            }

            override fun newArray(size: Int): Array<Page?> {
                return arrayOfNulls(size)
            }
        }
    }
}
