package com.example.foodie.managers;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodie.databinding.PantryItemCardBinding;
import com.example.foodie.models.PantryItem;

import java.util.List;
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder>{
    private List<PantryItem> pantryList;
    private OnPantryItemClickListener listener;

    public PantryAdapter(List<PantryItem> pantryList, OnPantryItemClickListener listener){
        this.pantryList = pantryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        PantryItemCardBinding binding = PantryItemCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new PantryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position){
        PantryItem pantryItem = pantryList.get(position);
        holder.bind(pantryItem);
    }

    @Override
    public int getItemCount(){
        return pantryList.size();
    }

    // ViewHolder inner class
    public class PantryViewHolder extends RecyclerView.ViewHolder {
        private PantryItemCardBinding binding;

        public PantryViewHolder(PantryItemCardBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PantryItem pantryItem){
            binding.pantryItemName.setText(pantryItem.getName());
            binding.pantryItemQuantity.setText(pantryItem.getQuantity());

            // handling clicks on pantryItem items
            itemView.setOnClickListener(v -> {
                if(listener!=null){
                    listener.onPantryClick(pantryItem);
                }
            });
        }
    }

    // Interface for click handling
    public interface OnPantryItemClickListener{
        void onPantryClick(PantryItem pantryItem);
    }

    // updating the list dynamically
    public void updateList(List<PantryItem> newList){
        this.pantryList = newList;
        notifyDataSetChanged();
    }
}
