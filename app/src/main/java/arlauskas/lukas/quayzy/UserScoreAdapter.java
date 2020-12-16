package arlauskas.lukas.quayzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserScoreAdapter extends ArrayAdapter<UserScores> {

    public static final String TAG = "UserListAdapter";
    private Context context;
    private int resource;

    public UserScoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserScores> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        int score = getItem(position).getScore();
        UserScores us = new UserScores(name, score);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        TextView tv1 = (TextView) convertView.findViewById(R.id.placeview);
        TextView tv2 = (TextView) convertView.findViewById(R.id.userview);
        TextView tv3 = (TextView) convertView.findViewById(R.id.scoreview);
        int pos = position+1;
        tv1.setText("" + pos);
        tv2.setText(name);
        tv3.setText("" + score);
        return convertView;
    }
}
