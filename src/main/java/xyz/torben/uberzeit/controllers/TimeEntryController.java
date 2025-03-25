package xyz.torben.uberzeit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.torben.uberzeit.entities.TimeEntry;
import xyz.torben.uberzeit.entities.UserInfo;
import xyz.torben.uberzeit.services.TimeEntryService;
import xyz.torben.uberzeit.services.UserService;

import java.util.List;
import java.util.UUID;

@Controller
public class TimeEntryController {

    private final TimeEntryService timeEntryService;
    private final UserService userService;

    @Autowired
    public TimeEntryController(TimeEntryService timeEntryService, UserService userService) {
        this.timeEntryService = timeEntryService;
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAppPage(Model model) {
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute("userInfo", userInfo);

        List<TimeEntry> timeEntries = timeEntryService.getForUser(userInfo);
        model.addAttribute("timeEntries", timeEntries);

        double plannedTotal = timeEntries.stream().mapToDouble(TimeEntry::getHoursPlanned).sum();
        double trackedTotal = timeEntries.stream().mapToDouble(TimeEntry::getHoursTracked).sum();
        double delta = trackedTotal - plannedTotal;
        model.addAttribute("hoursPlannedTotal", plannedTotal);
        model.addAttribute("hoursTrackedTotal", trackedTotal);
        model.addAttribute("hoursDelta", delta);

        return "index";
    }

    @RequestMapping(path = "/time-entries/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteEntry(@RequestParam("id") UUID id) {
        timeEntryService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/time-entries", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addEntry(@RequestParam("name") String name, @RequestParam("hoursPlanned") double hoursPlanned, @RequestParam("hoursTracked") double hoursTracked) {
        timeEntryService.save(new TimeEntry(name, hoursPlanned, hoursTracked));
        return "redirect:/";
    }
}
