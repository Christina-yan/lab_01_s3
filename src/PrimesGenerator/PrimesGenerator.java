package PrimesGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PrimesGenerator implements Iterable<Integer> {     //task2
    private final List<Integer> primes = new ArrayList<>();

    public PrimesGenerator(int n){
        int candidate = 2;
        while (primes.size() < n){
            if (isPrime(candidate)){
                primes.add(candidate);
            }
            candidate++;
        }
    }
    private boolean isPrime(int x){
        if (x<2) return false;
        for (int i = 2; i * i <= x; i++){
            if (x % i == 0) return false;
        }
        return true;
    }
    @Override
    public Iterator<Integer> iterator(){
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < primes.size();
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return primes.get(index++);
            }
        };
    }

    public Iterator<Integer> reverseIterator(){
        return new Iterator<>() {
            private int index = primes.size() - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return primes.get(index--);
            }
        };
    }

}
