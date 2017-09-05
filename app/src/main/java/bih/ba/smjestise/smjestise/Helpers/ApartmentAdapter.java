package bih.ba.smjestise.smjestise.Helpers;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ViewHolders.ApartmentDetails;
import bih.ba.smjestise.smjestise.ViewHolders.ApartmentViewHolder;


/**
 * Created by Džana on 18.1.2017.
 */

public  class ApartmentAdapter extends RecyclerView.Adapter<ApartmentViewHolder> {
    ArrayList<Apartments> mImages;



    public ApartmentAdapter(ArrayList<Apartments> mAds)
    {
        this.mImages = mAds;
    }

    @Override
    public ApartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.apartment_item, parent, false);
        return new ApartmentViewHolder(inflatedView);
    }

    public void onBindViewHolder(ApartmentViewHolder holder, int position) {
        final Apartments apartments = mImages.get(position);

        holder.bindAd(apartments);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ApartmentDetails.class);
                intent.putExtra("ad",apartments);
                //intent.putExtra("cancellation",apartments);


                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}

