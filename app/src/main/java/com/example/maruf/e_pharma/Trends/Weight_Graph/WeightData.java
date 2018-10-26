package com.example.maruf.e_pharma.Trends.Weight_Graph;

/**
 * Created by shuvo on 10/27/17.
 */

public class WeightData {
    static String  tWeight;
    static String tDate;
    static String tEventID;
    static String tOpenerID;
    private String weight;
    private String Date;
    private String openerID;
    private String eventID;

    public WeightData()
    {
        weight=Date="";
        eventID=openerID="";
    }

    WeightData(String Date,String weight,String eventID,String openerID)
    {
        this.Date=Date;
        this.weight=weight;
        this.eventID=eventID;
        this.openerID=openerID;
    }

    public static String gettWeight() {
        return tWeight;
    }

    public static void settWeight(String tWeight) {
        WeightData.tWeight = tWeight;
    }

    public static String gettDate() {
        return tDate;
    }

    public static void settDate(String tDate) {
        WeightData.tDate = tDate;
    }

    public static String gettEventID() {
        return tEventID;
    }

    public static void settEventID(String tEventID) {
        WeightData.tEventID = tEventID;
    }

    public static String gettOpenerID() {
        return tOpenerID;
    }

    public static void settOpenerID(String tOpenerID) {
        WeightData.tOpenerID = tOpenerID;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
