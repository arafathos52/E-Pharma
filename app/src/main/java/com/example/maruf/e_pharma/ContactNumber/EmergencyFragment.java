package com.example.maruf.e_pharma.ContactNumber;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

import static com.example.shuvo.medicare.R.id.emergency_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyFragment extends Fragment implements AdapterView.OnItemLongClickListener{


    ListView listView;

    View view;

    private Context context;
    public static final String TAG="Tag";
    ContactsDatabaseAdapter contactsDatabaseAdapter;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_emergency, container, false);
        floatingActionBar(view);

        listView=view.findViewById(R.id.emergency_details_list_view);


        this.view=view;

        return view;
    }


    //here items from emergency table are showed in list view
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getContext();
        contactsDatabaseAdapter=new ContactsDatabaseAdapter(context);

        //LogToast.L(TAG,"form activity created");

        try{
            listView.setAdapter(contactsDatabaseAdapter.populateViewEmergency());
        }catch (Exception e){
            //LogToast.L(TAG,"in catch statement"+e.toString());
        }

        // LogToast.L(TAG,"form activity created 2");
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {

                TextView textView=v.findViewById(emergency_name);
                String type_name=textView.getText().toString();
                //LogToast.L("ambulance",type_name);
                Intent intent=new Intent(getActivity(),EmergencyInfoActivity.class);
                intent.putExtra("e_name",type_name);
                startActivity(intent);

                try{
                    contactsDatabaseAdapter.getParticularEmergency(type_name);
                }catch (Exception e){
                    //LogToast.L(TAG,e+"");
                }


            }
        });



    }

    @Override
    public void onDestroyView() {
        //mContainer.removeAllViews();
        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.emergency_frame_layout);
        mContainer.removeAllViews();
        super.onDestroyView();
    }


    private void floatingActionBar(View view){
        FloatingActionButton fab = view.findViewById(R.id.add_emergency_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context,"doctorfb",Toast.LENGTH_LONG).show()
                Intent intent=new Intent(getActivity(),EmergencyAddActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        return false;
    }
}
