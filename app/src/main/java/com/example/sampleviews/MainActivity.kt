package com.example.sampleviews

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    // declare the variables as instance properties so they can be used in multiple methods
    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var flashButton: ToggleButton
    lateinit var cameraManager: CameraManager
    lateinit var cameraID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize view references
        editText = findViewById(R.id.myEditText)
        textView = findViewById(R.id.myText)
        button = findViewById(R.id.setButton)
        flashButton = findViewById(R.id.myToggleButton)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraID = cameraManager.cameraIdList[0]
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        // set listener
        button.setOnClickListener {
            textView.text = editText.text
            editText.setText("") //use setText as it takes a String as param and not an Editable
        }

        flashButton.setOnClickListener {
            if(flashButton.isChecked) {
                try {
                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraID, true)
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            } else {
                try {
                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraID, false)
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}