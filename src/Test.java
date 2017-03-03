
import java.util.HashSet;


/**
 * Created by ilya on 17.02.17.
 */
public class Test {
    public static void main(String[] args) {
        Bitset name1 = new Bitset(12);
        Bitset name2 = new Bitset(12);
        int[] set = {1,2,3,4,5,6};
        int[] set1 = {5,6,7,8,9};
        name1.add(set);
        name2.add(set1);
        name1.cross(name2);
        System.out.println(name1.belong(3));
        System.out.println(name1);
        System.out.println(String.valueOf(Character.toChars(255)));
    }
//        String.valueOf(Character.toChars(0)) "символ  с 00000000" в стринг формате


}
