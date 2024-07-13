import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        try {
            // Attempt to modify the list while iterating over it
            for (Integer number : list) {
                System.out.println("Current number: " + number);
                if (number == 3) {
                    list.remove(Integer.valueOf(number)); // This will cause ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
        }

        // Correct way to remove elements while iterating using iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println("Iterator current number: " + number);
            if (number == 4) {
                iterator.remove(); // This is safe and will not cause ConcurrentModificationException
            }
        }

        System.out.println("Final list: " + list);
    }
}
