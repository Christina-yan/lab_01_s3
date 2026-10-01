package Human;

import java.util.*;

public class HumanTest {
    public static void main(String[] args){
        List<Human> humans  = new ArrayList<>();
        humans.add(new Human("Kim", "Kardashian", 45));
        humans.add(new Human("Marshall", "Mathers", 53));
        humans.add(new Human("Lewis", "Hamilton", 41));
        humans.add(new Human("George", "Russel", 28));
        humans.add(new Human("Bruno", "Mars", 40));
        humans.add(new Human("George", "Russel", 28));

        System.out.println("Initial list: " + humans);

        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("\nHashSet: " + hashSet);

        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        System.out.println("LinkedHashSet: " + linkedHashSet);

        Set<Human> treeSet = new TreeSet<>(humans);
        System.out.println("TreeSet (natural): " + treeSet);

        Set<Human> treeByLastName = new TreeSet<>(new HumanComporatorByLastName());
        treeByLastName.addAll(humans);
        System.out.println("TreeSetByLastName: " + treeByLastName);

        Set<Human> treeByAge = new TreeSet<>(new Comparator<Human>() {
            @Override
            public int compare(Human a, Human b) {
                return Integer.compare(a.getAge(), b.getAge());
            }
        });
        treeByAge.addAll(humans);
        System.out.println("TreeSetByAge: " + treeByAge);
    }
}