package zgkprojekt.model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private final int LIMIT = 5;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }
}
