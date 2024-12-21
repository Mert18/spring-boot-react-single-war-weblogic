package dev.m2t.dbmanager.service;

import dev.m2t.dbmanager.dto.BaseResponse;
import dev.m2t.dbmanager.dto.request.CreateDatabaseRequestDto;
import dev.m2t.dbmanager.model.Database;
import dev.m2t.dbmanager.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DatabaseService {
    private final DatabaseRepository databaseRepository;

    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public BaseResponse createDatabase(CreateDatabaseRequestDto createDatabaseRequestDto) {
        Database database = new Database();
        database.setName(createDatabaseRequestDto.getName());

        databaseRepository.save(database);

        return new BaseResponse(true, "Database created successfully.", true);
    }

    public BaseResponse getDatabase(String id) {
        try {
            Database database = databaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Database with id " + id + " do not exists."));
            return new BaseResponse(true, "Database returned successfully.", false, database);
        } catch (javax.persistence.EntityNotFoundException e) {
            return new BaseResponse(false, e.getMessage(), false);
        }catch (Exception e) {
            return new BaseResponse(false, "Error getting database.", false);
        }
    }
}
