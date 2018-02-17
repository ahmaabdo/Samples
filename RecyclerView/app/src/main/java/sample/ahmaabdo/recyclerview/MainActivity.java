package sample.ahmaabdo.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ArabicMovies> moviesList = new ArrayList<>();

    private int[] poster = {
            R.drawable.blue, R.drawable.blue, R.drawable.blue,
            R.drawable.blue, R.drawable.blue, R.drawable.blue,
            R.drawable.blue, R.drawable.blue, R.drawable.blue};

    private String movieNames[] = {
            "abaculuss credere! ",
            "lacteas ridetis! ",
            "bullas unda! ",
            "acipensers nocere! ",
            "mortems cadunt! ",
            "sectams credere! ",
            "abnobas assimilant! ",
            "habitios experimentum! ",
            "absolutios mori!"
    };

    private String movieRates[] = {
            "6/10",
            "8.8/10",
            "9.8/10",
            "9.4/10",
            "7.2/10",
            "5.2/10",
            "4.6/10",
            "6.44/10",
            "7.6/10"
    };

    private String movieStory[] = {
            "Varius, nobilis spatiis cito amor de superbus, albus brodium!",
            "Talis, mirabilis apolloniatess grauiter captis de bassus, magnum abaculus!",
            "Fatalis, alter parmas tandem imitari de gratis, neuter luna!",
            "Mirabilis, velox ignigenas aliquando acquirere de altus, raptus contencio!",
            "Cum tabes potus, omnes eraes tractare fortis, ferox fluctuses!",
            "Cum itineris tramitem resistere, omnes abnobaes contactus neuter, flavum bullaes!",
            "Castus, mirabilis resistentias foris experientia de noster, talis species!",
            "Cum ventus ortum, omnes eleateses manifestum azureus, magnum classises!",
            "Pius, camerarius dominas patienter resuscitabo de velox, talis gabalium!"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < poster.length; i++) {
            moviesList.add(new ArabicMovies(movieNames[i], movieRates[i], movieStory[i], poster[i]));
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArabicMoviesAdapter adapter = new ArabicMoviesAdapter(moviesList);
        recyclerView.setAdapter(adapter);
    }
}
