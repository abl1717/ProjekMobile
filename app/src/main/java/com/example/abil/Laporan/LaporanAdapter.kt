package com.example.abil.Laporan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.abil.databinding.ItemLaporanBinding

class LaporanAdapter(
    context: Context,
    private val messages: List<LaporanModel>
) : ArrayAdapter<LaporanModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position]
// Muat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.imgLapor)
        binding.txtJudul.text = data.judul
        binding.txtMessage.text = data.messageText

        view.setOnClickListener {
            val intent = Intent(context, LaporanActivity::class.java)

            intent.putExtra("JUDUL", data.judul)
            intent.putExtra("MESSAGE", data.messageText)

            context.startActivity(intent)
//            Snackbar.make(
//                parent,
//                "Pesan dari ${data.senderName}: ${data.messageText}",
//                Snackbar.LENGTH_SHORT
//            ).show()
        }
        return view
    }
}