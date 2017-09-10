package bih.ba.smjestise.smjestise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    private FirebaseAuth firebaseAuth1;
    private String userID;
    private String cancellation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        userID= firebaseAuth1.getInstance().getCurrentUser().getUid(); //get current user ID

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
        cancellation=bundle.getString("cancellation");
        int num_of_days=(int) dayDiff.getDays_difference();
        price_to_pay=price_per_night*num_of_days;
        /*end of getting total amount needed to be payed*/
        checkin_reservation=(Button)findViewById(R.id.checkin_reservation);
        checkout_reservation=(Button)findViewById(R.id.checkout_reservation);
        checkin_reservation.setText("Check in:\n"+globalVariable_checkin.getCheckIN());
        checkout_reservation.setText("Check out:\n"+globalVariable_checkout.getCheckOUT());
        intro=(TextView)findViewById(R.id.det);
        if(Locale.getDefault().getLanguage().equals("hr") || Locale.getDefault().getLanguage().equals("bs"))
        {
            if(dayDiff.getDays_difference()>1) {
                intro.setText("Odabrali ste " + dayDiff.getDays_difference() + " noćenja." +
                        " Molimo popunite potrebne podatke da potvrdite rezervaciju..\n *Sva polja su obavezna!");
            }
            else{
                intro.setText("Odabrali ste" + dayDiff.getDays_difference() + " noćenje. Molimo popunite potrebne podatke da potvrdite rezervaciju." +
                        "\n *Sva polja su obavezna!");

            }
        }
        else {
            if(dayDiff.getDays_difference()>1) {
                intro.setText("You chose to stay " + dayDiff.getDays_difference() + " nights." +
                        " Please fill in details below in order to finish reservation.\n *All fields are required!");
            }
            else{
                intro.setText("You chose to stay " + dayDiff.getDays_difference() + " night. Please fill in details below in order to finish reservation." +
                        "\n *All fields are required!");

            }
        }



        pricePay=(TextView)findViewById(R.id.price_to_pay01);
        if(Locale.getDefault().getLanguage().equals("hr") || Locale.getDefault().getLanguage().equals("bs"))
        {
            pricePay.setText("Ukupna cijena: "+price_to_pay+ currency.getCurrency());
        }
        else{
            pricePay.setText("Total price: "+price_to_pay+ currency.getCurrency());

        }
        final String p_name=globalVariable_prop_name.getProperty_name();

        /*sending data to ReservationClass*/
        final String totalPrice=price_to_pay+currency.getCurrency(); //send total price to pay with currency in form of string

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

                /*checking if data is entered*/
                if(TextUtils.isEmpty(f_name_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your first name!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                if(TextUtils.isEmpty(l_name_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your last name!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                if(TextUtils.isEmpty(emailAddress_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your email address!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                if(TextUtils.isEmpty(userAddress_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your address!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                if(TextUtils.isEmpty(userPhoneNumber_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your phone number!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                if(TextUtils.isEmpty(userCity_value)){
                    Toast.makeText(ReservationActivity.this,"Please enter your city!",Toast.LENGTH_LONG).show();
                    //stopping the function of exectuing further
                    return;
                }
                /*end of checking if data is entered*/
                reservation.add(new ReservationClass(p_name,true,globalVariable_checkin.getCheckIN(),
                        globalVariable_checkout.getCheckOUT(),
                        t1.getT1(),t2.getT2(),hostCity.getHostcity(),f_name_value,l_name_value,emailAddress_value,
                        userAddress_value,userCity_value,userPhoneNumber_value,totalPrice,System.currentTimeMillis(),userID));

                String mGroupId = databaseReference.push().getKey();
                /*store data into Reserved Node*/

                fr_name.setFirst_name(f_name_value);
                ls_name.setLast_name(l_name_value);
                emailadd.setEmail(emailAddress_value);
                emailadd.setUserPhoneNumber(userPhoneNumber_value);
                emailadd.setTotalPriceToPay(totalPrice);
                emailadd.setUserAddress(userAddress_value);
                emailadd.setUserCity(userCity_value);

                if(cancellation.equals("yes")){
                    for (int i = 0; i < reservation.size(); i++) {
                        databaseReference.child("Reserved/"+propName.getProperty_name()).push().setValue(reservation.get(i));
                    }

                /*store data into UserReservations Node*/
                    for (int i = 0; i < reservation.size(); i++) {
                        databaseReference.child("UserReservations/"+userID).push().setValue(reservation.get(i));
                    }
                    Intent intentFinal = new Intent(ReservationActivity.this, FinalActivity01.class);
                    startActivity(intentFinal);
                }
                else {

                    Intent intentPayement=new Intent(ReservationActivity.this,Payment.class);
                    intentPayement.putExtra("price_to_pay",price_to_pay);
                    intentPayement.putExtra("getCurrency",currency.getCurrency());
                    startActivity(intentPayement);
                }
            }

        });


        /*get current date and send it to global var current date as string*/
        //for accessing *reservation made on*/


        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(System.currentTimeMillis());
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        propName.setCurrent_date(date);





    }


}

