package arlauskas.lukas.quayzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Menu : AppCompatActivity() {

    lateinit var preferencesConfig: PreferencesConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        preferencesConfig = PreferencesConfig(this@Menu)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val startGame : Button = findViewById(R.id.button8)
        var intent : Intent = getIntent()
        var username : String = preferencesConfig.ReadName()
        if(username.equals(""))
        {
            username = intent.extras?.getString("name", "User").toString()
        }
        findViewById<TextView>(R.id.textView12).setText("Hi, " + username)
        startGame.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Menu,MainActivity::class.java)
                startActivity(intent)
            }
        })
        val logout : Button = findViewById(R.id.logout)
        logout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                preferencesConfig.WriteLoginStatus(false)
                val intent = Intent(this@Menu, Startactivity::class.java)
                preferencesConfig.WriteName("")
                startActivity(intent)
                finish()
            }
        })
        val highscores : Button = findViewById(R.id.button9)
        highscores.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent = Intent(this@Menu, HighScores::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
