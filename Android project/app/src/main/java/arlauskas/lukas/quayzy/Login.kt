package arlauskas.lukas.quayzy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface OnLoginFormActivityListener
{
    fun performRegister()
    fun performLogin(name:String)
}

class Login : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var register : TextView
    lateinit var login : Button

    lateinit var onLoginFormActivityListener: OnLoginFormActivityListener
    companion object {
        lateinit var apiInterface: ApInterface
    }
    fun performLogin()
    {
        val Username : String = username.text.toString()
        val Password : String = password.text.toString()
        var call : Call<User> = apiInterface.performUserLogin(Username, Password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("Login", "Failure to get callback")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.body()?.response.equals("ok")) {
                    Startactivity.preferencesConfig.WriteLoginStatus(true)
                    Startactivity.preferencesConfig.WriteName(response.body()?.name)
                    var intent: Intent = Intent(this@Login, Menu::class.java)
                    intent.putExtra("name", response.body()?.name)
                    startActivity(intent)
                    finish()
                }
                else if(response.body()?.response.equals("failed")){
                    Startactivity.preferencesConfig.displayMessage("Login failed")
                }
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        apiInterface = ApiClient.getApiClient().create(ApInterface::class.java)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                performLogin()
            }
        })
        register = findViewById(R.id.textView8)
        register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Login, RegisterActivity::class.java)
                Log.i("Start Activity", "Registration called")
                startActivity(intent)
            }
        })


    }
}
