package com.example.abil.Laporan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LaporanTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 2

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListLaporanFragment()
            1 -> FormLaporanFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}