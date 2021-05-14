public class Main {

    public static void main(String[] args) {
        BinomialHeap<Integer> bh1 = new BinomialHeap<>();
        BinomialHeap<Integer> bh2 = new BinomialHeap<>();
        bh1.insert(10);
        bh1.insert(5);
        bh1.insert(20);
        bh1.showHeap();
        bh2.insert(20);
        bh2.insert(4);
        bh2.insert(2);
        bh2.showHeap();
        bh1.union(bh2);
        bh1.showHeap();
        System.out.println( bh1.minimumKey());
        System.out.println( bh1.extractMin());
        bh1.showHeap();
        System.out.println( bh1.minimumKey());
        bh1.decreaseKey(5, 11);
        bh1.decreaseKey(10, 3);
        System.out.println(bh1.size());
        bh1.showHeap();
        bh1.delete(5);
        System.out.println(bh1.size());
        bh1.showHeap();
    }
}
