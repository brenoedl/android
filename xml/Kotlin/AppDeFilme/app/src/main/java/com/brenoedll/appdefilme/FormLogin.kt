package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.tietEmailLogin.requestFocus()

        binding.bEntrar.setOnClickListener {
            val email = binding.tietEmailLogin.text.toString().trim()
            val senha = binding.tietSenhaLogin.text.toString()
            when {
                email.isEmpty() -> {
                    binding.tilEmailLogin.helperText = getString(R.string.erro_email1)
                    binding.tilEmailLogin.boxStrokeColor = getColor(R.color.orange)
                }
                senha.isEmpty() -> {
                    binding.tilSenhaLogin.helperText = getString(R.string.erro_sanha1)
                    binding.tilSenhaLogin.boxStrokeColor = getColor(R.color.orange)
                }
            }
        }

        binding.tvInscrevaSe.setOnClickListener {
            val intent = Intent(this, FormCadatro::class.java)
            startActivity(intent)
        }
    }
}