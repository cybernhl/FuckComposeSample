package com.top.compose.sample.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Immutable

@Immutable
data class User(
    var admin: Boolean,
    var chapterTops: List<String>,
    var email: String?,
    var icon: String?,
    var id: Int,
    var nickname: String? = "昵称",
    var publicName: String?,
    var token: String?,
    var type: Int,
    var username: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (admin) 1 else 0)
        parcel.writeStringList(chapterTops)
        parcel.writeString(email)
        parcel.writeString(icon)
        parcel.writeInt(id)
        parcel.writeString(nickname)
        parcel.writeString(publicName)
        parcel.writeString(token)
        parcel.writeInt(type)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}