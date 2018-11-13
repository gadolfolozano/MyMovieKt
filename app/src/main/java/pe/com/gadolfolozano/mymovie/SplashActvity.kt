package pe.com.gadolfolozano.mymovie

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}