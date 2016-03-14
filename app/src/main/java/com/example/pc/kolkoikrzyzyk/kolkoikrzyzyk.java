package com.example.pc.kolkoikrzyzyk;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolkoikrzyzyk);
        reset_game();
        hide_new_game_button();

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View v) {
        Button button=(Button) v;
        button.setText(player_char);
        button.setClickable(false);

        check_winner();
        change_player();
    }

    public void buttonOnClick_new_game(View v) {
// do something when the button is clicked
        new_game();
    }

    public void new_game(){
        player_char="o";
        player=1;
        change_player_anouce();

        Button button0=(Button)findViewById(R.id.button_p_0);
        Button button1=(Button)findViewById(R.id.button_p_1);
        Button button2=(Button)findViewById(R.id.button_p_2);
        Button button3=(Button)findViewById(R.id.button_p_3);
        Button button4=(Button)findViewById(R.id.button_p_4);
        Button button5=(Button)findViewById(R.id.button_p_5);
        Button button6=(Button)findViewById(R.id.button_p_6);
        Button button7=(Button)findViewById(R.id.button_p_7);
        Button button8=(Button)findViewById(R.id.button_p_8);

        button0.setText("");
        button0.setClickable(true);
        button0.setTextColor(0xFFFFFFFF);
        button0.setBackgroundColor(0xFFC3FF47);

        button1.setText("");
        button1.setClickable(true);
        button1.setTextColor(0xFFFFFFFF);
        button1.setBackgroundColor(0xFFC3FF47);

        button2.setText("");
        button2.setClickable(true);
        button2.setTextColor(0xFFFFFFFF);
        button2.setBackgroundColor(0xFFC3FF47);

        button3.setText("");
        button3.setClickable(true);
        button3.setTextColor(0xFFFFFFFF);
        button3.setBackgroundColor(0xFFC3FF47);

        button4.setText("");
        button4.setClickable(true);
        button4.setTextColor(0xFFFFFFFF);
        button4.setBackgroundColor(0xFFC3FF47);

        button5.setText("");
        button5.setClickable(true);
        button5.setTextColor(0xFFFFFFFF);
        button5.setBackgroundColor(0xFFC3FF47);

        button6.setText("");
        button6.setClickable(true);
        button6.setTextColor(0xFFFFFFFF);
        button6.setBackgroundColor(0xFFC3FF47);

        button7.setText("");
        button7.setClickable(true);
        button7.setTextColor(0xFFFFFFFF);
        button7.setBackgroundColor(0xFFC3FF47);

        button8.setText("");
        button8.setClickable(true);
        button8.setTextColor(0xFFFFFFFF);
        button8.setBackgroundColor(0xFFC3FF47);
        hide_new_game_button();
    }

