public class MyStack<T> implements IStack<T>{
    private static final int DEFAULT_CAPACITY = 3;
    T array[];
    int topIndex;

    @SuppressWarnings("unchecked")
    public MyStack(int capacity){
        array=(T[])(new Object[capacity]);
        topIndex=0;
    }

    public MyStack (){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return topIndex==0;
    }

    @Override
    public boolean isFull() {
        return topIndex==array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[--topIndex];
    }

    @Override
    public void push(T elem) throws FullStackException {
        if(isFull()){
            System.arraycopy(array,1,array,0,array.length-1);
            topIndex--;
        }
        array[topIndex++]=elem;
    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[topIndex-1];
    }

    public void wyswietl(){
        for(T thing:array){
            System.out.print(thing+" ");
        }
    }
}