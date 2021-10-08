package com.company.food;

import com.company.FoodType;

/**
 * @author Jahed, Nahid, Solaiman
 *  Grains class extending from abstract class Food to create Grains.
 */

public class Grains extends Food{
    public Grains() {
        this.weight = 0;
        this.foodType = FoodType.GRAINS;
        this.pricePerKg = 100;
    }
}
