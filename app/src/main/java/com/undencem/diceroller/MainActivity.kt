package com.undencem.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.undencem.diceroller.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        const val DICE1 = "dice1"
        const val DICE2 = "dice2"
    }

    private lateinit var binding: ActivityMainBinding

    private var diceValue1 = 0
    private var diceValue2 = 0

    //lateinit var diceImage1: ImageView
    //lateinit var diceImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /**
         * lateinit keyword promises the Kotlin computer that variable
         * will be initialized before the code calls any operations on it
         */

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rollButton.setOnClickListener {
            diceValue1 = getRandomDiceImage()
            diceValue2 = getRandomDiceImage()
            showDice(diceValue1,diceValue2)
        }

        binding.resetButton.setOnClickListener {
            resetCount();
        }

        if (savedInstanceState != null) {
            diceValue1=savedInstanceState.getInt(DICE1,0)
            diceValue2=savedInstanceState.getInt(DICE2,0)
            showDice(diceValue1,diceValue2)
        }
    }

    private fun showDice(dice1:Int ,dice2:Int){
        binding.diceView1.setImageResource(dice1)
        binding.diceView2.setImageResource(dice2)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("TAG", "onSaveInstanceState: started ")
        outState.putInt(DICE1, diceValue1)
        outState.putInt(DICE2, diceValue2)
    }

    private fun getRandomDiceImage(): Int {
        val randomInt = (1..6).random()

        return when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun resetCount() {
        diceValue1=0
        diceValue2=0
        binding.diceView1.setImageResource(R.drawable.empty_dice)
        binding.diceView2.setImageResource(R.drawable.empty_dice)
    }

}