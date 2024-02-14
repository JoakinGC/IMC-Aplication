package com.example.practica2.reciclerView

import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practica2.R
import com.example.practica2.databinding.ItemImcResultBinding
import java.time.LocalDate


class HolderIMCResult(view: View) : RecyclerView.ViewHolder(view) {

    var binding = ItemImcResultBinding.bind(view)


    var isMaleOrFemSelected = true
    var result: Double = 0.0
    var message: String? = null
    var color = ContextCompat.getColor(binding.tvImcResult.context, R.color.normal_weight)

    @RequiresApi(Build.VERSION_CODES.O)
    fun render(imcItem: ImcCard?) {
        if(imcItem != null) {
            binding.tvMonth.text = LocalDate.parse(imcItem.fecha).month.toString()
            binding.tvDay.text = LocalDate.parse(imcItem.fecha).dayOfMonth.toString()
            binding.tvYear.text = LocalDate.parse(imcItem.fecha).year.toString()
            binding.tvMessage.text = imcItem.message
            binding.tvGenere.text = imcItem.genero
            binding.tvImcResult.text = imcItem.imc.toString()
            binding.tvWeight.text = imcItem.weight.toString()+" Kg"

            result = imcItem.imc
            isMaleOrFemSelected =
                if (imcItem.genero?.contains(R.string.male.toString()) == true) true else false


            asignar()

            binding.tvWeight.setTextColor(color)
            binding.tvImcResult.setTextColor(color)
            binding.tvMessage.setTextColor(color)
        }else{
            binding.tvMonth.text ="#"
            binding.tvDay.text = "#"
            binding.tvYear.text = "#"
            binding.tvMessage.text = "#"
            binding.tvGenere.text = "#"
            binding.tvImcResult.text = "3"

            result = 0.0



            asignar()

            binding.tvImcResult.setTextColor(color)
            binding.tvMessage.setTextColor(color)
        }
    }

    fun asignar() {

        if (isMaleOrFemSelected) {
            color = when (result) {
                in 0.00..18.50 ->  ContextCompat.getColor(binding.tvImcResult.context, R.color.low_weight)
                in 18.51..24.99 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.normal_weight)
                in 25.00..29.99 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.over_weight)
                in 29.00..99.99 ->  ContextCompat.getColor(binding.tvImcResult.context, R.color.heavy_weight)
                else ->  ContextCompat.getColor(binding.tvImcResult.context, R.color.heavy_weight)

            }
        } else {
            color = when (result) {
                in 0.00..18.50 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.low_weight)
                in 18.51..23.9 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.normal_weight)
                in 24.00..28.9 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.over_weight)
                in 29.00..99.99 -> ContextCompat.getColor(binding.tvImcResult.context, R.color.heavy_weight)
                else -> ContextCompat.getColor(binding.tvImcResult.context, R.color.heavy_weight)
                }
            }
        }

}