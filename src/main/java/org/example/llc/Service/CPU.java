package org.example.llc.Service;

import javafx.collections.ObservableList;

public class CPU
{
    public boolean running = false;
    public int programCounter = 0;
    public int instReg = 0;
    public int addressReg = 0;
    public int accumulator = 0;


    public int getAccumulator()
    {
        return accumulator;
    }

    public void setAccumulator(int accumulator)
    {
        this.accumulator = accumulator;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public int getProgramCounter()
    {
        return programCounter;
    }

    public void setProgramCounter(int programCounter)
    {
        this.programCounter = programCounter;
    }

    public int getInstReg()
    {
        return instReg;
    }

    public void setInstReg(int instReg)
    {
        this.instReg = instReg;
    }

    public int getAddress()
    {
        return addressReg;
    }

    public void setAddress(int address)
    {
        this.addressReg = address;
    }

    public void increaseCounter()
    {
        programCounter++;
    }
}
