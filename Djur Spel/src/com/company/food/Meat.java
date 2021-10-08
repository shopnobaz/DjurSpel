package com.company.food;

import com.company.FoodType;
/**
 * @author Jahed, Nahid, Solaiman
 */


public class Meat extends Food {
    public Meat() {
        this.weight = 0;
        this.foodType = FoodType.MEAT;
        this.pricePerKg = 300;
    }
}
