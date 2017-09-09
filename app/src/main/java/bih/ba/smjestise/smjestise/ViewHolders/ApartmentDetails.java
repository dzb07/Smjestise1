package bih.ba.smjestise.smjestise.ViewHolders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.android.gms.vision.text.Line;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.CommentsAdapter;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.Comments;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;
import bih.ba.smjestise.smjestise.Helpers.SavedApartments;
import bih.ba.smjestise.smjestise.Helpers.UserReservationsAdapter;
import bih.ba.smjestise.smjestise.LeaveReview;
import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ReservationActivity;


public class ApartmentDetails extends  AppCompatActivity implements BaseSliderView.OnSliderClickListener, OnMapReadyCallback,
        ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;
    Apartments ad;
    HashMap<String, String> HashMapForURL;
    private double lat;
    private double longitude;
    HashMap<String, Integer> HashMapForLocalRes;
    private FirebaseAuth firebaseAuth;
    String userID;
    private Button reserveButton;
    private TextView ap_desc;
    private Button saveButton;
    private Button leaveReview;
    private LinearLayout commentsTOTAL;
    final ArrayList<Comments> ads = new ArrayList<Comments>();

    /*for displaying comments*/
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    CommentsAdapter mAdAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_details);

        final GlobalVars globalVariable = (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars globalVariable01 = (GlobalVars) getApplicationContext(); //make a accessing point


        final TextView property_name = (TextView) findViewById(R.id.property_name);
        TextView property_city = (TextView) findViewById(R.id.property_city);
        TextView property_address = (TextView) findViewById(R.id.property_address);
        ImageView perks_wifi= (ImageView)findViewById(R.id.perks_wifi);
        ImageView perks_pets= (ImageView)findViewById(R.id.perks_pets);
        ImageView perks_parking= (ImageView)findViewById(R.id.perks_parking);
        ImageView perks_breakfast= (ImageView)findViewById(R.id.perks_breakfast);
        ImageView perks_kitchen= (ImageView)findViewById(R.id.perks_kitchen);
        ImageView perks_bathroom= (ImageView)findViewById(R.id.perks_child_friendly);
        ImageView perks_child_friendly=(ImageView)findViewById(R.id.perks_child_friendly);
        ImageView perks_smoking=(ImageView)findViewById(R.id.perks_smoking);
        commentsTOTAL=(LinearLayout) findViewById(R.id.commentsTOTAL);
        saveButton=(Button) findViewById(R.id.save);
        ap_desc=(TextView) findViewById(R.id.ap_desc);
        reserveButton=(Button) findViewById(R.id.reserve);
        leaveReview=(Button) findViewById(R.id.writeComment);
        ap_desc.setMaxLines(3);
        userID= firebaseAuth.getInstance().getCurrentUser().getUid();
       // seeAllComments=(LinearLayout)findViewById(R.id.seeAllComments);

        /*RETRIEVE comments from database*/

                FirebaseDatabase.getInstance().getReference().child("PropertyRatings")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(globalVariable.getProperty_name())) {
                                    Iterable<DataSnapshot> children = dataSnapshot.child(globalVariable.getProperty_name()).getChildren();

                                    for (DataSnapshot child : children) {

                                        Comments r = child.getValue(Comments.class);
                                        //globalVariable_num_of_rooms.getNumOfRoomsVar()


                                        ads.add(r);
                                        mAdAdapter.notifyDataSetChanged();


                                    }
                                }
                                else{
                                    commentsTOTAL.setVisibility(View.GONE);
                                }

                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                mAdAdapter = new CommentsAdapter(ads);


                mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView04);
                mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

                mRecyclerView.setAdapter(mAdAdapter);


        /*end of retrieving comments from database*/


        /*open comment section*/


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();
        FirebaseDatabase.getInstance().getReference().child("Reserved").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(globalVariable.getProperty_name())) {
                    Iterable<DataSnapshot> children = dataSnapshot.child(globalVariable.getProperty_name()).getChildren();
                    for (DataSnapshot child : children) {
                        ReservationClass reserved = child.getValue(ReservationClass.class);
                        Comments r = child.getValue(Comments.class);

                        if (reserved.getUserID().equals(userID)&& !r.getUserID().equals(userID)) {
                            leaveReview.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else {
                    leaveReview.setVisibility(View.GONE);
                }


            }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




        leaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ApartmentDetails.this, LeaveReview.class);
                startActivity(i);

            }

        });

        final ArrayList<SavedApartments> savedApartments = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        if (getIntent().hasExtra("ad")) {
            final Apartments ad = (Apartments) getIntent().getSerializableExtra("ad");
            final GlobalVars globalVariable_prop_name = (GlobalVars) getApplicationContext(); //make a accessing point
            final GlobalVars currency = (GlobalVars) getApplicationContext(); //make a accessing point

            ap_desc.setText(ad.getAp_desc());
            globalVariable_prop_name.setProperty_name(ad.prop_name);//set property name of global variable
            getSupportActionBar().setTitle(ad.prop_name);
            lat= Double.parseDouble(ad.lat);
            longitude= Double.parseDouble(ad.longitude);
            property_name.setText(ad.prop_name);
            property_city.setText(ad.host_city);
            property_address.setText(ad.host_street);
            /*check for wifi*/
            if(ad.wifi.equals("yes")){
                perks_wifi.setVisibility(View.VISIBLE);
            }
            else{
                perks_wifi.setVisibility(View.GONE);

            }
           // check for parking
            if(ad.parking.equals("yes")){
                perks_parking.setVisibility(View.VISIBLE);
            }
            else{
                perks_parking.setVisibility(View.GONE);

            }
            //check for pets
            if(ad.pets.equals("yes")){
                perks_pets.setVisibility(View.VISIBLE);
            }
            else{
                perks_pets.setVisibility(View.GONE);

            }
            //check for breakfast
            if(ad.breakfast.equals("yes")){
                perks_breakfast.setVisibility(View.VISIBLE);
            }
            else{
                perks_breakfast.setVisibility(View.GONE);

            }
            //check for bathroom
            if(ad.bathroom.equals("yes")){
                perks_bathroom.setVisibility(View.VISIBLE);
            }
            else{
                perks_bathroom.setVisibility(View.GONE);

            }
            //check for bathroom
            if(ad.pets.equals("yes")){
                perks_pets.setVisibility(View.VISIBLE);
            }
            else{
                perks_pets.setVisibility(View.GONE);

            }
            //check for child friendly
            if(ad.child_friendly.equals("yes")){
                perks_child_friendly.setVisibility(View.VISIBLE);
            }
            else{
                perks_child_friendly.setVisibility(View.GONE);

            }
            //check for smoking
            if(ad.smoking_allowed.equals("yes")){
                perks_smoking.setVisibility(View.VISIBLE);
            }
            else{
                perks_smoking.setVisibility(View.GONE);

            }
        /*on reserve button click*/
            reserveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ApartmentDetails.this, ReservationActivity.class);
                    if (currency.getCurrency().equals("EUR"))
                        intent.putExtra("price",ad.getPrice_eur());
                    else
                        intent.putExtra("price",ad.getPrice());
                    intent.putExtra("cancellation", ad.getCancellation());
                    startActivity(intent);
                }
            });
        /*end reserve button*/
            final GlobalVars propName = (GlobalVars) getApplicationContext(); //make a accessing point
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference databaseReference = database.getReference();
                    savedApartments.add(new SavedApartments(firebaseAuth.getInstance().getCurrentUser().getUid(),propName.getProperty_name(),propName.getHostcity(),System.currentTimeMillis()));
                    for (int i = 0; i < savedApartments.size(); i++) {
                        databaseReference.child("SavedApartments/"+firebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(savedApartments.get(i));
                    }

                    /*set current user's id to globalvar */
                    propName.setCurrentUser(firebaseAuth.getInstance().getCurrentUser().getUid());
                    final SavedApartments a = new SavedApartments(); //make a accessing point
                    //date when xou saved apartment
                    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                    cal.setTimeInMillis(System.currentTimeMillis());
                    String date = DateFormat.format("dd-MM-yyyy", cal).toString();

                    Toast.makeText(getApplicationContext(),"You saved "+propName.getProperty_name(),Toast.LENGTH_LONG).show();

                }
            });


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







    }
    /*method to open read more of hotel description*/
    public void onClick(View v) {

        ap_desc.setMaxLines(Integer.MAX_VALUE);
    }


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}