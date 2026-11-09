package com.example.abil.Laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abil.R
import com.example.abil.databinding.FragmentLaporanBinding
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator

class LaporanFragment : Fragment() {
    private var _binding: FragmentLaporanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup Adapter
        val tabsAdapter = LaporanTabsAdapter(this)
        binding.viewPagerLaporan.adapter = tabsAdapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayoutLaporan, binding.viewPagerLaporan) { tab, position ->
            tab.text = if (position == 0) "Semua Laporan" else "Buat Laporan"
        }.attach()

        // TAMBAHKAN KODE INI: Cek apakah ada perintah untuk membuka tab spesifik
        arguments?.let { bundle ->
            val targetTab = bundle.getInt("TARGET_TAB", 0)
            binding.viewPagerLaporan.post {
                binding.viewPagerLaporan.setCurrentItem(targetTab, false)
            }
        }

        // Logika Penggantian Judul Toolbar
        binding.viewPagerLaporan.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                appCompatActivity.supportActionBar?.title =
                    if (position == 0) "Daftar Laporan" else "Buat Laporan Baru"
            }
        })
    }
}