package bih.ba.smjestise.smjestise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();


        /*check if user is already logged in*/

        if(firebaseAuth.getCurrentUser()!=null){
            //start profile activity, meaning main activity
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }
        buttonSignin=(Button)findViewById(R.id.buttonSignin);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignup=(TextView)findViewById(R.id.textViewSignup);

        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }


    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        //check if email is empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            //stopping the function of exectuing further
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            //stopping the function of exectuing further
            return;
        }
        progressDialog.setMessage("Log in...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    //successfully registered
                    //we will start profile activty here
                    finish();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }

            }
        });
    }
    @Override
    public void  onClick(View view){
        if(view==buttonSignin){
            userLogin();
        }
        if(view==textViewSignup){
            finish();//to close this activity
            startActivity(new Intent(this, Signiup.class));
        }
    }
}
