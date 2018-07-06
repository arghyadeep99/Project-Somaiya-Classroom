package com.example.somaiya.somaiyaclassroom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    RecyclerView recyclerView;
    ArrayList<String> contents = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public void update(String name, String url)
    {
        contents.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, RecyclerView recyclerView, ArrayList<String> contents, ArrayList<String> urls) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.contents = contents;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.download_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfFile.setText(contents.get(position));
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfFile;
        public ViewHolder(View itemView){
            super(itemView);
            nameOfFile=itemView.findViewById(R.id.nameoffile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildLayoutPosition(view);
                    //Uri uri = Uri.parse(urls.get(position));
                    Intent intent = new Intent();
                    //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    Log.e("URLS:",urls.get(position) + " " + contents.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }
}
