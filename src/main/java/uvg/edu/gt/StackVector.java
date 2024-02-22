package uvg.edu.gt;

import java.util.EmptyStackException;
import java.util.Vector;

public class StackVector<E> implements IStack<E> {
    private Vector<E> data;

    public StackVector() {
        data = new Vector<E>();
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
    public E peek() throws EmptyStackException {
        if (data.isEmpty()) {
            throw new EmptyStackException();
        }
        return data.elementAt(data.size() - 1);
    }

    @Override
    public E get(int i) {
        return data.get(i);
    }

    @Override
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
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

    public boolean isInStack(String value) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(String value) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
}