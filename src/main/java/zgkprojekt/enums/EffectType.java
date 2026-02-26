package zgkprojekt.enums;

public enum EffectType {
    EXTRA_ROLL,                     // roll twice
    SWAP_PLACES,                    // swap places with a player
    JUMP_AHEAD,                     // jump ahead a certain amount of spaces 1-4
    SLIDE_BACK,                     // make player slide back 1-4, only until startfield
    TELEPORT,                       // max +10 fields (maybe also reverse)
    //SKIP_TURN,
    PLACE_BLOCADE,                  // select a field where noone can pass for this round   
    KICK_PLAYER,
    //SHIELD,                         // place a shield on a figure, this round it cant be kicked
    TRAP_ON_FIELD,                  // select a figure to trap for that players next round
    BOOST_FIGURE,                   // 
    REVIVE_FIGURE,
    REVERSE_ORDER_OF_MOVE,

}
