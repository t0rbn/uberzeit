package xyz.torben.uberzeit.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.torben.uberzeit.entities.TimeEntry;
import xyz.torben.uberzeit.entities.UserInfo;
import xyz.torben.uberzeit.repositories.TimeEntryRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;
    private final UserService userService;

    @Autowired
    public TimeEntryService(TimeEntryRepository timeEntryRepository, UserService userService) {
        this.timeEntryRepository = timeEntryRepository;
        this.userService = userService;
    }

    public void save(TimeEntry timeEntry) {
        timeEntry.setUserEmail(userService.getUserInfo().email());
        timeEntry.setSavedAt(new Date());
        timeEntryRepository.save(timeEntry);
    }

    public void deleteById(UUID id) {
        timeEntryRepository.deleteById(id);
    }

    public List<TimeEntry> getForUser(UserInfo user) {
        return timeEntryRepository.findAllByUserEmailOrderBySavedAtDesc(user.email());
    }
}
