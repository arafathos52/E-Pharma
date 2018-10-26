package com.example.maruf.e_pharma.MediSearch;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shuvo.medicare.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMain extends Fragment {

    View view;
    private RecyclerView rvWord;
    private Word_Adapter word_adapter;
    private List<DictionaryModel> dictionaryModelList;
    private DatabaseHelper mDBHelper;
    public SearchMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_main, container, false);
        rvWord=(RecyclerView) view.findViewById(R.id.rvWord);
        rvWord.setLayoutManager(new LinearLayoutManager(this.getContext()));


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvWord.getContext(),DividerItemDecoration.VERTICAL);
        rvWord.addItemDecoration(dividerItemDecoration);
        mDBHelper=new DatabaseHelper(this.getContext());

        File database=getContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists() == false){
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this.getActivity())){
               // Toast.makeText(getContext(),"Copy success",Toast.LENGTH_LONG).show();
            }else {
                //Toast.makeText(getContext(),"Copy failed",Toast.LENGTH_LONG).show();

            }
        }

        dictionaryModelList=mDBHelper.getListWord("");

        word_adapter=new Word_Adapter();
        word_adapter.setData(dictionaryModelList);
        rvWord.setAdapter(word_adapter);

        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchWord(newText);
                return false;

            }
        });
        return  view;
    }

    private void searchWord(String wordSearch)
    {
        dictionaryModelList.clear();
        dictionaryModelList=mDBHelper.getListWord(wordSearch);
        word_adapter.setData(dictionaryModelList);
        rvWord.setAdapter(word_adapter);
    }
    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff)) >0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Database","Copy Success");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
