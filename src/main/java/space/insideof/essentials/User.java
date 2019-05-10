package space.insideof.essentials;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class User {
    private static final Map<Player, User> userMap = new HashMap<>();

    public static User getUser(Player player, boolean load) {
        User user = userMap.get(player);
        if(user == null && load)
            userMap.put(player, user = new User(player));
        return user;
    }

    public static void unloadUser(Player player) {
        User user = getUser(player, false);
        if(user != null) {
            try {
                user.saveData();
            } catch(IOException exception) {
                Main.getInstance().getLogger().severe("Unable to save user data for player " + player.getUniqueId() + "!");
                exception.printStackTrace();
            }
            userMap.remove(player, user);
        }
    }

    private Player player;

    private List<String> ipHistory;

    private User(Player player) {
        this.player = player;

        File file = new File(Main.getInstance().getDataFolder() + "/userdata", player.getUniqueId() + ".yml");
        YamlConfiguration data = file.exists() ? YamlConfiguration.loadConfiguration(file)
                                               : YamlConfiguration.loadConfiguration(new InputStreamReader(Main.getInstance().getResource("user.yml")));

        // load stuff
        ipHistory = data.getStringList("ip-history");

        String currentIp = player.getAddress().getHostName();
        if(!ipHistory.contains(currentIp))
            ipHistory.add(currentIp);
    }

    public List<String> getIpHistory()
    {
        return new ArrayList<>(ipHistory);
    }

    public Player getPlayer() {
        return player;
    }

    private void saveData() throws IOException {
        File file = new File(Main.getInstance().getDataFolder() + File.separator + "userdata", player.getUniqueId() + ".yml");
        if(file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        YamlConfiguration data = new YamlConfiguration();

        // save stuff
        data.set("ip-history", ipHistory);

        data.save(file);
    }
}
