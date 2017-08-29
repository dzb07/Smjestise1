package bih.ba.smjestise.smjestise.Helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ViewHolders.SavedApartmentsViewHolder;

/**
 * Created by Džana on 29.8.2017.
 */

public class SavedApartmentsAdapter extends RecyclerView.Adapter<SavedApartmentsViewHolder> {
    ArrayList<SavedApartments> savedApartments;
    private  Button removeSaved;
    private Context context;

    ;

    private LayoutInflater inflater;
    public SavedApartmentsAdapter(ArrayList<SavedApartments> mAds)
    {
        this.savedApartments = mAds;
    }

    @Override
    public SavedApartmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_apartment_item, parent, false);
        removeSaved=(Button) inflatedView.findViewById(R.id.removeSaved);
        return new SavedApartmentsViewHolder(inflatedView);
    }

    public void onBindViewHolder(final SavedApartmentsViewHolder holder, int position) {
        final SavedApartments image = savedApartments.get(position);
        holder.bindAd(image);
        final SavedApartments savedInfo=savedApartments.get(position);
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
                removeItem(savedInfo);
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
        return savedApartments.size();
    }

    private void removeItem(SavedApartments infoData) {

        int currPosition = savedApartments.indexOf(infoData);
        savedApartments.remove(currPosition);
        notifyItemRemoved(currPosition);
    }}


