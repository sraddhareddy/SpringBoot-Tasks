package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
  @PostMapping("track")
  public ResponseEntity<?> saveTrack(@RequestBody Track track){
      ResponseEntity responseEntity;
      try{
          trackService.saveTrack(track);
          responseEntity=new ResponseEntity<String>("Successfuly Created", HttpStatus.CREATED);
      }
      catch (Exception ex){
          responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
          ex.printStackTrace();
      }
      return responseEntity;
  }
  @GetMapping("track")
  public ResponseEntity<?> getAlltracks(){

        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
  }

  @DeleteMapping({"track/{id}"})
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>("track deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping({"track/{id}"})
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        return new ResponseEntity<String>("tracked",HttpStatus.OK);

    }
    @PutMapping("track")
    public ResponseEntity<?> UpdateTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(track);
            responseEntity= new ResponseEntity<String>("track updated", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping({"track/{name}"})
    public ResponseEntity<?> getTrackByName(@PathVariable(value="name") String name) {
        Track getTrack=(Track)trackService.findByTrackName(name);
        return new ResponseEntity<Track>(getTrack,HttpStatus.OK);


    }





}
