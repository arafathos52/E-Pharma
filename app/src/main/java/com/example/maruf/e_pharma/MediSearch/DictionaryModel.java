package com.example.maruf.e_pharma.MediSearch;

/**
 * Created by aladin on 10/20/17.
 */

public class DictionaryModel {
    private String id;
    private String medicine;
    private String generic;
    private String uses;
    private String sideEffects;
    private String dosage;
    private String brand;

    public DictionaryModel(String id, String medicine, String generic, String uses, String sideEffects, String dosage,String brand) {
        this.id = id;
        this.medicine = medicine;
        this.generic = generic;
        this.uses = uses;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.brand=brand;

    }

    public String getId() {
        return id;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getGeneric() {
        return generic;
    }

    public String getUses() {
        return uses;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public String getBrand_Names() {
        return brand;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setBrand_Names(String brand) {this.brand = brand; }
}
