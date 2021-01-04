package com.example.pokemonapp2.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonapp2.network.Pokemon;
import com.example.pokemonapp2.network.PokemonResponse;
import com.example.pokemonapp2.repository.PokemonRepository;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PokemonViewModel extends ViewModel {
    private PokemonRepository repository;
    MutableLiveData<List<Pokemon>> liveList= new MutableLiveData<>();
    LiveData<List<Pokemon>>  fav_list =new MutableLiveData<>();



    @ViewModelInject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Pokemon>> getLiveList() {
        return liveList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons(){
        repository.getPokemons().
                subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, List<Pokemon>>() {
                    @Override
                    public List<Pokemon> apply(@NonNull PokemonResponse pokemonResponse) throws Exception {
                        List<Pokemon> pokemonList = pokemonResponse.getResults();
                        for (Pokemon pokemon :pokemonList){
                            String url = pokemon.getUrl();
                            String[]arrayUrl =url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"
                                    +arrayUrl[arrayUrl.length-1]+".png");

                        }
                        return pokemonList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result->liveList.setValue(result),
                        error-> Log.d("ahmed", "getPokemons: "+error.getMessage())
                );
    }

    public void insertPokemon(Pokemon pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(Pokemon pokemon){
        repository.deletePokemon(pokemon);
    }
    public void getFavListOfPokemon(){
       fav_list = repository.getListOfPokemon();
    }
    public LiveData<List<Pokemon>> getFav_list() {
        return fav_list;
    }

}
