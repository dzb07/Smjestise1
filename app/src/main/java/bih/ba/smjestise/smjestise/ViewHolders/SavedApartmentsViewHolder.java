package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.SavedApartments;
import bih.ba.smjestise.smjestise.R;

/**
 * Created by Džana on 29.8.2017.
 */

public class SavedApartmentsViewHolder extends RecyclerView.ViewHolder {

    private ImageView mAdImage;
    private TextView mAdName01;
    private TextView mPrice;
    private TextView mAddress01;
    GlobalVars var1 = new GlobalVars();

    public SavedApartmentsViewHolder(View itemView) {
        super(itemView);
       // mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName01 = (TextView) itemView.findViewById(R.id.ad_name01);
        mAddress01 = (TextView) itemView.findViewById(R.id.ad_address01);

    }

    public void bindAd(SavedApartments ad) {
        mAdName01.setText(ad.getProp_name());
        mAddress01.setText(ad.getHost_city());
    }

}