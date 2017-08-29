package bih.ba.smjestise.smjestise.Helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ViewHolders.SavedApartmentsViewHolder;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Džana on 29.8.2017.
 */

public class SavedApartmentsAdapter extends RecyclerView.Adapter<SavedApartmentsViewHolder> {
    private  Button removeSaved;
    private Context context;
    private FirebaseAuth firebaseAuth;
    private ArrayList<SavedApartments> data;

    private LayoutInflater inflater;

    private int previousPosition = 0;
    public SavedApartmentsAdapter(ArrayList<SavedApartments> mAds)
    {
        this.data = mAds;
    }

    @Override
    public SavedApartmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_apartment_item, parent, false);
        removeSaved=(Button) inflatedView.findViewById(R.id.removeSaved);
        return new SavedApartmentsViewHolder(inflatedView);
    }

    public void onBindViewHolder(final SavedApartmentsViewHolder holder, int position) {
        final SavedApartments apartment = data.get(position);
        holder.bindAd(apartment);
        //SavedApartments savedInfo=savedApartments.get(position);
        final int currentPosition = position;
        final SavedApartments infoData = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_SHORT).show();
                //Open New Activity show details about advertisment
                int newPosition = holder.getAdapterPosition();
                Log.d("thien.van", "on Click onBindViewHolder");

            }
        });
        removeSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(infoData);
            }
        });

       /* holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeItem(savedInfo);
                return true;
            }
        });*/






        // Set a click listener for item remove button
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    private void removeItem(SavedApartments infoData) {

        int currPosition = data.indexOf(infoData);
        String apartmentToDelete = data.get(currPosition).getProp_name();
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
       // Toast.makeText(context,apartmentToDelete,Toast.LENGTH_LONG).show();

           DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query queryProperty = ref.child("SavedApartments/" + firebaseAuth.getInstance()
                    .getCurrentUser().getUid()).orderByChild("prop_name").equalTo(apartmentToDelete );

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
        }
    }


