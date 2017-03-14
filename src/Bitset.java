import java.util.Arrays;

public class
Bitset {
    final private int powerUniversum;
    final private byte[] bits;

    Bitset(int powerUniversum) {
        this.powerUniversum = powerUniversum;
        bits = new byte[powerUniversum / 8 + 1];
    }

    // Добавление 1 неотрицательного элемента в множество.

    void add(int i) {
        int octetPosition = i / 8;
        if (i >= powerUniversum && i < 0)
            throw new IllegalArgumentException();
        bits[octetPosition] = (byte) (bits[octetPosition] | 1 << i % 8);
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
            bits[octetPosition] = (byte) (bits[octetPosition] ^ 1 << i % 8);
        }
    }

    // Удаление массива неотрицательных элементов.

    void remove(int[] i) {
        for (int element : i
                ) {
            remove(element);
        }
    }

    // Пересечение множеств.

    Bitset and(Bitset bs) {
        if (powerUniversum != bs.powerUniversum) throw new IllegalArgumentException("Different powerUniversum");
        Bitset result = new Bitset(powerUniversum);
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            result.bits[i] = (byte) (bs.bits[i] & bits[i]);
        }
        return result;
    }

    // Объединение множеств.

    Bitset or(Bitset bs) {
        if (powerUniversum != bs.powerUniversum) throw new IllegalArgumentException("Different powerUniversum");
        Bitset result = new Bitset(powerUniversum);
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            result.bits[i] = (byte) (bs.bits[i] | bits[i]);
        }
        return result;
    }

    // Дополнение множеств.

    Bitset not() {
        Bitset result = new Bitset(powerUniversum);
        int octetPosition = powerUniversum / 8;
        for (int i = 0; i < octetPosition + 1; i++) {
            result.bits[i] = (byte) (~bits[i]);    //255-bits[i] инвертирует
        }
        result.bits[octetPosition] = (byte) (result.bits[octetPosition] & 127 >> 7 - powerUniversum % 8); // powerUniversum % 8 = 0 тогда в конструкторе добавлен запасной байт
        return result;
    }

    // Проверка принадлежности элемента множеству.

    Boolean contain(int i) {
        int mask = 1 << i % 8;
        return ((bits[i / 8] & mask) == mask);
    }

    // Мощность множества.

    int cardinality() {
        int count = 0;
        for (int i = 0; i < powerUniversum; i++) {
            if (contain(i)) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int j = 0; j < powerUniversum; j++) {
            if (contain(j)) result.append(", ").append(j);
        }
        if (result.length() > 1) result.delete(1, 3);
        return result.append("}").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bitset)) return false;

        Bitset bitset = (Bitset) o;

        return powerUniversum == bitset.powerUniversum && Arrays.equals(bits, bitset.bits);
    }

    @Override
    public int hashCode() {
        int result = powerUniversum;
        result = 31 * result + Arrays.hashCode(bits);
        return result;
    }
}
