package bih.ba.smjestise.smjestise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.Helpers.ApartmentAdapter;
import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;


public class SearchResults extends AppCompatActivity  {
    private static SearchResults instance = null;
    final ArrayList<Apartments> ads = new ArrayList<Apartments>();
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    ApartmentAdapter mAdAdapter;
    Integer reserved=0;
    String host_city;
    Boolean controller=false;
    final ArrayList<ReservationClass> res = new ArrayList<ReservationClass>();

    public static SearchResults getInstance() {
        if (instance == null) {
            instance = new SearchResults();
        }
        return instance;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        final GlobalVars checkin = ((GlobalVars) getApplicationContext());//need it to access check in from user choice
        final GlobalVars roomsNO = ((GlobalVars) getApplicationContext());//need it to access check in from user choice


        Bundle bundle = getIntent().getExtras();
        host_city= bundle.getString("destination");


        /*access num of rooms and store value to global var*/

        /*end of storing*/


        /*access global variable*/

       /* */



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();


        databaseReference.child("Reserved").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                    ReservationClass reserved = child.getValue(ReservationClass.class);
                    res.add(reserved);
                    if (host_city.equals(reserved.getHost_city())) {
                        if (checkin.getCheckIN().equals(reserved.getCheckin())) {
                            controller=true;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });


        databaseReference.child(host_city).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                    Apartments r = child.getValue(Apartments.class);
                    //globalVariable_num_of_rooms.getNumOfRoomsVar()
                    if (roomsNO.getNumOfRoomsVar() <= r.getNum_of_rooms()) {
                        if(controller==true){
                            continue;
                        }

                            ads.add(r);

                        mAdAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


                mAdAdapter = new ApartmentAdapter(ads);


            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

            mRecyclerView.setAdapter(mAdAdapter);

        }



}