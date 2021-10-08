package com.company.food;

import com.company.FoodType;

/**
 * @author Jahed, Nahid, Solaiman
 */

public class Vegetable extends Food {
    public Vegetable() {
        this.weight = 0;
        this.foodType = FoodType.VEGETABLE;
        this.pricePerKg = 200;
    }
}
