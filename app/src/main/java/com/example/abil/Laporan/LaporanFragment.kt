package com.example.abil.Laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abil.R
import com.example.abil.databinding.FragmentLaporanBinding
import com.google.android.material.chip.Chip

class LaporanFragment : Fragment() {
    private var _binding: FragmentLaporanBinding? = null
    private val binding get() = _binding!!

    // CATATAN: Pastikan Anda menambahkan variabel 'date: String' di LaporanModel.kt
    // Contoh: data class LaporanModel(val judul: String, val messageText: String, val avatarUrl: String, val date: String)
    private val messageList = listOf(
        LaporanModel(
            "Perbaikan Jalan",
            "Jalan di RT 01 RW 01 tidak layak dilalui karena lubang",
            "https://i.pravatar.cc/150?img=1",
            "2026-10-20"
        ), LaporanModel(
            "Pemasangan Lampu Jalan",
            "Lampu jalan di RT 03 RW 02 sudah redup karena faktor usia",
            "https://i.pravatar.cc/150?img=2",
            "2026-10-18"
        ), LaporanModel(
            "Perbaikan Jembatan",
            "Jembatan di sungai jambu terputus karena debit air meningkat",
            "https://i.pravatar.cc/150?img=3",
            "2026-10-15"
        ), LaporanModel(
            "Jalan Terputus",
            "Longsor mengakibatkan jalan akses keluar desa terputus",
            "https://i.pravatar.cc/150?img=5",
            "2026-10-10"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Laporan"

        // 1. Set up RecyclerView Awal (Menampilkan semua data)
        binding.rvLaporanItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLaporanItems.adapter = LaporanAdapter(requireContext(), messageList)

        // 2. Logika ChipGroup Filter
        binding.chipGroupLaporan.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull() // ambil id chip yang dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()

                // Buat daftar baru dengan filter
                val filteredData = when (selectedChipId) {
                    R.id.chipTerbaru -> {
                        messageList.sortedByDescending { it.date }
                    }

                    R.id.chipTerlama -> {
                        messageList.sortedBy { it.date }
                    }

                    else -> messageList
                }

                // Sesuai petunjuk modul: pasang adapter baru untuk memuat data yang sudah difilter
                binding.rvLaporanItems.adapter = LaporanAdapter(requireContext(), filteredData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}