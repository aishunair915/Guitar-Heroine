/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    private int front;
    private int rear;
    private int size;
    private double[] arr;
    private int currSize;
    
    public RingBuffer(int capacity)
    {
        arr = new double[capacity];
        size = capacity;
        front = 0;
        rear = -1;
        currSize = 0;
    }

    public int size()
    {
        return currSize;
    }
    
    public boolean isEmpty()
    {
        return (currSize==0);
    }
    
    public boolean isFull()
    {
        return (currSize == size);
    }
    
    public void add(double value)
    {
        rear = (rear + 1) % size;
        arr[rear] = value;
        currSize++;
    }
    
    public double peek()
    {
        return arr[front];
    }
    
    public double remove()
    {
        double val = arr[front];
        front = (front+1) % size;
        currSize--;
        return val;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
