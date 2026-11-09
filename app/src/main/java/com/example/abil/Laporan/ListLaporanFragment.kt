package com.example.abil.Laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abil.R
import com.example.abil.databinding.FragmentListLaporanBinding
import com.google.android.material.chip.Chip

class ListLaporanFragment : Fragment() {
    private var _binding: FragmentListLaporanBinding? = null
    private val binding get() = _binding!!
    private val messageList = mutableListOf(
        LaporanModel(
            "Perbaikan Jalan",
            "Jalan di RT 01 RW 01 tidak layak dilalui karena lubang",
            "https://i.pravatar.cc/150?img=1",
            "2026-10-20"
        ),
        LaporanModel(
            "Pemasangan Lampu Jalan",
            "Lampu jalan di RT 03 RW 02 sudah redup karena faktor usia",
            "https://i.pravatar.cc/150?img=2",
            "2026-10-18"
        ),
        LaporanModel(
            "Perbaikan Jembatan",
            "Jembatan di sungai jambu terputus karena debit air meningkat",
            "https://i.pravatar.cc/150?img=3",
            "2026-10-15"
        ),
        LaporanModel(
            "Jalan Terputus",
            "Longsor mengakibatkan jalan akses keluar desa terputus",
            "https://i.pravatar.cc/150?img=5",
            "2026-10-10"
        ),
        LaporanModel(
            "Selokan Mampet",
            "Drainase di Dusun Mekar tersumbat sampah, air meluap ke jalan",
            "https://i.pravatar.cc/150?img=6",
            "2026-10-09"
        ),
        LaporanModel(
            "Tumpukan Sampah Liar",
            "Ada warga luar desa membuang sampah sembarangan di pinggir sawah",
            "https://i.pravatar.cc/150?img=7",
            "2026-10-08"
        ),
        LaporanModel(
            "Tiang Listrik Miring",
            "Tiang listrik di pertigaan balai desa miring hampir roboh",
            "https://i.pravatar.cc/150?img=8",
            "2026-10-07"
        ),
        LaporanModel(
            "Pohon Tumbang",
            "Pohon beringin tua tumbang menutupi sebagian jalan utama",
            "https://i.pravatar.cc/150?img=9",
            "2026-10-05"
        ),
        LaporanModel(
            "Poskamling Rusak",
            "Atap poskamling RT 04 bocor parah saat hujan deras",
            "https://i.pravatar.cc/150?img=10",
            "2026-10-02"
        ),
        LaporanModel(
            "Bantuan Air Bersih",
            "Saluran air PAMSIMAS di Dusun Krajan mati total sejak dua hari lalu",
            "https://i.pravatar.cc/150?img=11",
            "2026-10-01"
        ),
        LaporanModel(
            "Paving Block Amblas",
            "Paving di area lapangan desa amblas dan membahayakan anak-anak",
            "https://i.pravatar.cc/150?img=12",
            "2026-09-28"
        ),
        LaporanModel(
            "Kabel Putus",
            "Ada kabel telepon yang menjuntai rendah ke jalan di RW 02",
            "https://i.pravatar.cc/150?img=13",
            "2026-09-27"
        ),
        LaporanModel(
            "Pagar Makam Roboh",
            "Tembok pembatas TPU desa roboh sepanjang 5 meter akibat hujan",
            "https://i.pravatar.cc/150?img=14",
            "2026-09-25"
        ),
        LaporanModel(
            "Gorong-gorong Jebol",
            "Gorong-gorong di depan SD Inpres jebol dilewati truk pasir",
            "https://i.pravatar.cc/150?img=15",
            "2026-09-22"
        ),
        LaporanModel(
            "Banjir Genangan",
            "Setiap hujan turun deras, jalan RT 02 selalu tergenang air setinggi lutut",
            "https://i.pravatar.cc/150?img=16",
            "2026-09-20"
        ),
        LaporanModel(
            "Lampu Taman Mati",
            "Tiga lampu di taman desa mati sehingga gelap dan rawan saat malam",
            "https://i.pravatar.cc/150?img=17",
            "2026-09-19"
        ),
        LaporanModel(
            "Asap Pembakaran Liar",
            "Pembakaran sampah ilegal di lahan kosong mengganggu pernapasan warga",
            "https://i.pravatar.cc/150?img=18",
            "2026-09-17"
        ),
        LaporanModel(
            "Rumput Liar Tinggi",
            "Ilalang di pinggir jalan masuk desa sudah terlalu tinggi, mengganggu pandangan",
            "https://i.pravatar.cc/150?img=19",
            "2026-09-15"
        ),
        LaporanModel(
            "Hewan Ternak Liar",
            "Banyak sapi warga yang dibiarkan berkeliaran memakan tanaman kebun",
            "https://i.pravatar.cc/150?img=20",
            "2026-09-14"
        ),
        LaporanModel(
            "Perbaikan Gapura",
            "Cat gapura selamat datang desa sudah memudar dan hurufnya lepas",
            "https://i.pravatar.cc/150?img=21",
            "2026-09-11"
        ),
        LaporanModel(
            "Fasilitas Polindes",
            "Kursi ruang tunggu Polindes banyak yang patah",
            "https://i.pravatar.cc/150?img=22",
            "2026-09-10"
        ),
        LaporanModel(
            "Pengadaan Tong Sampah",
            "Minimnya tong sampah umum di area pasar desa",
            "https://i.pravatar.cc/150?img=23",
            "2026-09-08"
        ),
        LaporanModel(
            "Kondisi Jembatan Gantung",
            "Tali baja jembatan gantung Dusun Koto mulai berkarat",
            "https://i.pravatar.cc/150?img=24",
            "2026-09-05"
        ),
        LaporanModel(
            "Jalan Tanah Licin",
            "Jalan tanah akses ke sawah sangat licin dan berlumpur saat hujan",
            "https://i.pravatar.cc/150?img=25",
            "2026-09-03"
        ),
        LaporanModel(
            "Permintaan Fogging",
            "Kasus DBD mulai muncul, warga meminta fogging di RW 01",
            "https://i.pravatar.cc/150?img=26",
            "2026-09-01"
        ),
        LaporanModel(
            "Pengeras Suara Masjid",
            "Toa masjid desa perlu diperbaiki karena suaranya mendengung",
            "https://i.pravatar.cc/150?img=27",
            "2026-08-28"
        ),
        LaporanModel(
            "Irigasi Sawah Rusak",
            "Dinding irigasi sekunder retak membuat pembagian air tidak merata",
            "https://i.pravatar.cc/150?img=28",
            "2026-08-25"
        ),
        LaporanModel(
            "Bantuan Kursi Roda",
            "Warga lansia di RT 05 membutuhkan bantuan kursi roda dari desa",
            "https://i.pravatar.cc/150?img=29",
            "2026-08-22"
        ),
        LaporanModel(
            "Penebangan Pohon Rawan",
            "Ada pohon kelapa mati di dekat pemukiman yang rawan tumbang",
            "https://i.pravatar.cc/150?img=30",
            "2026-08-20"
        ),
        LaporanModel(
            "Tanggul Sungai Kritis",
            "Tanggul penahan air di blok selatan sudah mulai tergerus air sungai",
            "https://i.pravatar.cc/150?img=31",
            "2026-08-18"
        ),
        LaporanModel(
            "Perbaikan WC Umum",
            "Fasilitas MCK umum di Dusun Krajan pintunya rusak parah",
            "https://i.pravatar.cc/150?img=32",
            "2026-08-15"
        ),
        LaporanModel(
            "Pengerukan Embung",
            "Embung desa semakin dangkal akibat penumpukan sedimen lumpur",
            "https://i.pravatar.cc/150?img=33",
            "2026-08-12"
        ),
        LaporanModel(
            "Jalan Desa Gelap",
            "Perlu tambahan titik lampu penerangan jalan menuju perumahan baru",
            "https://i.pravatar.cc/150?img=34",
            "2026-08-10"
        ),
        LaporanModel(
            "Perbaikan Balai Pertemuan",
            "Plafon balai desa ada yang jebol terkena rembesan air",
            "https://i.pravatar.cc/150?img=35",
            "2026-08-08"
        ),
        LaporanModel(
            "Usulan Pelatihan Warga",
            "Warga pemuda meminta diadakan pelatihan keterampilan UMKM",
            "https://i.pravatar.cc/150?img=36",
            "2026-08-05"
        ),
        LaporanModel(
            "Penertiban Balap Liar",
            "Jalan poros desa sering dipakai balap liar saat malam minggu",
            "https://i.pravatar.cc/150?img=37",
            "2026-08-03"
        ),
        LaporanModel(
            "Sumbangan Buku Perpustakaan",
            "Perpustakaan desa minim buku bacaan baru untuk anak-anak",
            "https://i.pravatar.cc/150?img=38",
            "2026-08-01"
        ),
        LaporanModel(
            "Pembinaan Karang Taruna",
            "Kegiatan karang taruna mandek, butuh pendampingan aparat desa",
            "https://i.pravatar.cc/150?img=39",
            "2026-07-28"
        ),
        LaporanModel(
            "Alat Berat untuk Longsor",
            "Butuh eskavator untuk membersihkan material longsoran skala kecil",
            "https://i.pravatar.cc/150?img=40",
            "2026-07-25"
        ),
        LaporanModel(
            "Sosialisasi Pemilu Desa",
            "Mohon ada edukasi tahapan pemilu bagi warga pemilih pemula",
            "https://i.pravatar.cc/150?img=41",
            "2026-07-22"
        ),
        LaporanModel(
            "Bantuan Pupuk Subsidi",
            "Petani mengeluhkan sulitnya mendapat alokasi pupuk bersubsidi",
            "https://i.pravatar.cc/150?img=42",
            "2026-07-20"
        ),
        LaporanModel(
            "Perbaikan lapangan voli",
            "Net dan garis lapangan voli warga di RW 04 sudah hancur",
            "https://i.pravatar.cc/150?img=43",
            "2026-07-18"
        ),
        LaporanModel(
            "Kekeringan Sumur Warga",
            "Beberapa sumur warga mulai kering, mohon bantuan suplai air",
            "https://i.pravatar.cc/150?img=44",
            "2026-07-15"
        ),
        LaporanModel(
            "Pelanggaran Batas Tanah",
            "Ada pembangunan ruko yang memakan bahu jalan milik desa",
            "https://i.pravatar.cc/150?img=45",
            "2026-07-12"
        ),
        LaporanModel(
            "Kamera CCTV Desa",
            "Sering terjadi pencurian motor, warga usul pemasangan CCTV di persimpangan",
            "https://i.pravatar.cc/150?img=46",
            "2026-07-10"
        ),
        LaporanModel(
            "Ambulans Desa",
            "Ban ambulans desa gundul, sangat berbahaya saat membawa pasien",
            "https://i.pravatar.cc/150?img=47",
            "2026-07-08"
        ),
        LaporanModel(
            "Permintaan Bibit Ikan",
            "Kelompok tani butuh bibit ikan lele untuk program ketahanan pangan",
            "https://i.pravatar.cc/150?img=48",
            "2026-07-05"
        ),
        LaporanModel(
            "Sertifikasi Tanah Desa",
            "Mohon penjelasan terkait program PTSL di balai desa",
            "https://i.pravatar.cc/150?img=49",
            "2026-07-02"
        ),
        LaporanModel(
            "Akses Internet Kelurahan",
            "WiFi gratis di area balai desa sering terputus",
            "https://i.pravatar.cc/150?img=50",
            "2026-07-01"
        ),
        LaporanModel(
            "Pengolahan Sampah Terpadu",
            "Bank sampah desa tidak aktif, warga butuh solusi pembuangan sampah",
            "https://i.pravatar.cc/150?img=51",
            "2026-06-28"
        )
    )

