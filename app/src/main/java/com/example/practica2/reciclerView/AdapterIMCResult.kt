package com.example.practica2.reciclerView

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.practica2.R

class AdapterIMCResult(val imcHostory: MutableList<ImcCard>?):
    RecyclerView.Adapter<HolderIMCResult>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderIMCResult {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_imc_result,parent,false)
        return HolderIMCResult(view)
    }

    override fun getItemCount(): Int = imcHostory?.size ?: -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HolderIMCResult, position: Int) {
        holder.render(imcHostory?.get(position) ?: null)
    }
}