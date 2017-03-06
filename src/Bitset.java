

/**
 * Created by ilya on 14.02.17.
 */
public class Bitset {
    private int powerUniversum;
    private StringBuffer bits = new StringBuffer(String.valueOf(Character.toChars(0)));

    Bitset(int powerUniversum) {
        this.powerUniversum = powerUniversum;
        for (int i = 0; i < powerUniversum / 8; i++) {
            bits.append(Character.toChars(0));
        }
    }

    // Добавление 1 неотрицательного элемента в множество.

    public void add(int i) {
        int octetPosition = i / 8;
        if (i >= powerUniversum)
            throw new IndexOutOfBoundsException("Outside of the Universum");
        bits.replace(octetPosition, octetPosition + 1, String.valueOf(Character.toChars((int) bits.charAt(octetPosition) | 1 << i % 8)));
    }

    // Добавление массива из неотрицательных чисел.

    public void add(int[] i) {
        for (int element : i) {
            add(element);
        }
    }

    // Удаление 1 неотрицательного элемента.

    public void remove(int i) {
        int octetPosition = i / 8;
        bits.replace(octetPosition, octetPosition + 1, String.valueOf(Character.toChars((int) bits.charAt(octetPosition) ^ 1 << i % 8)));
    }

    // Удаление массива неотрицательных элементов.

    public void remove(int[] i) {
        for (int element : i
                ) {
            if (belong(element)) remove(element);
        }
    }

    // Пересечение множеств.

    void and(Bitset bs) {
        StringBuffer bitcross = new StringBuffer(String.valueOf(Character.toChars(0)));
        for (int i = 0; i < Math.max(powerUniversum, bs.powerUniversum) / 8; i++) {
            bitcross.append(Character.toChars(0));
        }
        for (int i = 0; i < Math.min(powerUniversum, bs.powerUniversum) / 8 + 1; i++) {
            bitcross.replace(i, i + 1, String.valueOf(Character.toChars((int) bits.charAt(i) & (int) bs.bits.charAt(i))));
        }
        bits = bitcross;
        powerUniversum = Math.max(powerUniversum, bs.powerUniversum);
    }

    // Объединение множеств.

    public void or(Bitset bs) {
        StringBuffer bitcross;
        bitcross = new StringBuffer(bits.length() > bs.bits.length() ? bits : bs.bits);
        for (int i = 0; i < Math.min(powerUniversum, bs.powerUniversum) / 8 + 1; i++) {
            bitcross.replace(i, i + 1, String.valueOf(Character.toChars((int) bits.charAt(i) | (int) bs.bits.charAt(i))));
        }
        bits = bitcross;
        powerUniversum = Math.max(powerUniversum, bs.powerUniversum);
    }

    // Дополнение множеств.

    public void not() {
        for (int i = 0; i < powerUniversum / 8 + 1; i++) {
            bits.replace(i, i + 1, String.valueOf(Character.toChars(255 - (int) bits.charAt(i))));
        }
    }

    // Проверка принадлежности элемента множеству.

    Boolean belong(int i) {
        int bit = i % 8;
        return (((int) bits.charAt(i / 8) & 1 << bit) == 1 << bit);
    }

    // Мощность множества.

    public int powerSet() {
        int count = 0;
        for (int i = 0; i < this.powerUniversum; i++) {
            int mask = 1 << i % 8;
            if (mask == (mask & (int) bits.charAt(i / 8))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bitset)) return false;

        Bitset bitset = (Bitset) o;

        if (powerUniversum != bitset.powerUniversum) return false;
        return bits != null ? bits.equals(bitset.bits) : bitset.bits == null;
    }

    @Override
    public int hashCode() {
        int result = powerUniversum;
        result = 31 * result + (bits != null ? bits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int j = 0; j < this.powerUniversum; j++) {
            int mask = 1 << j % 8;
            if (mask == (mask & (int) bits.charAt(j / 8))) {
                result.append(", ").append(j);
            }
        }
        if (result.length() > 1) result.delete(1, 3);
        return result.append("}").toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
