package com.example.yappi.repository;

import com.example.yappi.models.VideoReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoReferenceRepository extends JpaRepository<VideoReference, Long > {
}
