package arlauskas.lukas.quayzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HighScores : AppCompatActivity() {

    lateinit var userslist : MutableList<UserScores>
    lateinit var userslistas : ArrayList<UserScores>
    lateinit var scorelist : ListView
    companion object {
        lateinit var apiInterface: ApInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)
        userslist = retrieveList()
        val button = findViewById<Button>(R.id.button13)
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@HighScores, Menu::class.java)
                startActivity(intent)
            }
        })
    }
    fun retrieveList() : MutableList<UserScores>
    {
        apiInterface = ApiClient.getApiClient().create(ApInterface::class.java)
        var mutableList = mutableListOf<UserScores>()
        var call : Call<ArrayList<UserScores>> = apiInterface.retrieveBest()
        call.enqueue(object : Callback<ArrayList<UserScores>> {
            override fun onResponse(
                call: Call<ArrayList<UserScores>>,
                response: Response<ArrayList<UserScores>>
            ) {
                for (x in 0 until response.body()?.size!!)
                {
                    mutableList.add(UserScores(response.body()!![x].Name, response.body()!![x].Score))

                }
                userslistas = ArrayList(userslist)
                scorelist = findViewById(R.id.listscores)
                var adapter : UserScoreAdapter = UserScoreAdapter(this@HighScores, R.layout.highscores_layout_adapter, userslistas)
                scorelist.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<UserScores>>, t: Throwable) {
                Log.i("Highscores", "Failure to get callback")
                t.printStackTrace()
            }
        })
        return mutableList
    }
}
