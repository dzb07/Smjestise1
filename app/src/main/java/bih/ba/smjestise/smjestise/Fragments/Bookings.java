package bih.ba.smjestise.smjestise.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;
import bih.ba.smjestise.smjestise.Helpers.SavedApartments;
import bih.ba.smjestise.smjestise.Helpers.SavedApartmentsAdapter;
import bih.ba.smjestise.smjestise.Helpers.UserReservationsAdapter;
import bih.ba.smjestise.smjestise.MainActivity;
import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.SearchMain;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Bookings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Bookings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bookings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth firebaseAuth1;
    private ReservationClass user;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    UserReservationsAdapter mAdAdapter;
    private String userID;
    final ArrayList<ReservationClass> ads = new ArrayList<ReservationClass>();
    private LinearLayout noReservations;
    private ImageView goToSearchMain;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView details;
    private OnFragmentInteractionListener mListener;
    private Button btn;
    private FirebaseAuth firebaseAuth;


    public Bookings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bookings.
     */
    // TODO: Rename and change types and number of parameters
    public static Bookings newInstance(String param1, String param2) {
        Bookings fragment = new Bookings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_bookings, container, false);
        userID= firebaseAuth1.getInstance().getCurrentUser().getUid();
        noReservations=(LinearLayout)rootView.findViewById(R.id.noReservations);
        goToSearchMain=(ImageView)rootView.findViewById(R.id.goToSearchMain);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();


        /*check if no reservations exist for this particular user, IF EXISTS DISPLAY IT*/
        FirebaseDatabase.getInstance().getReference().child("UserReservations")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(userID)) {
                            Iterable<DataSnapshot> children = dataSnapshot.child(userID).getChildren();
                            for (DataSnapshot child : children) {

                                ReservationClass r = child.getValue(ReservationClass.class);
                                //globalVariable_num_of_rooms.getNumOfRoomsVar()


                                ads.add(r);
                                mAdAdapter.notifyDataSetChanged();


                            }
                        }
                        else{
                            noReservations.setVisibility(View.VISIBLE);
                        }
                    }


                    @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


        mAdAdapter = new UserReservationsAdapter(ads);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView03);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setAdapter(mAdAdapter);



        //open searhMain on click of calendar
        goToSearchMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchMain.class);
                startActivity(intent);
            }
        });





        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }








}
