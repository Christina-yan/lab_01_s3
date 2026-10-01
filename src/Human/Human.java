package Human;

import java.util.Objects;

public class Human implements Comparable<Human>{
    private final String first_name;
    private final String last_name;
    private final int age;

    public Human(String first_name, String last_name, int age){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public String getFirst_name() {return first_name; }
    public String getLast_name() {return last_name; }
    public int getAge() {return age; }

    @Override
    public int compareTo(Human other) {
        int cmp = last_name.compareTo(other.last_name);
        if (cmp != 0) return cmp;
        cmp = first_name.compareTo(other.first_name);
        if (cmp != 0) return cmp;
        return Integer.compare(age, other.age);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Human h)) return false;
        return age == h.age
                && Objects.equals(first_name, h.first_name)
                && Objects.equals(last_name, h.last_name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(first_name, last_name, age);
    }

    @Override
    public String toString(){
        return first_name + " " + last_name + " ( " + age + " ) ";
    }



}





