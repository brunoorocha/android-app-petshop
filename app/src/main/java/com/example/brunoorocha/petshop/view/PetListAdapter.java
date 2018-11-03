package com.example.brunoorocha.petshop.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.brunoorocha.petshop.R;
import com.example.brunoorocha.petshop.model.Pets;

import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.ViewHolder> {

    private Context mContext;
    private List<Pets> pets;
    private int itemsCount;

    public PetListAdapter(Context mContext, int itemsCount) {
        this.mContext = mContext;
        this.itemsCount = itemsCount;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        view = inflater.inflate(R.layout.pet_view, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.petViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPetDetailsView(viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.petName.setText(this.pets.get(i).getName());
        viewHolder.petSpecie.setText(this.pets.get(i).getSpecies());

        String priceFormat = String.format("Price: $ %s.00", this.pets.get(i).getPrice());
        viewHolder.petPrice.setText(priceFormat);
        viewHolder.petThumbnail.setImageResource(this.pets.get(i).getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }

    private void showPetDetailsView(int index) {
        Pets pet = this.pets.get(index);
        Intent intent = new Intent(this.mContext, PetDetails.class);
        intent.putExtra("pet", pet);
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

            petThumbnail.setClipToOutline(true);
            petThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

    }
}
