package com.example.maruf.e_pharma.MediSearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

import java.util.List;


public class Word_Adapter extends RecyclerView.Adapter<Word_Adapter.ViewHolder> {

    public List<DictionaryModel> data;

    public Word_Adapter(){}
    public void setData(List<DictionaryModel> data){
        this.data=data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View wordView=inflater.inflate(R.layout.word_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(wordView,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       DictionaryModel dictionaryModel=data.get(position);
        holder.wordText.setText(dictionaryModel.getMedicine());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public TextView wordText;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context=context;
            wordText=(TextView) itemView.findViewById(R.id.wordtext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DictionaryModel dictionaryModel = data.get(position);
                    Intent intent = new Intent(context, DefinationActivity.class);
                    intent.putExtra("Medicine",dictionaryModel.getMedicine());
                    intent.putExtra("Generic",dictionaryModel.getGeneric());
                    intent.putExtra("Used For",dictionaryModel.getUses());
                    intent.putExtra("Side Effects",dictionaryModel.getSideEffects());
                    intent.putExtra("Dosage",dictionaryModel.getDosage());
                    intent.putExtra("Brand_Names",dictionaryModel.getBrand_Names());
                    context.startActivity(intent);

                }
            });


        }
    }
}
