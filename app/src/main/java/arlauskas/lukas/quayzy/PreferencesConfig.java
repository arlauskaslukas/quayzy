package arlauskas.lukas.quayzy;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PreferencesConfig {
    private SharedPreferences sharedPreferences;
    private Context context;
    public PreferencesConfig(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }
    public void WriteLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }
    public boolean ReadLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }
    public void WriteName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name), name);
        editor.commit();
    }
    public String ReadName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "");
    }
    public void displayMessage(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
