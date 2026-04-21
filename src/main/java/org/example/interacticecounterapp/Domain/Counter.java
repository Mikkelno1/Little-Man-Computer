package org.example.interacticecounterapp.Domain;

public class Counter
{

    private int startingValue = 0;
    private final int INCREMENT_VALUE = 1;
    private final int DECREMENT_VALUE = 1;


    public Counter()
    {
    }

    public void decrement()
    {
        startingValue -= DECREMENT_VALUE;
    }

    public void increment()
    {
        startingValue += INCREMENT_VALUE;
    }

    public void resetValue()
    {
        startingValue = 0;
    }

    public int getStartingValue()
    {
        return startingValue;
    }
}
