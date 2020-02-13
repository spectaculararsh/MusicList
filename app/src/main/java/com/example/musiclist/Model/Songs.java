package com.example.musiclist.Model;

public class Songs {
    private String image;
    private String albumName;
    private String artistName;
    private String songName;

    public Songs(String image, String songName, String artistName, String albumName) {
        this.image = image;
        this.albumName = albumName;
        this.artistName = artistName;
        this.songName = songName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
