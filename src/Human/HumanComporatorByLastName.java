package Human;

import java.util.Comparator;

public class HumanComporatorByLastName implements Comparator<Human>{
    @Override
    public int compare(Human a, Human b){
        return a.getLast_name().compareTo(b.getLast_name());
    }
}