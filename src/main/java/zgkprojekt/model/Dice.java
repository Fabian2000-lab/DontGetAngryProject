package zgkprojekt.model;

import java.util.Random;

public class Dice {

    private static Random _dice;
    private static int currentDiceRoll;
    private static double multiplier = 1;

    public static int roll()
    {
        if(_dice == null)
            _dice = new Random();

        currentDiceRoll = (int) ((_dice.nextInt(6) + 1) * multiplier);

        if(currentDiceRoll == 0)
            roll();

        return currentDiceRoll;
    }

    public static int getCurrentDiceRoll() {
        return currentDiceRoll;
    }

    public static void setMultiplier(double newMultiplier)
    {
        multiplier = newMultiplier;
    }
}