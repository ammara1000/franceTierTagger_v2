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
            return "";
        }
        //System.out.println("--------------1"+info.pseudo);
        String best_actual_gamemode=null;
        Integer best_retired_gamemode=null;
        if (info.tiers!=null) {
            //System.out.println("--------------2" + info.pseudo);
            for (String key : info.tiers.keySet()) {
                Tier value = info.tiers.get(key);
                if (best_actual_gamemode==null & info.tiers.get(key).tier!=null){
                    best_actual_gamemode = key;
                }
                else {
                    //System.out.println("key: "+key);
                    //System.out.println("best: "+best_actual_gamemode);
                    //System.out.println(info.tiers.get(best_actual_gamemode));
                    //System.out.println(info.tiers.get(key));
                    if (info.tiers.get(key).tier!=null){
                        if (importance.get(info.tiers.get(best_actual_gamemode).tier) < importance.get(info.tiers.get(key).tier)) {
                            best_actual_gamemode = key;
                        }
                    }
                }
                //System.out.println(best_actual_gamemode);
            }
            if (info.retired_tiers != null) {
                //System.out.println("--------------3" + info.pseudo);
                for (int i = 0; i < info.retired_tiers.size(); i++) {
                    Tier element = info.retired_tiers.get(i);
                    if (best_retired_gamemode == null){
                        best_retired_gamemode = i;
                    }
                    else{
                        if (importance.get(info.retired_tiers.get(best_retired_gamemode).tier) < importance.get(info.retired_tiers.get(i).tier)) {
                            best_retired_gamemode = i;
                        }
                    }
                }
                //System.out.println(best_retired_gamemode);
            }
            //System.out.println("--------------01" + info.pseudo);
            if (best_retired_gamemode != null) {
                //System.out.println("--------------4" + info.pseudo);
                if (best_actual_gamemode == null){
                    //System.out.println("--------------5" + info.pseudo);
                    return info.retired_tiers.get(best_retired_gamemode).tier + " " + info.retired_tiers.get(best_retired_gamemode).category;
                }
                else {
                    if (importance.get(info.retired_tiers.get(best_retired_gamemode).tier) < importance.get(info.tiers.get(best_actual_gamemode).tier)) {
                        //System.out.println("--------------5" + info.pseudo);
                        return info.retired_tiers.get(best_retired_gamemode).tier + " " + info.retired_tiers.get(best_retired_gamemode).category;
                    }
                }
            }
            //System.out.println("--------------02" + info.pseudo);
            if (best_actual_gamemode != null) {
                //System.out.println("--------------6" + info.pseudo);
                return "| "+info.tiers.get(best_actual_gamemode).tier + " " + info.tiers.get(best_actual_gamemode).category;
            }
        }
        //System.out.println("--------------7" + info.pseudo);
        return "";
    }
}
