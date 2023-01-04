package com.example.democonnectsamsung

import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.democonnectsamsung.databinding.ActivityControlBinding
import com.example.democonnectsamsung.model.SamsungRemote
import com.example.democonnectsamsung.model.TVReply
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.net.InetAddress

class ControlActivity : AppCompatActivity() {
    lateinit var binding: ActivityControlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnMenu.setOnClickListener(View.OnClickListener {
            val menu = Intent(applicationContext, MainActivity::class.java)
            startActivity(menu)
            finish()
        })

        binding.btnConnect.setOnClickListener(View.OnClickListener { v ->
            IP = binding.editTextIP.getText().toString()
            val mySnackbar =
                Snackbar.make(v, "Connected to " + IP, BaseTransientBottomBar.LENGTH_LONG)
            mySnackbar.show()
        })

        binding.btnSettings.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_MENU"))
            thread.start()
        })

        binding.btnHDMI1.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_HDMI"))
            thread.start()
        })

        binding.btnHDMI2.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_HDMI2"))
            thread.start()
        })

        binding.btnHDMI3.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_HDMI3"))
            thread.start()
        })

        binding.btnHDMI4.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_HDMI4"))
            thread.start()
        })

        binding.btnDown.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_DOWN"))
            thread.start()
        })

        binding.btnRight.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_RIGHT"))
            thread.start()
        })

        binding.btnLeft.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_LEFT"))
            thread.start()
        })

        binding.btnUp.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_UP"))
            thread.start()
        })

        binding.btnTools.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_TOOLS"))
            thread.start()
        })

//        binding.btnSource.setOnClickListener(View.OnClickListener {
//            val thread: Thread = Thread(createRunnable("KEY_PANNEL_SOURCE"))
//            thread.start()
//        })

        binding.btnPower.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_POWEROFF"))
            thread.start()
        })

        binding.btnZero.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_0"))
            thread.start()
        })

        binding.btnOne.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_1"))
            thread.start()
        })

        binding.btnTwo.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_2"))
            thread.start()
        })

        binding.btnThree.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_3"))
            thread.start()
        })

        binding.btnFour.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_4"))
            thread.start()
        })

        binding.btnFive.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_5"))
            thread.start()
        })

        binding.btnSix.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_6"))
            thread.start()
        })

        binding.btnSeven.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_7"))
            thread.start()
        })

        binding.btnEight.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_8"))
            thread.start()
        })

        binding.btnNine.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_9"))
            thread.start()
        })

        binding.btnVolUp.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_VOLUP"))
            thread.start()
        })

        binding.btnVolDown.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_VOLDOWN"))
            thread.start()
        })

//        binding.btnChannelUp.setOnClickListener(View.OnClickListener {
//            val thread: Thread = Thread(createRunnable("KEY_CHUP"))
//            thread.start()
//        })
//
//        binding.btnChannelDown.setOnClickListener(View.OnClickListener {
//            val thread: Thread = Thread(createRunnable("KEY_CHDOWN"))
//            thread.start()
//        })

        binding.btnReturn.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_RETURN"))
            thread.start()
        })

        binding.btnOkay.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_ENTER"))
            thread.start()
        })

        binding.btnHome.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_HOME"))
            thread.start()
        })

        binding.btnGuide.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_GUIDE"))
            thread.start()
        })

        binding.btnMute.setOnClickListener(View.OnClickListener {
            val thread: Thread = Thread(createRunnable("KEY_MUTE"))
            thread.start()
        })

        binding.btnCheckInternet.setOnClickListener(View.OnClickListener {
            var ssid = ""
            var ip = 0
            var BSSID = ""
            if (isNetworkConnected()) {
                val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                val wifiInfo: WifiInfo = wifiManager.connectionInfo
                if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
                    ssid = wifiInfo.ssid
                    ip = wifiInfo.ipAddress
                    BSSID = wifiInfo.bssid
                    binding.textViewInternet.setText("You are connected to $ssid   $BSSID")
                }
            } else {
                binding.textViewInternet.setText("No internet connection!")
            }
            //tryToReadSSID();
        })
    }

    var paramStr = "a parameter"
    var myRunnable = createRunnable(paramStr)

    private fun createRunnable(paramStr: String): Runnable {
        return Runnable {
            try {
                ExecuteCommand(paramStr)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    var IP = "wss://192.168.1.2:8002/api/v2/"

    @Throws(IOException::class, InterruptedException::class)
    fun ExecuteCommand(message: String?) {
        try {
            val address = InetAddress.getByName(IP) // 192 168 0 18
            val remote = SamsungRemote(address)
            val reply = remote.authenticate("Samsung Tablet")
            if (reply == TVReply.ALLOWED) {
                remote.keycode(message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("--->ExecuteCommand", "ExecuteCommand: ${e.message}")
        }
    }
    fun sendKey(message: String) {
        try {
            val address = InetAddress.getByName(IP) // 192 168 0 18
            val remote = SamsungRemote(address)
            val reply = remote.authenticate("Samsung Tablet")
            if (reply == TVReply.ALLOWED) {
                remote.keycode(message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("--->ExecuteCommand", "ExecuteCommand: ${e.message}")
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}