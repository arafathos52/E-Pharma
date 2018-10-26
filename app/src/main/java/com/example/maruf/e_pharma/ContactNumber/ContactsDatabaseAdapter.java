package com.example.maruf.e_pharma.ContactNumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import com.example.shuvo.medicare.R;

import java.util.HashMap;


/**
 * Created by shanto on 9/21/17.
 * this class is used for inserting & fetching data form database
 */

public class ContactsDatabaseAdapter {

    ContactsDatabaseHelper contactsDatabaseHelper;
    private SQLiteDatabase db;
    Context context;

    private static final String TAG="DatabaseTest";

    public ContactsDatabaseAdapter(Context context){
        contactsDatabaseHelper=new ContactsDatabaseHelper(context);
        this.context=context;
    }

    //insert single doctor data
    public long insertDoctorData(String name,String speciality,String phone,String email,String schedule,String address){
        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(contactsDatabaseHelper.DOCTOR_NAME,name);
        contentValues.put(contactsDatabaseHelper.SPECIALITY,speciality);
        contentValues.put(contactsDatabaseHelper.MOBILE,phone);
        contentValues.put(contactsDatabaseHelper.EMAIL,email);
        contentValues.put(contactsDatabaseHelper.SCHEDULE,schedule);
        contentValues.put(contactsDatabaseHelper.ADDRESS,address);

        long id=database.insert(contactsDatabaseHelper.DOCTOR_TABLE,contactsDatabaseHelper.ADDRESS,contentValues);

        return id;

    }

    //insert single ambulance data
    public long insertAmbulanceData(String name,String phone,String address){
        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(contactsDatabaseHelper.DRIVER_NAME,name);
        contentValues.put(contactsDatabaseHelper.aMOBILE,phone);
        contentValues.put(contactsDatabaseHelper.aADDRESS,address);

        //TODO : contactsDatabaseHelper.aADDRESS why used specificaly in insertion

        long id=database.insert(contactsDatabaseHelper.AMBULANCE_TABLE,contactsDatabaseHelper.aADDRESS,contentValues);

        return id;

    }

    //insert single emergency data
    public long insertEmergencyData(String name,String phone){
        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(contactsDatabaseHelper.TYPE,name);
        contentValues.put(contactsDatabaseHelper.eMOBILE,phone);

        long id=database.insert(contactsDatabaseHelper.EMERGENCY_TABLE,contactsDatabaseHelper.eMOBILE,contentValues);

        return id;

    }


    //fetch data for doctor list view
    public SimpleCursorAdapter populateView(){
        Cursor cursor=this.getAllRows();

        //LogToast.L(TAG,"form populate 1");

        String fromFieldName[]={contactsDatabaseHelper.DOCTOR_NAME,contactsDatabaseHelper.SPECIALITY};
        int toViewIDs[]={R.id.name, R.id.id};

        SimpleCursorAdapter mCursorAdapter=
                new SimpleCursorAdapter(context, R.layout.list_view_template,
                        cursor,
                        fromFieldName, toViewIDs);
        if(mCursorAdapter==null)
        {
            //LogToast.L(TAG,"form populate 2"+mCursorAdapter);
        }

        return mCursorAdapter;
    }

    //fetch data for ambulance list view
    public SimpleCursorAdapter populateViewAmbulance(){
        Cursor cursor=this.getAllRowsAmbulance();
        //startManagingCursor(cursor);

        //LogToast.L(TAG,"form populate 1");

        //TODO : replace this R.id.name and R.id.id with ambulance rest of the method is to changed

        String fromFieldName[]={contactsDatabaseHelper.DRIVER_NAME,contactsDatabaseHelper.aADDRESS};
        int toViewIDs[]={R.id.driver_name, R.id.driver_id};

        SimpleCursorAdapter mCursorAdapter=
                new SimpleCursorAdapter(context, R.layout.list_view_ambulance_template,
                        cursor,
                        fromFieldName, toViewIDs);
        if(mCursorAdapter==null)
        {
            //LogToast.L(TAG,"form populate 2"+mCursorAdapter);
        }
        return mCursorAdapter;
    }

    //fetch data for emergency list view
    public SimpleCursorAdapter populateViewEmergency(){
        Cursor cursor=this.getAllRowsEmergency();
        //startManagingCursor(cursor);

        //LogToast.L(TAG,"form populate 1");

        //TODO : replace this R.id.name and R.id.id with ambulance rest of the method is to changed

        String fromFieldName[]={contactsDatabaseHelper.TYPE,contactsDatabaseHelper.aMOBILE};
        int toViewIDs[]={R.id.emergency_name, R.id.emergency_id};

        SimpleCursorAdapter mCursorAdapter=
                new SimpleCursorAdapter(context, R.layout.list_view_emergency_template,
                        cursor,
                        fromFieldName, toViewIDs);
        if(mCursorAdapter==null)
        {
            //LogToast.L(TAG,"form populate 2"+mCursorAdapter);
        }
        return mCursorAdapter;
    }

