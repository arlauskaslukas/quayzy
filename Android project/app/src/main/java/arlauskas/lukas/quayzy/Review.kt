package arlauskas.lukas.quayzy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Review : AppCompatActivity() {

    lateinit var preferencesConfig: PreferencesConfig
    lateinit var apiInterface : ApInterface
    var score : Int = 0

    fun RetrieveScore(name : String) {
        val call : Call<UserScores> =  apiInterface.retrieveScore(name)
        call.enqueue(object : Callback<UserScores> {
            override fun onResponse(call: Call<UserScores>, response: Response<UserScores>) {
                score = response.body()?.Score!!
            }

            override fun onFailure(call: Call<UserScores>, t: Throwable) {
                Toast.makeText(this@Review,"Retrieving failed", Toast.LENGTH_SHORT).show()
                score = 0
            }
        })
    }

    fun SendScore(score : Int, name : String)
    {
        var call : Call<String> = apiInterface.setScore(score, name)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@Review,"Sending results failed", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.body().equals("ok"))
                {
                    val intent : Intent = Intent(this@Review, Menu::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@Review, "Sending results failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        apiInterface = ApiClient.getApiClient().create(ApInterface::class.java)
        preferencesConfig = PreferencesConfig(this.applicationContext)
        val name : String = preferencesConfig.ReadName()
        var intent : Intent = getIntent()
        val scorelocal = intent.getIntExtra("score", 0)
        var scoretext : TextView = findViewById(R.id.textView14)
        var doubleProc : Int = (scorelocal.toInt())*10
        var scoretotal : Int = scorelocal.toInt() + score
        scoretext.text= "$doubleProc%"
        var bundle : Bundle = intent.getBundleExtra("bundle")
        var list : ArrayList<QuestionScore> = bundle.getSerializable("questions") as ArrayList<QuestionScore>
        var listView : ListView = findViewById(R.id.list)
        var adapter : QuestionListAdapter = QuestionListAdapter(this@Review, R.layout.adapter_view_layout, list)
        listView.adapter = adapter
        val tryAgain : Button = findViewById(R.id.button10)
        tryAgain.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent = Intent(this@Review,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        val sendResults : Button = findViewById(R.id.button12)
        sendResults.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                SendScore(score, name)
            }
        })
    }
}