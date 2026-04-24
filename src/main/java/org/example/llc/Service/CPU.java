package org.example.llc.Service;

public class CPU
{
    //Boolean for stopping the program
    private boolean running = false;

    //Ints
    public int programCounter = 0;
    public int instReg = 0;
    public int addressReg = 0;
    public int accumulator = 0;

    /**
     * Resets the CPU state
     */
    public void resetCPU()
    {
        running = false;
        programCounter = 0;
        instReg = 0;
        addressReg = 0;
        accumulator = 0;
    }


    public void resetProgCount()
    {
        programCounter = 0;
    }

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
