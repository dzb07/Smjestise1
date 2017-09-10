package bih.ba.smjestise.smjestise.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.Fragments.Bookings;
import bih.ba.smjestise.smjestise.MainActivity;
import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ViewHolders.SavedApartmentsViewHolder;
import bih.ba.smjestise.smjestise.ViewHolders.UserReservationsViewHolder;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Džana on 30.8.2017.
 */

public class UserReservationsAdapter extends RecyclerView.Adapter<UserReservationsViewHolder> {
    private Button cancelReservation;
    private Context context;
    private FirebaseAuth firebaseAuth;
    private ArrayList<ReservationClass> data;
    private LayoutInflater inflater;

    GlobalVars a=new GlobalVars();
    private int previousPosition = 0;
    public UserReservationsAdapter(ArrayList<ReservationClass> mAds)
    {
        this.data = mAds;
    }
    public UserReservationsAdapter(Context context){
        this.context=context;
    }
    @Override
    public UserReservationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_reserved_apartment_item, parent, false);
        cancelReservation=(Button) inflatedView.findViewById(R.id.cancel_reservation);
        return new UserReservationsViewHolder(inflatedView);
    }

    public void onBindViewHolder(final UserReservationsViewHolder holder, int position) {
        final ReservationClass apartment = data.get(position);
        holder.bindAd(apartment);
        //SavedApartments savedInfo=savedApartments.get(position);
        final int currentPosition = position;
        final ReservationClass infoData = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_SHORT).show();
                //Open New Activity show details about advertisment
                int newPosition = holder.getAdapterPosition();
                Log.d("thien.van", "on Click onBindViewHolder");

            }
        });
        // Set a click listener for item remove button

        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(infoData);
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    private void removeItem(ReservationClass infoData) {

        int currPosition = data.indexOf(infoData);
        String apartmentToDelete = data.get(currPosition).getProp_name();
       /* AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);

        alertdialog.setMessage("Cancel?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alert=alertdialog.create();
        alert.setTitle("Doctor");
        alert.show();*/

        data.remove(currPosition);
        notifyItemRemoved(currPosition);
        deleteFromDatabase(apartmentToDelete);


    }

    public void deleteFromDatabase(String apartment){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query queryProperty = ref.child("UserReservations/" + firebaseAuth.getInstance()
                .getCurrentUser().getUid()).orderByChild("prop_name").equalTo(apartment);

        queryProperty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    propertySnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });



        Query queryProperty2 = ref.child("Reserved/" + apartment).orderByChild("userID").equalTo(firebaseAuth.getInstance().getCurrentUser().getUid() //get current user ID
);

        queryProperty2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    propertySnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }


}
