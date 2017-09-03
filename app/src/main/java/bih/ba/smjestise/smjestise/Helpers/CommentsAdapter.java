package bih.ba.smjestise.smjestise.Helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bih.ba.smjestise.smjestise.R;
import bih.ba.smjestise.smjestise.ViewHolders.CommentsViewHolder;

/**
 * Created by Džana on 3.9.2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {
    ArrayList<Comments> mComment;



    public CommentsAdapter(ArrayList<Comments> mAds)
    {
        this.mComment = mAds;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentsViewHolder(inflatedView);
    }

    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        final Comments image = mComment.get(position);
        holder.bindAd(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }
}

