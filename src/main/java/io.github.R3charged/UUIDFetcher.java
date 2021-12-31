package io.github.R3charged;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class UUIDFetcher {
    public static UUID getUUID(String playerName) throws Exception{
        String uuid = null;


        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName + "?");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = reader.readLine();
        uuid = line;
        String[] id = line.split(":");

        uuid = id[2];
        uuid = uuid.substring(1, 34);
        uuid =  (uuid.substring(0,8) + "-" + uuid.substring(8,12) + "-" + uuid.substring(12,16) + "-"+ uuid.substring(16,20)+ "-" + uuid.substring(20,uuid.length()-1));


        return UUID.fromString(uuid);

    }

    public static String getUsername(UUID playerUUID){
        String rename = null;
        String uuid = playerUUID.toString();

        try{
            URL url = new URL("https://api.mojang.com/user/profiles/" + uuid.replace("-","") + "/names");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            rename = line;
            String[] id = line.split("\"name\":");
            String[] temp = id[id.length-1].split("\"");
            rename = temp[1];
        }catch(Exception e){
            return null;
        }
        return rename;

    }
}
