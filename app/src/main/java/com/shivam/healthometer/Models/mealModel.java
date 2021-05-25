package com.shivam.healthometer.Models;

public class mealModel {

    String mealNo, meal, macro;

    public mealModel() {

    }

    public mealModel(String mealNo, String meal, String macro) {
        this.mealNo = mealNo;
        this.meal = meal;
        this.macro = macro;
    }

    public String getMealNo() {
        return mealNo;
    }

    public void setMealNo(String mealNo) {
        this.mealNo = mealNo;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getMacro() {
        return macro;
    }

    public void setMacro(String macro) {
        this.macro = macro;
    }
}