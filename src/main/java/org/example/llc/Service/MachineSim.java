package org.example.llc.Service;

public class MachineSim
{
    int[] memCels = new int[100];
    private final CPU cpu = new CPU();


    public void setMemoryValue(int addr, int value)
    {
        memCels[addr] = value;
    }

    public void handleOpcode(int codeValue)
    {
        int opcode = getOpCode(codeValue);
        int address = getAddress(codeValue);

        switch(opcode) {
            case 0:
                //Stop program or skip if there is a value
                if(address == 0)
                {
                    cpu.setRunning(false);
                }
                else
                {
                    cpu.setInstReg(cpu.getInstReg() + 1);
                }
                break;
            case 1:
                //get number from address, add it to accumulator
                cpu.setAccumulator(cpu.getAccumulator() + memCels[address]);
                break;
            case 2:
                // get number from address, sub it from accumulator
                cpu.setAccumulator(cpu.getAccumulator() - memCels[address]);
                break;
            case 3:
                // store value from accumlator to address
                memCels[address] = cpu.getAccumulator();
                break;
            case 4:
                // load value from given address
                cpu.setAccumulator(memCels[address]);
                break;
            case 6:
                // Jump to given address
                cpu.setProgramCounter(address);
                break;
            case 7:
                // Jump if accumulator is zero

                break;
            case 8:
                // Jump if accumulator is positive
                break;
            case 9:
                // if address is 01, request input
                // if address is 02, give output accumulator number
                break;
            default:
                // code block
                System.out.println("-_-");
        }
    }

    public int getOpCode(int codeValue)
    {
        String codeAsText = Integer.toString(codeValue);
        return Integer.parseInt(codeAsText.substring(0,1));
    }

    public int getAddress(int codeValue)
    {
        String codeAsText = Integer.toString(codeValue);
        return Integer.parseInt(codeAsText.substring(1,3));
    }

}
