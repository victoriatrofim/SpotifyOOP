package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import main.colectionsAudio.Library;
import main.test.Instruction;

public interface CommandInterface {
    void act(Instruction instruction);
}
