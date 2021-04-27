package sample;

/**
 * Class to control the pit values
 * Includes getting, setting and incrementing values.
 *
 * @author Kieran
 */
public class Hole {

    private int pitValue;

    /**
     * Constructor to set the default pit value at start of the game
     */
    public Hole() {
        this.pitValue = 0;
    }

    /**
     * Constructor with a parameter of stones
     *
     * @param stones - takes in the number of stones and sets them to a certain pit
     */
    public Hole(int stones) {
        this.pitValue = stones;
    }

    /**
     * Method to get pit value
     *
     * @return - the number of stones inside a certain pit
     */
    public int getPitValue() {
        return pitValue;
    }


    /**
     * Method to set the pit value to the given value
     *
     * @param value - for setting the pit value
     */
    public void setPitValue(int value) {
        this.pitValue = value;
    }

    /**
     * Method for incrementing the pit value
     *
     * @param value - increments the pit value
     */
    public void incrementPitValueX(int value) {
        this.pitValue += value;
    }

    /**
     * Method for incrementing the pit value by 1
     */
    public void incrementPitValue() {
        this.pitValue += 1;
    }

    /**
     * Method for incrementing the pit value by 2
     */
    public void incrementPitValue2() {
        this.pitValue += 2;
    }

    /**
     * Method clears the pit values.
     */
    public void clearPitValue() {
        this.pitValue = 0;
    }
}
