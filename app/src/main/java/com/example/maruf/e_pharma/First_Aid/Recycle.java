package com.example.maruf.e_pharma.First_Aid;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

public class Recycle extends RecyclerView.Adapter<Recycle.ViewHolder>  {


    private String[] titles = {"Food Poisoning",
            "Dehydration",
            "Low Blood Pressure",
            "Stroke",
            "Drowning",
            "Burn",
            "Vomiting Blood",
            "Panic Attack",
            "Shortness of Breath",
            "Bleeding",
            "Gastric",
             "Electrick Shock"};


    private int[] images = {R.drawable.icons1,
            R.drawable.icons2,
            R.drawable.icons3,
            R.drawable.icons4,
            R.drawable.icons5,
            R.drawable.icons6,
            R.drawable.icons7,
            R.drawable.icons8,
            R.drawable.icons9,
            R.drawable.icons10,
            R.drawable.icons11,
            R.drawable.icons12};


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        private Context context;




        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);


           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String pos = Integer.toString(position);
                    Log.d("item", pos);
                    Intent intent = new Intent(v.getContext(),IntroActivity.class);
                    intent.putExtra("adapter",pos);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}


