package com.brenoed.agendadecontatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.brenoed.agendadecontatos.databinding.ActivityEditarContatoBinding

class EditarContato : AppCompatActivity() {
    private lateinit var binding: ActivityEditarContatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}