package com.example.abil.Informasi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.abil.databinding.ItemInformasiBinding

class InformasiAdapter(
    private val context: Context,
    private val infoList: List<InformasiModel> // <-- Ubah di sini menjadi InformasiModel
) : RecyclerView.Adapter<InformasiAdapter.InfoViewHolder>() {

    inner class InfoViewHolder(val binding: ItemInformasiBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding =
            ItemInformasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val item = infoList[position]

        // <-- Pemanggilan data jauh lebih bersih tanpa tanda kurung siku []
        holder.binding.txtJudulInfo.text = item.title
        holder.binding.txtTanggalInfo.text = item.date

        holder.binding.root.setOnClickListener {
            Toast.makeText(context, "Membuka: ${item.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = infoList.size
}