package com.example.brunoorocha.petshop.presenter;

import android.content.Context;
import android.util.Log;

import com.example.brunoorocha.petshop.model.Pets;
import com.example.brunoorocha.petshop.service.Async;

import java.util.List;

public class PetListPresenter implements Async.OnLoadEventListener {

    private PetListView view;

    public void setView(PetListView view) {
        this.view = view;
    }

    public void loadPets() {
        Context mContext = Context.class.cast(view);
        Async asyncTask = new Async(this, mContext);
        asyncTask.execute("https://raw.githubusercontent.com/humbertobeltrao/AndroidUtils/master/pets.json");
    }

    @Override
    public void onLoadEvent(List<Pets> pets) {
        this.view.loadPetListWith(pets);
    }

    public interface PetListView {
        void loadPetListWith(List<Pets> pets);
    }
}
