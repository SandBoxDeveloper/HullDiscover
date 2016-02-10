package com.hulldiscover.zeus.hulldiscover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus on 10/02/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<PaletteViewHolder> {

    private List<Palette> palettes;

    public MyRecyclerAdapter(List<Palette> palettes) {
        this.palettes = new ArrayList<>();
        this.palettes.addAll(palettes);
    }

    // Specify the CardView to be used
    @Override
    public PaletteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new PaletteViewHolder(itemView);
    }

    // Map each Object's data to the corresponding widget in the CardView
    @Override
    public void onBindViewHolder(PaletteViewHolder paletteViewHolder, int i) {
        Palette palette = palettes.get(i);
        paletteViewHolder.titleText.setText(palette.getName());
        paletteViewHolder.contentText.setText(palette.getHexValue());
        paletteViewHolder.card.setCardBackgroundColor(palette.getIntValue());
    }

    // Returns the number of items in your list
    @Override
    public int getItemCount() {
        return palettes.size();
    }

}
