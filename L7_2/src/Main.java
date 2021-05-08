import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);

        bst.show(0);


        System.out.println(bst.upper(60));

        System.out.println(bst.lower(0));

        System.out.println("max value: "+bst.getMax());

        System.out.println("min value: "+bst.getMin());
    }
}
