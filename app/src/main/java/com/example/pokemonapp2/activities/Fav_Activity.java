package com.example.pokemonapp2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pokemonapp2.R;
import com.example.pokemonapp2.adapters.PokemonAdapter;
import com.example.pokemonapp2.network.Pokemon;
import com.example.pokemonapp2.viewmodel.PokemonViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Fav_Activity extends AppCompatActivity {

    PokemonViewModel pokemonViewModel;
    RecyclerView recyclerView;
    PokemonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_);
        recyclerView = findViewById(R.id.fav_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new PokemonAdapter();
        recyclerView.setAdapter(adapter);
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        pokemonViewModel.getFavListOfPokemon();
        pokemonViewModel.getFav_list().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                adapter.setPokemonList(pokemons);

            }
        });
        findViewById(R.id.btn_to_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fav_Activity.this, MainActivity.class));
            }
        });
        swipe();

    }

    private void swipe(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                Pokemon pokemonAt = adapter.getPokemonAt(position);
                pokemonViewModel.deletePokemon(pokemonAt);
                adapter.notifyDataSetChanged();
                Toast.makeText(Fav_Activity.this, "inserted done ", Toast.LENGTH_SHORT).show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
}
