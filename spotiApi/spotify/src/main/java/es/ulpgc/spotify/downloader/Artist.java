package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artist {

    public Map<String, List<String>> getArtists(String artistId) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        String response = accessor.get("/artists/" + artistId, Map.of());
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        List<String> followers = new ArrayList<>();
        JsonObject followers_item = jsonObject.get("followers").getAsJsonObject();
        followers.add(followers_item.getAsJsonObject().get("total").getAsString());

        List<String> popularity = new ArrayList<>();
        JsonElement popularity_item = jsonObject.get("popularity");
        popularity.add(popularity_item.getAsString());

        List<String> names = new ArrayList<>();
        JsonElement names_item = jsonObject.get("name");
        names.add(names_item.getAsString());


        Map<String, List<String>> map_artist = new HashMap<>();
        map_artist.put("Artist's Followers", followers);
        map_artist.put("Artist's Popularity", popularity);
        map_artist.put("Artist's Name", names);
        return map_artist;

    }
}

