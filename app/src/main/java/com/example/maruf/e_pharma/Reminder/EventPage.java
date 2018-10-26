package com.example.maruf.e_pharma.Reminder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.shuvo.medicare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EventPage extends Fragment {
    private static final String TAG = "MAIN";
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private FloatingActionButton fav;
    private ImageButton plus, edit;
    private ImageButton arrow;
    private FirebaseAuth mAuth;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    private DatabaseReference mDatabase, status;

    List<EventDetails> listEvent = new ArrayList<EventDetails>();
    ArrayList<String> listName = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_page, container, false);
        super.onCreate(savedInstanceState);



        recyclerView =
                view.findViewById(R.id.recycler_view_reminder);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        fav = (FloatingActionButton) view.findViewById(R.id.eventAdd);
        fav.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        fav.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));
       // edit = (ImageButton) view.findViewById(R.id.edit_cvp);

        final ProgressDialog dialog = null;
        //dialog = ProgressDialog.show(this.getContext(), "", "Loading", true);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetails.tName = null;
                EventDetails.tDate = null;
                EventDetails.tTime = null;
                EventDetails.tDescription = null;
                EventDetails.tEventID = null;
                EventDetails.tOpenerID = null;
                Intent intent = new Intent(view.getContext(), CreateEvents.class);
                intent.putExtra("tag", true);

                startActivity(intent);
            }
        });
      String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events").child(userId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listName.clear();
                listEvent.clear();
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    EventDetails temp = new EventDetails();
                    temp = users.getValue(EventDetails.class);
                    String key = dataSnapshot.getKey();
                    final String openerID = mAuth.getCurrentUser().getUid();
                    if (!temp.eventName.equals("") ) {
                        listEvent.add(temp);
                        listName.add(temp.eventName);
                    }
                    Log.d("check",temp.eventName);
                }
                adapter = new EventPageAdapter(listEvent);
                recyclerView.setAdapter(adapter);
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();





     return view;
    }
}
