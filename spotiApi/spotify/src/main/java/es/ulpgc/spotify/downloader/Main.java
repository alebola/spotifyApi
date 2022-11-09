package es.ulpgc.spotify.downloader;

import java.util.Map;

public class Main {

    private static final Map<String, String> artists = Map.of(
            "Andrew Garfield", "2garjIdgTW5i89s4Z3UT72",
            "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
            "Quevedo", "52iwsT98xCoGgiGntTiR7K",
            "Orslok", "2TzPi0fPdqVesjZ3Y2wY43",
            "Doblem", "6PebEMBc87yGO8Nhuspk7k");


    public static void main(String[] args) throws Exception {
        DataBaseManager dataBaseManager = new DataBaseManager();
        for (String Id : artists.values()){

        Artist artist = new Artist();
        String artist_name  = artist.getArtists(Id).get("Artist's Name").get(0);
        String followers = artist.getArtists(Id).get("Artist's Followers").get(0);
        String popularity = artist.getArtists(Id).get("Artist's Popularity").get(0);
        dataBaseManager.insertArtist(artist_name, followers, popularity, Id);

        Album album = new Album();
        album.getAlbums(Id);
         for (int x = 0; x < album.getAlbums(Id).get("Albums' ID").size(); x++){
            String albumId  = album.getAlbums(Id).get("Albums' ID").get(x);
            String total_tracks = album.getAlbums(Id).get("Albums' Total Tracks").get(x);
            String name = album.getAlbums(Id).get("Albums' Names").get(x);
            String date = album.getAlbums(Id).get("Albums' Release Dates").get(x);
            dataBaseManager.insertAlbum(albumId, name, total_tracks, date);
        }

        for (String albumId : album.getAlbums(Id).get("Albums' ID")) {
            Track track = new Track();
            track.getTrack(albumId);
            for (int x = 0; x < track.getTrack(albumId).get("ID Tracks").size(); x++) {
                String id_tracks = track.getTrack(albumId).get("ID Tracks").get(x);
                String name = track.getTrack(albumId).get("Tracks Names").get(x);
                String duration = track.getTrack(albumId).get("Tracks Duration").get(x);
                String explicit = track.getTrack(albumId).get("Explicit").get(x);
                dataBaseManager.insertTrack(id_tracks, name, duration, explicit);
                }
            }

        }

    }

}
