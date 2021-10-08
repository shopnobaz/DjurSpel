package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;

/**
        * @author Jahed, Nahid, Solaiman

        *  Abstract class of animal from where all animal classes are inheriting.
 *  This is an abstract class so it can not be instantiated.
 */

public abstract class Animal {
    public String name;
    public AnimalType animalType;
    public AnimalGender sex;
    public int health;
    public FoodType foodType;
    public int price;

    public abstract void increaseHealth();

    public void decreaseHealth(int healthToDecrease) {
        health = health - healthToDecrease;
        if(health < 0) {
            health = 0;
        }
    }

    public int calculateAnimalPrice() {
        return (health*price) / 100;
    }
}
