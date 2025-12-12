package zgkprojekt.model;

import java.util.ArrayList;

public class Endzone {
    private ArrayList<Field> endzones;
    private boolean isFull;

    public Endzone(ArrayList<Field> endzones) {
        this.endzones = endzones;
        this.isFull = false;
    }

    public ArrayList<Field> getEndzones()
    {
        return endzones;
    }

}
