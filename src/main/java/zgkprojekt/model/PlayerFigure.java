package zgkprojekt.model;

import zgkprojekt.enums.EffectType;

public class PlayerFigure {
    // combination of playerId + a number from 1 - 4 (for the 4 different figure a player can have)
    private String id;
    private String icon;
    private String position;
    private boolean hasEffect;
    private EffectType currentEffect;       // the effect of the figure for the current round

    public PlayerFigure(String id) {
        this.id = id;
        this.icon = null;
        this.position = null;
        this.hasEffect = false;
        this.currentEffect = null;
    }
}
