package io.github.R3charged;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ReferalMap {

    public final static UUID SERVER_ID = new UUID(0,0);

    private static HashMap<UUID, UUID> map = new HashMap<>();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static File dir = new File(Referrals.getDir() + "\\referrals.json");

    public static HashMap<UUID, UUID> get() {
        return map;
    }

    public static int getReferred(UUID sender) {
        int i = 0;
        for(UUID v : map.values()) {
            if(sender.equals(v)) {
                i++;
            }
        }
        return i;
    }

    public static String[] getReferredList(UUID sender) {
        ArrayList<UUID> list = new ArrayList<>();
        ReferalMap.get().forEach((k, e) -> {
            if (e.equals(sender)) {
                list.add(k);
            }
        });
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = UUIDFetcher.getUsername(list.get(i));
        }
        return arr;
    }

    public static void serialize() {
        try {
            FileWriter writer = new FileWriter(dir);
            System.out.println(gson.toJson(map));
            gson.toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            map = gson.fromJson(new FileReader(dir), new TypeToken<HashMap<UUID, UUID>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Referals: Could not deserialize.");
        }
    }
}
