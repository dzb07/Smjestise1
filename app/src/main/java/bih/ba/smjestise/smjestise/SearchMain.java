package bih.ba.smjestise.smjestise;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import java.net.DatagramPacket;
import java.util.Locale;

public class SearchMain extends AppCompatActivity {

    /*my variables*/
    private DatePickerDialog.OnDateSetListener mDateSetListener_in;
    private DatePickerDialog.OnDateSetListener mDateSetListener_out;

    private static final String TAG = "Hotels";
    private Button buttoncheckIn;
    private Button buttoncheckOut;
    private CheckBox checkBox;
    Button search;
    Context context;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener date2;

    /*end of myvariables*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        /*on search button open new activity*/
        search=(Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                Intent intLeft = new Intent(SearchMain.this, SearchResults.class);
                startActivity(intLeft);
            }
        });
        buttoncheckIn= (Button) findViewById(R.id.check_in);
        buttoncheckOut= (Button) findViewById(R.id.check_out);
        checkBox= (CheckBox) findViewById(R.id.checkbox_dates);
        if (checkBox == null) { Log.w("", "TextView is null"); }
        context=getApplicationContext();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    buttoncheckIn.setVisibility(View.GONE);
                    buttoncheckOut.setVisibility(View.GONE);
                }

                else{
                    buttoncheckIn.setVisibility(View.VISIBLE);
                    buttoncheckOut.setVisibility(View.VISIBLE);
                }

            }
        });

         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        buttoncheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }
        };
        buttoncheckOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchMain.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckIn.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckOut.setText(sdf.format(myCalendar.getTime()));
    }
}
