package org.example.llc.Application;

import org.example.llc.Domain.Counter;


public class HelloController
{

    private Counter counter = new Counter();


    public int showValue()
    {
        return counter.getStartingValue();
    }

    public void increment()
    {
        counter.increment();
    }

    public void decrement()
    {
        counter.decrement();
    }

    public void reset()
    {
        counter.resetValue();
    }


}
