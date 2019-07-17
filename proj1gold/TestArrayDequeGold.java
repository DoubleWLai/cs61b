import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();

        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }

        for (int i = 0; i < 10; i++) {
            int result = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + result
                            + " not equal to " + expected + "!",
                    expected, result);
        }

        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();

        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads1.addFirst(random);
            sad1.addFirst(random);
        }

        for (int i = 0; i < 10; i++) {
            int result = ads1.get(i);
            int expected = sad1.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + result
                            + " not equal to " + expected + "!",
                    expected, result);
        }


        for (int i = 0; i < 10; i++) {
            Integer result = ads.removeLast();
            Integer expected = sad.removeLast();
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + result
                            + " not equal to " + expected + "!",
                    expected, result);
        }

        for (int i = 0; i < 10; i++) {
            Integer result = ads1.removeFirst();
            Integer expected = sad1.removeFirst();
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + result
                            + " not equal to " + expected + "!",
                    expected, result);
        }



    }
}
