package Persistence;

import javafx.stage.FileChooser;
import org.example.llc.UI.CCMemoryCell;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReader
{
    String[] operatorArray;

    public String[] saveFile(String[] arr, File file)
    {
        int i = arr.length; int x = 0;
        PrintWriter output;
        try
        {
            output = new PrintWriter(file);
            while (x < i)
            {
                output.print(arr[x] + "\n"); x++;
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        output.close();
        return arr;
    }

    public String[] loadFile(File file) throws FileNotFoundException
    {
        int i = 0; try
    {
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        operatorArray = new String[100];
        while (scanner.hasNextLine())
        {
            String operator = scanner.nextLine();
            sb.append(operator);
            operatorArray[i] = operator;
            i++;
        }
        scanner.close();
        return operatorArray;
    } catch (RuntimeException e)
    {
        throw new RuntimeException(e);
    }
    }
}