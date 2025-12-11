package zgkprojekt.model;

import zgkprojekt.enums.EffectType;

public class Player {
    private int id;
    private String name;
    private int inventoryId;
    private PlayerFigure[] playerFigures;
    private boolean aktiveEffect;
    private EffectType effect;
    private Home home;
    private Endzone endzone;
    private String extra1;
    private String extra2;
    private String extra3;
}
