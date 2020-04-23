package com.example.httputilwithhttpurlconnection;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httputilwithhttpurlconnection.image.DefaultCache;
import com.example.httputilwithhttpurlconnection.image.ImageCache;
import com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.example.httputilwithhttpurlconnection.image.MemoryCache;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {
   private ImageLoader loader;
   private Context context;
   private List<String> urlList;
   private ImageCache cache;
   public static final String TAG = "MyAdapter";
   public MyAdapter(Context context, List<String> list){
       this.context = context;
       urlList = list;
       if (loader == null){
           loader = new ImageLoader(MemoryCache.getInstance(),R.drawable.ic_load,R.drawable.ic_fail);
       }
   }
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        MyViewholder myViewholder = new MyViewholder(view);
        Log.d(TAG,"OnCreate");
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
      loader.display(holder.imageView,urlList.get(position),10,10);
        Log.d(TAG,"Bind");
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
