package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import main.commands.searchBarCommands.SearchCommand;
import main.commands.searchBarCommands.SelectCommand;
import main.test.Instruction;

public class CommandGenerator {
    public CommandInterface generate(String command) {
        switch (command) {
            case "search": return new SearchCommand();
            case "select": return new SelectCommand();
            default: return null;
        }
    }

}
