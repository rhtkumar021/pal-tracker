package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository repository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository=timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newTimeEntry=repository.create(timeEntryToCreate);
        return new ResponseEntity(newTimeEntry,HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry=repository.find(timeEntryId);
        if(timeEntry!=null) {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
       List<TimeEntry> timeEntryList=repository.list();
        return new ResponseEntity(timeEntryList,HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry updateTimeEntry=repository.update(timeEntryId,expected);
        if(updateTimeEntry!=null)
        {
            return new ResponseEntity(updateTimeEntry,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(updateTimeEntry,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
       repository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    //    return (ResponseEntity) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
