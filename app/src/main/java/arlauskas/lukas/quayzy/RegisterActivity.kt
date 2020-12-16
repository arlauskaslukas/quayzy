package arlauskas.lukas.quayzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    lateinit var login : TextView
    lateinit var username : EditText
    lateinit var email : EditText
    lateinit var pwd : EditText
    lateinit var repeatpwd : EditText

    companion object {
        lateinit var apiInterface : ApInterface
    }

    fun performRegistration() {
        val Username : String = username.text.toString()
        val Email : String = email.text.toString()
        val Pwd : String = pwd.text.toString()
        val RepPwd : String = repeatpwd.text.toString()
        if(Pwd.equals(RepPwd)){
            var call: Call<User> = apiInterface.performRegistration(Email, Username, Pwd)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.body()?.response.equals("ok"))
                    {
                        Startactivity.preferencesConfig.displayMessage("Registration success")
                    }
                    else if(response.body()?.response.equals("err"))
                    {
                        Startactivity.preferencesConfig.displayMessage("Registration failed. Contact the developer.")
                    }
                    else if(response.body()?.response.equals("exists"))
                    {
                        Startactivity.preferencesConfig.displayMessage("User with such username already exists")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    System.out.println("failed")
                    t.printStackTrace()
                }
            })
        }
        else
        {
            //TODO: error "pwd don't match"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        username = findViewById(R.id.editText)
        email = findViewById(R.id.usernamereg)
        pwd = findViewById(R.id.passwordreg)
        repeatpwd = findViewById(R.id.password2)
        var SignUpButton : Button = findViewById(R.id.register)
        apiInterface = ApiClient.getApiClient().create(ApInterface::class.java)
        SignUpButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                //register
                try {
                    performRegistration()
                }catch (e:Exception)
                {
                    e.printStackTrace()
                }
                performRegistration()
                var intent = Intent(this@RegisterActivity, Menu::class.java)
                intent.putExtra("Name", username.text.toString())
                startActivity(intent)
            }
        })
        login = findViewById(R.id.textView10)
        login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(this@RegisterActivity, Login::class.java)
                startActivity(intent)
            }
        })
    }
}