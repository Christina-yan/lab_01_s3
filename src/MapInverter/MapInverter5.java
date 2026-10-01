package MapInverter;

import java.util.*;

public class MapInverter5 {

    public static <K, V> Map<V, K> invert(Map<K, V> source){
        Map<V, K> result = new HashMap<>();
        for (Map.Entry<K, V> e : source.entrySet()){
            result.put(e.getValue(), e.getKey());
        }
        return result;
    }

    public static void main(String[] args){
        Map<String, Integer> original = new LinkedHashMap<>();
        original.put("one", 1);
        original.put("two", 2);
        original.put("three", 3);

        System.out.println("Original Map: " + original);
        Map<Integer, String> inverted = invert(original);
        System.out.println("Inverted Map: " + inverted);

        //dup
        Map<String, Integer> withDuplicates = new LinkedHashMap<>();
        withDuplicates.put("a", 1);
        withDuplicates.put("b", 1);
        withDuplicates.put("c", 2);

        System.out.println("\nMap with duplicates: " + withDuplicates);
        System.out.println("Inverted: " + invert(withDuplicates));
    }
}
