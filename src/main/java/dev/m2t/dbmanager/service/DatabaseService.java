package dev.m2t.dbmanager.service;

import dev.m2t.dbmanager.dto.BaseResponse;
import dev.m2t.dbmanager.dto.request.CreateDatabaseRequestDto;
import dev.m2t.dbmanager.dto.request.ListDatabasesRequestDto;
import dev.m2t.dbmanager.model.Database;
import dev.m2t.dbmanager.model.DatabaseLevelEnum;
import dev.m2t.dbmanager.repository.DatabasePagingAndSortingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private final DatabasePagingAndSortingRepository databasePagingAndSortingRepository;
    private EntityManager entityManager;

    public DatabaseService(DatabasePagingAndSortingRepository databasePagingAndSortingRepository, EntityManager entityManager) {
        this.databasePagingAndSortingRepository = databasePagingAndSortingRepository;
        this.entityManager = entityManager;
    }

    public BaseResponse<Database> createDatabase(CreateDatabaseRequestDto createDatabaseRequestDto) {
        logger.info("Creating database {}", createDatabaseRequestDto.getName());
        Database database = new Database();
        database.setName(createDatabaseRequestDto.getName());
        database.setLevel(DatabaseLevelEnum.fromCode(createDatabaseRequestDto.getLevel()));

        Database savedDatabase = databasePagingAndSortingRepository.save(database);
        logger.info("Database {} with id {} created successfully.", database.getName(), database.getId());

        return new BaseResponse<>(true, "Database created successfully.", true, savedDatabase);
    }

    public BaseResponse<Database> getDatabase(Long id) {
        try {
            logger.info("Getting the database with id {}", id);
            Database database = databasePagingAndSortingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Database with id " + id + " do not exists."));
            return new BaseResponse<>(true, "Database returned successfully.", false, database);
        } catch (EntityNotFoundException e) {
            return new BaseResponse<>(false, e.getMessage(), false);
        }catch (Exception e) {
            logger.error("Error getting database. Error message: {}", e.getMessage());
            return new BaseResponse<>(false, "Error getting database.", false);
        }
    }

    public BaseResponse<Page<Database>> listDatabases(Pageable pageable, ListDatabasesRequestDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Database> query = cb.createQuery(Database.class);
        Root<Database> root = query.from(Database.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
        }
        if (filter.getLevel() != null) {
            predicates.add(cb.equal(root.get("level"), DatabaseLevelEnum.fromCode(filter.getLevel())));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));
        query.orderBy(cb.asc(root.get("name")));

        TypedQuery<Database> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Database> resultList = typedQuery.getResultList();
        long total = getTotalCount(filter); // Custom method to get total count
        return new BaseResponse<>(true, "Listing databases", false, new PageImpl<>(resultList, pageable, total));
    }

    private long getTotalCount(ListDatabasesRequestDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Database> root = countQuery.from(Database.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(cb.like(root.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getLevel() != null) {
            predicates.add(cb.equal(root.get("level"), DatabaseLevelEnum.fromCode(filter.getLevel())));
        }

        countQuery.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
