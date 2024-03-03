package com.example.duolingominecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuolingoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("lesson")) {
            // Simulate a Duolingo Spanish lesson
            sendLesson(player, 1); // You can replace 1 with the desired lesson number
            return true;
        }

        // Display command usage if incorrect arguments are provided
        sender.sendMessage(ChatColor.RED + "Usage: /duolingo lesson <lesson_number>");
        return true;
    }

    private void sendLesson(Player player, int lessonNumber) {
        // Replace the following with the actual Duolingo lesson content
        String lessonContent = getLessonContent(lessonNumber);

        // Send the lesson content to the player
        player.sendMessage(ChatColor.GREEN + "Duolingo Spanish Lesson " + lessonNumber + ":");
        player.sendMessage(lessonContent);
    }

    private String getLessonContent(int lessonNumber) {
        // Replace this with actual Duolingo lesson content retrieval logic
        // For simplicity, a static lesson content is used here
        switch (lessonNumber) {
            case 1:
                return "Hola - Hello\n" +
                        "Adiós - Goodbye\n" +
                        "Gracias - Thank you";
            // Add more cases for other lessons as needed
            default:
                return "Lesson not found.";
        }
    }
}
