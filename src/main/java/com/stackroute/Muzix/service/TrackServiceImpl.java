package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {


    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) {

        if(trackRepository.existsById(track.getId())){
            return null;
        }
        else {
            Track savetrack=trackRepository.save(track);
            return savetrack;
        }

    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track) {
        boolean status=false;
        int id=track.getId();
        Optional<Track> trackOptional=trackRepository.findById(track.getId());
        if(trackOptional.isPresent()) {
            Track newEntity = trackOptional.get();
            newEntity.setName(track.getName());
            newEntity.setId(track.getId());
            newEntity.setComment(track.getComment());
            return newEntity;
        }
        else {
            track = trackRepository.save(track);
            return track;
        }
    }

    @Override
    public Track deleteTrack(int id) {
        if(trackRepository.existsById(id))
        {
            trackRepository.deleteById(id);
        }
        else
        {
            return null;
        }
        return null;
    }

    @Override
    public Track getTrackById(int id) {
        Optional<Track> trackOptional=trackRepository.findById(id);
        if(trackOptional.isPresent()){
            return trackOptional.get();
        }
        else {
            return null;
        }


    }
}
