package zgkprojekt.enums;

public enum EventType {
    NOTHING,                        // no event in the cycle
    //REVERSE_PLAYING_ORDER,          // reverse play order
    //EXTRA_DIE,                      // all players get an extra roll
    //MODIFY_DICE,                    // add a +number or -number to the rolled dice
    //REVERSE_MOVEMENT_DIRECTION,     // all figure move in the opposite direction
    //DOUBLE_DICE_VALUE,
    //HALVE_DICE_VALUE,               // values are rounded down - 1 is a 0
    //TRAPPED_FIGURES,                // 1 figure from each player is trapped(cant move) for that round
    //WILD_DICE,                      // each number rolled produces a different effect (1-3 is a positive effect, 4-6 is a negative effect)
    STORM,                          // figures standing on certain fields gets pushed back 2
    //PEACE,                          // all players are safe from being kicked home
    WORM_HOLE,                      // one figure of a player switches places with another figure of another player
    //USE_LAST_FIGURE,                // player has to use the figure which is the most in the back
    //USE_FIRST_FIGURE                // player has to use the figure which is the most in the front
}

