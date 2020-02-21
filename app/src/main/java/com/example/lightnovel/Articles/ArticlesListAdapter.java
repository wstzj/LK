package com.example.lightnovel.Articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lightnovel.PlatePackage.PlateAdapter;
import com.example.lightnovel.R;

import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder>{
    private List<Articles> articlesList;
    private Context context;

    public ArticlesListAdapter(List<Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.articles_item,null,false);
        context=parent.getContext();
        ArticlesListAdapter.ViewHolder viewHolder=new ArticlesListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles articles = articlesList.get(position);

        holder.tag.setText(articles.getTag());
        holder.title.setText(articles.getTitle());
        holder.upDaterName.setText(articles.getUpDaterName());
        holder.lastRespondTime.setText(articles.getLastRespondTime());
        holder.urlForTitle.setText(articles.getUrlForTitle());

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    private ArticlesListAdapter.OnItemClickListener itemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v);
    }
    public void setItemClickListener(ArticlesListAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tag;
        private TextView title;
        private TextView lastRespondTime;
        private TextView upDaterName;
        private Button urlForTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tag=itemView.findViewById(R.id.tag);
            title=itemView.findViewById(R.id.title_name);
            lastRespondTime=itemView.findViewById(R.id.last_update_time);
            upDaterName=itemView.findViewById(R.id.upDater_Name);
            urlForTitle=itemView.findViewById(R.id.entering);

            urlForTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());

            }
        }
    }
}
