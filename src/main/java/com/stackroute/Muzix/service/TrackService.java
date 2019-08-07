package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exception.TrackAlreadyExistsException;
import com.stackroute.Muzix.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public Track deleteTrack(int id) throws TrackNotFoundException;
    public Track updateTrack(Track track) throws TrackNotFoundException;
    public Track getTrackById(int id) throws TrackNotFoundException;
    public List<Track> getAllTracks() throws TrackNotFoundException;
    public List<Track> findByTrackName(String name)throws TrackNotFoundException;
}