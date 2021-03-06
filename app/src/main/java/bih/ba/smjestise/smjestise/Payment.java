package bih.ba.smjestise.smjestise;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.ArrayList;

import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;

public class Payment extends AppCompatActivity {

    TextView m_response;
    PayPalConfiguration m_configuration;
    String m_payPalclientId="AaSwJ4Vmy8eKR00FKC1SS712kpKOMxX6tPenRfYCWGs9VKFmkGBQsQuSPgyNaeGXxDdbm6Ajkhs-pigS"; //id is link to paypal account
    Intent m_service;
    int m_paypalRequestCode=999;
    Integer price_to_pay;
    private String currency;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth1;
    private String userID;

    final DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        m_response=(TextView) findViewById(R.id.response);
        m_configuration=new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)//sendbox for test, production for real
                .clientId(m_payPalclientId);


        /*get price to pay*/
        Bundle bundle = getIntent().getExtras();
        price_to_pay=bundle.getInt("price_to_pay");
        currency=bundle.getString("getCurrency");

        Toast.makeText(Payment.this, price_to_pay.toString()+currency,Toast.LENGTH_LONG).show();
        m_service=new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration); //configuration above
        startService(m_service); //paypal service, listening to call of paypal app
    }

    public void pay(View view){
        PayPalPayment payment=new PayPalPayment(new BigDecimal(price_to_pay), currency, "Payment:",
                PayPalPayment.PAYMENT_INTENT_AUTHORIZE);

        Intent intent=new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent,m_paypalRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==m_paypalRequestCode){
            if(resultCode== Activity.RESULT_OK){

                //confirm that payment worked in order to avoid fraud
                PaymentConfirmation confirm=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirm!=null && confirm.getProofOfPayment().getState().equals("approved")){
                    userID= firebaseAuth1.getInstance().getCurrentUser().getUid(); //get current user ID
                    final GlobalVars accessVar = (GlobalVars) getApplicationContext(); //make a accessing point
                    final ArrayList<ReservationClass> reservation = new ArrayList<>();
                    reservation.add(new ReservationClass(accessVar.getProperty_name(),Boolean.TRUE,accessVar.getCheckIN(),accessVar.getCheckOUT(),
                            accessVar.getT1(),accessVar.getT2(),accessVar.getHostcity(),accessVar.getFirst_name(),
                            accessVar.getLast_name(),accessVar.getEmail(),accessVar.getUserAddress(),accessVar.getUserCity(),
                            accessVar.getUserPhoneNumber(),accessVar.getTotalPriceToPay(),System.currentTimeMillis(),userID));

                    for (int i = 0; i < reservation.size(); i++) {
                        databaseReference.child("Reserved/"+accessVar.getProperty_name()).push().setValue(reservation.get(i));
                    }

                /*store data into UserReservations Node*/
                    for (int i = 0; i < reservation.size(); i++) {
                        databaseReference.child("UserReservations/"+userID).push().setValue(reservation.get(i));
                    }
                    //if payment worked
                    Intent i=new Intent(this,ActivityAfterPayment.class);
                    startActivity(i);

                }
                else{



                }
            }

        }

    }
}
