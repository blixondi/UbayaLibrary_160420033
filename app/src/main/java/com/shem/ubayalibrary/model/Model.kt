package com.shem.ubayalibrary.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id")
    val bookId: String?,
    @SerializedName("judul")
    val bookTitle: String?,
    val isbn: String?,
    val sinopsis: String?,
    val penulis: String?,
    @SerializedName("tahun_terbit")
    val tahunTerbit: String?,
    val dimensi: String?,
    val penerbit: String?,
    val ketersediaan: String?,
    val gambar: String?
)

data class Thesis(
    @SerializedName("id")
    val thesisId: String?,
    val penulis: String?,
    @SerializedName("judul")
    val thesisTitle: String?,
    val abstrak: String?,
    @SerializedName("tahun_terbit")
    val tahunTerbit: String?,
    val jenis_pustaka: String?
)

data class History(
    val judul: String?,
    val gambar: String?,
    val status: String?,
    val tanggal: String?
)

data class User(
    val id: String?,
    val username: String?,
    val password: String?,
    val firstname: String?,
    val lastname: String?
)