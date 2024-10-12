package com.mgsoftware.csfv

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*

class bluetoohConfiguration_Activity : AppCompatActivity() {
    var send_data = false
    var lists: ListView? = null
    var macsAddress = mutableListOf<String>()
    var devicess = mutableListOf<String>()
    var arrayAdapter: ArrayAdapter<String>? = null
    var sw1: Switch? = null
    var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var onclicbutton = false


    var blockconection = false
    var dat = "U"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_bluetooh_configuration)


        val lists = findViewById<ListView>(R.id.lv1)

        //Array adapter parameters: context, theam, lit view
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, devicess)
        lists?.adapter = arrayAdapter

        lists?.setOnItemClickListener() { parent, view, position, id ->
            //TODO: Send data to main
            val retmain = Intent(this, MainActivity::class.java)
            retmain.putExtra("BeginConection", "true")
            retmain.putExtra("MAC",macsAddress[position])
            startActivity(retmain)
            finish()
        }

        if (bluetoothAdapter.isEnabled)
            pDevices()
        sw1 = findViewById<View>(R.id.switch1) as Switch
        sw1!!.textOff = "Off"
        sw1!!.textOn = "On"
        //checked listener
        sw1!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (sw1!!.isChecked) {



                //TODO: Turn On bluetooth
                if (bluetoothAdapter.equals(null)) {
                    Toast.makeText(this, "Your device don't support Bluetooth", Toast.LENGTH_SHORT)
                        .show()
                } else if (!bluetoothAdapter.equals(null)) {
                    ///The device support bluetooth
                    if (!bluetoothAdapter.isEnabled) {
                        //Bluetooth isn't activate
                        //Active bluetooth
                        val bluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        val REQUEST_ENABLE_BT = 2
                        startActivityForResult(bluetoothIntent, REQUEST_ENABLE_BT)
                    }
                }
            } else if (!sw1!!.isChecked) {
                //TODO: Turn Off bluetooth
                if (!bluetoothAdapter.isEnabled) {
                    //Bluetooth isn't activate
                    //Active bluetooth

                } else if (bluetoothAdapter.isEnabled) {
                    bluetoothAdapter.disable()
                    sw1!!.isChecked = false
                    devicess.clear()
                    macsAddress.clear()
                    arrayAdapter?.notifyDataSetChanged()
                    onclicbutton = false
                    blockconection = false

                }
            }
        }
        if (bluetoothAdapter.isEnabled) {
            sw1?.isChecked = true
        } else if (!bluetoothAdapter.isEnabled) {
            sw1?.isChecked = false
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode.equals(RESULT_OK)) {
            sw1!!.isChecked = true
            Toast.makeText(this, "Bluetooth is on!", Toast.LENGTH_SHORT).show()
            pDevices()

        } else if (resultCode.equals(RESULT_CANCELED)) {
            sw1!!.isChecked = false
            Toast.makeText(this, "Failed Turn on BLuetooth", Toast.LENGTH_SHORT).show()
        }
    }
    private fun pDevices() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        var numDevice = 0
        //clean the devices from the list view
        devicess.clear()
        macsAddress.clear()
        pairedDevices?.forEach { device ->
            numDevice++
            val dname = device.name
            devicess.add(dname)
            val daddress = device.address
            macsAddress.add(daddress)
            arrayAdapter?.notifyDataSetChanged()
            println("Device # $numDevice: \n Name: $dname \n Address: $daddress")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (bluetoothAdapter.isEnabled) {
            sw1?.isChecked = true
        } else if (!bluetoothAdapter.isEnabled) {
            devicess.clear()
            macsAddress.clear()
            arrayAdapter?.notifyDataSetChanged()
            sw1?.isChecked = false
        }
        pDevices()
    }



}