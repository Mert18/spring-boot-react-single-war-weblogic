package dev.m2t.dbmanager.repository;

import dev.m2t.dbmanager.model.Database;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DatabasePagingAndSortingRepository extends PagingAndSortingRepository<Database, Long> {
}
