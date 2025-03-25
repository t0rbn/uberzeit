package xyz.torben.uberzeit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.torben.uberzeit.entities.TimeEntry;

import java.util.List;
import java.util.UUID;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, UUID> {
    List<TimeEntry> findAllByUserEmailOrderBySavedAtDesc(String userEmail);
}
