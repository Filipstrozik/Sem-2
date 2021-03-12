public class VTStack<T> extends MyStack<T> {
    int _cursor;

    public VTStack(int capacity) {
        super(capacity);
        _cursor=super.topIndex-1;
    }

    public VTStack(){
        super();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }

    @Override
    public T pop() throws EmptyStackException {
        return super.pop();
    }

    @Override
    public void push(T elem){
        super.push(elem);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public T top() throws EmptyStackException {
        return super.top();
    }

    @Override
    public void wyswietl() {
        super.wyswietl();
    }

    public T peek() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return super.array[_cursor];
    }

    public void toTop(){
        _cursor=super.topIndex-1;
    }

    public void down() throws BottomOfStackException {
        if(_cursor==this.beginIndex)
            throw new BottomOfStackException();
        if(_cursor==0){
            _cursor=this.array.length;
        }
        _cursor--;
    }

}
