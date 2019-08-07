package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exception.TrackAlreadyExistsException;
import com.stackroute.Muzix.exception.TrackNotFoundException;
import com.stackroute.Muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    Track track;
    @Mock
    TrackRepository trackRepository;
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("kabira");
        track.setComment("good");
        list = new ArrayList<>();
        list.add(track);

    }
    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }
    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);


    }
    @Test
    public void getAllTrack() throws TrackNotFoundException {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        assertEquals(list,tracklist);
    }
    @Test
    public void deleteTrack()throws TrackNotFoundException {
        trackRepository.deleteById(track.getId());
        verify(trackRepository).deleteById(anyInt());
    }
    @Test
    public void updateTrackTest() throws TrackNotFoundException
    {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track updateTrack = null;
        try {
            updateTrack = trackService.saveTrack(track);
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(track,updateTrack);
    }


}
