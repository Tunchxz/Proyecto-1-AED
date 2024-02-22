package uvg.edu.gt;

import java.util.ArrayList;

public class StackArrayList<E> implements IStack<E> {

    protected ArrayList<E> data;

    public StackArrayList() {
        data = new ArrayList<E>();
    }

    @Override
    public void push(E item) {
        data.add(item);
    }

    @Override
    public E pop() {
        return data.remove(size() - 1);
    }

    @Override
    public E peek() {
        return data.get(size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public E get(int i) {
        return data.get(i);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public void remove(int i) {
        data.remove(i);
    }

    @Override
    public void removeFirst() {
        data.remove(0);
    }
}