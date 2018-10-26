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


/**
 * A simple {@link Fragment} subclass.
 */
public class AmbulanceFragment extends Fragment implements AdapterView.OnItemLongClickListener {


    ListView listView;
    View view;

    private Context context;
    public static final String TAG="Tag";
    ContactsDatabaseAdapter contactsDatabaseAdapter;

    public AmbulanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ambulance, container, false);
        floatingActionBar(view);

        //textView=view.findViewById(R.id.testtextView);
        listView=view.findViewById(R.id.ambulance_details_list_view);
        this.view=view;

        return view;
    }

    //here items from ambulance table are showed in list view
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getContext();
        contactsDatabaseAdapter=new ContactsDatabaseAdapter(context);

        //LogToast.L(TAG,"form activity created");
        //textView.setText(contactsDatabaseAdapter.loadData());

        try{
            listView.setAdapter(contactsDatabaseAdapter.populateViewAmbulance());
        }catch (Exception e){
            //LogToast.L(TAG,"in catch statement"+e.toString());
        }

        // LogToast.L(TAG,"form activity created 2");
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                // do whatever you want
//                DocotorInfo docotorInfo=new DocotorInfo();
//                setFragment(docotorInfo);
                TextView textView=v.findViewById(R.id.driver_name);
                String driver_name=textView.getText().toString();
                //LogToast.L("ambulance",driver_name);
                Intent intent=new Intent(getActivity(),AmbulanceInfoActivity.class);
                intent.putExtra("a_name",driver_name);
                startActivity(intent);

                // ContactsDatabaseAdapter contactsDatabaseAdapter=new ContactsDatabaseAdapter(getContext());
                try{
                    contactsDatabaseAdapter.getParticularAmbulance(driver_name);
                }catch (Exception e){
                    //LogToast.L(TAG,e+"");
                }




                //LogToast.T(getContext(),i+" "+v.getId()+" "+a+" "+l);
            }
        });



    }

    @Override
    public void onDestroyView() {
        //mContainer.removeAllViews();
        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.ambulance_frame_layout);
        mContainer.removeAllViews();
        super.onDestroyView();
    }


    private void floatingActionBar(View view){
        FloatingActionButton fab = view.findViewById(R.id.add_ambulance_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context,"doctorfb",Toast.LENGTH_LONG).show();
                //Log.d("shanto","done");
                Intent intent=new Intent(getActivity(),AmbulanceAddActivity.class);
                startActivity(intent);
                //LogToast.L("ambulance","done");

            }
        });


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        return false;
    }

}
