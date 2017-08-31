package bih.ba.smjestise.smjestise;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Payment extends AppCompatActivity {

    TextView m_response;
    PayPalConfiguration m_configuration;
    String m_payPalclientId="AaSwJ4Vmy8eKR00FKC1SS712kpKOMxX6tPenRfYCWGs9VKFmkGBQsQuSPgyNaeGXxDdbm6Ajkhs-pigS"; //id is link to paypal account
    Intent m_service;
    int m_paypalRequestCode=999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        m_response=(TextView) findViewById(R.id.response);
        m_configuration=new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)//sendbox for test, production for real
                .clientId(m_payPalclientId);


        m_service=new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration); //configuration above
        startService(m_service); //paypal service, listening to call of paypal app
    }

    public void pay(View view){
        PayPalPayment payment=new PayPalPayment(new BigDecimal(10), "EUR", "Test paypal",
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
                    //if payment worked
                    m_response.setText("Payment approved");
                }
                else{
                    m_response.setText("Payment not approved");

                }
            }

        }

    }
}
