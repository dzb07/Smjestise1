package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.vision.text.Text;

import bih.ba.smjestise.smjestise.Helpers.Apartments;
import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.R;


public class ApartmentViewHolder extends RecyclerView.ViewHolder {

    private ImageView mAdImage;
    private TextView mAdName;
    private TextView mPrice;
    private TextView mAddress;
    private TextView currency_desc;
    private  TextView cancellation;
    GlobalVars var1=new GlobalVars();
    public ApartmentViewHolder(View itemView) {
        super(itemView);
        mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName = (TextView) itemView.findViewById(R.id.ad_name);
        mAddress=(TextView) itemView.findViewById(R.id.ad_address);
        mPrice=(TextView) itemView.findViewById(R.id.ad_price);
        currency_desc=(TextView)itemView.findViewById(R.id.currency_desc);
        cancellation=(TextView)itemView.findViewById(R.id.cancellation);

    }

    public void bindAd(Apartments ad) {
        mAdName.setText(ad.prop_name);
        mAddress.setText(ad.host_street);
        //check if cancellation is free, if so, then show it in recycler view
        if(ad.cancellation.equals("yes")){
            cancellation.setVisibility(View.VISIBLE);
        }
        else{
            cancellation.setVisibility(View.GONE);
        }
        /*checking which currency is selected*/
       if(GlobalVars.currency.equals("EUR")) {
            mPrice.setText(String.valueOf(ad.price_eur));
            currency_desc.setText("EUR per night");
        }
        else{
            mPrice.setText(String.valueOf(ad.price));
           currency_desc.setText("USD per night");


       }
        Glide.with(mAdImage.getContext()).load(ad.urlImage1).into(mAdImage);
    }

}