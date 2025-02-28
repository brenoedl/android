package com.brenoedll.cores

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.cores.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private var cor = ""

    companion object {
        const val ARQUIVO_PREFERENCIAS = "ArquivoPreferencias"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCor1.setOnClickListener {
            cor = "#808080"
            salvar(cor)
        }

        binding.btCor2.setOnClickListener {
            cor = "#4BC351"
            salvar(cor)
        }

        binding.btCor3.setOnClickListener {
            cor = "#4457C5"
            salvar(cor)
        }

        binding.btCor4.setOnClickListener {
            cor = "#FF0000"
            salvar(cor)
        }

        binding.btCor5.setOnClickListener {
            cor = "#FFEB3B"
            salvar(cor)
        }

        binding.btCor6.setOnClickListener {
            cor = "#FF9800"
            salvar(cor)
        }

        binding.btCor7.setOnClickListener {
            cor = "#AC3CBF"
            salvar(cor)
        }
    }

    private fun salvar(cor: String) {
        binding.root.setBackgroundColor(Color.parseColor(cor))

        binding.btTrocar.setOnClickListener { view ->
            val preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("cor", cor)
            editor.apply()
            snackbar(view)
        }
    }

    private fun snackbar(view: View){
        val snackbar = Snackbar.make(view, getString(R.string.msg_sucesso), Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Ok") {}
        snackbar.setActionTextColor(getColor(R.color.blue))
        snackbar.setBackgroundTint(getColor(R.color.white))
        snackbar.setTextColor(getColor(R.color.black))
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
        val cor = preferencias.getString("cor", "")
        if (cor!!.isNotEmpty()) {
            binding.root.setBackgroundColor(Color.parseColor(cor))
        }
    }
}