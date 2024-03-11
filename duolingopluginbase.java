package com.example.duolingominecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DuolingoCommand implements CommandExecutor {

    private final Map<Integer, String> lessonContents;
    private final Map<Player, Integer> playerProgress;

    public DuolingoCommand() {
        // Initialize lesson contents in the constructor
        lessonContents = new HashMap<>();
        initializeLessons();

        // Initialize player progress tracking
        playerProgress = new HashMap<>();
    }

    private void initializeLessons() {
       // Add lessons and their content here
        lessonContents.put(1, "Hola - Hello\n" +
        "Adiós - Goodbye\n" +
        "Gracias - Thank you");
        lessonContents.put(2, "Por favor - Please\n" +
        "Perdón - Excuse me\n" +
        "Sí - Yes");
        lessonContents.put(3, "Buenos días - Good morning\n" +
        "Buena tarde - Good afternoon\n" +
        "Buena noche - Good night");
        lessonContents.put(4, "¿Cómo estás? - How are you?\n" +
        "Estoy bien - I am fine\n" +
        "Lo siento - I'm sorry");
        lessonContents.put(5, "La familia - The family\n" +
        "Padre - Father\n" +
        "Madre - Mother");
// Add more lessons as needed
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("lessons")) {
                sendAvailableLessons(player);
                return true;
            } else if (args[0].equalsIgnoreCase("reset")) {
                resetPlayerProgress(player);
                sender.sendMessage(ChatColor.GREEN + "Your Duolingo progress has been reset.");
                return true;
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("lesson")) {
            try {
                int lessonNumber = Integer.parseInt(args[1]);

                if (lessonContents.containsKey(lessonNumber)) {
                    sendLesson(player, lessonNumber);
                    updatePlayerProgress(player, lessonNumber);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Lesson not found.");
                    return true;
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid lesson number. Please provide a valid number.");
                return true;
            }
        }

        // Display command usage if incorrect arguments are provided
        sender.sendMessage(ChatColor.RED + "Usage: /duolingo lesson <lesson_number> OR /duolingo lessons OR /duolingo reset");
        return true;
    }

    private void sendLesson(Player player, int lessonNumber) {
        // Replace the following with the actual Duolingo lesson content
        String lessonContent = lessonContents.get(lessonNumber);

        // Send the lesson content to the player
        player.sendMessage(ChatColor.GREEN + "Duolingo Spanish Lesson " + lessonNumber + ":");
        player.sendMessage(lessonContent);
    }

    private void sendAvailableLessons(Player player) {
        player.sendMessage(ChatColor.YELLOW + "Available Duolingo Lessons:");
        for (Map.Entry<Integer, String> entry : lessonContents.entrySet()) {
            player.sendMessage(ChatColor.YELLOW + "Lesson " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private void resetPlayerProgress(Player player) {
        playerProgress.remove(player);
    }

    private void updatePlayerProgress(Player player, int lessonNumber) {
        playerProgress.put(player, lessonNumber);
    }
}
