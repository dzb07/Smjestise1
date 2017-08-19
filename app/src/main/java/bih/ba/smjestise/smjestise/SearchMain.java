package bih.ba.smjestise.smjestise;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Locale;

public class SearchMain extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    /*my variables*/
    private DatePickerDialog.OnDateSetListener mDateSetListener_in;
    private DatePickerDialog.OnDateSetListener mDateSetListener_out;

    private static final String TAG = "Hotels";
    private Button buttoncheckIn;
    private Button buttoncheckOut;
    private CheckBox checkBox;
    public EditText destination;
    public String destinationcity;
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

    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    /*end of myvariables*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);


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
        destination=(EditText) findViewById(R.id.destination);
        destinationcity=destination.getText().toString();
       /* PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
*/


        /*end of getting destination city*/

        buttoncheckIn= (Button) findViewById(R.id.check_in);
        buttoncheckOut= (Button) findViewById(R.id.check_out);
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




         /*on search button open new activity*/
        search=(Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                Intent intSearchResults = new Intent(SearchMain.this, SearchResults.class);
                /*send destination city to other activity*/
                intSearchResults.putExtra("destination", destinationcity);
                intSearchResults.putExtra("num_of_rooms",num_of_rooms.getText());
                intSearchResults.putExtra("num_of_adults",num_of_adults.getText());
                intSearchResults.putExtra("num_of_children",num_of_children.getText());
                startActivity(intSearchResults);

            }
        });
        /*end of opening SearchResults*/



    }








    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckIn.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        buttoncheckOut.setText(sdf.format(myCalendar.getTime()));
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
}
