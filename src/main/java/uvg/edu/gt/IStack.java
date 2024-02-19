package uvg.edu.gt;

import java.util.EmptyStackException;

public interface IStack<E> {

    public void push(E data);

    public E pop();

    public E peek() throws EmptyStackException;

    public E get(int i);

    public boolean empty();

    public int size();

    public void clear();

    public void remove(int i);

    public void removeFirst();

}