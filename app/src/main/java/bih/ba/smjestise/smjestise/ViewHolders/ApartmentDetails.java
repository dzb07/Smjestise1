package bih.ba.smjestise.smjestise.ViewHolders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.R;


public class ApartmentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_details);

        ImageView imageView = (ImageView) findViewById(R.id.imageAdDetails);
        TextView textView = (TextView) findViewById(R.id.textAdDetails);
        TextView textDesc = (TextView) findViewById(R.id.descriptionAdDetails);

        if (getIntent().hasExtra("ad")) {
            Apartments ad = (Apartments) getIntent().getSerializableExtra("ad");

                textView.setText(ad.prop_name);
                textDesc.setText(ad.host_city);


            Glide.with(imageView.getContext()).load(ad.urlImage1).into(imageView);
        }
    }
}