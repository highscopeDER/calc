package com.example.smartcount



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlin.math.*
import kotlinx.android.synthetic.main.activity_main.*


class  MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Answer")
        alert.setCancelable(true)

        val ChangeEqForm = AlertDialog.Builder(this)
        ChangeEqForm.setTitle("Equation form")
        ChangeEqForm.setCancelable(false)
        val ArrayEqForm = arrayOf("ax²+ bx + c = 0", "bx + c = 0", "ax²+ c = 0", "ax²+ bx = 0")
       ChangeEqForm.setSingleChoiceItems(ArrayEqForm, -1){
        dialog, which ->  defEqForm.text = ArrayEqForm[which]
           dialog.dismiss()
           when{
               which == 0 -> {
                   numA.isVisible = true; charA.isVisible = true
                   numB.isVisible = true; charB.isVisible = true
                   numC.isVisible = true; charC.isVisible = true
               }
               which == 1 -> {
                   numA.isVisible = false; charA.isVisible = false
                   numB.isVisible = true; charB.isVisible = true
                   numC.isVisible = true; charC.isVisible = true
               }
               which == 2 -> {
                   numA.isVisible = true; charA.isVisible = true
                   numB.isVisible = false; charB.isVisible = false
                   numC.isVisible = true; charC.isVisible = true
               }
               which == 3 -> {
                   numA.isVisible = true; charA.isVisible = true
                   numB.isVisible = true; charB.isVisible = true
                   numC.isVisible = false; charC.isVisible = false
               }
           }

       }

        defEqForm.setOnClickListener {
            ChangeEqForm.show()
        }



        fun  Solve_0(){


            var A = TurnInto(numA.text!!)
            var B = TurnInto(numB.text!!)
            var C = TurnInto(numC.text!!)


            when {
                charA.isChecked == false -> A = -A
                charB.isChecked == false -> B = -B
                charC.isChecked == false -> C = -C
            }
            val disc: Double = B * B + (- 4 * A * C)
            val x1: Double
            val x2: Double

            if(disc > 0){
                x1 = (-B + sqrt(disc)) / 2 * A
                x2 = (-B - sqrt(disc)) / 2 * A
                alert.setMessage("x1 = $x1, x2 = $x2")
            }

            else if (disc == 0.0) {
                x1 = -B / 2 * A
                x2 = x1

                alert.setMessage("x1 = $x1, x2 = $x2")
            }

            else if (disc < 0) {
                alert.setMessage("No answer because discriminant is less than zero")
            }
            alert.show()

            }
        fun Solve_1(){
            var B = TurnInto(numB.text!!)
            var C = TurnInto(numC.text!!)

            when {
                charB.isChecked == false -> B = -B
                charC.isChecked == false -> C = -C
            }
            val x = -C / B
            alert.setMessage("x = $x")
            alert.show()
        }
        fun Solve_2(){
            var A = TurnInto(numA.text!!)
            var C = TurnInto(numC.text!!)

            when {
                charA.isChecked == false -> A = -A
                charC.isChecked == false -> C = -C
            }
            val x1 = sqrt(-C / A)
            val x2 = -x1
            alert.setMessage("x1 = $x1, x2 = $x2 ")
            alert.show()
        }
        fun Solve_3(){
            var A = TurnInto(numA.text!!)
            var B = TurnInto(numB.text!!)

            when {
                charA.isChecked == false -> A = -A
                charB.isChecked == false -> B = -B
            }

            val x2 = -B / A
            alert.setMessage("x1 = 0, x2 = $x2")
            alert.show()

        }

        solveBut.setOnClickListener {
            if(defEqForm.text == "ax²+ bx + c = 0"){
                Solve_0()
            }
            else if(defEqForm.text == "bx + c = 0"){
                Solve_1()
            }
            else if(defEqForm.text == "ax²+ c = 0"){
                Solve_2()
            }
            else if(defEqForm.text == "ax²+ bx = 0"){
                Solve_3()
            }
        }

        clearBut.setOnClickListener {
            Clear()
        }

    }

        fun Clear() {
            numA.text.clear()
            numB.text.clear()
            numC.text.clear()
            charA.isChecked = true
            charB.isChecked = true
            charC.isChecked = true
        }

        fun TurnInto(value: Editable): Double{
        val numb = value.toString()
            val numb2 = numb.toDouble()
            return numb2
         }

}