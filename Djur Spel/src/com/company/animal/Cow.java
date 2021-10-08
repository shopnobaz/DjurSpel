package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;
/**
 * @author Jahed, Nahid, Solaiman
 */


public class Cow extends Animal{
    public Cow(String name, AnimalGender sex) {
        this.name = name;
        this.sex = sex;
        this.animalType = AnimalType.COW;
        this.foodType = FoodType.VEGETABLE;
        this.health = 100;
        this.price = 500;
    }

    @Override
    public void increaseHealth() {
        health = health + 20;
        if(health > 100) {
            health = 100;
        }
    }
}
