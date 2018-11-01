package com.example.brunoorocha.petshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recycler_view);
        setupRecyclerAdapter();
    }

    private void setupRecyclerAdapter() {
        PetListAdapter adapter = new PetListAdapter(this, 10);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(adapter);
    }

}
