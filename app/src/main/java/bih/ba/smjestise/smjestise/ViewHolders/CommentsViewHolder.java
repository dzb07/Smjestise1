package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bih.ba.smjestise.smjestise.Helpers.Comments;
import bih.ba.smjestise.smjestise.R;

/**
 * Created by Džana on 3.9.2017.
 */

public class CommentsViewHolder extends RecyclerView.ViewHolder {

  private TextView commentFromDatabase;
    private TextView usernameFromDatabase;
    private TextView rate;
    public CommentsViewHolder(View itemView) {
        super(itemView);
        commentFromDatabase=(TextView)itemView.findViewById(R.id.commentFromDatabase);
        usernameFromDatabase=(TextView)itemView.findViewById(R.id.usernameFromDatabase);
        rate=(TextView)itemView.findViewById(R.id.rate);

    }

    public void bindAd(Comments ad) {
        //set views
        commentFromDatabase.setText(ad.getCommentProperty());
        usernameFromDatabase.setText(ad.getUsername());;
        rate.setText(String.valueOf(ad.getRateProperty()));
    }
}

