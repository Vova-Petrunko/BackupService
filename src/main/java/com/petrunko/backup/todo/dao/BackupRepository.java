package com.petrunko.backup.todo.dao;

import com.petrunko.backup.todo.model.Backup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends MongoRepository<Backup, String> {
}
