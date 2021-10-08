package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;
/**
 * @author Jahed, Nahid, Solaiman
 */


public class Chicken extends Animal{
    public Chicken(String name, AnimalGender sex) {
        this.name = name;
        this.sex = sex;
        this.animalType = AnimalType.CHICKEN;
        this.foodType = FoodType.GRAINS;
        this.health = 100;
        this.price = 200;
    }

    @Override
    public void increaseHealth() {
        health = health + 50;
        if(health > 100) {
            health = 100;
        }
    }
}
