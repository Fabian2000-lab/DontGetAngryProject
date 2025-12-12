package zgkprojekt.model;

import java.util.ArrayList;

public class Home {
    private ArrayList<Field> homeFields;

    public Home(ArrayList<Field> homeFields) {
        this.homeFields = homeFields;
    }

    public ArrayList<Field> getHomeFields() {
        return homeFields;
    }
}
