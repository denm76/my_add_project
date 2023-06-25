package com.example.myadd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddsAdapter extends RecyclerView.Adapter <AddsAdapter.AddsViewHolder>{

    private List<Add> adds = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener;
    public ArrayList<Add> getAdds() {return new ArrayList<>(adds);}

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setAdds(List<Add> adds) {
        this.adds = adds;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.add_item,
                parent,
                false
        );
        return new AddsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddsViewHolder viewHolder, int position) {
        Add add = adds.get(position);
        TextView textViewTitle = viewHolder.linearAddItem.findViewById(R.id.textViewTitle);
        TextView textViewDescription = viewHolder.linearAddItem.findViewById(R.id.textViewDescription);
        TextView textViewPrice = viewHolder.linearAddItem.findViewById(R.id.textViewPrice);
        textViewTitle.setText(add.getTitle());
        textViewDescription.setText(add.getText());
        textViewPrice.setText(add.getPrice());

        int colorResId;
        switch (add.getPriority()) {
            case 0:
                colorResId = android.R.color.holo_green_light;
                break;
            case 1:
                colorResId = android.R.color.holo_orange_light;
                break;
            default:
                colorResId = android.R.color.holo_red_light;
        }

        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);
        viewHolder.linearAddItem.setBackgroundColor(color);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClickListener != null) {
                    onNoteClickListener.onNoteClick(add);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return adds.size();
    }

    static class AddsViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearAddItem;

        public AddsViewHolder(@NonNull View itemView) {
            super(itemView);
            linearAddItem = itemView.findViewById(R.id.linearAddItem);
        }
    }

    interface OnNoteClickListener {
        void onNoteClick(Add add);
    }


}
