package org.example.llc.Application;
import Persistence.FileReader;
import org.example.llc.Service.MachineSim;

import java.io.File;
import java.io.FileNotFoundException;

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
    public int writeToOutput()
    {
        return mSim.writeToOutput();
    }

    public void simulateGame(boolean running)
    {
       mSim.simulateGame(running);
    }

    public void passOpcodeToMemory(int address, int value)
    {
        mSim.setMemoryValue(address, value);
    }

    public void requestInput()
    {

    }
}