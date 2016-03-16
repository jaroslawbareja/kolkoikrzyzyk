package com.example.pc.kolkoikrzyzyk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class kolkoikrzyzyk extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "kolkoikrzyzykPref";
    private static final String PREFERENCES_PLAYER_1_NAME = "player_1_name";
    private static final String PREFERENCES_PLAYER_2_NAME = "player_2_name";
    private static final String PREFERENCES_PLAYER_1_SCORE = "player_1_score";
    private static final String PREFERENCES_PLAYER_2_SCORE = "player_2_score";
    private SharedPreferences preferences;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button_new_game;

    int score_player_1, score_player_2, player;
    String[] table = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
    };
    String player_char;

    TextView textview_1;
    TextView textview_2;
    TextView sc_player_1;
    TextView sc_player_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolkoikrzyzyk);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        textview_1=(TextView)findViewById(R.id.textView12);
        textview_2=(TextView)findViewById(R.id.textView8);

        button0=(Button)findViewById(R.id.button_p_0);
        button1=(Button)findViewById(R.id.button_p_1);
        button2=(Button)findViewById(R.id.button_p_2);
        button3=(Button)findViewById(R.id.button_p_3);
        button4=(Button)findViewById(R.id.button_p_4);
        button5=(Button)findViewById(R.id.button_p_5);
        button6=(Button)findViewById(R.id.button_p_6);
        button7=(Button)findViewById(R.id.button_p_7);
        button8=(Button)findViewById(R.id.button_p_8);
        button_new_game=(Button)findViewById(R.id.button);
        sc_player_1 = (TextView)findViewById(R.id.score_pl_1);
        sc_player_2 = (TextView)findViewById(R.id.score_pl_2);

        refresh_sharedpreferences();
        reset_game();
        hide_new_game_button();

    }

    @Override
    public void onResume(){
        super.onResume();

        refresh_sharedpreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kolkoikrzyzyk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View v) {
        Button button_clicked=(Button) v;
        button_clicked.setText(player_char);
        button_clicked.setClickable(false);

        check_winner();
        change_player();
    }

    public void buttonOnClick_new_game(View v) {
        new_game();
    }

    public void refresh_sharedpreferences(){
        String PREFERENCES_PLAYER_1_NAME_VAL = preferences.getString(PREFERENCES_PLAYER_1_NAME, "");
        String PREFERENCES_PLAYER_2_NAME_VAL = preferences.getString(PREFERENCES_PLAYER_2_NAME, "");

        textview_1.setText(PREFERENCES_PLAYER_1_NAME_VAL);
        textview_2.setText(PREFERENCES_PLAYER_2_NAME_VAL);
        reload_score();
    }
    public void new_game(){
        player_char="o";
        player=1;
        change_player_anouce();
        buttons_setClickable(true);
        buttons_setText("");
        buttons_setTextColor(Color.BLACK);
        buttons_setBackgroundColor(0xFFC3FF47);
        hide_new_game_button();
    }

    public void buttons_setClickable(boolean bool) {
        button0.setClickable(bool);
        button1.setClickable(bool);
        button2.setClickable(bool);
        button3.setClickable(bool);
        button4.setClickable(bool);
        button5.setClickable(bool);
        button6.setClickable(bool);
        button7.setClickable(bool);
        button8.setClickable(bool);
    }

    public void buttons_setTextColor(int color) {
        button0.setTextColor(color);
        button1.setTextColor(color);
        button2.setTextColor(color);
        button3.setTextColor(color);
        button4.setTextColor(color);
        button5.setTextColor(color);
        button6.setTextColor(color);
        button7.setTextColor(color);
        button8.setTextColor(color);
    }

    public void buttons_setBackgroundColor(int color) {
        button0.setBackgroundColor(color);
        button1.setBackgroundColor(color);
        button2.setBackgroundColor(color);
        button3.setBackgroundColor(color);
        button4.setBackgroundColor(color);
        button5.setBackgroundColor(color);
        button6.setBackgroundColor(color);
        button7.setBackgroundColor(color);
        button8.setBackgroundColor(color);
    }

    public void buttons_setText(String string) {
        button0.setText(string);
        button1.setText(string);
        button2.setText(string);
        button3.setText(string);
        button4.setText(string);
        button5.setText(string);
        button6.setText(string);
        button7.setText(string);
        button8.setText(string);
    }

    public void change_player(){
        if(player==1){
            player_char="x";
            player=2;
        }
        else {
            player_char="o";
            player=1;
        }
        change_player_anouce();
    }

    public void change_player_anouce(){
        TextView player_anouce = (TextView)findViewById(R.id.player_anouce);
        player_anouce.setText(getString(R.string.player_move) + " " + Integer.toString(player));
    }

    public void reset_game(){

        new_game();
        reload_score();
    }

    public void reload_score(){
        score_player_1=0;
        score_player_2=0;
        try {
            score_player_1 = Integer.parseInt(preferences.getString(PREFERENCES_PLAYER_1_SCORE, ""));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        try {
            score_player_2 = Integer.parseInt(preferences.getString(PREFERENCES_PLAYER_2_SCORE, ""));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        sc_player_1.setText(Integer.toString(score_player_1));
        sc_player_2.setText(Integer.toString(score_player_2));
    }

    public void add_point(){
        if ( player == 1 ) {
            score_player_1++;
        }
        else{
            score_player_2++;
        }
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(PREFERENCES_PLAYER_1_SCORE, String.valueOf(score_player_1));
        preferencesEditor.putString(PREFERENCES_PLAYER_2_SCORE, String.valueOf(score_player_2));
        preferencesEditor.commit();

        String player_won=getResources().getString(R.string.player_won);
        Toast.makeText(getApplicationContext(), player_won + player,
                Toast.LENGTH_LONG).show();
        buttons_setClickable(false);
        reload_score();
        show_new_game_button();
    }
    public void hide_new_game_button(){
        button_new_game.setVisibility(View.GONE);
    }
    public void show_new_game_button(){
        button_new_game.setVisibility(View.VISIBLE);
    }
    public void check_winner(){

        if (button0.getText().toString().equals(button1.getText().toString()) && button0.getText().toString().equals(button2.getText().toString()) && !button0.getText().toString().equals("")) {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button1.setTextColor(0xFF00FF00);
            button1.setBackgroundColor(0xFFFF0000);
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button3.getText().toString().equals(button4.getText().toString()) && button3.getText().toString().equals(button5.getText().toString()) && !button3.getText().toString().equals("")) {
            button3.setTextColor(0xFF00FF00);
            button3.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button5.setTextColor(0xFF00FF00);
            button5.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button6.getText().toString().equals(button7.getText().toString()) && button6.getText().toString().equals(button8.getText().toString()) && !button6.getText().toString().equals("")) {
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            button7.setTextColor(0xFF00FF00);
            button7.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button0.getText().toString().equals( button3.getText().toString()) && button0.getText().toString().equals(button6.getText().toString()) && !button0.getText().toString().equals("")) {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button3.setTextColor(0xFF00FF00);
            button3.setBackgroundColor(0xFFFF0000);
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button1.getText().toString().equals(button4.getText().toString()) && button1.getText().toString().equals(button7.getText().toString()) && !button1.getText().toString().equals("")) {
            button1.setTextColor(0xFF00FF00);
            button1.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button7.setTextColor(0xFF00FF00);
            button7.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button2.getText().toString().equals(button5.getText().toString()) && button2.getText().toString().equals(button8.getText().toString()) && !button2.getText().toString().equals("")) {
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            button5.setTextColor(0xFF00FF00);
            button5.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button0.getText().toString().equals(button4.getText().toString()) && button0.getText().toString().equals(button8.getText().toString()) && !button0.getText().toString().equals("")) {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button2.getText().toString().equals(button4.getText().toString()) && button2.getText().toString().equals(button6.getText().toString()) && !button2.getText().toString().equals("")) {
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            add_point();
        }
        else {
            if (!button0.getText().toString().isEmpty() && !button1.getText().toString().isEmpty() && !button2.getText().toString().isEmpty() && !button3.getText().toString().isEmpty() && !button4.getText().toString().isEmpty() && !button5.getText().toString().isEmpty() && !button6.getText().toString().isEmpty() && !button7.getText().toString().isEmpty() && !button8.getText().toString().isEmpty()) {
                show_new_game_button();
            }
        }


    }
}
