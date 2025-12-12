package zgkprojekt.model;

import zgkprojekt.enums.EffectType;

public class Item {
    private String name;
    private EffectType effect;

    public Item(String name, EffectType effect) {
        this.name = name;
        this.effect = effect;
    }

    public EffectType getEffectType() {
        return this.effect;
    }
}
