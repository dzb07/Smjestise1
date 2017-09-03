package bih.ba.smjestise.smjestise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.Comments;

import static android.widget.Toast.LENGTH_LONG;

public class LeaveReview extends AppCompatActivity {

    private RatingBar ratingOfProperty;
    private EditText commentOnProperty;
    private EditText username;
    private Button submitReview;
    final ArrayList<Comments> rateObject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        username=(EditText)findViewById(R.id.username);
        ratingOfProperty=(RatingBar)findViewById(R.id.ratingOfProperty);
        commentOnProperty=(EditText)findViewById(R.id.commentOnProperty);
        submitReview=(Button) findViewById(R.id.submitReview);
        final GlobalVars globalPoint = (GlobalVars) getApplicationContext(); //make a accessing point

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rate=ratingOfProperty.getRating();
                String komentar=commentOnProperty.getText().toString();
                String nameOfUser=username.getText().toString();
                Toast toast = Toast.makeText(LeaveReview.this, "Your rate is "+rate,LENGTH_LONG);
                toast.show();
                rateObject.add(new Comments(nameOfUser,komentar,rate));


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                for (int i = 0; i < rateObject.size(); i++) {
                    databaseReference.child("PropertyRatings/"+globalPoint.getProperty_name()).push().setValue(rateObject.get(i));
                }

                Intent i=new Intent(LeaveReview.this, ActivityAfterReview.class);
                startActivity(i);

            }
        });

    }
}
