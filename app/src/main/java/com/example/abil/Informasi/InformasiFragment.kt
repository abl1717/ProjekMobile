package com.example.abil.Informasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abil.R
import com.example.abil.databinding.FragmentInformasiBinding

class InformasiFragment : Fragment() {
    private var _binding: FragmentInformasiBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        InformasiModel("Undangan Rapat", "Diberitahukan kepada seluruh masyarakat", "2020-10-15"),
        InformasiModel("Gotong Royong", "Gotong royong 19 Oktober 2026", "2021-10-19"),
        InformasiModel(
            "Pembagian BLT Desa",
            "Pelaksanaan di Balai Desa pukul 09.00 WIB",
            "2022-10-25"
        ),
        InformasiModel("Posyandu Balita", "Diadakan di Balai RW 03", "2024-10-20")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        appCompatActivity.supportActionBar?.apply {
            title = "Papan Informasi"
            setDisplayHomeAsUpEnabled(true) // Menyalakan tombol panah kembali (<)
        }

        // Urutkan data berdasarkan tanggal terbaru
        val sortedData = dataListWithDesc.sortedByDescending { it.date }

        // 1. Set Data Highlight (Ambil item pertama/terbaru)
        if (sortedData.isNotEmpty()) {
            val highlightData = sortedData.first()
            binding.txtJudulHighlight.text = highlightData.title
            binding.txtTanggalHighlight.text = highlightData.date

            binding.cardHighlight.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "Membuka Highlight: ${highlightData.title}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 2. Set Data Info Terkini (Ambil sisa datanya, lompati item pertama)
        val rvData = sortedData.drop(1)

        // Setup RecyclerView secara Horizontal
        binding.rvInfoTerkini.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, // Mode scroll menyamping
            false
        )
        binding.rvInfoTerkini.adapter = InformasiAdapter(requireContext(), rvData)

        // Tombol Lihat Semua
        binding.txtLihatSemua.setOnClickListener {
            Toast.makeText(requireContext(), "Buka Halaman Semua Informasi", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}