import java.security.Key;
import java.util.Collections;
import java.util.Comparator;

public class BinomialHeap<K extends Comparable>{



    private class Node<K> {
        K key;
        int degree;
        Node<K> child,sibling,parent;


        public Node(K key){
            this.key = key;
            this.degree=0;
            this.child = null;
            this.parent = null;
            this.sibling = null;
        }

        void addNode(Node<K> other){
            other.parent = this;
            other.sibling = child;
            child = other;
            degree++;
        }

        public Node<K> reverse(Node<K> sib){
            Node<K> retNode;
            if(sibling!=null){
                retNode = sibling.reverse(this);
            } else {
                retNode = this;
            }
            sibling = sib;
            return retNode;
        }

        public int getSizeParent(){
            return (1+((parent == null) ? 0 : parent.getSizeParent()));
        }

        public int getSize(){
            return (1+((child==null)?0:child.getSize()) + ((sibling==null)?0:sibling.getSize()));
        }

    }


    private Node<K> head;
    private int size;

    private Comparator<K> comparator;

    public BinomialHeap(){
        size=0;
        head=null;

        this.comparator = new Comparator<>() {
            @Override
            public int compare(K o1, K o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public int size(){
        return size;
    }

    public void insert(K x){

        Node<K> X = new Node<>(x);
        if(this.head == null){
            head = X;
            size=1;
        }
        else {
            unionNodes(X);
            size++;
        }
    }

    private void unionNodes(Node<K> binHeap){
        merge(binHeap);

        Node<K> prevTemp = null, temp = head, nextTemp = head.sibling;

        while (nextTemp!=null){
            if((temp.degree != nextTemp.degree)||((nextTemp.sibling!=null) && (nextTemp.sibling.degree == temp.degree))){
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if(comparator.compare(temp.key, nextTemp.key)<=0){
                    temp.sibling =  nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                }
                else {
                    if(prevTemp == null){
                        head = nextTemp;
                    } else {
                      prevTemp.sibling = nextTemp;
                    }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }

    private void merge(Node<K> binHeap){
        Node<K> temp1 = head, temp2 = binHeap;

        while ((temp1!=null) && (temp2!=null)){
            if(temp1.degree == temp2.degree){
                Node<K> tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            } else {
                if(temp1.degree < temp2.degree){
                    if((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)){
                        Node<K> tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else {
                        temp1 = temp1.sibling;
                    }
                } else {
                    Node<K> tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == head)
                    {
                        head = temp1;
                    }
                }
            }
        }
        if(temp1==null){
            temp1 = head;
            while (temp1.sibling!=null){
                temp1 = temp1.sibling;
            }
            temp1.sibling=temp2;
        }
    }

    public void union(BinomialHeap<K> other){
        if (other.head == null) {
            return;
        }
        this.size += other.size;
        if (this.head == null) {
            this.head = other.head;
            return;
        }
        unionNodes(other.head);
    }

    public K minimumKey(){
        Node<K> minimum = minimum();
        return minimum.key;
    }

    private Node<K> minimum()
    {
        Node<K> y = this.head;
        Node<K> x = this.head;
        K min = x.key;
        while (x!=null){
            if(comparator.compare(x.key, min)<0){
                y=x;
                min = x.key;;
            }
            x=x.sibling;
        }
        return y;
    }

    public K extractMin(){
        if(head==null){
            return null;
        }
        Node<K> temp = head, prevTemp = null;
        Node<K> minNode = minimum();
        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }
        if (prevTemp == null) {
            head = temp.sibling;
        }
        else {
            prevTemp.sibling = temp.sibling;
        }

        temp = temp.child;
        Node<K> fakeNode = temp;

        while (temp != null)
        {
            temp.parent = null;
            temp = temp.sibling;
        }

        if ((head == null) && (fakeNode == null))
        {
            size = 0;
        } else {
            if ((head == null) && (fakeNode != null))
            {
                head = fakeNode.reverse(null);
                size = head.getSize();
            }
            else
            {
                if ((head != null) && (fakeNode == null))
                {
                    size = head.getSize();
                }
                else
                {
                    unionNodes(fakeNode.reverse(null));
                    size = head.getSize();
                }
            }
        }

        return minNode.key;

    }

    public void decreaseKey(K old, K new_val){
        if(comparator.compare(new_val, old)>0){
            System.out.println("Error! decrease, not increase!");
            return;
        }
        Node<K> temp = findNode(head ,old);
        if(temp == null){
            return;
        }
        temp.key = new_val;
        Node<K> tempP = temp.parent;

        while ((tempP!=null) &&(comparator.compare(temp.key, tempP.key)<0)){
            K z = temp.key;
            temp.key = tempP.key;
            tempP.key = z;

            temp= tempP;
            tempP = tempP.parent;
        }
    }


    public void delete(K x){
        decreaseKey(x, minimumKey());
        extractMin();
    }

    public Node<K> findNode(Node<K> h,K val){
        Node<K> temp = h , node = null;
        while (temp!=null){
            if(comparator.compare(temp.key, val)==0){
                node=temp;
                break;
            }
            if(temp.child ==null){
                temp = temp.sibling;
            } else {
                node = findNode(temp.child, val);
                if(node==null){
                    temp=temp.sibling;
                } else {
                    break;
                }
            }
        }
        return node;
    }



    public void  showHeap(){
        System.out.println("kopiec: ");
        displayHeap(head);
        System.out.println("\n");
    }

    private void displayHeap(Node<K> r){

        while (r!=null){
            System.out.println(r.key+" d "+r.degree+" h "+(r.getSizeParent() -1));
            displayHeap(r.child);
            r=r.sibling;
        }

    }

}
