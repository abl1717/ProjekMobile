package com.example.abil.Laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.abil.R
import com.example.abil.databinding.FragmentLaporanBinding

class LaporanFragment : Fragment() {
    private var _binding: FragmentLaporanBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        LaporanModel(
            "Perbaikan Jalan",
            "Jalan di RT 01 RW 01 tidak layak dilalui karena lubang",
            "https://i.pravatar.cc/150?img=1"
        ),
        LaporanModel(
            "Pemasangan Lampu Jalan",
            "Lampu jalan di RT 03 RW 02 sudah redup karena faktor usia",
            "https://i.pravatar.cc/150?img=2"
        ),
        LaporanModel(
            "Perbaikan Jembatan",
            "Jembatan di sungai jambu terputus karena debit air meningkat",
            "https://i.pravatar.cc/150?img=3"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon tumbang di Dusun Koto karena angin kencang",
            "https://i.pravatar.cc/150?img=4"
        ),
        LaporanModel(
            "Jalan Terputus",
            "Longsor mengakibatkan jalan akses keluar desa terputus",
            "https://i.pravatar.cc/150?img=5"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Laporan"

        val adapter = LaporanAdapter(requireContext(), messageList)
        binding.listLaporanItems.adapter = adapter
    }
}