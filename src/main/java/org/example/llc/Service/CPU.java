package org.example.llc.Service;

public class CPU
{
    private boolean running = false;
    private int programCounter = 0;
    private int instReg = 0;
    private int addressReg = 0;
    private int accumulator = 0;

    public int getAccumulator()
    {
        return accumulator;
    }

    public void setAccumulator(int accumulator)
    {
        this.accumulator = accumulator;
    }

    public boolean getRunning()
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

    public void increaseCounter()
    {
        programCounter++;
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

    public void resetCPU()
    {
        running = false;
        programCounter = 0;
        instReg = 0;
        addressReg = 0;
        accumulator = 0;
    }
}
