package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.R;


public class ApartmentViewHolder extends RecyclerView.ViewHolder {

    private ImageView mAdImage;
    private TextView mAdName;
    private TextView mPrice;
    GlobalVars t1; //make a accessing point

    public ApartmentViewHolder(View itemView) {
        super(itemView);
        mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName = (TextView) itemView.findViewById(R.id.ad_name);
        mPrice=(TextView) itemView.findViewById(R.id.ad_price);
    }

    public void bindAd(Apartments ad) {
       // if(t1.getT1())
        mAdName.setText(ad.prop_name);
        mPrice.setText(ad.price);
        Glide.with(mAdImage.getContext()).load(ad.urlImage1).into(mAdImage);
    }

}