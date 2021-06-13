public class Main {


    public static void main(String[] args) {
        String text = "ABABCDABABCDBBABABCD";
        String pattern = "ABABCD";

        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%4c",text.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%4d",i);
        }
        System.out.println();
        System.out.println(KMP.compute(pattern,text));
    }
}
