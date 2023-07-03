import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Queue 
{
    private LinkedList<Object> q; // declare the object of the queue using LinkedList
    private int size;

    // constructor without parameter
    public Queue() 
    {
        q = new LinkedList<>();
        size = 0;
    }

    // method to insert an object to a queue
    public void enqueue(Object item) 
    {
        q.addLast(item);
        size++;
    }

    // method to remove an object from a queue
    public Object dequeue() 
    {
        if (empty()) 
        {
            throw new NoSuchElementException("Queue is empty");
        } 
        else 
        {
            size--;
            return q.removeFirst(); // remove first element
        }
    }

    // method to test whether the queue is empty or not
    public boolean empty() 
    {
        return (size == 0);
    }

    // return the element at the front without removing it
    public Object front() 
    {
        if (!empty()) 
        {
            return q.getFirst();
        } 
        else 
        {
            System.out.println("Queue is empty!");
            return null;
        }
    }

    public int size() 
    {
        return size;
    }

}
