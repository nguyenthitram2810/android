package com.example.dogapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailsFragment extends Fragment {
    private ImageView dogImage;
    private TextView bredFor;
    private TextView bredGroup;
    private TextView lifeSpan;
    private TextView origin;
    private TextView temperament;
    private TextView dogName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        dogImage = view.findViewById(R.id.dog_img);
        bredFor = view.findViewById(R.id.bred_for);
        bredGroup = view.findViewById(R.id.breed_group);
        lifeSpan = view.findViewById(R.id.life_span);
        origin = view.findViewById(R.id.origin);
        temperament = view.findViewById(R.id.temperament);
        dogName = view.findViewById(R.id.dog_name);
        DetailsFragmentArgs args = DetailsFragmentArgs.fromBundle(getArguments());
        DogBreed dogBreed = args.getDogObject();
        Picasso.get().load(dogBreed.url).into(dogImage);
        bredFor.setText(dogBreed.bredFor);
        bredGroup.setText(dogBreed.breedGroup);
        lifeSpan.setText(dogBreed.lifeSpan);
        origin.setText(dogBreed.origin);
        temperament.setText(dogBreed.temperament);
        dogName.setText(dogBreed.name);
        return view;
    }
}