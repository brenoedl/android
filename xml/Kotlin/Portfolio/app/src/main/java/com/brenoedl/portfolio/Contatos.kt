package com.brenoedl.portfolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.portfolio.databinding.ActivityContatosBinding

class Contatos : AppCompatActivity() {
    private lateinit var binding: ActivityContatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbContatos.setNavigationOnClickListener {
            finish()
        }

        binding.ivWhatsapp.setOnClickListener {
            abrirWhatsapp()
        }
    }

    private fun abrirWhatsapp() {
        val numero = "+5514991467533"
        var intent:Intent

        if (numero.equals(numero)) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=$numero"))
            startActivity(intent)
        } else {
            intent = Intent(Intent.ACTION_DIAL, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"))
            startActivity(intent)
        }
    }
}