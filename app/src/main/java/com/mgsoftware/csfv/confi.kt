package com.mgsoftware.csfv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.EditText
import android.widget.Switch

class confi : AppCompatActivity() {
    val name_key_up = "up"
    val name_key_down = "down"
    val name_key_left = "left"
    val name_key_rigth = "rigth"
    val name_key_stop = "stop"
    var name_key_vi = "vi"
    var colorkey = "color"

    var Adelante :EditText? = null
    var Azquierda: EditText? = null
    var Atras: EditText? = null
    var Derecha: EditText? = null
    var Stop: EditText? = null
    var vi: Switch? = null
    var switchstate = false
    var color: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confi)

        var prefers = PreferenceManager.getDefaultSharedPreferences(this)

        Adelante = findViewById<EditText>(R.id.editText)
        Atras = findViewById<EditText>(R.id.editTex2)
        Azquierda= findViewById<EditText>(R.id.editText3)
        Derecha = findViewById<EditText>(R.id.editText4)
        Stop = findViewById<EditText>(R.id.editText6)



        Adelante?.setText(prefers.getString(name_key_up, "a"))
        Atras?.setText(prefers.getString(name_key_down, "r"))
        Azquierda?.setText(prefers.getString(name_key_left, "i"))
        Derecha?.setText(prefers.getString(name_key_rigth, "d"))
        Stop?.setText(prefers.getString(name_key_stop, "o"))
        color?.setText(prefers.getString(colorkey, "def"))




    }
    fun Save(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(name_key_up, Adelante?.text.toString())
        saver.putString(name_key_down, Atras?.text.toString())
        saver.putString(name_key_left, Azquierda?.text.toString())
        saver.putString(name_key_rigth, Derecha?.text.toString())
        saver.putString(name_key_stop, Stop?.text.toString())

        saver.apply()
    }
    fun Reestore(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(name_key_up, "a")
        saver.putString(name_key_down, "r")
        saver.putString(name_key_left, "i")
        saver.putString(name_key_rigth, "d")
        saver.putString(name_key_stop, "o")

        saver.apply()
    }
    public fun changeBackgrounToBlack(view: View) {
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(colorkey, "black")
        saver.apply()
        finish()
    }
    public fun changeBackgrounToTomate(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(colorkey, "tomate")
        saver.apply()
        finish()
    }
    public fun changeBackgrounToYellow(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(colorkey, "yellow")
        saver.apply()
        finish()
    }
    public fun changeBackgrounToGreen(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(colorkey, "green")
        saver.apply()
        finish()
    }
    public fun changeBackgrounToRed(view: View){
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(colorkey, "red")
        saver.apply()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        val saver = PreferenceManager.getDefaultSharedPreferences(this).edit()
        saver.putString(name_key_up, Adelante?.text.toString())
        saver.putString(name_key_down, Atras?.text.toString())
        saver.putString(name_key_left, Azquierda?.text.toString())
        saver.putString(name_key_rigth, Derecha?.text.toString())
        saver.putString(name_key_stop, Stop?.text.toString())

        saver.apply()
    }
}