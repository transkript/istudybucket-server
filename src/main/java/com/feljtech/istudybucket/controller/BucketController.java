package com.feljtech.istudybucket.controller;

import com.feljtech.istudybucket.dto.BucketDto;
import com.feljtech.istudybucket.entity.Bucket;
import com.feljtech.istudybucket.service.BucketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/bucket")
@Slf4j
public class BucketController {
    private final BucketService bucketService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BucketDto> createBucket(@RequestBody BucketDto newBucket) {
        return new ResponseEntity<>(
                bucketService.createBucket(newBucket),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "")
    public ResponseEntity<List<BucketDto>> getAll() {
        return new ResponseEntity<>(
                bucketService.getAllBuckets(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{bucketId}")
    public ResponseEntity<BucketDto> getById(@PathVariable(value = "bucketId") Long bucketId) {
        return new ResponseEntity<>(
                bucketService.getBucketById(bucketId),
                HttpStatus.OK
        );
    }
}
