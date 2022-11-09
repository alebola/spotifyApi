package es.ulpgc.spotify.downloader;

import java.sql.*;

public class DataBaseManager {
    public DataBaseManager(){
        String dbPath = "C:\\Users\\alebo\\IdeaProjects\\spotiApi\\spotify.db";
        try(Connection connection = connect(dbPath)) {
            Statement statement = connection.createStatement();
            //dropTable(statement);
            createTable(statement);
            //insert(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
                "artistId TEXT PRIMARY KEY," +
                "name TEXT," +
                "followers TEXT,"+
                "popularity TEXT" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS albums (" +
                "albumId TEXT PRIMARY KEY," +
                "name TEXT," +
                "[total tracks] TEXT,"+
                "date TEXT" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS tracks (" +
                "trackId TEXT PRIMARY KEY," +
                "name TEXT," +
                "duration TEXT,"+
                "explicit TEXT" +
                ");");
    }

    public void insertArtist(String name, String followers, String popularity, String artistId) throws SQLException {
        String dbPath = "C:\\Users\\alebo\\IdeaProjects\\spotiApi\\spotify.db";
        String sql = "INSERT INTO artists(artistId, name, followers, popularity) VALUES(?,?,?,?)";
                try (Connection conn = this.connect(dbPath);
                        PreparedStatement pstm = conn.prepareStatement(sql)){
                    pstm.setString(1, artistId);
                    pstm.setString(2, name);
                    pstm.setString(3, followers);
                    pstm.setString(4, popularity);
                    pstm.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
    }
    public void insertAlbum(String albumId, String name, String total_tracks, String date) throws SQLException {
        String dbPath = "C:\\Users\\alebo\\IdeaProjects\\spotiApi\\spotify.db";
        String sql = "INSERT or IGNORE INTO albums(albumId, name, [total tracks], date) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, albumId);
            pstm.setString(2, name);
            pstm.setString(3, total_tracks);
            pstm.setString(4, date);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTrack(String trackId, String name, String duration, String explicit) throws SQLException {
        String dbPath = "C:\\Users\\alebo\\IdeaProjects\\spotiApi\\spotify.db";
        String sql = "INSERT INTO tracks(trackId, name, duration, explicit) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, trackId);
            pstm.setString(2, name);
            pstm.setString(3, duration);
            pstm.setString(4, explicit);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection connect(String dbPath) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}