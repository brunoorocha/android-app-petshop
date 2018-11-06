package com.example.brunoorocha.petshop.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.brunoorocha.petshop.R;
import com.example.brunoorocha.petshop.model.Pets;
import com.example.brunoorocha.petshop.presenter.PetListPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PetListPresenter.PetListView {

    private RecyclerView recyclerView;
    private PetListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recycler_view);
        presenter = new PetListPresenter();
        presenter.setView(this);
        presenter.loadPets();
    }

    private void setupRecyclerAdapterWithPets(List<Pets> pets) {
        PetListAdapter adapter = new PetListAdapter(this, 10);
        adapter.setPets(pets);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadPetListWith(List<Pets> pets) {
        setupRecyclerAdapterWithPets(pets);
    }
}
