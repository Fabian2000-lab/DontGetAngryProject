package zgkprojekt.model;

import javafx.scene.shape.Polygon;
import zgkprojekt.enums.EffectType;

public class PlayerFigure {
    // combination of playerId + a number from 1 - 4 (for the 4 different figure a player can have)
    private String id;
    private String icon;
    private Field position;
    private boolean hasEffect;
    private Polygon polygon;
    
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

    public Field getPosition() {
        return this.position;
    }

    public void setPosition(Field postion) {
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

    public Polygon getPolygon() {
        return this.polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
}
