package com.distant.system.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, AbstractCommand> commands = new TreeMap<String, AbstractCommand>();


    static {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("studentexam", new StudentExamCommand());
        commands.put("examanswer", new StudentExamResultCommand());
        commands.put("deletesubject", new DeleteSubjectCommand());
        commands.put("addsubject", new AddSubjectCommand());
        commands.put("editsubject", new EditSubjectCommand());
        commands.put ("addquestion", new AddQuestionCommand());

        //commands.put("noCommand", new NothingFound());
        // commands.put("logout", new LogoutCommand());
        // commands.put("viewSettings", new ViewSettingsCommand());

    }

    public static AbstractCommand get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
