package bih.ba.smjestise.smjestise.ViewHolders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;
import java.util.HashMap;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ReservationActivity;


public class ApartmentDetails extends FragmentActivity implements BaseSliderView.OnSliderClickListener, OnMapReadyCallback,
        ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;
    Apartments ad;
    HashMap<String, String> HashMapForURL;
    private double lat;
    private double longitude;
    HashMap<String, Integer> HashMapForLocalRes;
    private ImageView wifi_image;
    private ImageView parking_image;
    private ImageView pets_image;
    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_details);

        TextView property_name = (TextView) findViewById(R.id.property_name);
        TextView property_city = (TextView) findViewById(R.id.property_city);
        TextView property_address = (TextView) findViewById(R.id.property_address);
        wifi_image=(ImageView) findViewById(R.id.wifi) ;
        parking_image=(ImageView) findViewById(R.id.parking);
        pets_image=(ImageView) findViewById(R.id.pets);
        reserveButton=(Button) findViewById(R.id.reserve);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        if (getIntent().hasExtra("ad")) {
            Apartments ad = (Apartments) getIntent().getSerializableExtra("ad");
            final GlobalVars globalVariable_prop_name = (GlobalVars) getApplicationContext(); //make a accessing point

            globalVariable_prop_name.setProperty_name(ad.prop_name);//set property name of global variable
            lat= Double.parseDouble(ad.lat);
            longitude= Double.parseDouble(ad.longitude);
            property_name.setText(ad.prop_name);
            property_city.setText(ad.host_city);
            property_address.setText(ad.host_street);
            /*check for wifi*/
            if(ad.wifi.equals("yes")){
                wifi_image.setVisibility(View.VISIBLE);
            }
            else{
                wifi_image.setVisibility(View.GONE);

            }
            /*check for parking*/
            if(ad.parking.equals("yes")){
                parking_image.setVisibility(View.VISIBLE);
            }
            else{
                parking_image.setVisibility(View.GONE);

            }
            /*check for pets*/
            if(ad.pets.equals("yes")){
                pets_image.setVisibility(View.VISIBLE);
            }
            else{
                pets_image.setVisibility(View.GONE);

            }




        }

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        Apartments ad = (Apartments) getIntent().getSerializableExtra("ad");
    //Call this method if you want to add images from URL .
        HashMapForURL = new HashMap<String, String>();

        HashMapForURL.put("1",ad.urlImage1 );
        HashMapForURL.put("2",ad.urlImage2 );
        HashMapForURL.put("3",ad.urlImage3 );
        HashMapForURL.put("4",ad.urlImage4 );
        HashMapForURL.put("5",ad.urlImage5 );
        HashMapForURL.put("6",ad.urlImage6 );
        HashMapForURL.put("7",ad.urlImage7 );
        HashMapForURL.put("8",ad.urlImage8 );
        HashMapForURL.put("9",ad.urlImage9 );
        HashMapForURL.put("10",ad.urlImage10 );



    //Call this method to add images from local drawable folder .
    //AddImageUrlFormLocalRes();

    //Call this method to stop automatic sliding.
    //sliderLayout.stopAutoCycle();

        for (String name : HashMapForURL.keySet()) {

        TextSliderView textSliderView = new TextSliderView(ApartmentDetails.this);

        textSliderView
                .description(name)
                .image(HashMapForURL.get(name))
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);

        textSliderView.bundle(new Bundle());

        textSliderView.getBundle()
                .putString("extra", name);

        sliderLayout.addSlider(textSliderView);
    }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(this);




        /*on reserve button click*/
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ApartmentDetails.this, ReservationActivity.class);
                startActivity(intent);
            }
        });
}       /*end reserve button*/

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.

        LatLng sydney = new LatLng(lat,longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Your destination"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}