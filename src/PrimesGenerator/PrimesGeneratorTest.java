package PrimesGenerator;

public class PrimesGeneratorTest {
    public static void main(String[] args){
        int N = 10;
        PrimesGenerator generator = new PrimesGenerator(N);

        System.out.println("The first " + N + " prime numbers (ascending): ");
        for (Integer prime : generator){
            System.out.print(prime + " ");
        }
        System.out.println();

        System.out.println("The first " + N + " prime numbers (descending) : ");
        var it = generator.reverseIterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();

    }
}
