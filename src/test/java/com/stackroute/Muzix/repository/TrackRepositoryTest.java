package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;
    @Before
    public void setUp()
    {
        track = new Track();
        track.setId(1);
        track.setName("Nenjinile");
        track.setComment("Awesome");
    }
    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(1,fetchTrack.getId());
    }
    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(1,"shape of you","");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }
    @Test
    public void testGetAllTrack() {
        Track track1 = new Track(1, "kabira", "good");
        Track track2 = new Track(2, "closer", "good");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("kabira", list.get(0).getName());

    }
    @Test
    public void testGetTrackByIdFailure(){

        Track tracktest1 = new Track(1,"Nenjinile","awesome");
        Track tracktest2 = new Track(2,"Munbe va","good");
        trackRepository.save(tracktest1);
        trackRepository.save(tracktest2);
        List<Track> list = trackRepository.findAll();
        Assert.assertNotEquals("Sai",list.get(0).getName());

    }

    @Test
    public void testUpdateComment(){
        trackRepository.save(track);
        track.setComment("good");
        trackRepository.save(track);
        String comment = (trackRepository.findById(1).get()).getComment();
        Assert.assertEquals("good", comment);
    }
    @Test
    public void testUpdateCommentFailure(){
        trackRepository.save(track);
        track.setComment("good");
        String comment = (trackRepository.findById(1).get()).getComment();
        Assert.assertNotEquals("good", comment);
    }
    @Test
    public void testDeleteTrackFailure(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotEquals(null,fetchTrack.getId());
    }

}
