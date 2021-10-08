package com.company;

import com.company.animal.*;
import com.company.food.Food;
import com.company.food.Grains;
import com.company.food.Meat;
import com.company.food.Vegetable;
import java.util.ArrayList;

/**
 * @author Jahed, Nahid, Solaiman
 *  Player class to create players to play the game.
 */

public class Player {
    public String name;
    private int money;

    public ArrayList<Animal> animals;
    public ArrayList<Food> foods;

    /**
     * Creates a player with a certain name
     *
     * //@param name  What name, how much money has
     *
     */

    public Player(String name) {
        this.name = name;
        this.money = 1000;
        animals = new ArrayList<>();
        foods = new ArrayList<>();
        foods.add(new Meat());
        foods.add(new Vegetable());
        foods.add(new Grains());
    }




    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money = this.money + amount;
    }

    public void deductMoney(int amount) {
        this.money = this.money - amount;
    }
    /**
     *Method to create player Summary
     */
    public void playerSummary() {
        for(int i=0; i<animals.size(); i++) {
            String playerAnimalsInformation = name + " owns a ";
            playerAnimalsInformation += animals.get(i).sex.toString();
            playerAnimalsInformation += " ";
            playerAnimalsInformation += animals.get(i).animalType.toString();
            playerAnimalsInformation += " named ";
            playerAnimalsInformation += animals.get(i).name;
            playerAnimalsInformation += " with health ";
            playerAnimalsInformation += animals.get(i).health;
            System.out.println(playerAnimalsInformation);
            System.out.println(":::::::::::::::::::::::::::::::::::::");
        }
        System.out.println(name + " has " + money + " SEK left.");
        for(int i=0; i<foods.size(); i++) {
            String playerFoodsInformation = name + " has ";
            playerFoodsInformation += foods.get(i).weight;
            playerFoodsInformation += " KG ";
            playerFoodsInformation += foods.get(i).foodType.toString();
            playerFoodsInformation += " left";
            System.out.println(playerFoodsInformation);
        }
    }
    /**
     *method to pair animal
     * @param type
     */
    public boolean doHavePair(AnimalType type) {
        boolean hasMale = false;
        boolean hasFemale = false;

        for(int i=0; i<animals.size(); i++) {
            if(animals.get(i).animalType == type) {
                if(animals.get(i).sex == AnimalGender.MALE) {
                    hasMale = true;
                } else if(animals.get(i).sex == AnimalGender.FEMALE) {
                    hasFemale = true;
                }
            }
        }

        if(hasMale && hasFemale) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method to check if there is animal to check health
     * @param type
     */
    public boolean doHaveAnimal(AnimalType type) {
        boolean doHaveAnimal = false;
        for(int i=0; i<animals.size(); i++) {
            if(animals.get(i).animalType==type && animals.get(i).health>0) {
                doHaveAnimal = true;
                break;
            }
        }
        return doHaveAnimal;
    }

    /**
     *method to decide newborn animal gender randomly
     */
    public AnimalGender decideGenderOfAnimalRandomly() {
        int randomNumber = 1 + (int)(Math.random() * ((2 - 1) + 1));
        if(randomNumber == 1) {
            return AnimalGender.MALE;
        } else {
            return AnimalGender.FEMALE;
        }
    }

    public boolean pairingSucceeded() {
        int randomNumber = 1 + (int)(Math.random() * ((2 - 1) + 1));
        if(randomNumber == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doHaveFoodForAnimal(AnimalType type) {
        boolean hasFoodForAnimal = false;
        if(type == AnimalType.COW || type==AnimalType.RABBIT) {
            if(foods.get(1).weight > 0) {
                hasFoodForAnimal = true;
            }
        } else if(type==AnimalType.DOG || type==AnimalType.CAT) {
            if(foods.get(0).weight > 0) {
                hasFoodForAnimal = true;
            }
        } else if(type == AnimalType.CHICKEN) {
            if(foods.get(2).weight > 0) {
                hasFoodForAnimal = true;
            }
        }
        return hasFoodForAnimal;
    }

    public void feedAnimal(AnimalType type) {
        if(type==AnimalType.COW) {
            foods.get(1).deductWeight();
        } else if(type==AnimalType.DOG) {
            foods.get(0).deductWeight();
        } else if(type==AnimalType.CAT) {
            foods.get(0).deductWeight();
        } else if(type==AnimalType.CHICKEN) {
            foods.get(2).deductWeight();
        } else if(type==AnimalType.RABBIT) {
            foods.get(1).deductWeight();
        }
        for(int i=0; i<animals.size(); i++) {
            if(type==animals.get(i).animalType) {
                animals.get(i).increaseHealth();
                break;
            }
        }
    }
    /**
     *player winner
     */
    public static Player winner(Player[] players) {
        double maxValue = -1;
        int indexOfMaxValue = -1;
        for(int i=0; i<players.length; i++) {
            if(players[i].getMoney() > maxValue) {
                indexOfMaxValue = i;
                maxValue = players[i].getMoney();
            }
        }
        return players[indexOfMaxValue];
    }
}
