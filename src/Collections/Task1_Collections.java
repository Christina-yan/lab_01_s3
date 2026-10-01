package Collections;

import java.util.*;

public class Task1_Collections {

    public void main(){
        int N = 20;
        Random random = new Random();

        Integer[] array = new Integer[N];
        for(int i = 0; i < N; i++){
            array[i] = random.nextInt(101);
        }
        System.out.println("1. Array: " + Arrays.toString(array));

        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("2. List: " + list);

        Collections.sort(list);
        System.out.println("3. Ascending: " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("4. Descending: " + list);

        Collections.shuffle(list);
        System.out.println("5. Shuffled: " + list);

        Collections.rotate(list, 1);
        System.out.println("6. Shift by 1: " + list);

        // 7 & 8
        Collections.sort(list);
        List<Integer> uniques = new ArrayList<>();
        List<Integer> duplicates = new ArrayList<>();

        for (Integer num : list){
            if (Collections.frequency(list, num) == 1){
                uniques.add(num);
            }
            else {
                duplicates.add(num);
            }
        }

        System.out.println("7. Uniques elements: " + uniques);
        System.out.println("8. Duplicate elements: " + duplicates);


        Integer[] result = list.toArray(new Integer[0]);
        System.out.println("9. Array from list: " + result);

        System.out.println("10. Frequency of occurrence: ");       // часота вхождений
        for (Integer num : new TreeSet<>(list)){
            System.out.println(" " + num + " -> " + Collections.frequency(list, num));
        }

    }
}
