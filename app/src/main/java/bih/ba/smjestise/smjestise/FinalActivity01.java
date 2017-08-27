package bih.ba.smjestise.smjestise;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class FinalActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final01);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        /*making automatcik redirect*/
        int timeout = 4000; // make the activity visible for 4 seconds

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(FinalActivity01.this, MainActivity.class);
                startActivity(homepage);
            }
        }, timeout);

    }
    //method for back button in action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}

