package Command;

import java.util.LinkedList;

public class CommandHandler {
    LinkedList<CommandIF> history = new LinkedList<>();
    LinkedList<CommandIF> redoList = new LinkedList<>();
    int maxLenght=10;
    public void handle(CommandIF command){
        if(command.doIt()){
            history.add(command);
            if(history.size() > maxLenght)
                history.removeFirst();
        }else {
            history.clear();
        }

        if(redoList.size() > maxLenght){
            redoList.removeFirst();
        }
    }

    public void undo(){
        if(history.size() > 0){
            CommandIF cmd = history.removeLast();
            cmd.undoIt();
            redoList.addLast(cmd);
        }
    }

    public void redo(){
        if(redoList.size() > 0){
            CommandIF cmd = redoList.removeLast();
            cmd.doIt();
            history.addLast(cmd);
        }
    }
}
