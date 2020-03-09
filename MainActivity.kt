import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlin.math.*
import kotlinx.android.synthetic.main.activity_main.*


class  MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        settBut.setOnClickListener {

            val settframe = AlertDialog.Builder(this)
            settframe.setTitle("Settings")
            val settframeview = getLayoutInflater()
                .inflate(R.layout.settings, null)
            settframe.setView(settframeview)
            settframe.show()
            settframe.setCancelable(true)
        }

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
                   numA.isVisible = true
                   numB.isVisible = true
                   numC.isVisible = true
               }
               which == 1 -> {
                   numA.isVisible = false
                   numB.isVisible = true
                   numC.isVisible = true
               }
               which == 2 -> {
                   numA.isVisible = true
                   numB.isVisible = false
                   numC.isVisible = true
               }
               which == 3 -> {
                   numA.isVisible = true
                   numB.isVisible = true
                   numC.isVisible = false
               }
           }

       }

        defEqForm.setOnClickListener {
            ChangeEqForm.show()
        }

        fun  Solve_0(){

            val A = TurnInto(numA.text!!)
            val B = TurnInto(numB.text!!)
            val C = TurnInto(numC.text!!)

            val disc: Double = (B * B) + (-4 * A * C)
            val coren: Double = sqrt(disc)
            val x1: Double
            val x2: Double

            if(disc > 0){
                x1 = (-B + coren) / (2 * A)
                x2 = (-B - coren) / (2 * A)
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
            val B = TurnInto(numB.text!!)
            val C = TurnInto(numC.text!!)

            val x = -C / B
            alert.setMessage("x = $x")
            alert.show()
        }
        fun Solve_2(){
            val A = TurnInto(numA.text!!)
            val C = TurnInto(numC.text!!)

            val x1 = sqrt(-C / A)
            val x2 = -x1
            alert.setMessage("x1 = $x1, x2 = $x2 ")
            alert.show()
        }
        fun Solve_3(){
            val A = TurnInto(numA.text!!)
            val B = TurnInto(numB.text!!)

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

        }

        fun TurnInto(value: Editable): Double{
        val numb = value.toString()
            val numb2 = numb.toDouble()
            return numb2
         }

}
