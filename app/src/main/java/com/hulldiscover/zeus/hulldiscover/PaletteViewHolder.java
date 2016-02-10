package com.hulldiscover.zeus.hulldiscover;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Zeus on 10/02/16.
 */
public class PaletteViewHolder extends RecyclerView.ViewHolder {
    protected TextView titleText;
    protected TextView contentText;
    protected CardView card;

    public PaletteViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.title);
        contentText = (TextView) itemView.findViewById(R.id.content);
        card = (CardView) itemView;
    }
}
