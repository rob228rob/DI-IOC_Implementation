package org.example.SupportPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SupportService {

    private static List<String> phrasesList;

    public SupportService() {
        if (phrasesList == null) {
            phrasesList = new ArrayList<>(
                    Arrays.asList(
                            "Do not give up, champion!!",
                            "You cant go through every struggle",
                            "Be happy, honey",
                            "You are not the same",
                            "just chill and do your best",
                            "With God you can everything you can image"));
        }
    }

    public static void addNewPhrases(String newPhrase) {
        phrasesList.add(newPhrase);
    }

    public static String getRandomPhrase() {
        var randInt = new Random();

        return phrasesList.get(randInt.nextInt(phrasesList.size()));
    }

}
