package bih.ba.smjestise.smjestise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import bih.ba.smjestise.smjestise.Helpers.ApartmentAdapter;
import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;


public class SearchResults extends AppCompatActivity {
    private static SearchResults instance = null;
    final ArrayList<Apartments> ads = new ArrayList<Apartments>();
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    ApartmentAdapter mAdAdapter;
    String host_city;
    String curreny;
    int controller;
    private int result;
    TextView searched_city;
    TextView selected_num_of_persons;
    TextView number_of_nigths;
    TextView number_of_children01;
    private int suma;
    public int counter1;
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
        // provide compatibility to all the versions
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final GlobalVars roomsNO = ((GlobalVars) getApplicationContext());//need it to access check in from user choice
        final GlobalVars num_of_adults = ((GlobalVars) getApplicationContext());
        final GlobalVars num_of_children=((GlobalVars) getApplicationContext());
        final GlobalVars checkin=((GlobalVars) getApplicationContext());
        final GlobalVars checkout=((GlobalVars) getApplicationContext());
        final GlobalVars days_difference=((GlobalVars) getApplicationContext());
        /*extract number of nights selected*/
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String c1=checkin.getCheckIN();
        String c2=checkout.getCheckOUT();

        try {
            Date date1 = myFormat.parse(c1);
            Date date2 = myFormat.parse(c2);
            long diff = date2.getTime() - date1.getTime();
            days_difference.setDays_difference(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*end of extraction of number of nights selected*/

        Bundle bundle = getIntent().getExtras();
        host_city = bundle.getString("destination");
        curreny=bundle.getString("price");//GETTING THE FLAG OF WHICH CURRENCY HAS BEEN SELECTED
        getSupportActionBar().setTitle(host_city);//set the action bar text to name of selected city

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

        databaseReference.child(host_city).orderByChild("price").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                   final Apartments r = child.getValue(Apartments.class);
                    //globalVariable_num_of_rooms.getNumOfRoomsVar()
                    if (roomsNO.getNumOfRoomsVar() <= r.getNum_of_rooms()) {

                        FirebaseDatabase.getInstance().getReference().child("Reserved").child(r.getProp_name())
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                        for (DataSnapshot child : children) {

                                            ReservationClass reserved = child.getValue(ReservationClass.class);

                                            if (reserved.getTimestamp1() == 100) {

                                                // counter_value.setCounter(1);

                                                ads.remove(r);
                                                break;
                                            } else {

                                                ads.add(r);
                                                mAdAdapter.notifyDataSetChanged();

                                            }

                                        }

                                    }


                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

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







        /*set the header of search results, with city specified, number of persons and number of nights*/
        searched_city = (TextView) findViewById(R.id.searched_city);
        searched_city.setText(host_city);

        selected_num_of_persons = (TextView) findViewById(R.id.selected_num_of_persons);
        selected_num_of_persons.setText(String.valueOf(num_of_adults.getNum_of_adults()) + " adults");

        number_of_nigths = (TextView) findViewById(R.id.number_of_nigths);
        number_of_nigths.setText(String.valueOf(days_difference.getDays_difference()+" nights"));
        /*check if children is selected 1, if is then set appropriate text view*/
        if(num_of_children.getNum_of_children()==1){
            number_of_children01=(TextView) findViewById(R.id.selected_num_of_children);
            number_of_children01.setText(", "+String.valueOf(num_of_children.getNum_of_children())+" child");

        }
        /*if more children then set appropriate text*/
        if(num_of_children.getNum_of_children()>1){
            number_of_children01=(TextView) findViewById(R.id.selected_num_of_children);
            number_of_children01.setText(", "+String.valueOf(num_of_children.getNum_of_children())+" children");

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}










