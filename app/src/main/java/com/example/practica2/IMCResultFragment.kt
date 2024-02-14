package com.example.practica2

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.databinding.FragmentImcResultBinding
import com.example.practica2.reciclerView.AdapterIMCResult


import com.example.practica2.reciclerView.getImcResultHistory




class   IMCResultFragment : Fragment() {

    private  var binding:FragmentImcResultBinding? = null
    @RequiresApi(Build.VERSION_CODES.O)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImcResultBinding.inflate(inflater,container,false)
        val view = binding?.root

        var imcResultAdaptar  = AdapterIMCResult(getImcResultHistory())

        binding?.rvHistory?.layoutManager = LinearLayoutManager(context)
        binding?.rvHistory?.adapter = imcResultAdaptar

        binding?.btnRetro?.setOnClickListener{requireActivity().onBackPressed()}

        return view
    }




    companion object {
        fun newInstance() =
            IMCResultFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}