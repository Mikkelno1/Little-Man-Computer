package org.example.llc.Application;
import Persistence.FileReader;
import org.example.llc.Service.MachineSim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class HelloController
{

    FileReader fileReader = new FileReader();
    MachineSim mSim = new MachineSim();



    public String[] saveFile(String[] arr, File file)
    {
        return fileReader.saveFile(arr, file);
    }

    public String[] loadFile(File file) throws FileNotFoundException
    {
        return fileReader.loadFile(file);
    }

    public void loadInput(String text)
    {
        mSim.loadInput(text);
    }

    public void setRunning(boolean running)
    {
       mSim.setRunning(running);
    }

    public void step()
    {
        mSim.step();
    }


    public void passOpcodeToMemory(int address, int value)
    {
        mSim.setMemoryValue(address, value);
    }

    public boolean isWaiting()
    {
        return mSim.isWaiting();
    }

    public List<Integer> getOutputValues()
    {
        return mSim.getOutputValues();
    }

    public int getAccumulator()
    {
        return mSim.getAccumulator();
    }

    public int getProgramCounter()
    {
        return mSim.getProgramCounter();
    }

    public int getInstReg()
    {
        return mSim.getInstReg();
    }

    public int getAddress()
    {
        return mSim.getAddress();
    }

    public int[] getMemory()
    {
        return mSim.getMemory();
    }

    public boolean isRunning()
    {
        return mSim.isRunning();
    }

    public void resetProgram()
    {
        mSim.resetProgram();
    }

    public void resetProgCount()
    {
        mSim.resetProgCount();
    }

}