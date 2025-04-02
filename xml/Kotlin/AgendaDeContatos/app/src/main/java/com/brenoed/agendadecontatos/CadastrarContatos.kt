package com.brenoed.agendadecontatos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoed.agendadecontatos.databinding.ActivityCadastrarContatosBinding

class CadastrarContatos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarContatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarContatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}