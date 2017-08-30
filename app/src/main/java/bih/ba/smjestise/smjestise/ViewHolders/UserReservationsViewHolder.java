package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bih.ba.smjestise.smjestise.Helpers.GlobalVars;
import bih.ba.smjestise.smjestise.Helpers.ReservationClass;
import bih.ba.smjestise.smjestise.Helpers.SavedApartments;
import bih.ba.smjestise.smjestise.R;

/**
 * Created by Džana on 30.8.2017.
 */

public class UserReservationsViewHolder extends RecyclerView.ViewHolder {

    private TextView propertyName;
    private TextView propertyAddress;
    private Button checkinDate;
    private Button checkoutDate;
    private TextView cijena;
    private Button cancelReservation;

    GlobalVars var1 = new GlobalVars();

    public UserReservationsViewHolder(View itemView) {
        super(itemView);
        propertyName = (TextView) itemView.findViewById(R.id.propertyName);
        propertyAddress = (TextView) itemView.findViewById(R.id.propertyAddress);
        checkinDate=(Button)itemView.findViewById(R.id.checkinUSER);
        checkoutDate=(Button) itemView.findViewById(R.id.checkoutUSER);
        cijena=(TextView)itemView.findViewById(R.id.cijena);
        cancelReservation=(Button) itemView.findViewById(R.id.cancel_reservation);

    }

    public void bindAd(ReservationClass ad) {
        propertyName.setText(ad.getProp_name());
        propertyAddress.setText(ad.getHost_city());
        checkinDate.setText(ad.getCheckin());
        checkoutDate.setText(ad.getCheckout());
        cijena.setText("Total price to pay"+ad.getPrice_to_pay());
    }

}