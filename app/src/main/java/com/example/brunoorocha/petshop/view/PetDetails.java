package com.example.brunoorocha.petshop.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.brunoorocha.petshop.R;
import com.example.brunoorocha.petshop.model.Pets;

public class PetDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_details);

        Pets pet = (Pets) getIntent().getSerializableExtra("pet");
        TextView petNameTextView = findViewById(R.id.pet_details_name);
        TextView petSpeciesTextView = findViewById(R.id.pet_details_specie);
        TextView petFoodsLikeTextView = findViewById(R.id.pet_details_foods_like);
        TextView petFoodsDisikeTextView = findViewById(R.id.pet_details_foods_dislike);

        String likesConcated = TextUtils.join(", ", pet.getFoods().getLikes());
        String dislikesConcated = TextUtils.join(", ", pet.getFoods().getDislikes());

        petNameTextView.setText(pet.getName());
        petSpeciesTextView.setText(pet.getSpecies());
        petFoodsLikeTextView.setText(likesConcated);
        petFoodsDisikeTextView.setText(dislikesConcated);
    }
}
