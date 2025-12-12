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
    private String extra1;
    private String extra2;
    private String extra3;

    public Player (String id, String name, Endzone endzones, Home home) {
        this.id = id; 
        this.name = name;
        this.inventory = new Inventory();
        this.playerFigures = initializePlayerFigureIds();
        this.activeEffect = false;
        this.home = home;
        this.endzone = endzones;
    }

    private PlayerFigure[] initializePlayerFigureIds() {
        PlayerFigure[] figures = new PlayerFigure[4];

        for (int i = 0; i < 4; i++) {
            figures[i] = new PlayerFigure(this.id + "_" + (i + 1));
        }

        return figures;
    }
}
