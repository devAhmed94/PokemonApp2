package com.example.pokemonapp2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonapp2.R;
import com.example.pokemonapp2.network.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>  {
     List<Pokemon> pokemonList = new ArrayList<>();

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.item_tv.setText(pokemonList.get(position).getName());
        Glide.with(holder.itemView.getContext())
                .load(pokemonList.get(position).getUrl())
                .into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public Pokemon getPokemonAt(int position){
        return pokemonList.get(position);
    }

     class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView item_tv;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            item_tv = itemView.findViewById(R.id.item_tv);

        }
    }
}