public void block_table() {
    Button button0=(Button)findViewById(R.id.button_p_0);
    Button button1=(Button)findViewById(R.id.button_p_1);
    Button button2=(Button)findViewById(R.id.button_p_2);
    Button button3=(Button)findViewById(R.id.button_p_3);
    Button button4=(Button)findViewById(R.id.button_p_4);
    Button button5=(Button)findViewById(R.id.button_p_5);
    Button button6=(Button)findViewById(R.id.button_p_6);
    Button button7=(Button)findViewById(R.id.button_p_7);
    Button button8=(Button)findViewById(R.id.button_p_8);

    button0.setClickable(false);
    button1.setClickable(false);
    button2.setClickable(false);
    button3.setClickable(false);
    button4.setClickable(false);
    button5.setClickable(false);
    button6.setClickable(false);
    button7.setClickable(false);
    button8.setClickable(false);

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
        score_player_1=0;
        score_player_2=0;
        new_game();
        reload_score();
    }

    public void reload_score(){
        TextView sc_player_1 = (TextView)findViewById(R.id.score_pl_1);
        sc_player_1.setText(Integer.toString(score_player_1));
        TextView sc_player_2 = (TextView)findViewById(R.id.score_pl_2);
        sc_player_2.setText(Integer.toString(score_player_2));
    }

    public void add_point(){
        if ( player == 1 ) {
            score_player_1++;
        }
        else{
            score_player_2++;
        }
        String player_won=getResources().getString(R.string.player_won);
        Toast.makeText(getApplicationContext(), player_won + player,
                Toast.LENGTH_LONG).show();
        block_table();
        reload_score();
        show_new_game_button();
    }
    public void hide_new_game_button(){
        Button button_new_game=(Button)findViewById(R.id.button);
        button_new_game.setVisibility(View.GONE);
    }
    public void show_new_game_button(){
        Button button_new_game=(Button)findViewById(R.id.button);
        button_new_game.setVisibility(View.VISIBLE);
    }
    public void check_winner(){
        Button button0=(Button)findViewById(R.id.button_p_0);
        Button button1=(Button)findViewById(R.id.button_p_1);
        Button button2=(Button)findViewById(R.id.button_p_2);
        Button button3=(Button)findViewById(R.id.button_p_3);
        Button button4=(Button)findViewById(R.id.button_p_4);
        Button button5=(Button)findViewById(R.id.button_p_5);
        Button button6=(Button)findViewById(R.id.button_p_6);
        Button button7=(Button)findViewById(R.id.button_p_7);
        Button button8=(Button)findViewById(R.id.button_p_8);

        if (button0.getText().toString() == button1.getText().toString() && button0.getText().toString() == button2.getText().toString() && button0.getText().toString() !="") {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button1.setTextColor(0xFF00FF00);
            button1.setBackgroundColor(0xFFFF0000);
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button3.getText().toString() == button4.getText().toString() && button3.getText().toString() == button5.getText().toString() && button3.getText().toString() !="") {
            button3.setTextColor(0xFF00FF00);
            button3.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button5.setTextColor(0xFF00FF00);
            button5.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button6.getText().toString() == button7.getText().toString() && button6.getText().toString() == button8.getText().toString() && button6.getText().toString() !="") {
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            button7.setTextColor(0xFF00FF00);
            button7.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button0.getText().toString() == button3.getText().toString() && button0.getText().toString() == button6.getText().toString() && button0.getText().toString() !="") {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button3.setTextColor(0xFF00FF00);
            button3.setBackgroundColor(0xFFFF0000);
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button1.getText().toString() == button4.getText().toString() && button1.getText().toString() == button7.getText().toString() && button1.getText().toString() !="") {
            button1.setTextColor(0xFF00FF00);
            button1.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button7.setTextColor(0xFF00FF00);
            button7.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button2.getText().toString() == button5.getText().toString() && button2.getText().toString() == button8.getText().toString() && button2.getText().toString() !="") {
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            button5.setTextColor(0xFF00FF00);
            button5.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button0.getText().toString() == button4.getText().toString() && button0.getText().toString() == button8.getText().toString() && button0.getText().toString() !="") {
            button0.setTextColor(0xFF00FF00);
            button0.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button8.setTextColor(0xFF00FF00);
            button8.setBackgroundColor(0xFFFF0000);
            add_point();
        }

        else if (button2.getText().toString() == button4.getText().toString() && button2.getText().toString() == button6.getText().toString() && button2.getText().toString() !="") {
            button2.setTextColor(0xFF00FF00);
            button2.setBackgroundColor(0xFFFF0000);
            button4.setTextColor(0xFF00FF00);
            button4.setBackgroundColor(0xFFFF0000);
            button6.setTextColor(0xFF00FF00);
            button6.setBackgroundColor(0xFFFF0000);
            add_point();
        }
        else {
            if (button0.getText().toString() !="" && button1.getText().toString() !="" && button2.getText().toString() !="" && button3.getText().toString() !="" && button4.getText().toString() !="" && button5.getText().toString() !="" && button6.getText().toString() !="" && button7.getText().toString() !="" && button8.getText().toString() !="") {
                show_new_game_button();
            }
        }


    }
}
