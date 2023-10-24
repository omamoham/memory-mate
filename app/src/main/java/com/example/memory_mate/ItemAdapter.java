package com.example.memory_mate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private OnItemClickListener onItemClickListener;

    public ItemAdapter(OnItemClickListener listener) {
        this.items = new ArrayList<>();
        this.onItemClickListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        String itemNameWithPrefix = "Item Name: " + item.getName();
        holder.itemName.setText(itemNameWithPrefix);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Item item = items.get(position);
                        onItemClickListener.onItemClick(item);
                    }
                }
            });
        }
    }
}


