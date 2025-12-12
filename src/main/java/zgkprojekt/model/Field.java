package zgkprojekt.model;

import java.util.ArrayList;

import javafx.scene.shape.Circle;
import zgkprojekt.enums.EffectType;
import zgkprojekt.enums.FieldType;

public class Field {
    private int id;
    private ArrayList<Player> players;
    private EffectType effect;
    private FieldType fieldType;
    private Circle circle;
    private String extra1;
    private String extra2;
    private String extra3;

    public Field(int id, FieldType fieldType, Circle circle) {
        this.id = id;
        this.fieldType = fieldType;
        this.circle = circle;
        this.players = null;
        this.effect = null;
    }
}
