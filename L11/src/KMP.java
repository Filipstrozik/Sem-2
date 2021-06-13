import java.util.ArrayList;

class KMP {

    public KMP(){}

    public static ArrayList<Integer> compute(String patern, String text) {
        int M = patern.length();
        int N = text.length();
        int[] lps = new int[M];

        ArrayList<Integer> answer = new ArrayList<>();


        fillLpsTable(patern, lps);

        int i = 0, j = 0;
        while (i < N){
            if(patern.charAt(j)== text.charAt(i)){
                j++;
                i++;
            }
            if(j==M){
                //found pattern
//                System.out.println("Found pattern at index " + (i-j));
                answer.add((i-j));

                j = lps[j-1];
            } else if(i < N && patern.charAt(j) != text.charAt(i)){
                if(j != 0){
                    j = lps[j -1];
                } else {
                    i++;
                }
            }
        }
        System.out.println("Found pattern at indexes: ");
        return answer;
    }

    public static void fillLpsTable(String patern, int[] lps) {
        int length = 0;
        int i = 1;
        lps[0] = 0;

        while (i < patern.length()) {
            if (patern.charAt(i) == patern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {                // pat[i] != pat[length]{
                if (length != 0) {

                    length = lps[length - 1];

                } else { //len == 0

                    lps[i] = length;
                    i++;
                }
            }

        }
    }

}
