package org.example.llc.Application;
import Persistence.FileReader;

import java.io.File;
import java.io.FileNotFoundException;

public class HelloController
{

    FileReader fileReader = new FileReader();

    public String[] saveFile(String[] arr, File file)
    {
        return fileReader.saveFile(arr, file);
    }
    public String[] loadFile(File file) throws FileNotFoundException
    {
        return fileReader.loadFile(file);
    }
}