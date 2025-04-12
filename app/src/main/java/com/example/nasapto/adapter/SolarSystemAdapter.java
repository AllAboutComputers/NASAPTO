package com.example.nasapto.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasapto.pojo.SolarSystemItem;
import com.example.nasapto.databinding.RowMultipleItemBinding;
import com.example.nasapto.databinding.RowSingleItemBinding;

import java.util.ArrayList;

public class SolarSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SINGLE = 0;
    private static final int TYPE_MULTIPLE = 1;
    private final ArrayList<SolarSystemItem> items;
    private final ItemClickedListener itemClickedListener;

    public SolarSystemAdapter(ArrayList<SolarSystemItem> items, ItemClickedListener itemClickedListener) {
        this.items = items;
        this.itemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_MULTIPLE) {
            return new MultipleItemViewHolder(RowMultipleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
        return new SingleItemViewHolder(RowSingleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MultipleItemViewHolder) {
            ((MultipleItemViewHolder) holder).binding.imageViewBg.setImageResource(items.get(position).imageBg);
            ((MultipleItemViewHolder) holder).binding.textViewTitle.setText(items.get(position).title);
            ((MultipleItemViewHolder) holder).binding.textViewTitle2.setText(items.get(position).title2);
            ((MultipleItemViewHolder) holder).binding.textViewTitle2.setOnClickListener(view -> itemClickedListener.itemClickedInternal(items.get(position)));
        } else if (holder instanceof SingleItemViewHolder) {
            ((SingleItemViewHolder) holder).binding.imageViewBg.setImageResource(items.get(position).imageBg);
            ((SingleItemViewHolder) holder).binding.textViewTitle.setText(items.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
       /* if (items.get(position).title.equals("Earth")) {
            return TYPE_MULTIPLE;
        }*/
        return TYPE_SINGLE;
    }

    public class SingleItemViewHolder extends RecyclerView.ViewHolder {
        private RowSingleItemBinding binding;

        public SingleItemViewHolder(@NonNull RowSingleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> itemClickedListener.itemClicked(items.get(getAdapterPosition())));
        }
    }

    public class MultipleItemViewHolder extends RecyclerView.ViewHolder {
        private RowMultipleItemBinding binding;

        public MultipleItemViewHolder(@NonNull RowMultipleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> itemClickedListener.itemClicked(items.get(getAdapterPosition())));
        }
    }
}
