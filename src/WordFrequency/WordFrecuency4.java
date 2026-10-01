package WordFrequency;

import java.util.*;

public class WordFrecuency4 {
    public static void main(String[] args){
        String text = "The quick brown fox jumps over the lazy dog. " +
                "The dog barks, and the fox runs away. Quick, quick, quick! ";

        String[] tokens = text.toLowerCase().split("[^a-z]+");

        Map<String, Integer> frequency = new HashMap<>();
        for (String word : tokens){
            if (word.isEmpty()) continue;
            frequency.merge(word, 1, Integer::sum);
        }

        System.out.println("Words frequency: ");
        for (Map.Entry<String, Integer> e : frequency.entrySet()){
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        System.out.println("\nSorted output: ");
        new TreeMap<>(frequency).forEach((k, v) -> System.out.println(k + " -> " + v));

    }




}
