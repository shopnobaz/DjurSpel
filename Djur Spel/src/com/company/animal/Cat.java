package com.company.animal;

import com.company.AnimalGender;
import com.company.AnimalType;
import com.company.FoodType;

/**
 * @author Jahed, Nahid, Solaiman
 *  Cat class extending from abstract class Animal to create Cats.
 */

public class Cat extends Animal{
    public Cat(String name, AnimalGender sex) {
        this.name = name;
        this.sex = sex;
        this.foodType = FoodType.MEAT;
        this.animalType = AnimalType.CAT;
        this.health = 100;
        this.price = 300;
    }

    @Override
    public void increaseHealth() {
        health = health + 40;
        if(health > 100) {
            health = 100;
        }
    }
}
