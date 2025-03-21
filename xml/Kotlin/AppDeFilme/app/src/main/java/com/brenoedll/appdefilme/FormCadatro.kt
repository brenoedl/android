package com.brenoedll.appdefilme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityFormCadatroBinding

class FormCadatro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadatroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadatroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.tietEmailCadastro.requestFocus()

        binding.btCadastrar.setOnClickListener {
            val email = binding.tietEmailCadastro.text.toString()

            if (email.isEmpty()) {
                binding.tilEmailCadastro.helperText = getString(R.string.erro_email1)
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.red)
            } else {
                binding.tilEmailCadastro.helperText = ""
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.blue)
            }
        }
    }
}