    //fetch all row  of doctor
    public Cursor getAllRows(){
        SQLiteDatabase db=contactsDatabaseHelper.getWritableDatabase();
        String where=null;
        String columns[]={"rowid _id",contactsDatabaseHelper.UID,contactsDatabaseHelper.DOCTOR_NAME,contactsDatabaseHelper.SPECIALITY,
                          };
        Cursor cursor=db.query(contactsDatabaseHelper.DOCTOR_TABLE,columns,where,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //fetch all row  of ambulance
    public Cursor getAllRowsAmbulance(){
        SQLiteDatabase db=contactsDatabaseHelper.getWritableDatabase();
        String where=null;
        String columns[]={"rowid _id",contactsDatabaseHelper.aUID,contactsDatabaseHelper.DRIVER_NAME,contactsDatabaseHelper.aADDRESS,
        };
        Cursor cursor=db.query(contactsDatabaseHelper.AMBULANCE_TABLE,columns,where,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //fetch all row  of emergency
    public Cursor getAllRowsEmergency(){
        SQLiteDatabase db=contactsDatabaseHelper.getWritableDatabase();
        String where=null;
        String columns[]={"rowid _id",contactsDatabaseHelper.eUID,contactsDatabaseHelper.TYPE,contactsDatabaseHelper.eMOBILE,
        };
        Cursor cursor=db.query(contactsDatabaseHelper.EMERGENCY_TABLE,columns,where,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //when a single doctor info is needed to show,this method is called form DoctorInfoActivity
    public HashMap getParticularDoctor(String doctorName){

       SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        String columns[]={contactsDatabaseHelper.UID,contactsDatabaseHelper.DOCTOR_NAME,contactsDatabaseHelper.SPECIALITY,contactsDatabaseHelper.MOBILE,
          contactsDatabaseHelper.EMAIL,contactsDatabaseHelper.ADDRESS,contactsDatabaseHelper.SCHEDULE};
        Cursor cursor=database.query(contactsDatabaseHelper.DOCTOR_TABLE,columns,contactsDatabaseHelper.DOCTOR_NAME+" = '"+doctorName+"'",null,null,null,null);

        HashMap<String,String > hashMap=new HashMap<String, String>();

        while (cursor.moveToNext()){
            //LogToast.L("Tag",cursor.getString(0));
            //LogToast.L("Tag","test");
            hashMap.put("uid",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.UID)));
            hashMap.put("name",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.DOCTOR_NAME)));
            hashMap.put("speciality",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.SPECIALITY)));
            hashMap.put("mobile",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.MOBILE)));
            hashMap.put("email",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.EMAIL)));
            hashMap.put("address",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.ADDRESS)));
            hashMap.put("schedule",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.SCHEDULE)));

           //name=cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.DOCTOR_NAME));
            //hashMap.put("name","done");
        }

        //LogToast.L("Tag",cursor.getString(1));
        //LogToast.L("ambulance",hashMap.get("name")+" forom hash" +hashMap.get("uid"));
        return hashMap;
    }

    //when a single ambulance info is needed to show,this method is called form AmbulanceInfoActivity
    public HashMap getParticularAmbulance(String driverName){

        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        String columns[]={contactsDatabaseHelper.DRIVER_NAME,contactsDatabaseHelper.aMOBILE,contactsDatabaseHelper.aADDRESS};
        Cursor cursor=database.query(contactsDatabaseHelper.AMBULANCE_TABLE,columns,contactsDatabaseHelper.DRIVER_NAME+" = '"+driverName+"'",null,null,null,null);

        HashMap<String,String > hashMap=new HashMap<String, String>();

        while (cursor.moveToNext()){
            //LogToast.L("Tag",cursor.getString(0));
            //LogToast.L("Tag","test");
            hashMap.put("name",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.DRIVER_NAME)));
            hashMap.put("mobile",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.aMOBILE)));
            hashMap.put("address",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.aADDRESS)));

        }

        //LogToast.L("ambulance",hashMap.get("name")+" forom hash" +hashMap.get("mobile"));
        //LogToast.L("Tag",cursor.getString(1));
        return hashMap;
    }

    //when a single emergency info is needed to show,this method is called for EmergencyInfoActivity
    public HashMap getParticularEmergency(String type){

        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        String columns[]={contactsDatabaseHelper.TYPE,contactsDatabaseHelper.aMOBILE};
        Cursor cursor=database.query(contactsDatabaseHelper.EMERGENCY_TABLE,columns,contactsDatabaseHelper.TYPE+" = '"+type+"'",null,null,null,null);

        HashMap<String,String > hashMap=new HashMap<String, String>();

        while (cursor.moveToNext()){
            //LogToast.L("Tag",cursor.getString(0));
            //LogToast.L("Tag","test");
            hashMap.put("name",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.TYPE)));
            hashMap.put("mobile",cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.eMOBILE)));
        }

        //LogToast.L("Tag",cursor.getString(1));
        //LogToast.L("ambulance",hashMap.get("name")+" forom hash" +hashMap.get("mobile"));
        return hashMap;
    }

    public void close(){
        contactsDatabaseHelper.close();
    }


    static class ContactsDatabaseHelper extends SQLiteOpenHelper{


        Context context;
        public static String TAG="Tag";


        private static final String CONTACTS_DATABASE="ContactsDatabase.db";
        private static final int DATABASE_VER=1;

        //columns for doctro table
        private static final String DOCTOR_TABLE="doctortable";
        private static final String UID="_uid";
        private static final String DOCTOR_NAME="name";
        private static final String SPECIALITY="speciality";
        private static final String MOBILE="mobile_num";
        private static final String EMAIL="email";
        private static final String ADDRESS="address";
        private static final String SCHEDULE="schedule";
        private static final String COMMA_SEP=" , ";

        public static String getUID() {
            return UID;
        }

        public static String getaUID() {
            return aUID;
        }

        public static String geteUID() {
            return eUID;
        }

        public static String getDoctorTable() {
            return DOCTOR_TABLE;
        }

        public static String getAmbulanceTable() {
            return AMBULANCE_TABLE;
        }

        public static String getEmergencyTable() {
            return EMERGENCY_TABLE;
        }

        //columns for ambulance table
        private static final String AMBULANCE_TABLE="ambulancetable";
        private static final String aUID="_uid";
        private static final String DRIVER_NAME="name";
        private static final String aMOBILE="mobile_num";
        private static final String aADDRESS="address";

        //columns for emergency table
        private static final String EMERGENCY_TABLE="emergencytable";
        private static final String eUID="_uid";
        private static final String TYPE="emergency_type";
        private static final String eMOBILE="mobile_num";

        public static String getDoctorName() {
            return DOCTOR_NAME;
        }

        public static String getDriverName() {
            return DRIVER_NAME;
        }

        public static String getTYPE() {
            return TYPE;
        }

        private static final String CREATE_DOCTOR_TABLE="CREATE TABLE "+DOCTOR_TABLE+" ("+
                UID+" INTEGER "+COMMA_SEP+DOCTOR_NAME+" VARCHAR(20) PRIMARY KEY "+
                COMMA_SEP+SPECIALITY+" VARCHAR(20)"+COMMA_SEP+MOBILE+" VARCHAR(15)"+COMMA_SEP+
                EMAIL+" VARCHAR(25)"+COMMA_SEP+SCHEDULE+" VARCHAR(30)"+COMMA_SEP+ADDRESS+" VARCHAR(40));";

        private static final String CREATE_AMBULANCE_TABLE="CREATE TABLE "+AMBULANCE_TABLE+" ("+
                aUID+" INTEGER "+COMMA_SEP+DRIVER_NAME+" VARCHAR(20) PRIMARY KEY "+
                COMMA_SEP+aMOBILE+" VARCHAR(20)"+COMMA_SEP+ADDRESS+" VARCHAR(40));";

        private static final String CREATE_EMERGENCY_TABLE="CREATE TABLE "+EMERGENCY_TABLE+" ("+
                eUID+" INTEGER "+COMMA_SEP+TYPE+" VARCHAR(20) PRIMARY KEY "+
                COMMA_SEP+eMOBILE+" VARCHAR(20));";


        public ContactsDatabaseHelper(Context context) {
            super(context, CONTACTS_DATABASE,null, DATABASE_VER);
            this.context=context;
            //LogToast.T(context,"constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                sqLiteDatabase.execSQL(CREATE_DOCTOR_TABLE);
                sqLiteDatabase.execSQL(CREATE_AMBULANCE_TABLE);
                //LogToast.M(context,"successfull doctor");
                //LogToast.L(TAG,"ambu");
                sqLiteDatabase.execSQL(CREATE_EMERGENCY_TABLE);
                //LogToast.T(context,"successfull doctor");
                //LogToast.L(TAG,"emer");
            }catch (SQLException e){
                LogToast.L(TAG,e.getMessage());
                LogToast.T(context,e.getMessage());
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


    }

}
