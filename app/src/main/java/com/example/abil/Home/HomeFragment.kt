package com.example.abil.Home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.abil.Informasi.InformasiAdapter
import com.example.abil.Informasi.InformasiFragment
import com.example.abil.Informasi.InformasiModel
import com.example.abil.Laporan.LaporanFragment
import com.example.abil.R
import com.example.abil.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.os.Handler

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Handler untuk Jam Real-time
    private val timeHandler = Handler(Looper.getMainLooper())
    private lateinit var timeRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Set Sapaan Pengguna
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = requireActivity().intent.getStringExtra("username")
            ?: sharedPref.getString("username", "Warga")
        binding.txtUsername.text = "Halo, $user!"

        // 2. Set Foto Profil dengan Glide (Gambar Avatar Dummy)
        Glide.with(this)
            .load("https://i.pravatar.cc/150?img=12")
            .into(binding.imgProfile)

        // 3. Jalankan Jam & Tanggal Real-time
        startClock()

        // 4. Data Dummy Pengumuman (Sama seperti di Halaman Informasi)
        val dataPengumuman = listOf(
            InformasiModel(
                "Undangan Rapat",
                "Diberitahukan kepada seluruh masyarakat",
                "2020-10-15"
            ),
            InformasiModel("Gotong Royong", "Gotong royong 19 Oktober 2026", "2021-10-19"),
            InformasiModel(
                "Pembagian BLT Desa",
                "Pelaksanaan di Balai Desa pukul 09.00 WIB",
                "2022-10-25"
            )
        ).sortedByDescending { it.date }

        // Setup RecyclerView Pengumuman di Home (Horizontal)
        binding.rvPengumumanHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPengumumanHome.adapter = InformasiAdapter(requireContext(), dataPengumuman)

        // 5. Logika Navigasi Tombol

        // Tombol Buat Laporan Baru -> Buka LaporanFragment dan arahkan ke Tab 1 (Form)
        binding.btnLaporBeranda.setOnClickListener {
            val laporanFragment = LaporanFragment()
            val bundle = Bundle()
            bundle.putInt("TARGET_TAB", 1) // 1 adalah index untuk FormLaporanFragment
            laporanFragment.arguments = bundle

            pindahFragment(laporanFragment, R.id.btnLaporBeranda)
        }

        // Tombol Navigasi Laporan (Buka Tab 0/Default)
        binding.btnLaporRound.setOnClickListener {
            pindahFragment(LaporanFragment(), R.id.btnLaporRound)
        }

        // Tombol Navigasi Informasi
        binding.btnInfoRound.setOnClickListener {
            pindahFragment(InformasiFragment(), R.id.btnInfoRound)
        }

        // Tombol Vote (Biarkan kosong sementara)
        binding.btnVoteRound.setOnClickListener {
            // TODO: Logika Vote
        }
    }

    private fun startClock() {
        // Format jam "09:00" dan tanggal "Senin, 02 Okt 2026"
        val timeFormat = SimpleDateFormat("HH:mm", Locale("id", "ID"))
        val dateFormat = SimpleDateFormat("EEEE, dd MMM yyyy", Locale("id", "ID"))

        timeRunnable = object : Runnable {
            override fun run() {
                val currentTime = Date()
                binding.txtJam.text = timeFormat.format(currentTime)
                binding.txtTgl.text = dateFormat.format(currentTime)

                // Update setiap 1 menit (60.000 ms) agar hemat baterai
                timeHandler.postDelayed(this, 60000)
            }
        }
        timeHandler.post(timeRunnable)
    }

    // Fungsi bantuan untuk mengganti Fragment sekaligus memperbarui Bottom Navigation
    private fun pindahFragment(fragment: Fragment, bottomNavId: Int) {
        // Ubah tampilan fragment
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment
            ) // Ganti R.id.fragment_container jika nama ID Anda berbeda
            .addToBackStack(null)
            .commit()

        // Geser ikon aktif di Bottom Navigation
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.selectedItemId = bottomNavId
    }
}