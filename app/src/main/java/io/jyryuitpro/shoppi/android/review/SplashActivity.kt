package io.jyryuitpro.shoppi.android.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// https://developer.android.com/develop/ui/views/launch/splash-screen
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}