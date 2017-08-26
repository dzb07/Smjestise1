package bih.ba.smjestise.smjestise;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;

public class SearchMain extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    /*my variables*/
    private DatePickerDialog.OnDateSetListener mDateSetListener_in;
    private DatePickerDialog.OnDateSetListener mDateSetListener_out;

    private static final String TAG = "Hotels";
    private Button buttoncheckIn;
    private Button buttoncheckOut;
    private CheckBox checkBox;
    public String destinationcity;
    private String val_checkin;
    private String val_checkout;
    Button search;
    Context context;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener date2;
    private Button num_of_rooms;
    private Button num_of_adults;
    private Button num_of_children;
    private static final String LOG_TAG = "SearchMain";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView;
    Apartments variable;
    /*radio buttons for currency*/
    RadioButton KM;
    RadioButton EUR;


    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    /*end of myvariables*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        //checking for action bar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /*Vars to set and get global variables*/
        final GlobalVars roomsNO= (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars adultstNO= (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars childrenNO= (GlobalVars) getApplicationContext(); //make a accessing point
        final GlobalVars host_city= (GlobalVars) getApplicationContext(); //make a accessing point


        /*accessing radio bbuttons for currency*/
        KM=(RadioButton)findViewById(R.id.KM);
        EUR=(RadioButton)findViewById(R.id.EUR);

        /*load currency json file*/
       /* AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://openexchangerates.org/api/latest.json?app_id=9a0ebdf3948d458590a440c8a32551a6", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                //JSONObject jsnobject=new JSONObject(String.valueOf(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

        });*/
        mGoogleApiClient = new GoogleApiClient.Builder(SearchMain.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();
        mAutocompleteTextView = (AutoCompleteTextView) findViewById(R.id
                .autoCompleteTextView);
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);

        /*get value of destination city*/
       /* PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
*/


        /*end of getting destination city*/

        buttoncheckIn= (Button) findViewById(R.id.check_in);
        val_checkin=buttoncheckIn.getText().toString();
        ((GlobalVars) this.getApplication()).setCheckIN(val_checkin);

        buttoncheckOut= (Button) findViewById(R.id.check_out);
        val_checkout=buttoncheckOut.getText().toString();
        ((GlobalVars) this.getApplication()).setCheckOUT(val_checkout);



        checkBox= (CheckBox) findViewById(R.id.checkbox_dates);
        if (checkBox == null) { Log.w("", "TextView is null"); }
        context=getApplicationContext();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    buttoncheckIn.setVisibility(View.GONE);
                    buttoncheckOut.setVisibility(View.GONE);
                }

                else{
                    buttoncheckIn.setVisibility(View.VISIBLE);
                    buttoncheckOut.setVisibility(View.VISIBLE);
                }

            }
        });

         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        buttoncheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }
        };
        buttoncheckOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchMain.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /*open number picker on button click for rooms*/
        num_of_rooms=(Button) findViewById(R.id.num_of_rooms);

        num_of_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumPicker(num_of_rooms);
            }
        });
        /*end of opening number picker on button click*/

          /*open number picker on button click for adults*/
        num_of_adults=(Button) findViewById(R.id.num_of_adults);
        num_of_adults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumPicker(num_of_adults);
            }
        });

        /*end of opening number picker on button click*/

          /*open number picker on button click for children*/
        num_of_children=(Button) findViewById(R.id.num_of_children);
        num_of_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumPicker(num_of_children);
            }
        });
        /*end of opening number picker on button click*/




        final GlobalVars checkin = ((GlobalVars) getApplicationContext());//need it to access check in from user choice
        final GlobalVars currency = ((GlobalVars) getApplicationContext());//need it to access CURRENCY from user choice

         /*on search button open new activity*/
        search=(Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                //set global variable of number of rooms to what is chosen
                Toast.makeText(getApplicationContext(),checkin.getCheckIN(),Toast.LENGTH_LONG).show();
                roomsNO.setNumOfRoomsVar(Integer.parseInt(num_of_rooms.getText().toString()));
                adultstNO.setNum_of_adults(Integer.parseInt(num_of_adults.getText().toString()));
                childrenNO.setNum_of_children(Integer.parseInt(num_of_children.getText().toString()));
               // host_city.setHost_city()
                Intent intSearchResults = new Intent(SearchMain.this, SearchResults.class);
                /*send destination city to other activity*/
                intSearchResults.putExtra("destination", destinationcity);
                if(EUR.isChecked())
                {
                    currency.setCurrency("EUR");
                }
                else{
                    currency.setCurrency("KM");

                }


                startActivity(intSearchResults);

            }
        });
        /*end of opening SearchResults*/

    /*handling ImageButtons in horizontal sroll view and opening wikipedia pages*/
        ImageButton sarajevo=(ImageButton) findViewById(R.id.sarajevo);
        sarajevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://bs.wikipedia.org/wiki/Sarajevo");

            }
        });
        ImageButton mostar=(ImageButton)findViewById(R.id.mostar);
        mostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://hr.wikipedia.org/wiki/Mostar");

            }
        });
        ImageButton hutovo_blato=(ImageButton)findViewById(R.id.hutovo_blato);
        hutovo_blato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("http://hutovo-blato.ba/");

            }
        });
        ImageButton banja_luka=(ImageButton)findViewById(R.id.banja_luka);
        banja_luka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://hr.wikipedia.org/wiki/Banja_Luka");

            }
        });
        ImageButton bihac=(ImageButton)findViewById(R.id.bihac);
        bihac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://hr.wikipedia.org/wiki/Biha%C4%87");

            }
        });

    }

    /*method to open wikipedia on button click*/
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }







    private void updateLabel() {
        String myFormat = "dd MM yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckIn.setText(sdf.format(myCalendar.getTime()));
        ((GlobalVars) this.getApplication()).setCheckIN(buttoncheckIn.getText().toString());
        long timestamp1 = myCalendar.getTimeInMillis();
        ((GlobalVars) this.getApplication()).setT1(timestamp1);

    }
    private void updateLabel2() {
        String myFormat = "dd MM yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckOut.setText(sdf.format(myCalendar.getTime()));
        ((GlobalVars) this.getApplication()).setCheckOUT(buttoncheckOut.getText().toString());
        long timestamp2 = myCalendar.getTimeInMillis();
        ((GlobalVars) this.getApplication()).setT2(timestamp2);
    }

/*method to open number picker dialog on button clicked, and sets number to button text*/
    public void showNumPicker(final Button button)
    {

        final Dialog d = new Dialog(SearchMain.this);
        d.setTitle("Rooms");
        d.setContentView(R.layout.num_picker_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(40); // max value 40
        np.setMinValue(1);   // min value 1
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                button.setText(String.valueOf(np.getValue()));

                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();


    }
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();
            destinationcity=place.getName().toString();
           /* mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }*/
        }
    };
    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }
    private Date stringToDate(String aDate,String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

//method for back button in action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}



