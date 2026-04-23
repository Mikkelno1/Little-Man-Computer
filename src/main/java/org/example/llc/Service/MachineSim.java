package org.example.llc.Service;

public class MachineSim
{
    int[] memory = new int[100];
    private final CPU cpu;

    public MachineSim(CPU cpu)
    {
        this.cpu = cpu;
    }

    public void simulateGame()
    {
        while(cpu.getRunning())
        {
            cpu.setInstReg(memory[cpu.getProgramCounter()] / 100);
            cpu.setAddress(memory[cpu.getProgramCounter()] % 100);
            handleOpcode(cpu.getInstReg(), cpu.getAddress());
        }
    }

    public void handleOpcode(int opcode, int address) //public void handleOpcode(int codeValue)
    {
        //int opcode = codeValue / 100; //gets the first number
        //int address = codeValue % 100; //gets the remainder

        switch(opcode)
        {
            case 0:
                //Stop program or skip if there is a value
                if (address == 0)
                {
                    cpu.setRunning(false);
                }
                else
                {
                    cpu.increaseCounter();
                }
                break;
            case 1:
                //get number from address, add it to accumulator
                cpu.setAccumulator(cpu.getAccumulator() + memory[address]);
                cpu.increaseCounter();
                break;
            case 2:
                // get number from address, sub it from accumulator
                cpu.setAccumulator(cpu.getAccumulator() - memory[address]);
                cpu.increaseCounter();
                break;
            case 3:
                // store value from accumlator to address
                memory[address] = cpu.getAccumulator();
                cpu.increaseCounter();
                break;
            case 5:
                // load value from given address
                cpu.setAccumulator(memory[address]);
                cpu.increaseCounter();
                break;
            case 6:
                // Jump to given address
                cpu.setProgramCounter(address);
                break;
            case 7:
                // Jump if accumulator is zero
                if (cpu.getAccumulator() == 0)
                {
                    cpu.setProgramCounter(address);
                }
                else
                {
                    cpu.increaseCounter();
                }
                break;
            case 8:
                // Jump if accumulator is positive
                if (cpu.getAccumulator() > 0)
                {
                    cpu.setProgramCounter(address);
                }
                else
                {
                    cpu.increaseCounter();
                }
                break;
            case 9:
                // if address is 01, request input
                if (address == 1)
                {
                    //add logic code
                    cpu.increaseCounter();
                }
                // if address is 02, give output accumulator number
                if(address == 2)
                {
                    //add logic code
                    cpu.increaseCounter();
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
}
