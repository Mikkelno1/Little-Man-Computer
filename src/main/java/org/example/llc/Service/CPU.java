package org.example.llc.Service;

public class CPU
{
    private Boolean running = false;
    private int programCounter = 0;
    private int instReg = 0;
    private int addressReg = 0;

    public int getAccumulator()
    {
        return accumulator;
    }

    public void setAccumulator(int accumulator)
    {
        this.accumulator = accumulator;
    }

    private int accumulator = 0;

    public Boolean getRunning()
    {
        return running;
    }

    public void setRunning(Boolean running)
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
}
