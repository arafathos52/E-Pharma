package com.example.maruf.e_pharma.User_Profile;

/**
 * Created by shuvo on 11/3/17.
 */

public class profile_data {
     static String  tName;
     static String tGmail;
     static String tEventID;
     static String tOpenerID;
    static String tAge;
    static String tHeight;
     static String tMobile;
    private static String tGender;

    private String name;
    private String gmail;
    private String openerID;
    private String eventID;
    private String age;
    private String height;
    private String mobile;
    private String gender;

    public profile_data()
    {
        setGender(name=gmail=age=height=mobile="");
        eventID=openerID="";
    }

    profile_data(String name,String gmail,String age,String height,String mobile,String gender,String eventID,String openerID)
    {
        this.setName(name);
        this.setGmail(gmail);
        this.setAge(age);
        this.setHeight(height);
        this.setMobile(mobile);
        this.setGender(gender);
        this.setEventID(eventID);
        this.setOpenerID(openerID);
    }


    public static String gettName() {
        return tName;
    }

    public static void settName(String tName) {
        profile_data.tName = tName;
    }

    public static String gettGmail() {
        return tGmail;
    }

    public static void settGmail(String tGmail) {
        profile_data.tGmail = tGmail;
    }

    public static String gettEventID() {
        return tEventID;
    }

    public static void settEventID(String tEventID) {
        profile_data.tEventID = tEventID;
    }

    public static String gettOpenerID() {
        return tOpenerID;
    }

    public static void settOpenerID(String tOpenerID) {
        profile_data.tOpenerID = tOpenerID;
    }

    public static String gettAge() {
        return tAge;
    }

    public static void settAge(String tAge) {
        profile_data.tAge = tAge;
    }

    public static String gettHeight() {
        return tHeight;
    }

    public static void settHeight(String tHeight) {
        profile_data.tHeight = tHeight;
    }

    public static String gettMobile() {
        return tMobile;
    }

    public static void settMobile(String tMobile) {
        profile_data.tMobile = tMobile;
    }

    public static String gettGender() {
        return tGender;
    }

    public static void settGender(String tGender) {
        profile_data.tGender = tGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getOpenerID() {
        return openerID;
    }

    public void setOpenerID(String openerID) {
        this.openerID = openerID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
