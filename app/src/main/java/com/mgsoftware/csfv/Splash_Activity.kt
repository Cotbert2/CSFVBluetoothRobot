package com.mgsoftware.csfv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import kotlin.jvm.internal.MagicApiIntrinsics

class Splash_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            findViewById<ImageView>(R.id.imageView7).setImageResource(R.drawable.ic_stopwof)
        },1000)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            onDestroy()
        },2000)

    }

}