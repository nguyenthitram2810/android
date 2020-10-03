package com.example.dogapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DogBreed> dogBreeds;
    private ArrayList<DogBreed> dogBreeds1;
    private ArrayList<DogBreed> dogBreeds2;
    private Context mContext;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public MyAdapter(ArrayList<DogBreed> dogBreeds, Context mContext) {
        this.dogBreeds = dogBreeds;
        this.mContext = mContext;
        this.dogBreeds1 = new ArrayList<DogBreed>(dogBreeds);
        this.dogBreeds2 = new ArrayList<DogBreed>(dogBreeds);
        dogBreeds1 = dogBreeds;
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
        viewBinderHelper.setOpenOnlyOne(true);
        Picasso.get().load(dogBreeds.get(position).url).placeholder(R.drawable.loading_image).into(holder.dogImage);
        holder.dogName.setText(dogBreeds.get(position).name);
        holder.dogBredFor.setText(dogBreeds.get(position).bredFor);
        holder.tvInfo.setText("Lifespan: "+dogBreeds.get(position).lifeSpan+"\n Origin: " +
                dogBreeds.get(position).origin);
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<DogBreed> filteredList = new ArrayList<DogBreed>();
            Log.d("DEBUG4", String.valueOf((ArrayList) dogBreeds1));
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dogBreeds1);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DogBreed item : dogBreeds1) {
                    if (item.name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            Log.d("DEBUG20",String.valueOf((List) results.values));
            Log.d("DEBUG22", String.valueOf((List) dogBreeds1));
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d("DEBUG21", (String) constraint);
            dogBreeds2 = (ArrayList<DogBreed>) results.values;
            // mContacts.addAll((List) results.values);
            dogBreeds = dogBreeds2;
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView dogImage;
        public TextView dogName;
        public TextView dogBredFor;
        public LinearLayout cardDog;
        public TextView tvInfo;

        public MyViewHolder(@NonNull View view) {
            super(view);
            view = view;
            dogImage = view.findViewById(R.id.dog_img);
            dogName = view.findViewById(R.id.dog_name);
            dogBredFor = view.findViewById(R.id.dog_bred_for);
            cardDog = view.findViewById(R.id.card_dog);
            tvInfo = view.findViewById(R.id.tv_info);
            cardDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ListFragmentDirections.ActionListFragmentToDetailsFragment action = ListFragmentDirections.actionListFragmentToDetailsFragment(dogBreeds.get(position));
                    Navigation.findNavController(v).navigate(action);
                }
            });
        }
    }
}
