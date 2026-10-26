package com.example.abil.Informasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abil.R
import com.example.abil.databinding.FragmentInformasiBinding
import com.google.android.material.chip.Chip

class InformasiFragment : Fragment() {
    private var _binding: FragmentInformasiBinding? = null
    private val binding get() = _binding!!

    private val dataList = listOf(
        "Kotlin", "Java", "Python", "C++", "JavaScript",
        "Dart", "Swift", "Go", "Ruby", "R",
        "PHP", "C#", "TypeScript", "Shell", "SQL",
        "Perl", "Rust", "Scala", "Haskell", "Lua",
        "Erlang", "Prolog", "Assembly", "Objective-C", "VBA"
    )

    private val dataListWithDesc = listOf(
        mapOf(
            "title" to "Undangan Rapat",
            "desc" to "Diberitahukan kepada seluruh masyarakat",
            "date" to "2020-10-15"
        ),
        mapOf(
            "title" to "Gotong Royong",
            "desc" to "Gotong royong 19 Oktober 2026",
            "date" to "2021-10-19"
        ),
        mapOf(
            "title" to "Pembagian BLT Desa",
            "desc" to "Pelaksanaan di Balai Desa pukul 09.00 WIB",
            "date" to "2022-10-25"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_informasi, container, false)
        _binding = FragmentInformasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Informasi"

//        val adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            dataList
//        )
//// Hubungkan listViewItems dengan adapter
//        binding.listViewItems.adapter = adapter
//// Aksi saat item diklik
//        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
//            val selectedItem = dataList[position]
//            Toast.makeText(
//                requireContext(), "Kamu memilih: $selectedItem",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
        val displayList = ArrayList<Map<String, String>>()
        displayList.addAll(dataListWithDesc.sortedByDescending { it["date"] })

        val adapter = SimpleAdapter(
            requireContext(),
            displayList,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"), // key yang diambil dari data
            intArrayOf(android.R.id.text1, android.R.id.text2) // tempat tampilnya
        )
        binding.listViewItems.adapter = adapter
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = displayList[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            val date = selectedItem["date"]
            Toast.makeText(
                requireContext(), "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull() // ambil id chip yang dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                val filterText = chip.text.toString()
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
                val filteredData = when (selectedChipId) {
                    R.id.chipTerbaru -> {
                        dataListWithDesc.sortedByDescending { it["date"] }
                    }

                    R.id.chipTerlama -> {
                        dataListWithDesc.sortedBy { it["date"] }
                    }

                    else -> dataListWithDesc
                }
                displayList.clear()
                displayList.addAll(filteredData)
                adapter.notifyDataSetChanged()
            }
        }
    }
}