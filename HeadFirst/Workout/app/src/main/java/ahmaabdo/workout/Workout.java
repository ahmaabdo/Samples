package ahmaabdo.workout;

/**
 * Created by Ahmad on Feb 6, 2018.
 */

public class Workout {
    private String name, description;

    //workouts is an array of four Workouts.
    public static final Workout[] workouts = {
            new Workout("Cur quadra volare? ",
                    "Contencio de\n brevis rumor,\n locus abactor! "),
            new Workout("Nunquam visum fluctus. ",
                    "Axonas sunt\n fraticinidas de\n noster zirbus. "),
            new Workout("Cur assimilatio tolerare? ",
                    "Sunt galluses\n perdere nobilis,\n castus vitaes. "),
            new Workout("Cur armarium cadunt? ",
                    "Classis de\n velox abnoba,\n carpseris ausus! "),
            new Workout("Nunquam promissio deus. ",
                    "Camerarius lacta\n sed mire convertams\n rumor est. "),
            new Workout("Cur era favere? ",
                    "Sunt bromiumes\n demitto ferox,\n brevis nixes. "),
            new Workout("Nunquam attrahendam lacta. ",
                    "Emeritis classis\n nunquam attrahendams\n domina est. "),
            new Workout("The Limb Loosener",
                    "Clemens hibrida\n tandem experientias\n ionicis tormento est. "),
            new Workout("Core Agony",
                    "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special",
                    "5 Pull-ups\n10 Push-ups\n15 Squats"),
            new Workout("Strength and Length",
                    "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    };

    //Each Workout has a name and description
    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return this.name;
    }
}
