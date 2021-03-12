public class MyStack<T> implements IStack<T>{
    private static final int DEFAULT_CAPACITY = 3;
    T array[];
    int topIndex;
    int beginIndex;


    @SuppressWarnings("unchecked")
    public MyStack(int capacity){
        array=(T[])(new Object[capacity+1]);
    }

    public MyStack (){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return topIndex==beginIndex;
    }

    @Override
    public boolean isFull() {
        return beginIndex==(topIndex+1)%array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        if (topIndex == 0){
            topIndex=array.length;
        }
        topIndex--;
        return array[topIndex];
    }

    @Override
    public void push(T elem) throws FullStackException {
        if(isFull()){
            beginIndex++;
            beginIndex%=array.length;
        }
        array[topIndex++]=elem;
        topIndex%=array.length;
    }

    @Override
    public int size() {
        return (topIndex+array.length-beginIndex) % array.length;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[topIndex-1];
    }

    public void wyswietl(){//zwykły for
        int n = beginIndex;
        for(int i=0;i<3;i++){
            System.out.print(array[n]+" ");
            n++;
            n%=array.length;
        }
    }
}
