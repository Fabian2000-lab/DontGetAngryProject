package zgkprojekt.model;

import java.util.Random;

public class Dice {

    private static Random _dice;
    private static int currentDiceRoll;

    public static int roll()
    {
        if(_dice == null)
            _dice = new Random();

        currentDiceRoll = _dice.nextInt(6) + 1;

        return currentDiceRoll;
    }

    public static int getCurrentDiceRoll() {
        return currentDiceRoll;
    }
}