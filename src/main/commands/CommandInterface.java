package main.commands;

import main.test.Instruction;


public interface CommandInterface {
    /**
     * @param instruction arrayNode of data from Json
     */
    void act(Instruction instruction);
}
