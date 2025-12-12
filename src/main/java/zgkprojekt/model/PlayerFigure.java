package zgkprojekt.model;

import zgkprojekt.enums.EffectType;

public class PlayerFigure {
    // combination of playerId + a number from 1 - 4 (for the 4 different figure a player can have)
    private String id;
    private String icon;
    private String position;
    private boolean hasEffect;
    
    // the effect of the figure for the current round
    private EffectType currentEffect;       

    public PlayerFigure(String id) {
        this.id = id;
        this.icon = null;
        this.position = null;
        this.hasEffect = false;
        this.currentEffect = null;
    }

    public String getId () {
        return this.id;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String postion) {
        this.position = postion;
    }

    public Boolean getHasEffect() {
        return this.hasEffect;
    }

    public void setHasEffect(Boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public EffectType getCurrentEffect() {
        return this.currentEffect;
    }

    public void setCurrentEffect(EffectType effectType) {
        this.currentEffect = effectType;
    }
}
