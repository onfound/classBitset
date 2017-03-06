import static org.junit.Assert.*;

import  org.junit.Test;
/**
 * Created by ilya on 05.03.17.
 */
public class BitsetTest {
    private Bitset bs = new Bitset(0);
    private Bitset bs1 = new Bitset(30);
    private Bitset bs2 = new Bitset(40);
    private Bitset bs3 = new Bitset(100);
    private Bitset bs4 = new Bitset(10);

    @Test
    public void add()throws Exception{
        bs2.add(new int[]{1,2,6,6,5,13,23,3,35,22,24,5,21});
        assertEquals("{1, 2, 3, 5, 6, 13, 21, 22, 23, 24, 35}",bs2.toString());
        bs2.add(20);
        assertEquals("{1, 2, 3, 5, 6, 13, 20, 21, 22, 23, 24, 35}", bs2.toString());
        bs2.add(new int[]{28,29,31,32,33});
        assertEquals("{1, 2, 3, 5, 6, 13, 20, 21, 22, 23, 24, 28, 29, 31, 32, 33, 35}",bs2.toString());
        bs2.add(39);
        assertEquals("{1, 2, 3, 5, 6, 13, 20, 21, 22, 23, 24, 28, 29, 31, 32, 33, 35, 39}", bs2.toString());
    }
    @Test
    public void remove()throws Exception{
        bs2.add(new int[]{5,9,4,2,6,1,0,12,14,17,15,7,17,18,21,26,24,0,27,31,33,36,39});
        bs2.remove(new int[]{0,17,14,21,24,26,39});
        assertEquals("{1, 2, 4, 5, 6, 7, 9, 12, 15, 18, 27, 31, 33, 36}",bs2.toString());
        bs2.remove(1);
        assertEquals("{2, 4, 5, 6, 7, 9, 12, 15, 18, 27, 31, 33, 36}",bs2.toString());
        bs2.remove(31);
        assertEquals("{2, 4, 5, 6, 7, 9, 12, 15, 18, 27, 33, 36}",bs2.toString());
        bs2.remove(new int[]{2,4,5,6,7,9, 12, 15, 18, 27, 33, 36});
        assertEquals("{}",bs2.toString());
    }

    @Test
    public void and()throws Exception{
        bs2.add(new int[]{5,9,4,2,6,1,0,12,14,17,15,7,17,18,21,26,24,0,27,31,33,36,39});
        bs3.add(new int[]{1,2,6,6,5,13,23,3,35,22,24,5,21,58,62,24,85,99,82,69,51,43,48,48,49,42,47,95,91});
        bs1.add(new int[]{1,2,5,6,21,24});

        bs2.and(bs3);
        assertEquals(bs1.toString(),bs2.toString());
        bs2.and(bs);
        assertEquals("{}",bs2.toString());
        bs2.add(1);
        bs2.and(bs3);
        assertEquals("{1}",bs2.toString());
    }
    @Test
    public void or()throws Exception{
        bs2.add(new int[]{1,8,9,21,31,39});
        bs3.add(new int[]{1,2,3,4,5,66,78,99,55});
        bs2.or(bs3);
        assertEquals("{1, 2, 3, 4, 5, 8, 9, 21, 31, 39, 55, 66, 78, 99}",bs2.toString());
        bs3.or(bs);
        assertEquals("{1, 2, 3, 4, 5, 55, 66, 78, 99}",bs3.toString());
        bs3.and(bs);
        bs3.or(bs);
        assertEquals("{}",bs3.toString());
    }
    @Test
    public void not()throws Exception{
        bs1.add(new int[]{1,5,8,10,16,25});
        bs1.not();
        bs4.add(new int[]{0,1,2,3,4,5,6,7,8,9});
        bs4.not();
        assertEquals("{0, 2, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27, 28, 29}",bs1.toString());
        assertEquals("{}",bs4.toString());
    }
    @Test
    public void belong()throws Exception{
        bs1.add(new int[]{0,2,3,6,7,8});
        assertTrue(bs1.belong(3));
        assertFalse(bs1.belong(5));
        assertFalse(bs1.belong(1));
        assertTrue(bs1.belong(8));
    }

    @Test
    public void powerSet()throws Exception{
        bs1.add(new int[]{0,1,4,6,13,25,28,23});
        bs2.add(new int[]{1,1,1,1,1,1,2,2,2,2,3});
        assertEquals(8, bs1.powerSet());
        assertEquals(3,bs2.powerSet());
        assertEquals(0,bs4.powerSet());
    }
}