import java.util.Random;

/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private static final int SAMPLING_RATE = 44100;
    private static final double DECAY_FACTOR = 0.994;
    
    private RingBuffer ringBuffer;
    private int count;
    
    public GuitarString(double frequency) 
    {
        int capacity = (int) (SAMPLING_RATE/frequency);
        ringBuffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            ringBuffer.add(0.0);
        }
        count = 0;
    }

    public GuitarString(double[] array) 
    {
        ringBuffer = new RingBuffer(array.length);
        for (int i = 0; i < array.length; i++) {
            ringBuffer.add(array[i]);
        }
        count = 0;
    }

    public void pluck() 
    {
        //int n = ringBuffer.size();
        /*while (!ringBuffer.isEmpty()) {
            ringBuffer.remove();
        }*/
        /*for (int i = 0; i < n; i++) {
            ringBuffer.remove();
            ringBuffer.add(Math.random()-0.5);
        }*/
        for (int i = 0; i < ringBuffer.size(); i++) {
            double temp = ringBuffer.remove();
            ringBuffer.add(temp + Math.random() - 0.500);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double first = ringBuffer.remove();
        double second = ringBuffer.peek();
        count++;
        double avg = DECAY_FACTOR * (first + second) / 2.0;
        ringBuffer.add(avg);
    }

    // return the current sample
    public double sample() 
    {
        return ringBuffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return count;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
