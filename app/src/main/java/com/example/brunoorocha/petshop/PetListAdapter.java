package com.example.brunoorocha.petshop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.ViewHolder> {

    private Context mContext;
    private int itemsCount;

    public PetListAdapter(Context mContext, int itemsCount) {
        this.mContext = mContext;
        this.itemsCount = itemsCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        view = inflater.inflate(R.layout.pet_view, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.petViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPetDetailsView();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return this.itemsCount;
    }

    private void showPetDetailsView() {
        Intent intent = new Intent(this.mContext, PetDetails.class);
        this.mContext.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView petName;
        TextView petSpecie;
        TextView petPrice;
        ImageView petThumbnail;
        LinearLayout petViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.pet_name);
            petSpecie = itemView.findViewById(R.id.pet_specie);
            petPrice = itemView.findViewById(R.id.pet_price);
            petThumbnail = itemView.findViewById(R.id.pet_thumbnail);
            petViewItem = itemView.findViewById(R.id.pet_view_item);

        }

    }
}
