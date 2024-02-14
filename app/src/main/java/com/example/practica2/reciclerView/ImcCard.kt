package com.example.practica2.reciclerView

import android.os.Parcel
import android.os.Parcelable

data class ImcCard(
    var genero: String?,
    var imc:Double,
    var weight:Double,
    var fecha: String?,
    var message: String?
):Parcelable{




    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(genero)
        parcel.writeDouble(imc)
        parcel.writeDouble(weight)
        parcel.writeString(fecha)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImcCard> {
        override fun createFromParcel(parcel: Parcel): ImcCard {
            return ImcCard(parcel)
        }

        override fun newArray(size: Int): Array<ImcCard?> {
            return arrayOfNulls(size)
        }
    }

}

var imcResult: MutableList<ImcCard> = mutableListOf<ImcCard>()
fun getImcResultHistory():MutableList<ImcCard> = imcResult
fun addIMCResultHistory(imc:ImcCard) = imcResult.add(imc)



