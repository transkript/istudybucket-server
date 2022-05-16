package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
