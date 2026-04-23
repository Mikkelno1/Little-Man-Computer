package org.example.llc.Service;

import javafx.collections.ObservableList;

public class CPU
{
    private Boolean running = false;
    private int programCounter = 0;
    private int instReg = 0;
    private int addressReg = 0;
    private String input = "";
    private ObservableList<String>  output;
    private int accumulator = 0;

    public int getAccumulator()
    {
        return accumulator;
    }

    public void setAccumulator(int accumulator)
    {
        this.accumulator = accumulator;
    }

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

    public ObservableList<String> writeToInput(String input)
    {
        return output;
    }

    public String loadInput()
    {
        return input;
    }

    public boolean startProgram()
    {
        return running;
    }

}
