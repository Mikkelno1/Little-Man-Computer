package org.example.llc.Service;

public class OpCodeHandler
{
    public void handleOpcode(String memoryCode)
    {
        String opcode = getOpCode(memoryCode);
        String address = getAddress(memoryCode);

        switch(opcode) {
            case "0":
                if(address.equals("00")) {;/*Boolean StopProgram*/}
                else {/*return number*/
                    /*increment Program Counter +1*/}
                break;
            case "1":
                //get number from address, add it to accumulator
                break;
            case "2":
                // get number from address, sub it from accumulator
                break;
            case "3":
                // store value from accumlator to address
                break;
            case "5":
                // load value from given address
                break;
            case "6":
                // Jump to given address
                break;
            case "7":
                // Jump if accumulator is zero
                break;
            case "8":
                // Jump if accumulator is positive
                break;
            case "9":
                // if address is 01, request input
                // if address is 02, give output accumulator number
                break;
            default:
                // code block
        }
    }

    public String getOpCode(String memoryCode)
    {
        return memoryCode.substring(0,1);
    }

    public String getAddress(String memoryCode)
    {
        return memoryCode.substring(1,3);
    }

}
