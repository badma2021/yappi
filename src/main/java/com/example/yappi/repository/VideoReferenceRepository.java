package com.example.yappi.repository;

import com.example.yappi.models.VideoReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VideoReferenceRepository extends JpaRepository<VideoReference, Long > {
    @Modifying
    @Transactional
    @Query("UPDATE VideoReference e SET e.hash = ?1 where e.id = ?2 and e.hash IS NULL")
    void updateHash(String hash, Long id);

    @Query("SELECT e FROM VideoReference e WHERE e.hash IS NULL")
    List<VideoReference> findEntitiesWithColumnNull();
}
