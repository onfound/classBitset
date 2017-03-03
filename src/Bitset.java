import java.util.Arrays;

/**
 * Created by ilya on 14.02.17.
 */
public class Bitset {
        private int powerfullUniversum;
        private String bit;
        private int[] masksAscii = {1,2,4,8,16,32,64,128};

        Bitset(int powerfullUniversum){
            this.powerfullUniversum = powerfullUniversum;
            bit = String.valueOf(Character.toChars(0));
            for (int i = 0; i < powerfullUniversum/8 ; i++) {
                bit += String.valueOf(Character.toChars(0));
            }
        }

        @Override
        public String toString(){
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < this.powerfullUniversum; j++) {
                if (masksAscii[j%8]==(masksAscii[j%8] & (int) bit.charAt(j/8))){
                    result.append(j).append(", ");
                }
            }
            return "{" + result.delete(result.lastIndexOf(", "), result.lastIndexOf(", ")+2) + "}";
        }

        void add(int i){
            if (i>=powerfullUniversum) throw new IndexOutOfBoundsException("Outside of the Universum");
            StringBuilder bit1 = new StringBuilder();
            bit = bit1.append(bit.substring(0,i/8)).append(Character.toChars((int) bit.charAt(i / 8) | masksAscii[i%8])).append(bit.substring(i/8+1,bit.length())).toString();
        }
        void add(int[] i){
            for (int element:i){
                add(element);
            }
        }
        void remove(int i){
            StringBuilder bit2 = new StringBuilder();
            bit = bit2.append(bit.substring(0,i/8)).append(Character.toChars((int) bit.charAt(i / 8) ^ masksAscii[i%8])).append(bit.substring(i/8+1,bit.length())).toString();
        }
        void remove(int[] i){
            for (int element:i
                 ) {
                if (belong(element))remove(element);
            }
        }
        void cross(Bitset bs){

        }
        void combining(Bitset bs){

        }
        void supplement(Bitset bs){

        }
        Boolean belong(int i){
            return (((int) bit.charAt(i / 8) & masksAscii[i%8]) == masksAscii[i%8]);
        }

}
