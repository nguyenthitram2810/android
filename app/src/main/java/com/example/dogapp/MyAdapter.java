package com.example.dogapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DogBreed> dogBreeds;
    private Context mContext;


    public MyAdapter(ArrayList<DogBreed> dogBreeds, Context mContext) {
        this.dogBreeds = dogBreeds;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(dogBreeds.get(position).url).into(holder.dogImage);
        holder.dogName.setText(dogBreeds.get(position).name);
        holder.dogBredFor.setText(dogBreeds.get(position).bredFor);
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView dogImage;
        public TextView dogName;
        public TextView dogBredFor;
        public LinearLayout cardDog;

        public MyViewHolder(@NonNull View view) {
            super(view);
            view = view;
            dogImage = view.findViewById(R.id.dog_img);
            dogName = view.findViewById(R.id.dog_name);
            dogBredFor = view.findViewById(R.id.dog_bred_for);
            cardDog = view.findViewById(R.id.card_dog);
            cardDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ListFragmentDirections.ActionListFragmentToDetailsFragment action = ListFragmentDirections.actionListFragmentToDetailsFragment(dogBreeds.get(position));
                    Navigation.findNavController(v).navigate(action);
                }
            });
//            dogImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    DogBreed dog = dogBreeds.get(getAdapterPosition());
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("dog", dog);
//                    DetailsFragment detailsFragment = new DetailsFragment();
//                    detailsFragment.setArguments(bundle);
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment, detailsFragment).addToBackStack(null).commit();
//                }
//            });
        }
    }
}
