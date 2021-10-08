package com.company;

import com.company.animal.*;
import com.company.food.Grains;
import com.company.food.Meat;
import com.company.food.Vegetable;

/**
 * @author Jahed, Nahid, Solaiman
 *  Store class from where a player can buy or sell animals.
 *  Player can also only buy food from this class
 */

public class Store {
    public void buyAnimal(AnimalType type, String name, AnimalGender gender, Player player) {
        if(type == AnimalType.COW) {
            Cow cow = new Cow(name, gender);
            player.animals.add(cow);
            player.deductMoney(cow.price);
        } else if(type == AnimalType.DOG) {
            Dog dog = new Dog(name, gender);
            player.animals.add(dog);
            player.deductMoney(dog.price);
        } else if(type == AnimalType.CAT) {
            Cat cat = new Cat(name, gender);
            player.animals.add(cat);
            player.deductMoney(cat.price);
        } else if(type == AnimalType.CHICKEN) {
            Chicken chicken = new Chicken(name, gender);
            player.animals.add(chicken);
            player.deductMoney(chicken.price);
        } else if(type == AnimalType.RABBIT) {
            Rabbit rabbit = new Rabbit(name, gender);
            player.animals.add(rabbit);
            player.deductMoney(rabbit.price);
        }
    }

    /**
     * Method to sell Animal
     * @param type
     * @param player
     */
    public void sellAnimal(AnimalType type, Player player) {
        for(int i=0; i<player.animals.size(); i++) {
            Animal animal = player.animals.get(i);
            if(animal.animalType==type && animal.health>0) {
                int price = player.animals.get(i).calculateAnimalPrice();
                System.out.println("animal price = " + price);
                player.addMoney(price);
                player.animals.remove(animal);
                break;
            }
        }
    }
    /**
     *method to buy food
     * @param type
     * @param player
     */

    public void buyFood(FoodType type, Player player) {
        if(type == FoodType.MEAT) {
            Meat meat = (Meat) player.foods.get(0);
            meat.addWeight();
            player.deductMoney(meat.pricePerKg);
        } else if(type == FoodType.VEGETABLE) {
            Vegetable vegetable = (Vegetable) player.foods.get(1);
            vegetable.addWeight();
            player.deductMoney(vegetable.pricePerKg);
        } else if(type == FoodType.GRAINS) {
            Grains grains = (Grains) player.foods.get(2);
            grains.addWeight();
            player.deductMoney(grains.pricePerKg);
        }
    }
}
