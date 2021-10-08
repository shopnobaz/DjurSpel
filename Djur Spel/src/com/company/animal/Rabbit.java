package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;
/**
 * @author Jahed, Nahid, Solaiman
 */


public class Rabbit extends Animal{
    public Rabbit(String name, AnimalGender sex) {
        this.name = name;
        this.sex = sex;
        this.animalType = AnimalType.RABBIT;
        this.foodType = FoodType.VEGETABLE;
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
