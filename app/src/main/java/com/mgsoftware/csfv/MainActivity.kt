package com.mgsoftware.csfv

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.preference.PreferenceManager
import android.text.BoringLayout
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import java.io.IOException
import java.io.OutputStream
import java.util.*

class MainActivity : AppCompatActivity() {


    val name_key_up = "up"
    val name_key_down = "down"
    val name_key_left = "left"
    val name_key_rigth = "rigth"
    val name_key_stop = "stop"
    var name_key_vi = "vi"

    var Adelante :String? = null
    var Azquierda: String? = null
    var Atras: String? = null
    var Derecha: String? = null
    var Stop: String? = null
    var vi: Boolean? = null


    var statusimage: ImageView? = null
    var bueconf: Intent? = null
    var istouchingabutton = false
    var send_data = false
    var lists: ListView? = null
    var macsAddress = mutableListOf<String>()
    var devicess = mutableListOf<String>()
    var arrayAdapter: ArrayAdapter<String>? = null
    var sw1: Switch? = null
    var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var onclicbutton = false
    var ConectionBluetooth: Conection? = null
    var blockconection = false
    var dat = "U"
    var activityenabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)

        var viewvista = findViewById<View>(R.id.fondo) as androidx.constraintlayout.widget.ConstraintLayout
        //viewvista.setBackgroundColor(Color.BLACK)

        var viob = getSystemService(VIBRATOR_SERVICE) as Vibrator

        var prefers = PreferenceManager.getDefaultSharedPreferences(this)

        Adelante = prefers.getString(name_key_up, "a")
        Atras = prefers.getString(name_key_down, "r")
        Azquierda=prefers.getString(name_key_left, "i")
        Derecha=prefers.getString(name_key_rigth, "d")
        Stop=prefers.getString(name_key_stop, "o")
        vi= prefers.getBoolean(name_key_vi, false)

        statusimage = findViewById(R.id.imageView6)
        val btnadelante = findViewById<View>(R.id.imageView)
        val btnatras = findViewById<View>(R.id.imageView2)
        val btnderecha = findViewById<View>(R.id.imageView3)
        val btnizquierda = findViewById<View>(R.id.imageView4)
        btnadelante.setOnTouchListener(object:View.OnTouchListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(onclicbutton) {
                    if (event?.action == MotionEvent.ACTION_UP) {
                        ConectionBluetooth?.writebytes(Stop.toString())
                        istouchingabutton = false
                    } else if (event?.action == MotionEvent.ACTION_DOWN) {
                        ConectionBluetooth?.writebytes(Adelante.toString())
                        istouchingabutton = true




                    }
                }else{
                    ToastBlEn()
                }
                return true
            }
        })
        btnatras.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(onclicbutton) {
                    if (event?.action == MotionEvent.ACTION_UP) {
                        ConectionBluetooth?.writebytes(Stop.toString())
                        istouchingabutton = false
                    } else if (event?.action == MotionEvent.ACTION_DOWN ) {
                        ConectionBluetooth?.writebytes(Atras.toString())
                        istouchingabutton = true
                    }
                }else{
                    ToastBlEn()
                }
                return true
            }
        })
        btnderecha.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(onclicbutton) {
                    if (event?.action == MotionEvent.ACTION_UP ) {
                        ConectionBluetooth?.writebytes(Stop.toString())
                        istouchingabutton = false
                    } else if (event?.action == MotionEvent.ACTION_DOWN ) {
                        ConectionBluetooth?.writebytes(Derecha.toString())
                        istouchingabutton = true
                    }
                }else{
                    ToastBlEn()
                }
                return true
            }
        })
        btnizquierda.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(onclicbutton) {
                    if (event?.action == MotionEvent.ACTION_UP ) {
                        ConectionBluetooth?.writebytes(Stop.toString())
                        istouchingabutton = false
                    } else if (event?.action == MotionEvent.ACTION_DOWN ) {
                        ConectionBluetooth?.writebytes(Azquierda.toString())
                        istouchingabutton = true

                }
                }else{
                    ToastBlEn()
                }
                             return true
            }
        })
    }
    public fun bluetoothconfi(view: View){
        var bueconf = Intent(this, bluetoohConfiguration_Activity::class.java)
        startActivity(bueconf)

    }
    var veces = 0
    public fun pagewiew(view: View) {
        /*if(veces == 2) {
            veces = 0
            val page = Intent(this, page_Activity::class.java)
            startActivity(page)
        }else{
            veces++
        }*/

        if(veces ==2) {
            veces =0
            val page = Intent(this, page_Activity::class.java)
            startActivity(page)
        }else{
            veces++
        }
        Handler().postDelayed({
                              veces = 0
        },1000)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(!bluetoothAdapter.isEnabled) {
            statusimage?.setImageResource(R.drawable.ic_stop2t)
        }else {
            statusimage?.setImageResource(R.drawable.ic_stop2)
        };
        if(PreferenceManager.getDefaultSharedPreferences(this).getString("color","def")?.equals("black") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.BLACK)
        }else if(PreferenceManager.getDefaultSharedPreferences(this).getString("color","def")?.equals("tomate") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.parseColor("#FF5722"))
        }else if(PreferenceManager.getDefaultSharedPreferences(this).getString("color","def")?.equals("yellow") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.parseColor("#FFEB3B"))
        }else if(PreferenceManager.getDefaultSharedPreferences(this).getString("color","def")?.equals("green") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.parseColor("#4CAF50"))
        }else if(PreferenceManager.getDefaultSharedPreferences(this).getString("color","def")?.equals("red") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.parseColor("#D9E6CA"))
        }else if(PreferenceManager.getDefaultSharedPreferences(this).getString("def","def")?.equals("black") == true){
            findViewById<View>(R.id.fondo).setBackgroundColor(Color.RED)
        }
    }
    public fun confi(view: View){
        val confifuration = Intent(this, confi::class.java)
        startActivity(confifuration)
    }


    override fun onResume() {
        super.onResume()
        var prefers = PreferenceManager.getDefaultSharedPreferences(this)

        Adelante = prefers.getString(name_key_up, "a")
        Atras = prefers.getString(name_key_down, "r")
        Azquierda=prefers.getString(name_key_left, "i")
        Derecha=prefers.getString(name_key_rigth, "d")
        Stop=prefers.getString(name_key_stop, "o")
        vi= prefers.getBoolean(name_key_vi, false)
        if(!bluetoothAdapter.isEnabled) {
            statusimage?.setImageResource(R.drawable.ic_stop2t)
        }else {
            statusimage?.setImageResource(R.drawable.ic_stop2)
        }
        var conectq = intent.getStringExtra("BeginConection")
        var macaddressid = intent.getStringExtra("MAC")

        if(conectq.equals("true")&& !macaddressid.equals("")){
            Toast.makeText(this,macaddressid, Toast.LENGTH_SHORT).show()
            if (bluetoothAdapter.isEnabled) {

                //MAC Address of arduino hc05
                val device = bluetoothAdapter.getRemoteDevice(macaddressid)

                var bluetoothSocket: BluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))

                blockconection = bluetoothSocket.isConnected

                Toast.makeText(
                    this,
                    onclicbutton.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                if (!bluetoothSocket.isConnected && !blockconection) {
                    try {
                        if (!onclicbutton) {
                            onclicbutton = bluetoothSocket.isConnected
                            blockconection = true

                        } else {
                            Toast.makeText(
                                this,
                                "Ya existe una conexion",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(this, "Failed Conection", Toast.LENGTH_SHORT).show()
                    }

                    try {

                        bluetoothSocket!!.connect()
                        onclicbutton = true
                        //TODO: Thread class
                        ConectionBluetooth = Conection(bluetoothSocket)
                        ConectionBluetooth!!.start()
                        if (bluetoothSocket.isConnected)
                            blockconection = true

                        Toast.makeText(this, "Complete conection!", Toast.LENGTH_SHORT).show()

                    } catch (e: IOException) {
                        try {


                            Toast.makeText(this, "No se pudo conectar :(", Toast.LENGTH_SHORT).show()
                            bueconf = Intent(this, bluetoohConfiguration_Activity::class.java)

                        } catch (f: IOException) {
                        }
                    }
                } else {
                    Toast.makeText(this, "Another device is yect conected!", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(
                    this,
                    onclicbutton.toString(),
                    Toast.LENGTH_SHORT
                ).show()



            } else {
                Toast.makeText(this, "First active bluetooth", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun ToastBlEn(){
        Toast.makeText(this, "Primero debes conectar un dispositivo", Toast.LENGTH_SHORT).show()
    }

    inner class Conection(socket: BluetoothSocket?) : Thread() {
        var mmOutStream: OutputStream? = null

        fun writebytes(input: String) {
            if(bluetoothAdapter.isEnabled) {
                try {
                    mmOutStream!!.write(input.toByteArray())
                } catch (e: IOException) {
                    println("Conection Failed")
                }
            }else{
                println("no esta conectado bluetooth")
            }
        }

        init {
            var inOut: OutputStream? = null
            try {
                inOut = socket!!.outputStream
            } catch (e: IOException) {
            }
            mmOutStream = inOut
        }
    }
}