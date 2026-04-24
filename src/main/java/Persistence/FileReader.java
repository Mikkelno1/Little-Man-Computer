package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReader
{
    String[] opcodesArray;

    public void saveFile(int[] arr, File file)
    {
        try (PrintWriter output = new PrintWriter(file))
        {
            for (int i : arr)
            {
                output.println(i);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }



    public String[] loadFile(File file) throws FileNotFoundException
    {
        int i = 0;
        try
        {
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            opcodesArray = new String[100];
            while (scanner.hasNextLine())
            {
                String operator = scanner.nextLine();
                sb.append(operator);
                opcodesArray[i] = operator;
                i++;
            }
            scanner.close();
            return opcodesArray;
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }
}