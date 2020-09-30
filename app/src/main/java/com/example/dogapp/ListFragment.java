package com.example.dogapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SearchView;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.modelview.DogApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {
    private ArrayList<DogBreed> arrDogs;
    private MyAdapter adapter;
    private RecyclerView rvDog;
    private DogApiService apiService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arrDogs = new ArrayList<>();
        apiService = new DogApiService();
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvDog = view.findViewById(R.id.rv_dog);
        rvDog.setLayoutManager(new GridLayoutManager(getContext(), 2));
        apiService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        for(DogBreed dog: dogBreeds) {
                            Log.d("DEBUG1", dog.name);
                            arrDogs.add(dog);
                        }
                        adapter = new MyAdapter(arrDogs, getContext());
                        rvDog.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
        return view;
    }
}