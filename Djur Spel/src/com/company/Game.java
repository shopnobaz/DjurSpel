package com.company;

import com.company.animal.*;
import com.company.food.Grains;
import com.company.food.Meat;
import com.company.food.Vegetable;
import java.util.Scanner;

/**
 * @author Jahed, Nahid, Solaiman
 *  Game class where the game is starting, showing menus, handling inputs and showing outputs.
 */

public class Game {
    Store store;
    int roundsOfGame;
    Player[] players;
    int currentRoundNumber;

    public Game() {
        currentRoundNumber = 1;
        store = new Store();
        startGame();
    }
    /**
     *method to start the game with total round numbers
     */
    private int takeInputAsNumber(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()){
            System.out.println("Please enter a number:");
            input.nextLine();
        }
        return input.nextInt();
    }

    private String takeInputAsString(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void startGame() {
        String enterRoundsMessage = "1. How many rounds Do you want to play? (5-30):";
        int roundsEntered = takeInputAsNumber(enterRoundsMessage);
        while (true) {
            if(roundsEntered < 5) {
                System.out.println("Minimum rounds should be 5");
                roundsEntered = takeInputAsNumber(enterRoundsMessage);
            } else if(roundsEntered > 30) {
                System.out.println("Maximum rounds should be 30");
                roundsEntered = takeInputAsNumber(enterRoundsMessage);
            } else {
                roundsOfGame = roundsEntered;
                setNumberOfPlayers();
                break;
            }
        }
    }
    /**
     *method to set numbers of playrs with user input
     */
    private void setNumberOfPlayers() {
        String setNumberOfPlayersMessage = "Enter number of players (2-4):";
        int playersEntered = takeInputAsNumber(setNumberOfPlayersMessage);
        while (true) {
            if(playersEntered < 2) {
                System.out.println("Minimum players should be 2");
                playersEntered = takeInputAsNumber(setNumberOfPlayersMessage);
            } else if(playersEntered > 4) {
                System.out.println("Maximum players should be 4");
                playersEntered = takeInputAsNumber(setNumberOfPlayersMessage);
            } else {
                players = new Player[playersEntered];
                takePlayersInfo();
                startRounds();
                break;
            }
        }
    }
    /**
     *method to take players name
     */
    private void takePlayersInfo() {
        for(int i=0; i<players.length; i++) {
            String name = takeInputAsString("Enter player " + (i+1) + " name: ");
            players[i] = new Player(name);
        }

    }

    private void startRounds() {
        for(int i=1; i<=roundsOfGame; i++) {
            System.out.println("Round " + i);
            System.out.println("");
            takePlayersInputForRound(i);
            showInformationAfterRound(i);
        }
        finishGame();
    }
    /**
     *method to dsiplay information after a round
     * @param int
     *
     */

    private void showInformationAfterRound(int roundNumber) {
        System.out.println("Round " + roundNumber + " summary:");
        for(int i=0; i<players.length; i++) {
            players[i].playerSummary();
        }
    }

    private void takePlayersInputForRound(int roundNumber) {
        for(int i=0; i<players.length; i++) {
            System.out.println(players[i].name + " input");
            System.out.println( players[i].name + "  YOU HAVE :" + players[i].getMoney() +"SEK"); ////
            showRoundMenuForPlayer(roundNumber, players[i]);

        }
    }
    /**
     *method to show user game menu and takes integer as input
     */

    private void showRoundMenuForPlayer(int roundNumber, Player player) {
        if(roundNumber == 1) {
            int input = takeInputAsNumber("1.Buy animal   2.Buy food   3.Feed animal   4.Pair animal   5.Sell animal");
            if (input == 1) {
                showAnimalMenu(player);
            } else if (input == 2) {
                showFoodMenu(player);
            } else if (input == 3) {
                if(player.animals.size()==0) {
                    System.out.println("Wrong choice. You lost your round.");
                } else {
                    showFeedAnimalMenu(player);
                }
            } else if (input == 4) {
                if(player.animals.size()==0) {
                    System.out.println("Wrong choice. You lost your round.");
                } else {
                    showPairAnimalMenu(player);
                }
            } else if (input == 5) {
                if(player.animals.size()==0) {
                    System.out.println("Wrong choice. You lost your round.");
                } else {
                    showSellAnimalMenu(player);
                }
            } else {
                showInvalidInputErrorMessage();
                showRoundMenuForPlayer(roundNumber, player);
            }
        } else {
            if(player.animals.size()==0 && player.getMoney()==0) {
                System.out.println(player.name + " is lost.");
            } else {
                int input = takeInputAsNumber("1.Buy animal   2.Buy food   3.Feed animal   4.Pair animal   5.Sell animal");
                if (input == 1) {
                    showAnimalMenu(player);
                } else if (input == 2) {
                    showFoodMenu(player);
                } else if (input == 3) {
                    showFeedAnimalMenu(player);
                } else if (input == 4) {
                    showPairAnimalMenu(player);
                } else if (input == 5) {
                    showSellAnimalMenu(player);
                } else {
                    showInvalidInputErrorMessage();
                    showRoundMenuForPlayer(roundNumber, player);
                }
            }
        }
        for(int i=0; i<player.animals.size(); i++) {
            player.animals.get(i).decreaseHealth(generateRandomHealthConsumed());
        }
    }

    private void showInvalidInputErrorMessage() {
        System.out.println("Invalid input. Please try again!");
    }

    private int takeAnimalSexInput() {
        return takeInputAsNumber("Enter gender:\n1.Male  2.Female");
    }

    private void showAnimalMenu(Player player) {
        int animalChoice = takeInputAsNumber("Which animal you want to buy?\n1.Cow 500 SEK   2.Dog 400 SEK  3.Cat 300 SEK  4.Chicken 200 SEK   5.Rabbit 200 SEK");
        if(animalChoice < 1 || animalChoice > 5) {
            showInvalidInputErrorMessage();
            showAnimalMenu(player);
        } else {
            if(animalChoice == 1){
                if(player.getMoney() >= 500) {
                    String name = takeInputAsString("Name your cow: ");
                    int gender = takeAnimalSexInput();
                    while(true) {
                        if (gender == 1) {
                            store.buyAnimal(AnimalType.COW, name, AnimalGender.MALE, player);
                            break;
                        } else if(gender == 2) {
                            store.buyAnimal(AnimalType.COW, name, AnimalGender.FEMALE, player);
                            break;
                        } else {
                            showInvalidInputErrorMessage();
                            gender = takeAnimalSexInput();
                        }
                    }
                } else {
                    System.out.println("You do not have enough money to buy a cow.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 2){
                if(player.getMoney() >= 400) {
                    String name = takeInputAsString("Name your dog: ");
                    int gender = takeAnimalSexInput();
                    while(true) {
                        if (gender == 1) {
                            store.buyAnimal(AnimalType.DOG, name, AnimalGender.MALE, player);
                            break;
                        } else if(gender == 2) {
                            store.buyAnimal(AnimalType.DOG, name, AnimalGender.FEMALE, player);
                            break;
                        } else {
                            showInvalidInputErrorMessage();
                            gender = takeAnimalSexInput();
                        }
                    }
                } else {
                    System.out.println("You do not have enough money to buy a dog.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 3){
                if(player.getMoney() >= 300) {
                    String name = takeInputAsString("Name your cat: ");
                    int gender = takeAnimalSexInput();
                    while(true) {
                        if (gender == 1) {
                            store.buyAnimal(AnimalType.CAT, name, AnimalGender.MALE, player);
                            break;
                        } else if(gender == 2) {
                            store.buyAnimal(AnimalType.CAT, name, AnimalGender.FEMALE, player);
                            break;
                        } else {
                            showInvalidInputErrorMessage();
                            gender = takeAnimalSexInput();
                        }
                    }
                } else {
                    System.out.println("You do not have enough money to buy a cat.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 4){
                if(player.getMoney() >= 200) {
                    String name = takeInputAsString("Name your chicken: ");
                    int gender = takeAnimalSexInput();
                    while(true) {
                        if (gender == 1) {
                            store.buyAnimal(AnimalType.CHICKEN, name, AnimalGender.MALE, player);
                            break;
                        } else if(gender == 2) {
                            store.buyAnimal(AnimalType.CHICKEN, name, AnimalGender.FEMALE, player);
                            break;
                        } else {
                            showInvalidInputErrorMessage();
                            gender = takeAnimalSexInput();
                        }
                    }
                } else {
                    System.out.println("You do not have enough money to buy a chicken.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 5){
                if(player.getMoney() >= 200) {
                    String name = takeInputAsString("Name your rabbit: ");
                    int gender = takeAnimalSexInput();
                    while(true) {
                        if (gender == 1) {
                            store.buyAnimal(AnimalType.RABBIT, name, AnimalGender.MALE, player);
                            break;
                        } else if(gender == 2) {
                            store.buyAnimal(AnimalType.RABBIT, name, AnimalGender.FEMALE, player);
                            break;
                        } else {
                            showInvalidInputErrorMessage();
                            gender = takeAnimalSexInput();
                        }
                    }
                } else {
                    System.out.println("You do not have enough money to buy a rabbit.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            }
        }
    }

    private void showFoodMenu(Player player) {
        int foodChoice = takeInputAsNumber("Which food you want to buy?\n1.Meat 300 SEK   2.Vegetable 200 SEK   3.Grains 100 SEK");
        if(foodChoice < 1 || foodChoice > 3) {
            showInvalidInputErrorMessage();
            showFoodMenu(player);
        } else {
            if(foodChoice == 1){
                if(player.getMoney() >= 300) {
                    store.buyFood(FoodType.MEAT, player);
                    /*
                    Meat meat = (Meat) player.foods.get(0);
                    meat.addWeight();
                    player.deductMoney(meat.pricePerKg);
                    */
                } else {
                    System.out.println("You do not have enough money to buy meat.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(foodChoice == 2){
                if(player.getMoney() >= 200) {
                    store.buyFood(FoodType.VEGETABLE, player);
                    /*
                    Vegetable vegetable = (Vegetable) player.foods.get(1);
                    vegetable.addWeight();
                    player.deductMoney(vegetable.pricePerKg);
                    */
                } else {
                    System.out.println("You do not have enough money to buy vegetables.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(foodChoice == 3){
                if(player.getMoney() >= 100) {
                    store.buyFood(FoodType.GRAINS, player);
                    /*
                    Grains grains = (Grains) player.foods.get(2);
                    grains.addWeight();
                    player.deductMoney(grains.pricePerKg);
                    */
                } else {
                    System.out.println("You do not have enough money to buy grains.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            }
        }
    }

    private int generateRandomHealthConsumed() {
        int randomNumber = 1 + (int)(Math.random() * ((3 - 1) + 1));
        return randomNumber * 10;
    }

    private void showPairAnimalMenu(Player player) {
        int pairChoice = takeInputAsNumber("Which type of animals you want to pair?\n1.Cow   2.Dog   3.Cat  4.Chicken   5.Rabbit");
        if(pairChoice < 1 || pairChoice > 5) {
            showInvalidInputErrorMessage();
            showPairAnimalMenu(player);
        } else {
            if(pairChoice == 1){
                if(player.doHavePair(AnimalType.COW)) {
                    if(player.pairingSucceeded()) {
                        String name = takeInputAsString("Enter name of newly born cow: ");
                        AnimalGender gender = player.decideGenderOfAnimalRandomly();
                        player.animals.add(new Cow(name, gender));
                    } else {
                        System.out.println("Unfortunately pairing did not succeed.");
                    }
                } else {
                    System.out.println(player.name + " do not have a pair of Cows.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(pairChoice == 2){
                if(player.doHavePair(AnimalType.DOG)) {
                    if(player.pairingSucceeded()) {
                        String name = takeInputAsString("Enter name of newly born dog: ");
                        AnimalGender gender = player.decideGenderOfAnimalRandomly();
                        player.animals.add(new Dog(name, gender));
                    } else {
                        System.out.println("Unfortunately pairing did not succeed.");
                    }
                } else {
                    System.out.println(player.name + " do not have a pair of Dogs.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(pairChoice == 3){
                if(player.doHavePair(AnimalType.CAT)) {
                    if(player.pairingSucceeded()) {
                        String name = takeInputAsString("Enter name of newly born cat: ");
                        AnimalGender gender = player.decideGenderOfAnimalRandomly();
                        player.animals.add(new Cat(name, gender));
                    } else {
                        System.out.println("Unfortunately pairing did not succeed.");
                    }
                } else {
                    System.out.println(player.name + " do not have a pair of Cats.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(pairChoice == 4){
                if(player.doHavePair(AnimalType.CHICKEN)) {
                    if(player.pairingSucceeded()) {
                        String name = takeInputAsString("Enter name of newly born chicken: ");
                        AnimalGender gender = player.decideGenderOfAnimalRandomly();
                        player.animals.add(new Chicken(name, gender));
                    } else {
                        System.out.println("Unfortunately pairing did not succeed.");
                    }
                } else {
                    System.out.println(player.name + " do not have a pair of Chickens.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(pairChoice == 5){
                if(player.doHavePair(AnimalType.RABBIT)) {
                    if(player.pairingSucceeded()) {
                        String name = takeInputAsString("Enter name of newly born rabbit: ");
                        AnimalGender gender = player.decideGenderOfAnimalRandomly();
                        player.animals.add(new Rabbit(name, gender));
                    } else {
                        System.out.println("Unfortunately pairing did not succeed.");
                    }
                } else {
                    System.out.println(player.name + " do not have a pair of Rabbits.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            }
        }
    }

    private void showSellAnimalMenu(Player player) {
        int sellChoice = takeInputAsNumber("Which type of animals you want to sell?\n1.Cow   2.Dog   3.Cat  4.Chicken   5.Rabbit");
        if(sellChoice < 1 || sellChoice > 5) {
            showInvalidInputErrorMessage();
            showSellAnimalMenu(player);
        } else {
            if(sellChoice == 1){
                if(player.doHaveAnimal(AnimalType.COW)) {
                    store.sellAnimal(AnimalType.COW, player);
                } else {
                    System.out.println(player.name + " do not have any cow to sell.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(sellChoice == 2){
                if(player.doHaveAnimal(AnimalType.DOG)) {
                    store.sellAnimal(AnimalType.DOG, player);
                } else {
                    System.out.println(player.name + " do not have any dog to sell.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(sellChoice == 3){
                if(player.doHaveAnimal(AnimalType.CAT)) {
                    store.sellAnimal(AnimalType.CAT, player);
                } else {
                    System.out.println(player.name + " do not have any cat to sell.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(sellChoice == 4){
                if(player.doHaveAnimal(AnimalType.CHICKEN)) {
                    store.sellAnimal(AnimalType.CHICKEN, player);
                } else {
                    System.out.println(player.name + " do not have any chicken to sell.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(sellChoice == 5){
                if(player.doHaveAnimal(AnimalType.RABBIT)) {
                    store.sellAnimal(AnimalType.RABBIT, player);
                } else {
                    System.out.println(player.name + " do not have any rabbit to sell.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            }
        }
    }

    private void showFeedAnimalMenu(Player player) {
        int animalChoice = takeInputAsNumber("Which type of animals you want to feed?\n1.Cow   2.Dog   3.Cat  4.Chicken   5.Rabbit");
        if(animalChoice < 1 || animalChoice > 5) {
            showInvalidInputErrorMessage();
            showFeedAnimalMenu(player);
        } else {
            if(animalChoice == 1){
                if(player.doHaveFoodForAnimal(AnimalType.COW)) {
                    player.feedAnimal(AnimalType.COW);
                } else {
                    System.out.println(player.name + " does not have food for cow.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 2){
                if(player.doHaveFoodForAnimal(AnimalType.DOG)) {
                    player.feedAnimal(AnimalType.DOG);
                } else {
                    System.out.println(player.name + " does not have food for dog.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 3){
                if(player.doHaveFoodForAnimal(AnimalType.CAT)) {
                    player.feedAnimal(AnimalType.CAT);
                } else {
                    System.out.println(player.name + " does not have food for cat.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 4){
                if(player.doHaveFoodForAnimal(AnimalType.CHICKEN)) {
                    player.feedAnimal(AnimalType.CHICKEN);
                } else {
                    System.out.println(player.name + " does not have food for chicken.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            } else if(animalChoice == 5){
                if(player.doHaveFoodForAnimal(AnimalType.RABBIT)) {
                    player.feedAnimal(AnimalType.RABBIT);
                } else {
                    System.out.println(player.name + " does not have food for rabbit.");
                    System.out.println("Wrong choice. You lost your round.");
                }
            }
        }
    }

    private void finishGame() {
        for(int i=0; i<players.length; i++) {
            Player player = players[i];
            for(int j=0; j<player.animals.size(); j++) {
                int price = player.animals.get(j).calculateAnimalPrice();
                player.addMoney(price);
            }
        }
        for(int i=0; i<players.length; i++){
            System.out.println("Final amount of " + players[i].name + ":" + players[i].getMoney());
        }

        Player winner = Player.winner(players);
        System.out.println("Winner is " + winner.name);
    }
}