package zgkprojekt.model;

import zgkprojekt.enums.EffectType;

public class Player {
    private String id;
    private String name;
    private Inventory inventory;
    private PlayerFigure[] playerFigures;
    private boolean activeEffect;
    private EffectType effect;
    private Home home;
    private Endzone endzone;
    private final Field startField;
    private String extra1;
    private String extra2;
    private String extra3;

    public Player (String id, String name, Endzone endzones, Home home, Field startField) {
        this.id = id; 
        this.name = name;
        this.inventory = new Inventory();
        this.playerFigures = initializePlayerFigureIds();
        this.activeEffect = false;
        this.home = home;
        this.endzone = endzones;
        this.startField = startField;
    }

    private PlayerFigure[] initializePlayerFigureIds() {
        PlayerFigure[] figures = new PlayerFigure[4];

        for (int i = 0; i < 4; i++) {
            figures[i] = new PlayerFigure(this.id + "_" + (i + 1));
        }

        return figures;
    }

    public String getName(){
        return name;
    }

    public Field getStartField(){
        return this.startField;
    }

    public PlayerFigure[] getPlayerFigures() {
        return this.playerFigures;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public boolean getHasActiveEffect() {
        return this.activeEffect;
    }

    public void setHasActiveEffect(Boolean activeEffect) {
        this.activeEffect = activeEffect;
    }

    public EffectType getEffect() {
        return this.effect;
    }

    public Home getHome() {
        return this.home;
    }

    public Endzone getEnzone() {
        return this.endzone;
    }


}
