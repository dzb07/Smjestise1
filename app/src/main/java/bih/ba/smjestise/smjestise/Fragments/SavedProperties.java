package bih.ba.smjestise.smjestise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import bih.ba.smjestise.smjestise.Helpers.ApartmentAdapter;
import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.SavedApartments;
import bih.ba.smjestise.smjestise.Helpers.SavedApartmentsAdapter;
import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.SearchMain;
import bih.ba.smjestise.smjestise.ViewHolders.SavedApartmentsViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SavedProperties.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SavedProperties#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedProperties extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String userID;
    private FirebaseAuth firebaseAuth1;
    private SavedApartments user;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    SavedApartmentsAdapter mAdAdapter;
    final ArrayList<SavedApartments> ads = new ArrayList<SavedApartments>();
    private LinearLayout noSavedApartments;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference db;
    private DatabaseReference mDatabase;
    private ImageView goToSearchMain;
    ListView lv;

    private OnFragmentInteractionListener mListener;
    private ArrayList<String> apartmentList=new ArrayList<>();

    ListView l1;
    public SavedProperties() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SavedProperties.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedProperties newInstance(String param1, String param2) {
        SavedProperties fragment = new SavedProperties();
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
        final View rootView=inflater.inflate(R.layout.fragment_saved_properties, container, false);
        userID= firebaseAuth1.getInstance().getCurrentUser().getUid();
        noSavedApartments=(LinearLayout)rootView.findViewById(R.id.noSavedApartments);
        goToSearchMain=(ImageView)rootView.findViewById(R.id.goToSearchMain);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

        databaseReference.child("SavedApartments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userID)) {
                    Iterable<DataSnapshot> children = dataSnapshot.child(userID).getChildren();
                    for (DataSnapshot child : children) {

                        SavedApartments r = child.getValue(SavedApartments.class);
                        //globalVariable_num_of_rooms.getNumOfRoomsVar()


                        ads.add(r);
                        mAdAdapter.notifyDataSetChanged();


                    }
                }
                else{
                    noSavedApartments.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mAdAdapter = new SavedApartmentsAdapter(ads);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView02);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setAdapter(mAdAdapter);


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
