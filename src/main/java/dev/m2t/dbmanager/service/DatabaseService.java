package dev.m2t.dbmanager.service;

import dev.m2t.dbmanager.dto.BaseResponse;
import dev.m2t.dbmanager.dto.request.CreateDatabaseRequestDto;
import dev.m2t.dbmanager.model.Database;
import dev.m2t.dbmanager.repository.DatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private final DatabaseRepository databaseRepository;

    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public BaseResponse<Database> createDatabase(CreateDatabaseRequestDto createDatabaseRequestDto) {
        logger.info("Creating database {}", createDatabaseRequestDto.getName());
        Database database = new Database();
        database.setName(createDatabaseRequestDto.getName());

        Database savedDatabase = databaseRepository.save(database);
        logger.info("Database {} with id {} created successfully.", database.getName(), database.getId());

        return new BaseResponse<>(true, "Database created successfully.", true, savedDatabase);
    }

    public BaseResponse<Database> getDatabase(Long id) {
        try {
            logger.info("Getting the database with id {}", id);
            Database database = databaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Database with id " + id + " do not exists."));
            return new BaseResponse<>(true, "Database returned successfully.", false, database);
        } catch (EntityNotFoundException e) {
            return new BaseResponse<>(false, e.getMessage(), false);
        }catch (Exception e) {
            logger.error("Error getting database. Error message: {}", e.getMessage());
            return new BaseResponse<>(false, "Error getting database.", false);
        }
    }
}
