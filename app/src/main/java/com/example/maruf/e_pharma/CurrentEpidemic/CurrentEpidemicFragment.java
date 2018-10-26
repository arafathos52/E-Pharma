package com.example.maruf.e_pharma.CurrentEpidemic;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentEpidemicFragment extends Fragment {


    public static final String TAG="Tag";

    TextView diseaseTitle;
    TextView diseaseDescription;
    TextView diseaseSymtom;
    TextView diseasePrevent;

    TextToSpeech textToSpeech;

    FloatingActionButton textToSpeechFab;
    FloatingActionButton main_fab;

    public CurrentEpidemicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_current_epidemic,container,false);

        diseaseTitle=view.findViewById(R.id.disease_title);
        diseaseDescription=view.findViewById(R.id.disease_description);
        diseaseSymtom=view.findViewById(R.id.disease_symptom);
        diseasePrevent=view.findViewById(R.id.disease_prevent);

        textToSpeechFab=view.findViewById(R.id.text_to_speech_fab);
        textToSpeechFab.canScrollVertically(0);
        //textToSpeechFab.setVisibility(View.GONE);

        //This portion will not be needed if we remove the main floating action bar
//        main_fab=getActivity().findViewById(R.id.fab);
//        main_fab.setVisibility(View.GONE);

        //main_fab.setImageResource(R.drawable.text_to_speech);

        Log.d(TAG,"onCreateView");



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG,"onActivityCreated");

        //diseaseDescription.setText(MyFirebaseMessagingService.RECEIVED_DATA);

        //jsonUtil(MyFirebaseMessagingService.RECEIVED_DATA);
        jsonUtil(readEpidemicData());

        //This is for text ot speech Start

        textToSpeech=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });


        final int[] fabTapCounter = {0};
        final boolean[] flag = {false};

        textToSpeechFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fabTapCounter[0]++;

                if(fabTapCounter[0]%2==1){
                    textToSpeechFab.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    textToSpeechHelper(diseaseTitle.getText().toString()+".");

//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    textToSpeechHelper("Description.");
                    textToSpeechHelper(diseaseDescription.getText().toString()+".");

//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    textToSpeechHelper("Symptom.");
                    textToSpeechHelper(diseaseSymtom.getText().toString()+".");

//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    textToSpeechHelper("Prevention.");
                    textToSpeechHelper(diseasePrevent.getText().toString()+".");


//                    flag[0] =true;
                }

                if(fabTapCounter[0]%2==0){
                    textToSpeechFab.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    if(textToSpeech!=null){
                        textToSpeech.stop();
                        //textToSpeech.shutdown();
                    }
                }

            }
        });

        //Text to speech end

        Log.d(TAG,"on activity created");

    }

    private void textToSpeechHelper(String toSpeak){
        textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_ADD,null);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(textToSpeech!=null){
            textToSpeech.stop();
            //textToSpeech.shutdown();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.shutdown();
        }
    }

    private class Disease{

        private String description;
        private String prevent;
        private String symptom;

        public Disease(String description, String prevent, String symptom) {
            this.description = description;
            this.prevent = prevent;
            this.symptom = symptom;
        }
    }

    public void jsonUtil(String string){
        try {
            JSONObject jsonObject=new JSONObject(string);

            String description=jsonObject.getString("description");
            String prevent=jsonObject.getString("prevent");
            String symptom=jsonObject.getString("symptom");
            String title=jsonObject.getString("title");

            Log.d(TAG,"jsonUtil before settext");

            diseaseDescription.setText(description);
            diseasePrevent.setText(prevent);
            diseaseSymtom.setText(symptom);
            diseaseTitle.setText(title);
            Log.d(TAG," after settext");

        } catch (JSONException e) {
            Log.d(TAG,e.toString());
        }

    }

    private String  readEpidemicData(){
        FileInputStream fileInputStream=null;
        StringBuffer stringBuffer=new StringBuffer();
        File myFile=new File(getContext().getFilesDir(),"epidemic.txt");

        try{
           fileInputStream=new FileInputStream(myFile);
            int read;

            while ((read=fileInputStream.read())!=-1){
                stringBuffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG,e.getMessage());

        } catch (IOException e) {
            //e.printStackTrace();
            Log.e(TAG,e.getMessage());
        }
        finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                    Log.e(TAG,e.getMessage());
                }
            }
        }
        return stringBuffer.toString();
    }
}


