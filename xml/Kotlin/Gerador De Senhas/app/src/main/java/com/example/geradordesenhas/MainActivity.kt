package com.example.geradordesenhas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geradordesenhas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val alfabeto = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btGerarSenha.setOnClickListener {
            val letra1 = alfabeto.random()
            val letra2 = alfabeto.random()
            val numero = Random.nextInt(1000,9999)
            binding.tvSenha.text = "$letra1$letra2-$numero"
        }
    }
}