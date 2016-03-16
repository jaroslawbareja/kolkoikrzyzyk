package com.example.pc.kolkoikrzyzyk;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "kolkoikrzyzykPref";
    private static final String PREFERENCES_PLAYER_1_NAME = "player_1_name";
    private static final String PREFERENCES_PLAYER_2_NAME = "player_2_name";
    private static final String PREFERENCES_PLAYER_1_SCORE = "player_1_score";
    private static final String PREFERENCES_PLAYER_2_SCORE = "player_2_score";
    private SharedPreferences preferences;

    EditText edittext_1;
    EditText edittext_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        edittext_1=(EditText)findViewById(R.id.editText);
        edittext_2=(EditText)findViewById(R.id.editText2);
    }

    public void saveData(View v){
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextData_1 = edittext_1.getText().toString();
        String editTextData_2 = edittext_2.getText().toString();
        preferencesEditor.putString(PREFERENCES_PLAYER_1_NAME, editTextData_1);
        preferencesEditor.putString(PREFERENCES_PLAYER_2_NAME, editTextData_2);
        preferencesEditor.commit();
    }
    public void resetPoints(View v){
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(PREFERENCES_PLAYER_1_SCORE, "0");
        preferencesEditor.putString(PREFERENCES_PLAYER_2_SCORE, "0");
        preferencesEditor.commit();
    }
}