    private lateinit var adapterLaporan: LaporanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Inisialisasi RecyclerView awal
        binding.rvLaporanItems.layoutManager = LinearLayoutManager(requireContext())
        adapterLaporan = LaporanAdapter(requireContext(), messageList)
        binding.rvLaporanItems.adapter = adapterLaporan

        // MENDENGARKAN DATA BARU DARI FORM
        setFragmentResultListener("requestLaporanBaru") { _, bundle ->
            val judul = bundle.getString("judul") ?: ""
            val desc = bundle.getString("desc") ?: ""
            val avatarUrl = bundle.getString("avatarUrl") ?: ""
            val date = bundle.getString("date") ?: ""

            val laporanBaru = LaporanModel(
                judul = judul,
                messageText = desc,
                avatarUrl = avatarUrl,
                date = date,
                status = "Diterima",
                dukungan = 0
            )

            // Tambahkan data ke urutan paling atas (index 0)
            messageList.add(0, laporanBaru)

            // Refresh adapter dan otomatis gulir ke atas
            adapterLaporan.notifyItemInserted(0)
            binding.rvLaporanItems.scrollToPosition(0)
        }

        // Logika Filter ChipGroup
        binding.chipGroupLaporan.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()

                val filteredData = when (selectedChipId) {
                    R.id.chipTerbaru -> messageList.sortedByDescending { it.date }
                    R.id.chipTerlama -> messageList.sortedBy { it.date }
                    else -> messageList
                }
                binding.rvLaporanItems.adapter = LaporanAdapter(requireContext(), filteredData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}