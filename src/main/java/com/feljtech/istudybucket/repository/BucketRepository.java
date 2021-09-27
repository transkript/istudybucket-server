package com.feljtech.istudybucket.repository;

import com.feljtech.istudybucket.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
