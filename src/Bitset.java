import java.util.Arrays;

/**
 * Created by ilya on 14.02.17.
 */
public class Bitset {
    final private int powerUniversum;
    final private char[] bits1;

    Bitset(int powerUniversum) {
        this.powerUniversum = powerUniversum;
        bits1 = new char[powerUniversum / 8 + 1];
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            bits1[i] = 0;
        }
    }

    // Добавление 1 неотрицательного элемента в множество.

    void add(int i) {
        int octetPosition = i / 8;
        if (i >= powerUniversum)
            throw new IllegalArgumentException("Outside of the Universum");
        bits1[octetPosition] = Character.toChars(bits1[octetPosition] | 1 << i % 8)[0];
    }

    // Добавление массива из неотрицательных чисел.

    void add(int[] i) {
        for (int element : i) {
            add(element);
        }
    }

    // Удаление 1 неотрицательного элемента.

    void remove(int i) {
        if (contain(i)) {
            int octetPosition = i / 8;
            bits1[octetPosition] = Character.toChars(bits1[octetPosition] ^ 1 << i % 8)[0];
        }
    }

    // Удаление массива неотрицательных элементов.

    void remove(int[] i) {
        for (int element : i
                ) {
            if (contain(element)) remove(element);
        }
    }

    // Пересечение множеств.

    Bitset and(Bitset bs) {
        if (powerUniversum != bs.powerUniversum) throw new IllegalArgumentException("Different powerUniversum");
        Bitset result = new Bitset(powerUniversum);
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            result.bits1[i] = Character.toChars(bs.bits1[i] & bits1[i])[0];
        }
        return result;
    }


    // Объединение множеств.

    Bitset or(Bitset bs) {
        if (powerUniversum != bs.powerUniversum) throw new IllegalArgumentException("Different powerUniversum");
        Bitset result = new Bitset(powerUniversum);
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            result.bits1[i] = Character.toChars(bs.bits1[i] | bits1[i])[0];
        }
        return result;
    }

    // Дополнение множеств.
    Bitset not() {
        Bitset result = new Bitset(powerUniversum);
        int octetPosition = powerUniversum / 8;
        for (int i = 0; i < octetPosition + 1; i++) {
            result.bits1[i] = Character.toChars(255 - bits1[i])[0];
        }
        result.bits1[octetPosition] = Character.toChars(result.bits1[octetPosition] & 127 >> 7 - powerUniversum % 8)[0];
        return result;
    }

    // Проверка принадлежности элемента множеству.

    Boolean contain(int i) {
        int bit = i % 8;
        return ((bits1[i / 8] & 1 << bit) == 1 << bit);
    }

    // Мощность множества.

    int powerSet() {
        int count = 0;
        for (int i = 0; i < powerUniversum; i++) {
            int mask = 1 << i % 8;
            if (mask == (mask & bits1[i / 8])) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int j = 0; j < powerUniversum; j++) {
            int mask = 1 << j % 8;
            if (mask == (mask & bits1[j / 8])) {
                result.append(", ").append(j);
            }
        }
        if (result.length() > 1) result.delete(1, 3);
        return result.append("}").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bitset)) return false;

        Bitset bitset = (Bitset) o;

        if (powerUniversum != bitset.powerUniversum) return false;
        return Arrays.equals(bits1, bitset.bits1);
    }

    @Override
    public int hashCode() {
        int result = powerUniversum;
        result = 31 * result + Arrays.hashCode(bits1);
        return result;
    }
}
