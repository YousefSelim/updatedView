package com.boorsaty.sehtk

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dd.processbutton.FlatButton
import com.dd.processbutton.iml.ActionProcessButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_auth.*

class Auth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "AuthActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Log.d("AuthActivity", "Initialization Complete ")

        val signup = findViewById<ActionProcessButton>(R.id.signup_btn)
        supportActionBar?.hide()
        //val arabictypeface = resources.getFont(R.font.a)
        val arabicc = resources.getFont(R.font.arabic_font)
        signup_btn.typeface = arabicc
        username_tv.typeface = arabicc
        password_tv.typeface = arabicc
        nationalid_tv.typeface = arabicc
        email_tv.typeface = arabicc
        signup_btn.setOnClickListener {
            if (email_tv.text.isNullOrEmpty() || password_tv.text.isNullOrEmpty() || nationalid_tv.text.isNullOrEmpty() || username_tv.text.isNullOrEmpty()) {
                Toast.makeText(this, "برجاء ملئ جميع البيانات بشكل صحيح", Toast.LENGTH_LONG).show()
            } else {
                signup.setMode(ActionProcessButton.Mode.ENDLESS)
                signup.setProgress(1)
                Log.d("AuthActivity", "Started Signup")
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email_tv.text.toString(), password_tv.text.toString())
                    .addOnSuccessListener {
                        Log.d(TAG, "Authentication done !!! , UID = " + FirebaseAuth.getInstance().uid.toString())
                        Log.d(TAG, "Starting Saving data to database")

                        signup.setProgress(100)
                        val uid = FirebaseAuth.getInstance().uid
                        val databaseRef = FirebaseDatabase.getInstance().getReference("/Users/$uid")
                        databaseRef.setValue(UserData(uid.toString(),username_tv.text.toString(),false,18,nationalid_tv.toString())).addOnFailureListener(
                            {
                                Log.d(TAG,"Err doing data store due to ${it.toString()}")
                            }
                        )
                        checkAuthentication()
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Err Making user account , authentication failed due to " + it.toString())
                    }

            }
        }
    }

    private fun checkAuthentication() {
        if (FirebaseAuth.getInstance().uid != null) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
    private fun startLogin() {
        if (FirebaseAuth.getInstance().uid != null) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
