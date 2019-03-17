package `in`.news.ui.model

import android.os.Parcel
import android.os.Parcelable


class Category constructor(var label: String) : Parcelable
{
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.label);
        dest?.writeString(this.category);
    }


    var category=label.toLowerCase()

    constructor(parcel: Parcel) : this(parcel.readString()) {
        label = parcel.readString()
        category = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }


}