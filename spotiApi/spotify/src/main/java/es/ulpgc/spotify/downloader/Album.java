package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {

    public Map<String, List<String>> getAlbums(String artistId) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        String response = accessor.get("/artists/" + artistId + "/albums", Map.of());

        List<String> albums = new ArrayList<>();
        List<String> total_tracks = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> date = new ArrayList<>();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        JsonArray items = jsonObject.get("items").getAsJsonArray();
        for (JsonElement item : items) {
                albums.add(item.getAsJsonObject().get("id").getAsString());
                total_tracks.add(item.getAsJsonObject().get("total_tracks").getAsString());
                names.add(item.getAsJsonObject().get("name").getAsString());
                date.add(item.getAsJsonObject().get("release_date").getAsString());
            }

        Map<String, List<String>> map_album = new HashMap<>();
        map_album.put("Albums' ID", albums);
        map_album.put("Albums' Total Tracks", total_tracks);
        map_album.put("Albums' Names", names);
        map_album.put("Albums' Release Dates", date);
        return map_album;
        }
    }

