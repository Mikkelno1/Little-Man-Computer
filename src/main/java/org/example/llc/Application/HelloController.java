package org.example.llc.Application;
import Persistence.FileReader;
import javafx.collections.ObservableList;
import org.example.llc.Service.CPU;

import java.io.File;
import java.io.FileNotFoundException;

public class HelloController
{

    FileReader fileReader = new FileReader();
    CPU cpu = new CPU();

    public String[] saveFile(String[] arr, File file)
    {
        return fileReader.saveFile(arr, file);
    }
    public String[] loadFile(File file) throws FileNotFoundException
    {
        return fileReader.loadFile(file);
    }
    public String loadInput(String text)
    {
        return cpu.loadInput();
    }
    public ObservableList<String> writeToOutput(String text)
    {
        return cpu.writeToInput(text);
    }

    public boolean startProgram(boolean running)
    {
        return cpu.startProgram();
    }
}