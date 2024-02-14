package com.example.practica2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit

import com.example.practica2.ImcActivity.Companion.DATE_RESULT
import com.example.practica2.ImcActivity.Companion.GENERO_KEY
import com.example.practica2.ImcActivity.Companion.HEIGHT_KEY

import com.example.practica2.ImcActivity.Companion.IMC_KEY
import com.example.practica2.ImcActivity.Companion.WEIGHT_KEY

import com.example.practica2.databinding.ActivityResultImcBinding
import com.example.practica2.reciclerView.ImcCard

import com.example.practica2.reciclerView.addIMCResultHistory



class ResultImcActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityResultImcBinding

    private  var sevedInstanceStateGlobal: Bundle? = null
    private var maleOrFem: String = ""
    private  var message:String = ""
    private var date:String ?=null
    private var result:Double = 0.0
    private var height:Double = 0.0
    private var weight:Double = 0.0



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding =ActivityResultImcBinding .inflate(layoutInflater)
        setContentView(binding.root)


        sevedInstanceStateGlobal = savedInstanceState

        result = intent.extras?.getDouble(IMC_KEY) ?: 0.0
        val genero:Boolean = intent.extras?.getBoolean(GENERO_KEY) ?: false
        date = intent.extras?.getString(DATE_RESULT)
        height = intent.extras?.getDouble(HEIGHT_KEY)?:0.0
        maleOrFem = if(genero) "Hombre" else "Mujer"
        weight = intent.extras?.getDouble(WEIGHT_KEY) ?: 0.0


        initUI(result,genero)
        initListeners()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListeners() {
        binding.btnReCalculate.setOnClickListener{onBackPressed()}
        binding.btnHistory.setOnClickListener{
            changeVisiblity()
            initFragmnet()
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun initFragmnet() {
        Log.i("mesasage","$result")
        addIMCResultHistory(ImcCard(maleOrFem,result,weight,date,message))
        if(sevedInstanceStateGlobal == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<IMCResultFragment>(R.id.fragmentPepe)

            }
        }
    }

    fun changeVisiblity(){
        if(binding.fragmentPepe.visibility==1){
            binding.containerResult.visibility = View.VISIBLE
            binding.fragmentPepe.visibility = View.INVISIBLE
        }else{
            binding.fragmentPepe.visibility = View.VISIBLE
            binding.containerResult.visibility = View.INVISIBLE
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(result: Double, genero:Boolean){
        if(genero) calculateFem(result,genero) else calculateFem(result,genero)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateFem(result: Double, isMaleOrFemSelected:Boolean) {
        val color: Int?
        val descrption: String?
        val messageFem: String?


        if(isMaleOrFemSelected) {
            when (result) {
                in 0.00..18.50 ->{
                    messageFem=getString(R.string.title_low_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.low_weight)
                    descrption = getString(R.string.description_low_weight)
                }in 18.51..24.99 ->{
                    messageFem=getString(R.string.title_normal_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.normal_weight)
                    descrption =getString(R.string.description_normal_weight)
                }in 25.00..29.99 -> {
                    messageFem = getString(R.string.title_overweight_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.over_weight)
                    descrption = getString(R.string.description_overweight_weight)
                }in 29.00..99.99 -> {
                    messageFem =getString(R.string.title_heavy_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.heavy_weight)
                    descrption = getString(R.string.description_heavy_weight)
                }else ->{
                    messageFem =getString(R.string.error)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.heavy_weight)
                    descrption = getString(R.string.error)
                }
            }
        }else{
            when (result) {
                in 0.00..18.50 ->{
                    messageFem=getString(R.string.title_low_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.low_weight)
                    descrption = getString(R.string.description_low_weight)
                }in 18.51..23.9 ->{
                    messageFem=getString(R.string.title_normal_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.normal_weight)
                    descrption =getString(R.string.description_normal_weight)
                }in 24.00..28.9 -> {
                    messageFem = getString(R.string.title_overweight_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.over_weight)
                    descrption = getString(R.string.description_overweight_weight)
                }in 29.00..99.99 -> {
                    messageFem =getString(R.string.title_heavy_weight)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.heavy_weight)
                    descrption = getString(R.string.description_heavy_weight)
                }else ->{
                    messageFem =getString(R.string.error)
                    message = messageFem
                    color = ContextCompat.getColor(this,R.color.heavy_weight)
                    descrption = getString(R.string.error)
                }
            }

        }


        binding.tvResult.text = messageFem
        binding.tvIBM.text = result.toString()
        binding.tvResult.setTextColor(color)
        binding.tvDescription.text = descrption

    }
}