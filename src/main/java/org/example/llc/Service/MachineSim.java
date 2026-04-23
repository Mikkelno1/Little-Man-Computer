package org.example.llc.Service;

public class MachineSim
{
    int[] memory = new int[100];
    int opcode;
    int address;
    private final CPU CPU = new CPU();

    public void simulateGame(boolean running)
    {
        CPU.setRunning(running);
        while(CPU.getRunning())
        {
            CPU.setInstReg(memory[CPU.getProgramCounter()] / 100);
            CPU.setAddress(memory[CPU.getProgramCounter()] % 100);
            handleOpcode(CPU.getInstReg(), CPU.getAddress());
            break;
        }
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
                } else
                {
                    CPU.increaseCounter();
                }
                break;
            case 1:
                //get number from address, add it to accumulator
                CPU.setAccumulator(CPU.getAccumulator() + memory[address]);
                System.out.println(CPU.accumulator + " add");
                CPU.increaseCounter();
                break;
            case 2:
                // get number from address, sub it from accumulator
                CPU.setAccumulator(CPU.getAccumulator() - memory[address]);
                System.out.println(CPU.accumulator + " minus");
                CPU.increaseCounter();
                break;
            case 3:
                // store value from accumlator to address

                /**
                 * mega fix later not now yes
                 */
                memory[address] = CPU.getAccumulator();
                System.out.println(CPU.accumulator + " save");
                CPU.increaseCounter();
                break;
            case 5:
                // load value from given address
                CPU.setAccumulator(memory[address]);
                System.out.println(CPU.accumulator + " load");
                CPU.increaseCounter();
                break;
            case 6:
                // Jump to given address
                CPU.setProgramCounter(address);
                System.out.println(CPU.accumulator + " jump");
                break;
            case 7:
                // Jump if accumulator is zero
                if (CPU.getAccumulator() == 0)
                {
                    CPU.setProgramCounter(address);
                    System.out.println(CPU.accumulator + " jump on zero");
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
                    System.out.println(CPU.accumulator + " jump on positive");
                } else
                {
                    CPU.increaseCounter();
                }
                break;
            case 9:
                if (address == 1)
                {
                    //request input
                    CPU.increaseCounter();
                }
                if (address == 2)
                {
                    writeToOutput();
                    CPU.increaseCounter();
                }
                break;
            default:
                // code block
                System.out.println("-_-");
        }
    }

    public void setMemoryValue(int addr, int value)
    {
        memory[addr] = value;
    }


    public int writeToOutput()
    {
        return CPU.getAccumulator();
    }


    public void loadInput(String value)
    {
        int newValue = Integer.parseInt(value);
        CPU.setAccumulator(newValue);
    }

    public void requestInput()
    {

    }

}
