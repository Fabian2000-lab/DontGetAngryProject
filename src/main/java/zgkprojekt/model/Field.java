package zgkprojekt.model;

import javafx.scene.shape.Circle;
import zgkprojekt.enums.EventType;
import zgkprojekt.enums.FieldType;

public class Field {
    private int id;
    private PlayerFigure player;
    private EventType effect;
    private FieldType fieldType;
    private Circle circle;
    private String extra1;
    private String extra2;
    private String extra3;

    public Field(int id, FieldType fieldType, Circle circle) {
        this.id = id;
        this.fieldType = fieldType;
        this.circle = circle;
        this.player = null;
        this.effect = null;
    }

    public int getId() {
        return this.id;
    }

    public void setPlayer(PlayerFigure player) {
        this.player = player;
    }

    public  PlayerFigure getPlayer() {
        return this.player;
    }

    public void setEffectType(EventType type) {
        this.effect = type;
    }

    public EventType getEffect() {
        return this.effect;
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }


}
