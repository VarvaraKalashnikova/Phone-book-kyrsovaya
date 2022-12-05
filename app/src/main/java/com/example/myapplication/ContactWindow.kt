package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dialogYesOrNo

class ContactWindow : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_contact)
        title = "Контакт"
        val dbHelper = DBHelper(this)
        val Text = findViewById<TextView>(R.id.textView4)
        val TextName = findViewById<TextView>(R.id.textView2)
        val TextPhone = findViewById<TextView>(R.id.textView6)
        val button = findViewById<Button>(R.id.button)
        val buttonDrop = findViewById<Button>(R.id.button2)
        val buttonChange = findViewById<Button>(R.id.button3)
        val id = intent.getLongExtra("Id", 0)
        val objects = dbHelper.getById(id)

        Text.text = "Фамилия:  " + objects?.title
        TextName.text = "Имя:      " + objects?.name
        TextPhone.text = "Телефон:   " + objects?.telephone


        buttonChange.setOnClickListener {
            val intent = Intent(this@ContactWindow, Change::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }
        buttonDrop.setOnClickListener {
            val uid = id
            dialogYesOrNo(
                this,
                "Удалить контакт?",
                "Удалить?",
                DialogInterface.OnClickListener { dialog, id ->
                    dbHelper.remove(uid)
                    val intent = Intent(this@ContactWindow, MainActivity::class.java)
                    startActivity(intent)
                })
        }
        button.setOnClickListener {

            val intent = Intent(this@ContactWindow, MainActivity::class.java)
            startActivity(intent)
        }

        //pepey
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}