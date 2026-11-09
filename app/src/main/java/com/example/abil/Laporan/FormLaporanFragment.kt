package com.example.abil.Laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.abil.R
import com.example.abil.databinding.FragmentFormLaporanBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.fragment.app.setFragmentResult

class FormLaporanFragment : Fragment() {
    private var _binding: FragmentFormLaporanBinding? = null
    private val binding get() = _binding!!

    private var imageUriLokal: String = ""

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imageUriLokal = uri.toString()
            Glide.with(this).load(uri).into(binding.imgPreview)
            binding.layoutPlaceholderFoto.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardFoto.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnKirim.setOnClickListener {
            val judul = binding.edtJudul.text.toString().trim()
            val desc = binding.edtDeskripsi.text.toString().trim()

            if (judul.isEmpty() || desc.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Judul dan Deskripsi tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = sdf.format(Date())
            val finalImageUrl =
                if (imageUriLokal.isNotEmpty()) imageUriLokal else "https://i.pravatar.cc/150?img=99"

            // 1. Bungkus data ke dalam Bundle
            val bundle = Bundle().apply {
                putString("judul", judul)
                putString("desc", desc)
                putString("avatarUrl", finalImageUrl)
                putString("date", currentDate)
            }

            // 2. Kirim data ke ListLaporanFragment menggunakan Fragment Result
            setFragmentResult("requestLaporanBaru", bundle)

            Toast.makeText(requireContext(), "Laporan Berhasil Dibuat!", Toast.LENGTH_SHORT).show()

            // Reset UI form
            binding.edtJudul.text?.clear()
            binding.edtDeskripsi.text?.clear()
            binding.imgPreview.setImageDrawable(null)
            binding.layoutPlaceholderFoto.visibility = View.VISIBLE
            imageUriLokal = ""

            // Pindah tab ke Daftar Laporan
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPagerLaporan)
            viewPager.currentItem = 0
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}