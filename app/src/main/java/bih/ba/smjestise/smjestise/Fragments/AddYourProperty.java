package bih.ba.smjestise.smjestise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bih.ba.smjestise.smjestise.Helpers.BottomNavigationViewHelper;
import bih.ba.smjestise.smjestise.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddYourProperty.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddYourProperty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddYourProperty extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button sendEmail;
    private OnFragmentInteractionListener mListener;

    public AddYourProperty() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddYourProperty.
     */
    // TODO: Rename and change types and number of parameters
    public static AddYourProperty newInstance(String param1, String param2) {
        AddYourProperty fragment = new AddYourProperty();
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
        View rootView=inflater.inflate(R.layout.fragment_add_your_property, container, false);

        sendEmail=(Button) rootView.findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });


        BottomNavigationView bottomNavigationView=(BottomNavigationView) rootView.findViewById(R.id.bootom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())  {
                    case R.id.home:
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.booked:
                        FragmentManager fragmentManager1 = getFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        Bookings bookingFragment = new Bookings();
                        fragmentTransaction1.replace(R.id.fragment_container, bookingFragment);
                        fragmentTransaction1.commit();
                        break;
                    case R.id.favourites:
                        FragmentManager fragmentManager2 = getFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        SavedProperties savedFragment = new SavedProperties();
                        fragmentTransaction2.replace(R.id.fragment_container, savedFragment);
                        fragmentTransaction2.commit();
                        break;
                    case R.id.aboutinfo:
                        FragmentManager fragmentManager3=getFragmentManager();
                        FragmentTransaction fragmentTransaction3=fragmentManager3.beginTransaction();
                        AboutApp aboutFragment=new AboutApp();
                        fragmentTransaction3.replace(R.id.fragment_container,aboutFragment);
                        fragmentTransaction3.commit();
                        break;
                }
                return true;
            }
        });

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//disable animation on bottom menu

        return rootView;
    }



    protected void sendEmail() {
        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","dzana_basic@hotmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Register your property");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear,\n\nI would like to register my property on your app.\n\nRegards");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            getActivity().finish();
            //Log.i("Finished sending email...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
        }
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
