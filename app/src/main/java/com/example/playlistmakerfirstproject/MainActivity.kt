package com.example.playlistmakerfirstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.searchId)
        val mediaButton = findViewById<Button>(R.id.mediaId)
        val settingButton = findViewById<Button>(R.id.settingsId)

        searchButton.setOnClickListener {
            val searchIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchIntent)
        }
        mediaButton.setOnClickListener {
            val mediaIntent = Intent(this,LibraryActivity::class.java )
            startActivity(mediaIntent)
        }
        settingButton.setOnClickListener {
            val settingIntent = Intent(this,SettingActivity::class.java)
            startActivity(settingIntent)
        }
    }
}
