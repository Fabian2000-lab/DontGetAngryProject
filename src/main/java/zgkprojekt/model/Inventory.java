package zgkprojekt.model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private final int LIMIT = 5;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }
 
    public ArrayList<Item> getItems() {
        return this.items;
    }

    public Item getInventoryItemByIndex(int index) throws IndexOutOfBoundsException {
        // check index is in range 0-4
        if (index < 0 || index >= LIMIT) {
            throw new IndexOutOfBoundsException("Index must be a number from 0-4");
        }

        // check if an item exists at that index
        if (index >= this.items.size()) {
            // no item stored at this index
            return null;
        }

        return this.items.get(index);
    }

    public void setItem(Item item) throws Exception {
        if (items.size() >= LIMIT) {
            throw new Exception("Inventory already full");
        }

        this.items.add(item);
    }
}
