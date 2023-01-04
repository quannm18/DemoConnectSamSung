package com.example.democonnectsamsung

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.democonnectsamsung.databinding.ActivityMainBinding
import com.example.democonnectsamsung.model.ResponseDevice
import com.koushikdutta.async.AsyncServer.LOGTAG
import com.samsung.multiscreen.*


class MainActivity : AppCompatActivity() {
    private lateinit var mService: Service
    private var search: Search = Service.search(this)
    private lateinit var responseDevice: ResponseDevice
    private lateinit var binding: ActivityMainBinding
    private lateinit var url: Uri
    private lateinit var application: Channel
    private lateinit var macIP: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener {
            startDiscovery()
        }

        binding.tvStatus.setOnClickListener {
            startActivity(Intent(this, ControlActivity::class.java))
        }

        binding.btnScan.setOnLongClickListener {
            connect(macIP, mService.uri)
            true
        }
    }

    private fun startDiscovery() {
        if (search == null) {
            search = Service.search(this)
        }
        search.setOnServiceFoundListener { service ->
            Log.d(LOGTAG, "Search onFound() $service")
            binding.tvStatus.text = "onFound ${service.name}"
            mService = service
            url = service.uri
            service.getDeviceInfo(object : com.samsung.multiscreen.Result<Device> {
                override fun onSuccess(p0: Device?) {
                    macIP = p0?.wifiMac.toString()
                }

                override fun onError(p0: Error?) {

                }

            })
//            connect(service, service.uri)
        }
        search.setOnServiceLostListener { service ->
            Log.d(LOGTAG, "Search onLost() $service")
        }
        search.setOnStopListener {
            Log.d(
                LOGTAG, "Search onStop() services found: " + search.services.size
            )
        }
        search.start()
// You can also stop the discovery process after some amount of time. Preferably once the user has selected a service to work with
        search.stop()

    }

    companion object {
        val tag = "Main"
    }

    fun connect(service: Service, url: Uri) {
        Log.e(
            LOGTAG,
            "ábnfkansc"
        )
        val channelId = "com.example.democonnectsamsung"
        val application = service.createApplication(url, channelId)
//        application = service.createApplication(url, channelId)
        application.setOnConnectListener { p0 ->
            binding.progressBar.visibility = View.GONE
            Log.e(
                LOGTAG,
                "Application.onConnect() client: $p0"
            );
        }
        application.connect(object : Result<Client> {
            override fun onSuccess(p0: Client?) {
                Log.e(LOGTAG, "Application connect onSuccess() client: " + p0.toString())
            }

            override fun onError(p0: Error?) {
                Log.e(LOGTAG, "Application connect onError() error: " + p0.toString())
            }

        })

    }

    fun connect(mac: String, mUri: Uri) {
        Service.WakeOnWirelessAndConnect(
            mac,
            mUri,
            object : com.samsung.multiscreen.Result<Service> {
                override fun onSuccess(p0: Service?) {
                    if (p0 != null) {
                        val app = p0.createChannel(mUri)
                        app.connect(object : com.samsung.multiscreen.Result<Client> {
                            override fun onSuccess(client: Client?) {
                                if (client != null) {
                                    Log.e("log", "onSuccess: $client")
                                }
                            }

                            override fun onError(error: Error?) {
                                Log.e("log", "onError: " + error?.message); }

                        })
                    }
                }

                override fun onError(error: Error?) {
                    Log.e("log", "onError: " + error?.message); }

            })
    }
}

