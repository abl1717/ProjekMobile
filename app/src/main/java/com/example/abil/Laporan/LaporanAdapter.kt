package com.example.abil.Laporan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abil.databinding.ItemLaporanBinding

class LaporanAdapter(
    private val context: Context,
    private val messages: List<LaporanModel>
) : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {

    inner class LaporanViewHolder(val binding: ItemLaporanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaporanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        val data = messages[position]

        // Set Data Teks
        holder.binding.txtJudul.text = data.judul
        holder.binding.txtStatus.text = data.status
        holder.binding.txtDukungan.text = "${data.dukungan} Dukungan"

        // Muat Gambar
        Glide.with(context)
            .load(data.avatarUrl)
            .into(holder.binding.imgLapor)

        // Aksi Klik Seluruh Kartu (Ke Detail)
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, LaporanActivity::class.java)
            intent.putExtra("JUDUL", data.judul)
            intent.putExtra("MESSAGE", data.messageText)
            context.startActivity(intent)
        }

        // Aksi Klik Tombol Vote
        holder.binding.btnVote.setOnClickListener {
            Toast.makeText(context, "Anda mem-vote: ${data.judul}", Toast.LENGTH_SHORT).show()
            // Logika simpan vote ke database nantinya diletakkan di sini
        }
    }

    override fun getItemCount(): Int = messages.size
}