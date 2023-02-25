package com.example.playlistmakerfirstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val backSettingButton = findViewById<Button>(R.id.backId)
        backSettingButton.setOnClickListener {
            val backSettingIntent = Intent(this,MainActivity::class.java)
            startActivity(backSettingIntent)
        }
    }
}