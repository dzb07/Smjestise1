package bih.ba.smjestise.smjestise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.Helpers.RateApplicationAdapter;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;
import bih.ba.smjestise.smjestise.R;

import static android.widget.Toast.LENGTH_LONG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RateApp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RateApp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateApp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText commentofUser;
    private Button openCommentSection;
    private Button submitRate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RateApp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RateApp.
     */
    // TODO: Rename and change types and number of parameters
    public static RateApp newInstance(String param1, String param2) {
        RateApp fragment = new RateApp();
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
        final View rootView=inflater.inflate(R.layout.fragment_rate_app, container, false);
        openCommentSection=(Button) rootView.findViewById(R.id.openComment);
        final RatingBar mBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

        final ArrayList<RateApplicationAdapter> rateObject = new ArrayList<>();
        commentofUser=(EditText)rootView.findViewById(R.id.userComment);

        openCommentSection.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {

                                                      if(commentofUser.getVisibility()==View.VISIBLE)
                                                          commentofUser.setVisibility(View.GONE);

                                                      else
                                                      commentofUser.setVisibility(View.VISIBLE);

                                                  }

        });


        /*sending rate to database*/
        submitRate=(Button) rootView.findViewById(R.id.submitRate);
        submitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rate=mBar.getRating();
                String komentar=commentofUser.getText().toString();
                Toast toast = Toast.makeText(getActivity(), "Your rate is "+rate,LENGTH_LONG);
                toast.show();
                rateObject.add(new RateApplicationAdapter(rate,komentar));


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                for (int i = 0; i < rateObject.size(); i++) {
                    databaseReference.child("AppRatings").push().setValue(rateObject.get(i));
                }

            }
        });
        /*end of sending rate to database*/


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

