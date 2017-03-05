
import java.util.HashSet;


/**
 * Created by ilya on 17.02.17.
 */
public class Test {
    public static void main(String[] args) {
        Bitset name1 = new Bitset(15);
        Bitset name2 = new Bitset(44);
        int[] set1 = {0,2,4,6};
        int[] set2 = {35};
        name1.add(set1);
        name2.add(set2);
        name1.supplement();
        System.out.println(name1);
    }
}
