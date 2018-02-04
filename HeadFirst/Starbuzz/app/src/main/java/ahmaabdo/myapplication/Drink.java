package ahmaabdo.myapplication;

/**
 * Created by Ahmad on Feb 4, 2018.
 */

public class Drink {
    private String name, description;
    private int imgRedID;

    //drinks is an array of Drink
    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter)};

    //Each Drink has a name, description, and an image resource
    private Drink(String name, String description, int imgRedID) {
        this.name = name;
        this.description = description;
        this.imgRedID = imgRedID;
    }

    //Generate getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImgRedID() {
        return imgRedID;
    }

    public String toString() {
        return this.name;
    }
}
