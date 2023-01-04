package com.example.democonnectsamsung.model

data class ResponseDevice (
    val device: MyDevice,
    val id: String,
    val name: String,
    val remote: String,
    val type: String,
    val uri: String,
    val version: String
)

data class MyDevice (
    val FrameTVSupport: String,
    val GamePadSupport: String,
    val ImeSyncedSupport: String,
    val OS: String,
    val TokenAuthSupport: String,
    val VoiceSupport: String,

    val countryCode: String,
    val description: String,
    val developerIP: String,
    val developerMode: String,
    val duid: String,
    val firmwareVersion: String,
    val id: String,
    val ip: String,
    val model: String,
    val modelName: String,
    val name: String,
    val networkType: String,
    val resolution: String,
    val smartHubAgreement: String,
    val ssid: String,
    val type: String,
    val udn: String,

    val wifiMac: String
)
