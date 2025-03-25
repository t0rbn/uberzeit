package xyz.torben.uberzeit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class TimeEntry {

    @Id
    @GeneratedValue
    public UUID Id;

    public String userEmail;

    public String name;

    public double hoursPlanned;

    public double hoursTracked;

    public Date savedAt;

    public TimeEntry() {

    }

    public TimeEntry(String name, double hoursPlanned, double hoursTracked) {
        this.name = name;
        this.hoursPlanned = hoursPlanned;
        this.hoursTracked = hoursTracked;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursPlanned() {
        return hoursPlanned;
    }

    public void setHoursPlanned(int hoursPlanned) {
        this.hoursPlanned = hoursPlanned;
    }

    public double getHoursTracked() {
        return hoursTracked;
    }

    public void setHoursTracked(int hoursTracked) {
        this.hoursTracked = hoursTracked;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }
}
