package org.example.llc.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Creates Print Writers to enable save and load functionality
 */

public class FileReader
{
    String[] opcodesArray;

    /**
     * Handles save logic
     * @param arr is array to be saved
     * @param file is file to be saved
     */
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

    /**
     * Handles load functionality
     * @param file file to be read by scanner
     * @return returns array to be passed on to UI
     * @throws FileNotFoundException throws exception
     */
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