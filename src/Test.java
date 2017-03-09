/**
 * Created by ilya on 07.03.17.
 */
public class Test {
    public static void main(String[] args) {
        Bitset name1 = new Bitset(45);
        name1.add(new int[]{1,3,6,9,13,14,19,23,24,25});
        name1.remove(1);
        name1.remove(6);
        name1.remove(23);
        System.out.println(name1);
        System.out.println((int)'À');
    }
}
