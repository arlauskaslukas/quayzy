package arlauskas.lukas.quayzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class Startactivity : AppCompatActivity() {

    companion object
    {
        lateinit var preferencesConfig: PreferencesConfig
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startactivity)
        preferencesConfig = PreferencesConfig(this@Startactivity)
        if(preferencesConfig.ReadLoginStatus())
        {
            val intent = Intent(this@Startactivity, Menu::class.java)
            intent.putExtra("username", preferencesConfig.ReadName())
            startActivity(intent)
        }
        var login : Button = findViewById(R.id.button5)
        login.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@Startactivity, Login::class.java)
                startActivity(intent)
            }
        })
        var register : Button = findViewById(R.id.button6)
        register.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@Startactivity, RegisterActivity::class.java)
                Log.i("Start Activity", "Registration called")
                startActivity(intent)
            }
        })
    }
}
