package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

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
    private TextView date_when_saved;
    GlobalVars var1 = new GlobalVars();

    public SavedApartmentsViewHolder(View itemView) {
        super(itemView);
       // mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName01 = (TextView) itemView.findViewById(R.id.ad_name01);
        mAddress01 = (TextView) itemView.findViewById(R.id.ad_address01);
        date_when_saved=(TextView)itemView.findViewById(R.id.date_when_saved);

    }

    public void bindAd(SavedApartments ad) {
        mAdName01.setText(ad.getProp_name());
        mAddress01.setText(ad.getHost_city());

        //get date

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(ad.getSaved_on());
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        if(Locale.getDefault().getLanguage().equals("hr") || Locale.getDefault().getLanguage().equals("bs"))
        {
            date_when_saved.setText("Spremljeno na datum: "+date);
        }
        else{
            date_when_saved.setText("Saved on: "+date);

        }
    }

}