package com.brenoedl.calculadoradenotas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.calculadoradenotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalc.setOnClickListener {
            val n1 = binding.txtN1.text.toString()
            val n2 = binding.txtN2.text.toString()
            val n3 = binding.txtN3.text.toString()
            val n4 = binding.txtN4.text.toString()
            val faltas = binding.etFaltas.text.toString()

            if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty() || n4.isEmpty() || faltas.isEmpty()) {
                binding.tvResp.text = binding.root.context.getString(R.string.erro)
                binding.tvResp.setTextColor(getColor(R.color.red))
            } else {
                calcularMedia(n1.toInt(), n2.toInt(), n3.toInt(), n4.toInt(), faltas.toInt())
            }
        }
    }
    private fun calcularMedia(n1: Int, n2: Int, n3: Int, n4: Int, faltas: Int) {
        val media = (n1 + n2 + n3 + n4) / 4
        if (media >= 5 && faltas <= 20) {
            binding.tvResp.text = binding.root.context.getString(R.string.msgA) + "\n" + binding.root.context.getString(R.string.msgMF) + " " + media
            binding.tvResp.setTextColor(getColor(R.color.green))
        } else if (faltas > 20) {
            binding.tvResp.text = binding.root.context.getString(R.string.msgRF) + "\n" + binding.root.context.getString(R.string.msgMF) + " " + media
            binding.tvResp.setTextColor(getColor(R.color.red))
        } else if (media < 5) {
            binding.tvResp.text = binding.root.context.getString(R.string.msgRN) + "\n" + binding.root.context.getString(R.string.msgMF) + " " + media
            binding.tvResp.setTextColor(getColor(R.color.red))
        }
    }
}