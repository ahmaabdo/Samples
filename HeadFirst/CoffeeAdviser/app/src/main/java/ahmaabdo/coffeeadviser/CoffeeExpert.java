package ahmaabdo.coffeeadviser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on Jan 28, 2018.
 */

public class CoffeeExpert {

    List<String> getBrands(String name) {
        List<String> brands = new ArrayList<>();
        if (name.equals("Nescafe")) {
            brands.add("CoffeeMix");
            brands.add("Nescafe Gold");
        } else {
            brands.add("Espresso");
            brands.add("Davidoff cafe");
        }
        return brands;
    }
}
