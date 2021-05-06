import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> {

    public BinarySearchTree() {
        this._root = null;
    }

    private class Node {

        T value;
        Node left, right;

        public Node(T elem) {
            this.value = elem;
        }

        public Node(T elem, Node left, Node right) {
            this.value = elem;
            this.left = left;
            this.right = right;
        }
    }

    //Root and comparator of BST
    private Node _root;
    private Comparator<T> _comparator = null;

    //Constructor of BST
    public BinarySearchTree(Comparator<T> comparator) {
        _comparator = comparator;
        this._root = null;
    }

    public Node getRoot(){
        return _root;
    }

    public T getRootValue(){
        return _root.value;
    }

    public void insert(T elem) { //Tutaj pewnie trzeba sprawdzić, czy juz nie jest przypadkiem!
        _root = insert(_root, elem);
    }

    private Node insert(Node node, T elem) {
        if (node == null) {
            node = new Node(elem);
            return node;
        } else {
            int cmp = _comparator.compare(elem, node.value);
            if (cmp < 0) {
                node.left = insert(node.left, elem);
            } else if (cmp > 0) {
                node.right = insert(node.right, elem);
            }//brak warunku ze element juz jest w drzewie sprawia, ze zwracamy po prostu wezel z tym elementem.(nic sie nie dzieje)
        }
        return node;
    }

    public void delete(T elem) {
        _root = delete(_root, elem);
    }

    private Node delete(Node node, T elem) {
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            int cmp = _comparator.compare(elem, node.value);
            if (cmp < 0) {
                node.left = delete(node.left, elem);
            } else if (cmp > 0) {
                node.right = delete(node.right, elem);
            } else {
                if (node.left == null && node.right == null) {
                    return null;
                } else if (node.left != null && node.right != null) {

                    node.right = detachMin(node, node.right);
                } else {
                    node = (node.left != null) ? node.left : node.right;
                }
            }
        }
        return node;
    }

    private Node detachMin(Node del, Node node) {
        if (node.left != null) {
            node.left = detachMin(del, node.left);
        } else {
            del.value = node.value;
            node = node.right;
        }
        return node;
    }

    private Node findMaximumNode(Node node) {
        if (node.right != null) {
            node.right = findMaximumNode(node.right);
        }
        return node;
    }

    public T getMax() {
        if (_root == null) {
            throw new NoSuchElementException();
        }
        Node node = findMaximumNode(_root);
        return node.value;
    }

    private Node findMinimumNode(Node node) {
        if (node.left != null) {
            node.left = findMinimumNode(node.left);
        }
        return node;
    }

    public T getMin() {
        if (_root == null) {
            throw new NoSuchElementException();
        }
        Node node = findMinimumNode(_root);
        return node.value;
    }

    public T lower(T elem) {
        Node lowNode = lowerNode(_root, elem);
        if (lowNode == null) {
            return null;
        } else {
            return lowNode.value;
        }
    }

    private Node lowerNode(Node node, T elem) {
        if (node == null) {
            return null;
        }
        int cmp = _comparator.compare(node.value, elem);
        if (cmp==0){
            return node;
        } else if(cmp<0){
            Node k = lowerNode(node.right, elem);
            if(k==null){
                return node;
            } else {
                return k;
            }
        } else if(cmp>0){
            return lowerNode(node.left,elem);
        }

        return null;
    }

    public T upper(T elem){
        Node upNode = upperNode(_root, elem);
        if (upNode == null) {
            return null;
        } else {
            return upNode.value;
        }
    }

    private Node upperNode(Node node, T elem){
        if(node==null){
            return null;
        }
        int cmp = _comparator.compare(node.value, elem);
        if(cmp==0){
            return node;
        } else if(cmp <0){
            return upperNode(node.right,elem);
        } else if(cmp >0){
            Node k = upperNode(node.left, elem);
            if(k==null){
                return node;
            } else {
                return k;
            }
        }
        return null;
    }

    public T successor(T elem) {
        Node upNode = upperNode(_root, elem);
        if (upNode == null) {
            return null;
        } else {
            return upNode.value;
        }
    }

    private Node successorNode(Node node, T elem) {
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            int cmp = _comparator.compare(elem, node.value);
            if (cmp == 0) {
                if (node.right != null) {
                    return findMinimumNode(node.right);
                } else {
                    return null;
                }
            } else if (cmp < 0) {
                Node retNode = upperNode(node.left, elem);
                if (retNode == null) {
                    return node;
                } else {
                    return retNode;
                }
            } else { //cmp>0
                return upperNode(node.right, elem);
            }
        }

    }

    public T predecessor(T elem) {
        Node lowNode = lowerNode(_root, elem);
        if (lowNode == null) {
            return null;
        } else {
            return lowNode.value;
        }
    }

    private Node predecessorNode(Node node, T elem) {
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            int cmp = _comparator.compare(elem, node.value);
            if (cmp == 0) {
                if (node.left != null) {
                    return findMaximumNode(node.left);
                } else {
                    return null;
                }
            } else if (cmp < 0) {
                Node retNode = lowerNode(node.left, elem);
                if (retNode == null) {
                    return node;
                } else {
                    return retNode;
                }
            } else { //cmp>0
                return lowerNode(node.right, elem);
            }
        }

    }

    public T find(T elem) {
        Node node = search(_root, elem);
        if (node == null) {
//            System.out.println("NIE ma drzewie!" + elem);
            return null;
        } else {
//            System.out.println("JEST w drzewie!" + elem);
            return node.value;
        }
    }

    private Node search(Node root, T elem) {
        if (root == null || _comparator.compare(elem, root.value) == 0) {
            return root;
        } else {
            int cmp = _comparator.compare(elem, root.value);
            if (cmp < 0) {
                return search(root.left, elem);
            } else if (cmp > 0) {
                return search(root.right, elem);
            }
        }
        return root;
    }

    public void inOrder() {
        inOrderRec(_root);
        System.out.println();
    }

    private void inOrderRec(Node _root) {
        if (_root == null) {
            return;
        }
        inOrderRec(_root.left);
        System.out.print(_root.value + " ");
        inOrderRec(_root.right);
    }

    public void preOrder() {
        preOrderRec(_root);
        System.out.println();
    }

    private void preOrderRec(Node _root) {
        if (_root == null) {
            return;
        }
        System.out.print(_root.value + " ");
        preOrderRec(_root.left);
        preOrderRec(_root.right);
    }

    public void postOrder() {
        postOrderRec(_root);
        System.out.println();
    }

    private void postOrderRec(Node _root) {
        if (_root == null) {
            return;
        }
        postOrderRec(_root.left);
        postOrderRec(_root.right);
        System.out.print(_root.value + " ");
    }

    public void show(int var) {
        switch (var) {
            case 0:
                inOrder();
                break;
            case 1:
                preOrder();
                break;
            case 2:
                postOrder();
                break;
        }
    }

}
