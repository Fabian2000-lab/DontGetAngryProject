package zgkprojekt.model;

import java.util.ArrayList;

import zgkprojekt.enums.EffectType;
import zgkprojekt.enums.FieldType;

public class Field {
    private int id;
    private ArrayList<Player> players;
    private EffectType effect;
    private FieldType fieldType;
    private String extra1;
    private String extra2;
    private String extra3;
}
