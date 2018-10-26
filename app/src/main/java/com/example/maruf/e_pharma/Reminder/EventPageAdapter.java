package com.example.maruf.e_pharma.Reminder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shuvo.medicare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by shuvo on 10/17/17.
 */

public class EventPageAdapter extends RecyclerView.Adapter<EventPageAdapter.ViewHolder> {

    private List<EventDetails> list;
    private Context context;
    private DatabaseReference mDatabase ,mDatabaseCount;
    private FirebaseAuth mAuth;
    public static  int position;
    public EventPageAdapter(List<EventDetails> list) {
        this.list = list;
        this.context = context;
        Log.d("check","eventName");
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text1,text2,text3,text4;
        public ImageButton delete,arrow,edit;
        public CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            text1=(TextView) itemView.findViewById(R.id.name_cvp);
            text2=(TextView) itemView.findViewById(R.id.date_cvp);
            text3=(TextView) itemView.findViewById(R.id.details_cvp);
            text4 = (TextView) itemView.findViewById(R.id.timet_cvp);
            delete = (ImageButton) itemView.findViewById(R.id.delete_cvp) ;
            cv=(CardView) itemView.findViewById(R.id.card_view_pill_final);

          //  edit = itemView.findViewById(R.id.edit_cvp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     position = getAdapterPosition();

                }
            });

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final EventDetails temp=list.get(position);
        holder.text1.setText(temp.getEventName());
        Log.d("naamm",temp.getEventName());
        holder.text2.setText(temp.getEventDate());
        holder.text4.setText(temp.getEventTime());
        holder.text3.setText(temp.getEventDescription());

        mAuth = FirebaseAuth.getInstance();
        String userID =  mAuth.getCurrentUser().getUid();



        final String eventID = temp.eventID;
        final String[] s = new String[1];




        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String eventId = list.get(position).eventID;
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

                if(EventDetails.tEventID != null || EventDetails.tEventID != "")
                {
                    EventDetails.tEventID = mDatabase.push().getKey();
                }
                mAuth = FirebaseAuth.getInstance();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Log.d("none",userId);
                final String openerID = mAuth.getCurrentUser().getUid();

                mDatabase.child(userId).child(eventID).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
