package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exception.TrackAlreadyExistsException;
import com.stackroute.Muzix.exception.TrackNotFoundException;
import com.stackroute.Muzix.repository.TrackRepository;
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
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("user already exists");
        }
        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null) {
            throw new TrackAlreadyExistsException("user already exists");
        }
        return savedTrack;
    }


    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException{
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
    public Track deleteTrack(int id) throws TrackNotFoundException{
        if(trackRepository.existsById(id))
        {
            trackRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException("not found");

        }
        return null;

    }

    @Override
    public Track getTrackById(int trackId) throws TrackNotFoundException {
        if(!trackRepository.findById(trackId).isPresent()) {
            throw new TrackNotFoundException("user not found");
        }
        return trackRepository.findById(trackId).get();
    }
  @Override
    public List<Track> findByTrackName(String name) throws TrackNotFoundException{
      return trackRepository.findByTrackName(name);
  }

}
