package ahmaabdo.bitsandpizzas;

/**
 * Created by Ahmad on Feb 9, 2018.
 */

public class Pizza {
    //Each pizza has a name and image resource id that refers to the pizza image
    private String name;
    private int imgResId;

    //Pizza constructor
    private Pizza(String name, int imgResId) {
        this.name = name;
        this.imgResId = imgResId;
    }

    public static final Pizza[] pizzas = {
            new Pizza("Lixa", R.drawable.diavolo),
            new Pizza("Pars", R.drawable.funghi),
            new Pizza("Stella", R.drawable.diavolo),
            new Pizza("Gemna", R.drawable.funghi),
            new Pizza("Caelos", R.drawable.diavolo),
            new Pizza("Exemplar", R.drawable.funghi),
            new Pizza("Burgus", R.drawable.diavolo),
            new Pizza("Buxum", R.drawable.funghi),
            new Pizza("Cirpi", R.drawable.diavolo),
            new Pizza("Orexis", R.drawable.funghi),
            new Pizza("Diavolo", R.drawable.diavolo),
            new Pizza("Funghi", R.drawable.funghi)
    };
    public static final Pizza[] pastas = {
            new Pizza("Brabeuta ", R.drawable.diavolo),
            new Pizza("Hortus ", R.drawable.funghi),
            new Pizza("Vita ", R.drawable.diavolo),
            new Pizza("Torquis ", R.drawable.funghi),
            new Pizza("Galatae ", R.drawable.diavolo),
            new Pizza("Bubo ", R.drawable.funghi),
            new Pizza("Era ", R.drawable.diavolo),
            new Pizza("Oenipons ", R.drawable.funghi),
            new Pizza("Brigantium ", R.drawable.diavolo),
            new Pizza("Habitio ", R.drawable.funghi),
            new Pizza("Medicina ", R.drawable.diavolo),
            new Pizza("Orgia ", R.drawable.funghi)
    };
    public static final Pizza[] stores = {
            new Pizza("Extum  ", R.drawable.diavolo),
            new Pizza("Imber  ", R.drawable.funghi),
            new Pizza("Quadrata  ", R.drawable.diavolo),
            new Pizza("Bursa  ", R.drawable.funghi),
            new Pizza("Nutrix  ", R.drawable.diavolo),
            new Pizza("Cirpi  ", R.drawable.funghi),
            new Pizza("Cursus  ", R.drawable.diavolo),
            new Pizza("Brabeuta  ", R.drawable.funghi),
            new Pizza("Piscinam  ", R.drawable.diavolo),
            new Pizza("Messor  ", R.drawable.funghi),
            new Pizza("Messor  ", R.drawable.diavolo),
            new Pizza("Hortus  ", R.drawable.funghi)
    };

    //Getter methods for the private variables
    public String getName() {
        return name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String toString() {
        return this.name;
    }
}
