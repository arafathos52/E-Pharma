package com.example.maruf.e_pharma.Reminder;

import com.google.firebase.auth.FirebaseAuth;


public class EventDetails {
    static String  tName,tDate,tTime,tDescription,tEventID,tOpenerID;
    public String eventName ,eventDate, eventTime,eventDescription ,openerID,eventID;
    private FirebaseAuth mAuth;
    public static EventDetails temporary;
    EventDetails(String eventName, String eventDate, String eventTime, String eventDescription,  String eventID , String openerID)
    {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.eventID= eventID;
        this.openerID = openerID;
    }

    public EventDetails() {
        eventName=eventDate=eventTime=eventDescription="Me";
        eventID=openerID="me";
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }



    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getOpernerID() {
        return openerID;
    }

    public void setOpernerID(String opernerID) {
        this.openerID = opernerID;
    }


}
