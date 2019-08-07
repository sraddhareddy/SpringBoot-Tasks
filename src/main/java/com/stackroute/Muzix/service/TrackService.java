package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track);
    public List<Track> getAllTracks();
    public Track updateTrack(Track track);
    public Track deleteTrack(int id);
    public Track getTrackById(int id);
    public List<Track> findByTrackName(String name);
}
