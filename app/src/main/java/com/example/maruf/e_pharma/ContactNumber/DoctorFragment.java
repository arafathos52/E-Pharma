package com.example.maruf.e_pharma.ContactNumber;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
public class DoctorFragment extends Fragment implements AdapterView.OnItemLongClickListener{


    ListView listView;
    View view;

    ContactsDatabaseAdapter.ContactsDatabaseHelper mContactsDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;


    private Context context;
    public static final String TAG="Tag";
    ContactsDatabaseAdapter contactsDatabaseAdapter;
    public DoctorFragment() {
        // Required empty public constructor


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doctor, container, false);
        floatingActionBar(view);

        listView=view.findViewById(R.id.doctor_details_list_view);


        this.view=view;

        return view;
    }

    //here items from doctor table are showed in list view
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getContext();
        contactsDatabaseAdapter=new ContactsDatabaseAdapter(context);

        mContactsDatabaseHelper=new ContactsDatabaseAdapter.ContactsDatabaseHelper(getContext());
        mSqLiteDatabase=mContactsDatabaseHelper.getWritableDatabase();


        //LogToast.L(TAG,"form activity created");
        //textView.setText(contactsDatabaseAdapter.loadData());

        try{
            listView.setAdapter(contactsDatabaseAdapter.populateView());
        }catch (Exception e){
            //LogToast.L(TAG,"in catch statement"+e.toString());
        }

       // LogToast.L(TAG,"form activity created 2");
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {

                TextView textView=v.findViewById(R.id.name);
                String doctor_name=textView.getText().toString();
                //LogToast.L("ambulance",doctor_name);
                Intent intent=new Intent(getActivity(),DoctorInfoActivity.class);
                intent.putExtra("d_name",doctor_name);
                startActivity(intent);

               //ContactsDatabaseAdapter contactsDatabaseAdapter=new ContactsDatabaseAdapter(getContext());
                try{
                    contactsDatabaseAdapter.getParticularDoctor(doctor_name);
                }catch (Exception e){
                    //LogToast.L(TAG,e+"");
                }

            }
        });




    }

    @Override
    public void onDestroyView() {
        //mContainer.removeAllViews();
        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.doctor_frame_layout);
        mContainer.removeAllViews();
        super.onDestroyView();
    }


    private void floatingActionBar(View view){
        FloatingActionButton fab = view.findViewById(R.id.add_doctor_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context,"doctorfb",Toast.LENGTH_LONG).show();
                //Log.d("shanto","done");
                Intent intent=new Intent(getActivity(),DoctorAddActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        return false;
    }
}
