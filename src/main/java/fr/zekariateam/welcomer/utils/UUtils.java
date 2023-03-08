package fr.zekariateam.welcomer.utils;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UUtils {

    public void sendMessage(String message, CommandSender sender) {
        String finalMessage = message;

        if (finalMessage.contains("<center>")) {
            finalMessage = finalMessage.replace("<center>", "");
            finalMessage = sendCenteredMessage(finalMessage);
        }

        sender.sendMessage(finalMessage);
    }

    public String sendCenteredMessage(@NotNull String message){
        int CENTER_PX = 154;
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb + message;
    }

}
