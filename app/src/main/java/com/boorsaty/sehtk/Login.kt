package com.boorsaty.sehtk

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.view.*
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val signin_btn = findViewById<CircularProgressButton>(R.id.signin_for_login_btn)
        val email = findViewById<TextFieldBoxes>(R.id.email_for_login_tv)
        val password = findViewById<TextFieldBoxes>(R.id.password_for_login_tv)
        signin_btn.setOnClickListener {
            checkAuthentication()
            if (email.extended_edit_text.text.isNullOrEmpty() || password.extended_edit_text1.text.isNullOrEmpty()) {
                Toast.makeText(this, "برجاء ادخال البريد الاكتروني و كلمة السر", Toast.LENGTH_LONG).show()
            } else {
                signin_btn.startAnimation()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    email.extended_edit_text.text.toString(),
                    password.extended_edit_text1.text.toString()
                ).addOnSuccessListener {
                    signin_btn.setProgress(100.0f)
                    checkAuthentication()
                }
                    .addOnFailureListener {
                        signin_btn.revertAnimation {
                            signin_btn.setBackgroundResource(R.drawable.background2)

                        }
                        // signin_btn.setBackgroundDrawable((R.drawable.background2.toDrawable()))
                        Toast.makeText(this, "البريد الاكتروني / كلمة السر غير متوافقين", Toast.LENGTH_LONG).show()
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
}
