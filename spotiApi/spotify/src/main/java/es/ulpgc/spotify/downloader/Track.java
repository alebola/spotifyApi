package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Track {

    public Map<String, List<String>> getTrack(String albumId) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        String response = accessor.get("/albums/" + albumId + "/tracks?limit=50", Map.of());

        List<String> tracks_id = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> duration = new ArrayList<>();
        List<String> explicit = new ArrayList<>();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        JsonArray items = jsonObject.get("items").getAsJsonArray();
        for (JsonElement item : items) {
            tracks_id.add(item.getAsJsonObject().get("id").getAsString());
            names.add(item.getAsJsonObject().get("name").getAsString());
            duration.add(item.getAsJsonObject().get("duration_ms").getAsString());
            explicit.add(item.getAsJsonObject().get("explicit").getAsString());
        }


        Map<String, List<String>> map_track = new HashMap<>();
        map_track.put("ID Tracks", tracks_id);
        map_track.put("Tracks Names", names);
        map_track.put("Tracks Duration", duration);
        map_track.put("Explicit", explicit);
        return map_track;


    }
}
