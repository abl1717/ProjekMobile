package com.example.abil.Tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abil.LoginActivity
import com.example.abil.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {
    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLewati.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }
}