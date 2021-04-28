package arlauskas.lukas.quayzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.ContentHandler;
import java.util.ArrayList;

public class QuestionListAdapter extends ArrayAdapter<QuestionScore> {
    public static final String TAG = "QuestionListAdapter";
    private Context context;
    private int resource;

    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<QuestionScore> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String question = getItem(position).getQuestion();
        String score = getItem(position).getScore();
        QuestionScore qs = new QuestionScore(question, score);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView16);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView17);
        tv1.setText(question);
        tv2.setText(score);
        return convertView;
    }
}
