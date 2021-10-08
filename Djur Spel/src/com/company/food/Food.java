package com.company.food;

import com.company.FoodType;

/**
 * @author Jahed, Nahid, Solaiman
 *  Abstract class of food from where all food classes are inheriting.
 *  This is an abstract class so it can not be instantiated.
 */

public abstract class Food {
    public int weight;
    public int pricePerKg;
    public FoodType foodType;

    public void addWeight() {
        weight++;
    }

    public void deductWeight() {
        weight--;
    }
}
