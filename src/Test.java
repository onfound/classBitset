
import java.util.HashSet;


/**
 * Created by ilya on 17.02.17.
 */
public class Test {
    public static void main(String[] args) {
        Bitset name1 = new Bitset(45);
        int[] set1 = {0, 1, 2, 3, 4, 5, 6};
        int[] set2 = {2, 5, 13, 7, 35, 26};
        name1.add(set1);
        System.out.println(name1);
    }
}
