package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long,TimeEntry> timeEntryMap=new HashMap<>();
    private long timeId=0L;

    @Override
    public TimeEntry create(TimeEntry newTimeEntry) {
        long timeEntryId=++timeId;
        TimeEntry createdTimeEntry=new TimeEntry(timeEntryId,newTimeEntry.getProjectId(),newTimeEntry.getUserId(),newTimeEntry.getDate(),newTimeEntry.getHours());
        timeEntryMap.put(timeEntryId,createdTimeEntry);
        return createdTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
       return timeEntryMap.get(timeEntryId);

    }

    @Override
    public TimeEntry update(long timeEntryId,TimeEntry timeEntryToUpdate) {
        TimeEntry search=find(timeEntryId);
        if(search!=null) {
            TimeEntry newTimeEntry = new TimeEntry(timeEntryId, timeEntryToUpdate.getProjectId(), timeEntryToUpdate.getUserId(), timeEntryToUpdate.getDate(), timeEntryToUpdate.getHours());
            timeEntryMap.replace(timeEntryId, newTimeEntry);
            return newTimeEntry;
        }
        else return null;

    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
       List<TimeEntry> timeEntryList=new ArrayList<>(timeEntryMap.values());
       return timeEntryList;
    }
}
