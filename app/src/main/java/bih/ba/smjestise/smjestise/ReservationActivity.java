package bih.ba.smjestise.smjestise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;

public class ReservationActivity extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    Button checkin_reservation;
    Button checkout_reservation;
    TextView intro;
    TextView pricePay;
    Integer price_to_pay;
    Integer price_per_night;
    private Button confirmReservation;
    private EditText f_name;
    private EditText l_name;
    private EditText emailAddress;
    private EditText userAddress;
    private EditText userCity;
    private EditText userPhoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        TextView ap=(TextView) findViewById(R.id.apartman_za_rez);
        final ArrayList<ReservationClass> reservation = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        final GlobalVars globalVariable_prop_name = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars globalVariable_checkin= (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars globalVariable_checkout = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars t1 = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars t2 = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars propName = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars dayDiff = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars currency = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars hostCity = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars fr_name = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars ls_name = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars emailadd = (GlobalVars) getApplicationContext(); //make a accessing point




        /*getting total amount needed to be payed*/
        Bundle bundle = getIntent().getExtras();
        price_per_night=bundle.getInt("price");
        int num_of_days=(int) dayDiff.getDays_difference();
        price_to_pay=price_per_night*num_of_days;
        /*end of getting total amount needed to be payed*/
        checkin_reservation=(Button)findViewById(R.id.checkin_reservation);
        checkout_reservation=(Button)findViewById(R.id.checkout_reservation);
        checkin_reservation.setText("Check in:\n"+globalVariable_checkin.getCheckIN());
        checkout_reservation.setText("Check out:\n"+globalVariable_checkout.getCheckOUT());
        intro=(TextView)findViewById(R.id.det);
        if(dayDiff.getDays_difference()>1) {
            intro.setText("You chose to stay " + dayDiff.getDays_difference() + " nights." +
                    " Please fill in details below in order to finish reservation");
        }
        else{
            intro.setText("You chose to stay " + dayDiff.getDays_difference() + " night. Please fill in details below in order to finish reservation.");

        }


        pricePay=(TextView)findViewById(R.id.price_to_pay01);
        pricePay.setText("Total price: "+price_to_pay+ currency.getCurrency());
        final String p_name=globalVariable_prop_name.getProperty_name();
        /*sending data to ReservationClass*/


        /*end of sending data to ReservationClass*/

        ap.setText(p_name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       final DatabaseReference databaseReference = database.getReference();
        //access button to reserve//
        confirmReservation=(Button)findViewById(R.id.confirm_reservation);
        confirmReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*accessing values of edittext fields*/
                f_name=(EditText) findViewById(R.id.f_name);
                String f_name_value=f_name.getText().toString();

                l_name=(EditText) findViewById(R.id.l_name);
                String l_name_value=l_name.getText().toString();

                emailAddress=(EditText) findViewById(R.id.email_add);
                String emailAddress_value=emailAddress.getText().toString();

                userAddress=(EditText) findViewById(R.id.user1_address);
                String userAddress_value=userAddress.getText().toString();

                userPhoneNumber=(EditText) findViewById(R.id.user1_mob_phone);
                String userPhoneNumber_value=userPhoneNumber.getText().toString();

                userCity=(EditText) findViewById(R.id.user1_city);
                String userCity_value=userCity.getText().toString();
                reservation.add(new ReservationClass(p_name,true,globalVariable_checkin.getCheckIN(),
                        globalVariable_checkout.getCheckOUT(),
                        t1.getT1(),t2.getT2(),hostCity.getHostcity(),f_name_value,l_name_value,emailAddress_value,
                        userAddress_value,userCity_value,userPhoneNumber_value));

                String mGroupId = databaseReference.push().getKey();
                /*store data into Reserved Node*/
                for (int i = 0; i < reservation.size(); i++) {
                    databaseReference.child("Reserved/"+propName.getProperty_name()).push().setValue(reservation.get(i));
                }

                /*store data into UserReservations Node*/
                for (int i = 0; i < reservation.size(); i++) {
                    databaseReference.child("UserReservations").push().setValue(reservation.get(i));
                }
                fr_name.setFirst_name(f_name_value);
                ls_name.setLast_name(l_name_value);
                emailadd.setEmail(emailAddress_value);


                Intent intentFinal=new Intent(ReservationActivity.this,FinalActivity01.class);
                startActivity(intentFinal);
            }

        });



    }


}

