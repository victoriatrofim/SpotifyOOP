package main.commands;

import main.UserOptions.Database;
import main.commands.playerCommands.*;
import main.commands.searchBarCommands.SearchCommand;
import main.commands.searchBarCommands.SelectCommand;

public class CommandGenerator {
    /**
     * @param command
     * @return
     */
    public CommandInterface generate(final String command, final Database database) {
        switch (command) {
            case "search": return new SearchCommand(database);
            case "select": return new SelectCommand(database);
            case "load" : return new LoadCommand(database);
            case "status": return new StatusCommand(database);
            case "playPause" : return new PlayPauseCommand(database);
            case "repeat" : return new RepeatCommand(database);
            case "shuffle": return new ShuffleCommand(database);

//            case "backward": return new BackwardCommand(database);
//            case "forward" : return new ForwardCommand(database);
//            case "addRemoveInPlaylist": return new AddRemoveInPlaylistCommand(database);
//            case "next" : return new NextCommand(database);
//            case "prev" : return new PrevCommand(database);
//            case "createPlaylist": return new SelectCommand(database);
//            case "follow" : return new LoadCommand(database);
//            case "showPlaylists": return new StatusCommand(database);
//            case "switchVisibility" : return new PlayPauseCommand(database);
//            case "like" : return new RepeatCommand(database);
//            case "getTop5Playlists" : return new RepeatCommand(database);
//            case "showPreferredSongs" : return new LoadCommand(database);
//            case "getTop5Songs": return new StatusCommand(database);
            default: return new ExitCommand(database);
        }
    }

}
