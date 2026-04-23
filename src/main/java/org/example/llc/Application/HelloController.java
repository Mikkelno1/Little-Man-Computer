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
    /*
    public int sendAccuToOutput()
    {
        return mSim.sendAccuToOutput();
    }
    */
    public void simulateGame(boolean running)
    {
       mSim.setRunning(running);
       //mSim.simulateGame();
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
}