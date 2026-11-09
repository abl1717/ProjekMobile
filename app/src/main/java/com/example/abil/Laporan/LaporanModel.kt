package com.example.abil.Laporan

data class LaporanModel(
    val judul: String,
    val messageText: String,
    val avatarUrl: String,
    val date: String, // Tambahan parameter tanggal untuk keperluan filter
    val status: String = "Diproses",
    val dukungan: Int = 0
)
