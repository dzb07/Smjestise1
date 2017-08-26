package bih.ba.smjestise.smjestise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;

public class ReservationActivity extends AppCompatActivity {
    String plate,a,slot,q,w,e,r;
    String l,pol="null";

    Calendar c = Calendar.getInstance();
    String sDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
    int shour=c.get(Calendar.HOUR_OF_DAY);
    int smin =c.get(Calendar.MINUTE);
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ArrayList<ReservationClass> reservation = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        final GlobalVars globalVariable_prop_name = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars globalVariable_checkin= (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars globalVariable_checkout = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars t1 = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars t2 = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars propName = (GlobalVars) getApplicationContext(); //make a accessing point






        String p_name=globalVariable_prop_name.getProperty_name();

        reservation.add(new ReservationClass("Zenica",p_name,true,globalVariable_checkin.getCheckIN(),globalVariable_checkout.getCheckOUT(),
        t1.getT1(),t2.getT2() ));


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        String mGroupId = databaseReference.push().getKey();
        for (int i = 0; i < reservation.size(); i++) {
            databaseReference.child("Reserved/"+propName.getProperty_name()).push().setValue(reservation.get(i));
        }

    }


}

