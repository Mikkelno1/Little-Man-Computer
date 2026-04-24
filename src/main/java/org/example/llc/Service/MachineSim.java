package org.example.llc.Service;

import Persistence.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineSim
{
    private int[] memory = new int[100];
    private boolean waiting = false;
    private boolean trouble = false;
    private final CPU CPU = new CPU();
    private final List<Integer> outputValues = new ArrayList<>();
    FileReader fileReader = new FileReader();


    public void saveFile(File file)
    {
        fileReader.saveFile(memory, file);
    }

    public void loadFile(File file) throws FileNotFoundException
    {
        String[] opcodesAsText = fileReader.loadFile(file);

        for (int i = 0; i < opcodesAsText.length ; i++)
        {
            int opcode = Integer.parseInt(opcodesAsText[i]);
            memory[i] = opcode;
        }
    }

    public void step()
    {
        if (!CPU.isRunning() || isWaiting() ) { return; }
        CPU.setInstReg(memory[CPU.getProgramCounter()] / 100);
        CPU.setAddress(memory[CPU.getProgramCounter()] % 100);
        handleOpcode(CPU.getInstReg(), CPU.getAddress());
    }


    public void handleOpcode(int opcode, int address)
    {
        System.out.println(opcode + " " + address);
        switch(opcode)
        {
            case 0:
                //Stop program or skip if there is a value
                if (address == 0)
                {
                    CPU.setRunning(false);
                    waiting = false;
                } else
                {
                    CPU.increaseCounter();
                }
                break;
            case 1:
                //get number from address, add it to accumulator
                CPU.setAccumulator(CPU.getAccumulator() + memory[address]);
                System.out.println(CPU.getAccumulator() + " add");
                CPU.increaseCounter();
                break;
            case 2:
                // get number from address, sub it from accumulator
                CPU.setAccumulator(CPU.getAccumulator() - memory[address]);
                System.out.println(CPU.getAccumulator() + " minus");
                CPU.increaseCounter();
                break;
            case 3:
                // store value from accumlator to address
                memory[address] = CPU.getAccumulator();
                System.out.println(CPU.getAccumulator() + " save");
                CPU.increaseCounter();
                break;
            case 5:
                // load value from given address
                CPU.setAccumulator(memory[address]);
                System.out.println(CPU.getAccumulator() + " load");
                CPU.increaseCounter();
                break;
            case 6:
                // Jump to given address
                CPU.setProgramCounter(address);
                System.out.println(CPU.getAccumulator() + " jump");
                break;
            case 7:
                // Jump if accumulator is zero
                if (CPU.getAccumulator() == 0)
                {
                    CPU.setProgramCounter(address);
                    System.out.println(CPU.getAccumulator() + " jump on zero");
                } else
                {
                    CPU.increaseCounter();
                }
                break;
            case 8:
                // Jump if accumulator is positive
                if (CPU.getAccumulator() > 0)
                {
                    CPU.setProgramCounter(address);
                    System.out.println(CPU.getAccumulator() + " jump on positive");
                } else
                {
                    CPU.increaseCounter();
                }
                break;
            case 9:
                if (address == 1)
                {
                    //request input
                    CPU.setRunning(false);
                    waiting = true;
                    CPU.increaseCounter();
                }
                else if (address == 2)
                {
                    //sendAccuToOutput();
                    outputValues.add(CPU.getAccumulator());
                    CPU.increaseCounter();
                }
                break;
            default:
                // error code
                trouble = true;
                setRunning(false);
                System.out.println("-_-");
        }
    }

    public boolean isTrouble()
    {
        return trouble;
    }

    public void setTrouble(boolean trouble)
    {
        this.trouble = trouble;
    }

    public void setMemoryValue(int addr, int value)
    {
        memory[addr] = value;
    }

    /**
     *
     * @param code
     */
    public void loadInput(String code)
    {
        int newValue = Integer.parseInt(code);
        CPU.setAccumulator(newValue);
        waiting = false;
    }

    public boolean isWaiting()
    {
        return waiting;
    }

    public void setRunning(boolean bool)
    {
        CPU.setRunning(bool);
    }

    public List<Integer> getOutputValues()
    {
        return outputValues;
    }

    public int getProgramCounter()
    {
        return CPU.getProgramCounter();
    }

    public int getAccumulator()
    {
        return CPU.getAccumulator();
    }

    public int getInstReg()
    {
        return CPU.getInstReg();
    }

    public int getAddress()
    {
        return CPU.getAddress();
    }

    public int[] getMemory()
    {
        return memory;
    }

    public boolean isRunning()
    {
        return CPU.isRunning();
    }

    /**
     * Fills memory with blanks(0) and resets the cpu
     */
    public void resetProgram()
    {
        Arrays.fill(memory, 0);
        CPU.resetCPU();
    }

    public void resetProgCount()
    {
        CPU.resetProgCount();
    }


}
