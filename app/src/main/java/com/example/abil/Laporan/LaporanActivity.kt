package com.example.abil.Laporan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.abil.R
import com.example.abil.databinding.ActivityLaporanBinding

class LaporanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("JUDUL")
        val message = intent.getStringExtra("MESSAGE")

        binding.txtJudul.text = judul
        binding.txtMessage.text = message

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detail Laporan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }
}