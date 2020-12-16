package arlauskas.lukas.quayzy

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import kotlin.concurrent.thread
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {

    lateinit var progressText : TextView
    lateinit var score : TextView
    lateinit var progressBar : ProgressBar
    lateinit var question : TextView
    lateinit var answer1 : Button
    lateinit var answer2 : Button
    lateinit var answer3 : Button
    lateinit var answer4 : Button
    lateinit var abort : Button
    lateinit var stcorrect : String
    var scoreval = 0
    var progressval = 1;
    lateinit var list : MutableList<Int>
    lateinit var questions : ArrayList<QuestionScore>

    companion object {
        lateinit var apiInterface : ApInterface
    }

    fun GenId() : Int {
        var id = Random.nextInt(1,11)
        while (list.contains(id)) {
            id = Random.nextInt(1,11)
        }
        return id
    }

    fun GenerateIds() {
        list = MutableList(10) {index -> 0}
        for(x in 0 until list.size step 1)
            list[x] = GenId()
        System.out.println(list)
    }

    fun GetQuestion() {
        val call : Call<Question> = apiInterface.retrieveQuestion(list[progressval-1])
        call.enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if(response.body()?.response.equals("ok"))
                {
                    question.setText(response.body()?.question)
                    answer1.setText(response.body()?.answer1)
                    answer2.setText(response.body()?.answer2)
                    answer3.setText(response.body()?.answer3)
                    answer4.setText(response.body()?.answer4)
                    stcorrect= response.body()?.correactanswer!!
                    answer1.background = resources.getDrawable(R.drawable.button)
                    answer2.background = resources.getDrawable(R.drawable.button)
                    answer3.background = resources.getDrawable(R.drawable.button)
                    answer4.background = resources.getDrawable(R.drawable.button)
                    answer1.isEnabled = true;
                    answer2.isEnabled = true;
                    answer3.isEnabled = true;
                    answer4.isEnabled = true;
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                System.out.println("failed")
                t.printStackTrace()
            }
        })
    }

    fun CheckAnswer(button : Button) : Boolean {
        if(button.text.equals(stcorrect))
        {
            return true;
        }
        return false;
    }

    fun clickedButton(button: Button){
        answer1.isEnabled = false;
        answer2.isEnabled = false;
        answer3.isEnabled = false;
        answer4.isEnabled = false;
        if(CheckAnswer(button))
        {
            button.background = resources.getDrawable(R.drawable.correctbutton)
            scoreval++
            questions.add(QuestionScore(question.text.toString(),"1/1"))
        }
        else {
            answer1.background = resources.getDrawable(R.drawable.button)
            searchForCorrectButton()
            questions.add(QuestionScore(question.text.toString(),"0/1"))
        }
        if(progressval!=10) {
            progressval++
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(10 * progressval, true)
            } else {
                progressBar.setProgress(10 * progressval)
            }
            progressText.text = "Progress: " + progressval + "/10"
            score.text = "Score: " + scoreval
            var handler : Handler = Handler()
            var r : Runnable = Runnable {
                GetQuestion()
            }
            handler.postDelayed(r, 500)
        } else {
            var intent : Intent = Intent(this@MainActivity, Review::class.java)
            var bundle : Bundle = Bundle()
            bundle.putSerializable("questions", questions as Serializable)
            intent.putExtra("bundle", bundle)
            intent.putExtra("score", scoreval)
            startActivity(intent)
            finish()

            //irasyti rezultatus i duombaze
            //siusti perziureti rezultatus
        }

    }

    fun searchForCorrectButton() {
        if(CheckAnswer(answer1))
        {
            answer1.background = resources.getDrawable(R.drawable.correctbutton)
        }
        else if(CheckAnswer(answer2))
        {
            answer1.background = resources.getDrawable(R.drawable.correctbutton)
        }
        else if(CheckAnswer(answer3))
        {
            answer1.background = resources.getDrawable(R.drawable.correctbutton)
        }
        else if(CheckAnswer(answer4))
        {
            answer1.background = resources.getDrawable(R.drawable.correctbutton)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GenerateIds()
        apiInterface = ApiClient.getApiClient().create(ApInterface::class.java)
        question = findViewById(R.id.textView)
        progressText = findViewById(R.id.textView2)
        score = findViewById(R.id.textView3)
        progressBar = findViewById(R.id.progressBar)
        progressBar.setProgress(0)
        answer1 = findViewById(R.id.button)
        answer2 = findViewById(R.id.button2)
        answer3 = findViewById(R.id.button3)
        answer4 = findViewById(R.id.button4)
        questions = ArrayList(10)
        GetQuestion()
        answer1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                clickedButton(answer1)
            }
        })
        answer2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                clickedButton(answer2)
            }
        })
        answer3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                clickedButton(answer3)
            }
        })
        answer4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                clickedButton(answer4)
            }
        })
    }
}
