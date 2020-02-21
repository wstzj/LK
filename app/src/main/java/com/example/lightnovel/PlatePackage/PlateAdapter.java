package com.example.lightnovel.PlatePackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lightnovel.R;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.ViewHolder>  {

    private List<Plate> plateList;
    private Context context;


    public PlateAdapter(List<Plate> plateList, Context context) {
        this.plateList = plateList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.plate_item,null,false);
        context=parent.getContext();
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plate plate =plateList.get(position);
        Glide.with(context).load(plate.getUrlForImage()).into(holder.plateImage);
        holder.lastUpdate.setText(plate.getLastUpdate());
        holder.plateName.setText(plate.getName());

        holder.enterButton.setText(plate.getUrlForPlate());

    }

    @Override
    public int getItemCount() {
        return plateList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v);
    }

    private OnItemClickListener itemClickListener;


    public void setItemClickListener(PlateAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView plateImage;

        TextView plateName;
        TextView  lastUpdate;

        Button enterButton;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            plateImage=itemView.findViewById(R.id.plate_image);
            plateName =itemView.findViewById(R.id.plate_name);
            lastUpdate=itemView.findViewById(R.id.last_respond_text);
            enterButton=itemView.findViewById(R.id.plate_entering);

            itemView.setOnClickListener(this);
            lastUpdate.setOnClickListener(this);
            enterButton.setOnClickListener(this);


        }
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {

                itemClickListener.onItemClick(v, getAdapterPosition());

            }
        }
    }






}
