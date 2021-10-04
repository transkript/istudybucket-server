package com.feljtech.istudybucket.data.repository;

import com.feljtech.istudybucket.data.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
