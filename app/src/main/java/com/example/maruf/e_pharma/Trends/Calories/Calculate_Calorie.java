package com.example.maruf.e_pharma.Trends.Calories;




public class Calculate_Calorie extends Calorie_Data{

    public Calculate_Calorie(int weight, int height, int age, char gender, String activ) {
        super(weight, height, age, gender, activ);
    }

    private double getBmr(){
        if (gender=='M'){
            return 66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age);
        }else{
            return 655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age);
        }
    }

    public String getCalorieNeed(){
        double bmr = getBmr();
        double calorieNeed;
        String result ="0";

        if (activ.equals("Low")){
            calorieNeed = 1.3 * bmr;
        }else if (activ.equals("Medium")){
            calorieNeed = 1.5 * bmr;
        }else{
            calorieNeed = 1.7 * bmr;
        }
        result = String.format("%.0f",calorieNeed);
        return result;
    }

    public String getBodyCategory(){
        double bbr = getBbr();
        if (bbr>100){
            //FAT
            return "FAT";
        }else if (bbr>=90){
            //NORMAL
            return "NORMAL";
        }else{
            //THIN
            return "THIN";
        }
    }

    private double getBbr(){
        return (weight/(height-100)*100);
    }
}
