package zgkprojekt.model;

import java.util.ArrayList;

public class Endzone {
    private ArrayList<Field> fields;
    private boolean isFull;

    public Endzone() {
        this.fields = new ArrayList<Field>();
        this.isFull = false;
    }
}
