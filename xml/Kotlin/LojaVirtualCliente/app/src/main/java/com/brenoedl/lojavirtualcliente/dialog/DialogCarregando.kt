package com.brenoedl.lojavirtualcliente.dialog

import android.app.Activity
import android.app.AlertDialog
import com.brenoedl.lojavirtualcliente.R

class DialogCarregando(private val activity: Activity) {
    private lateinit var dialog: AlertDialog

    fun iniciarCarregamentoAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.dialog_carregando, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun liberarAlertDialog() {
        dialog.dismiss()
    }
}