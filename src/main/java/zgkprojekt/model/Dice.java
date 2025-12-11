package zgkprojekt.model;

import java.util.Random;

public class Dice {

    private static Random _dice;

    public static int roll()
    {
        if(_dice == null)
            _dice = new Random();

        return _dice.nextInt(6) + 1;
    }
}