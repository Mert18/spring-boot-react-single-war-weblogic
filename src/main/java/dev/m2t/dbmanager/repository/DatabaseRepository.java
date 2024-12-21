package dev.m2t.dbmanager.repository;

import dev.m2t.dbmanager.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseRepository extends JpaRepository<Database, Long> {
}
