package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityTelaPrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.tvSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }
    }
}