package com.arsa.smscounter.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arsa.smscounter.R


class MainActivity : AppCompatActivity() {
    val PERMISSION_REQUEST_READ_SMS = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }

    fun checkPermission(){
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_SMS),
                PERMISSION_REQUEST_READ_SMS
            )
        }
    }
}