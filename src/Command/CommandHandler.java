package Command;

import java.util.LinkedList;

public class CommandHandler {
    LinkedList<CommandIF> history = new LinkedList<>();
    LinkedList<CommandIF> redoList = new LinkedList<>();
    int maxLenght=10;

    public void handle(CommandIF command){
        if(command.doIt()){     //se riusciamo a eseguire il comando lo aggiungiamo alla storia
            history.add(command);
            if(history.size() > maxLenght)  //se la storia ha più elementi di maxLenght rimuoviamo il primo comando
                history.removeFirst();
        }else {
            history.clear();
        }

        //dopo che eseguiamo un comando bisogna svuotare la redolist perchè il "futuro" non ha più senso
        if(!redoList.isEmpty()){
            redoList.removeFirst();
        }
    }

    public void undo(){
        if(!history.isEmpty()){
            CommandIF cmd = history.removeLast();
            cmd.undoIt();
            redoList.addLast(cmd);
        }
    }

    public void redo(){
        if(!redoList.isEmpty()){
            CommandIF cmd = redoList.removeLast();
            cmd.doIt();
            history.addLast(cmd);
        }
    }
}
