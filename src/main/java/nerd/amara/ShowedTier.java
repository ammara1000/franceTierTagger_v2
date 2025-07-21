package nerd.amara;

import nerd.amara.tiers.PlayerInfo;
import nerd.amara.tiers.Tier;

import java.util.HashMap;
import java.util.Map;

public class ShowedTier {
    private static Map<String,Integer> importance=Map.ofEntries(
            Map.entry("LT6", 1),
            Map.entry("HT6", 2),
            Map.entry("LT5", 3),
            Map.entry("HT5", 4),
            Map.entry("LT4", 5),
            Map.entry("HT4", 6),
            Map.entry("LT3", 7),
            Map.entry("HT3", 8),
            Map.entry("RLT2", 9),
            Map.entry("LT2", 10),
            Map.entry("RHT2", 11),
            Map.entry("HT2", 12),
            Map.entry("RLT1", 13),
            Map.entry("LT1", 14),
            Map.entry("RHT1", 15),
            Map.entry("HT1", 16)
    );
    public static String showed_tier(PlayerInfo info){
        if (info==null){
            return "unranked";
        }
        String best_actual_gamemode=null;
        Integer best_retired_gamemode=null;
        if (info.tiers!=null) {
            for (String key : info.tiers.keySet()) {
                Tier value = info.tiers.get(key);
                if (best_actual_gamemode == null || importance.get(info.tiers.get(best_actual_gamemode).tier) < importance.get(info.tiers.get(key).tier)) {
                    best_actual_gamemode = key;
                }
            }
        }
        if (info.retired_tiers!=null) {
            for (int i = 0; i < info.retired_tiers.size(); i++) {
                Tier element = info.retired_tiers.get(i);
                if (best_retired_gamemode == null || importance.get(info.retired_tiers.get(best_retired_gamemode).tier) < importance.get(info.retired_tiers.get(i).tier)) {
                    best_retired_gamemode = i;
                }
            }
        }
        if (best_retired_gamemode!=null) {
            if (best_actual_gamemode==null || importance.get(info.retired_tiers.get(best_retired_gamemode).tier)<importance.get(info.tiers.get(best_actual_gamemode).tier)){
                return info.retired_tiers.get(best_retired_gamemode).tier + " " + info.retired_tiers.get(best_retired_gamemode).category;
            }
        }
        if (best_actual_gamemode!=null){
            return info.tiers.get(best_actual_gamemode).tier+" "+info.tiers.get(best_actual_gamemode).category;
        }
        return "unranked";
    }
}
