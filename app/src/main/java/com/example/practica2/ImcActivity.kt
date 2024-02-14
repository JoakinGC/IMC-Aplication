package com.example.practica2

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.practica2.databinding.ActivityImcBinding
import com.example.practica2.reciclerView.ImcCard
import java.time.LocalDate

class ImcActivity : AppCompatActivity() {



    private lateinit var binding : ActivityImcBinding

    private var vieMaleSelecte:Boolean = true
    private var viewFemaleSelecte:Boolean = false
    private var manOrFem :Boolean = false
    private var currentWeight:Int = 70
    private var currentAge:Int = 20
    private var currentHeight:Int = 120
    private var imc:Double = 0.0

    companion object{
        const val IMC_KEY = "IMC_RESULT"
        const val GENERO_KEY = "GENERO_KEY"
        const val DATE_RESULT = "DATE_RESULT"
        const val HEIGHT_KEY = "HEIGHT_KEY"
        const val WEIGHT_KEY = "WEIGHT_KEY"


    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initUI()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListeners() {
        binding.viewMale.setOnClickListener{
            changeGemder()
            setGenderColor()
        }
        binding.viewFemale.setOnClickListener{
            changeGemder()
            setGenderColor()
        }

        binding.rsHeight.addOnChangeListener{ _,value,_ ->

            val df = DecimalFormat("#.##")
            currentHeight =  df.format(value).toInt()
            binding.tvHeight.text = "$currentHeight cm"

        }

        binding.btnPlusWeight.setOnClickListener{
            currentWeight++
            setWeight()
        }

        binding.btnSubstractWeight.setOnClickListener{
            currentWeight--
            setWeight()
        }

        binding.btnAgePlus.setOnClickListener{
            currentAge++
            setAge()
        }

        binding.btnAgeSubstrac.setOnClickListener{
            currentAge--
            setAge()
        }

        binding.btnCalculate.setOnClickListener{
            val result = calculateIBM()
            //agregarLista()
            navigateToResult(result)
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun navigateToResult(result:Double){
        val intent = Intent(this, ResultImcActivity::class.java)

        //bundle.putParcelableArrayList(LISTA_HISTORICO,ArrayList(imcHistory))
        intent.putExtra(IMC_KEY,result)
        intent.putExtra(GENERO_KEY,manOrFem)
        intent.putExtra(DATE_RESULT,LocalDate.now().toString())
        intent.putExtra(HEIGHT_KEY,currentHeight.toDouble())
        intent.putExtra(WEIGHT_KEY,currentWeight.toDouble())

        startActivity(intent)
    }

    private fun calculateIBM():Double{
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        this.imc = imc
        return df.format(imc).toDouble()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeight(){
        binding.tvWeight.text = currentWeight.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setAge(){
        binding.txAge.text = currentAge.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun changeGemder(){
        vieMaleSelecte = !vieMaleSelecte
        viewFemaleSelecte = !viewFemaleSelecte
        manOrFem =  vieMaleSelecte
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setGenderColor(){
        binding.viewMale.setCardBackgroundColor(getBackgroundColor(vieMaleSelecte))
        binding.viewFemale.setCardBackgroundColor(getBackgroundColor(viewFemaleSelecte))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int{
        val colorReference = if(isSelectedComponent) R.color.back_componen_selectedt else R.color.back_component


        return ContextCompat.getColor(this,colorReference)
    }
}