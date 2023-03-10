package fr.zekariateam.welcomer.objects;

import java.util.List;

public class Reward {

    private Integer chance;
    private List<String> commands;
    private List<String> messages;

    public Reward(Integer chance, List<String> commands, List<String> messages) {
        this.chance = chance;
        this.commands = commands;
        this.messages = messages;
    }

    public Integer getChance() {
        return chance;
    }

    public List<String> getCommands() {
        return commands;
    }

    public List<String> getMessages() {
        return messages;
    }
}
