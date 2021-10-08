package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;
/**
 * @author Jahed, Nahid, Solaiman
 */


public class Dog extends Animal{
    public Dog(String name, AnimalGender sex) {
        this.name = name;
        this.sex = sex;
        this.animalType = AnimalType.DOG;
        this.foodType = FoodType.MEAT;
        this.health = 100;
        this.price = 400;
    }

    @Override
    public void increaseHealth() {
        health = health + 30;
        if(health > 100) {
            health = 100;
        }
    }
}
