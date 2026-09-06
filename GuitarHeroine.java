import java.util.Scanner;

/**
 * This GuitarHeroine object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarHeroine
{
    public static void main(String[] args) 
    {
        // Create two guitar strings, for concert A and C
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        
        int NUM_STRINGS = 37;
        double BASE_FREQUENCY = 440.0;
        double MULTIPLIER = 1.05956;
        
        GuitarString[] guitarStrings = new GuitarString[NUM_STRINGS];
        for (int i = 0; i < NUM_STRINGS; i++) {
            double frequency = BASE_FREQUENCY * Math.pow(1.05956, i - 24);
            guitarStrings[i] = new GuitarString(frequency);
        }

        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                int index = keyboard.indexOf(key);
                if (index != -1 && index < NUM_STRINGS) {
                    guitarStrings[index].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0.0;
            for (int i = 0; i < NUM_STRINGS; i++) {
                sample += guitarStrings[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < NUM_STRINGS; i++) {
                guitarStrings[i].tic();
            }
        }
    }
}
