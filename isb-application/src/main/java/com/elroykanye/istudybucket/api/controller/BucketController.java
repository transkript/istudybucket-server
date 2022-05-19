package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/bucket")
public class BucketController {
    private final BucketService bucketService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> addBucket(@RequestBody BucketDto bucketDto) {
        log.info("Adding bucket: {}", bucketDto);
        return new ResponseEntity<>(bucketService.createBucket(bucketDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<BucketDto>> getBuckets() {
        log.info("Getting all buckets");
        return ResponseEntity.ok(bucketService.getAllBuckets());
    }

    @GetMapping(value = "/{bucketId}")
    public ResponseEntity<BucketDto> getBucket(@NotNull @PathVariable Long bucketId) {
        log.info("Getting bucket by id: {}", bucketId);
        return ResponseEntity.ok(bucketService.getBucketById(bucketId));
    }

    @PutMapping(value = "/{bucketId}")
    public ResponseEntity<EntityResponse> updateBucket(@NotNull @PathVariable Long bucketId, @RequestBody BucketDto bucketDto) {
        log.info("Updating bucket with id: {}", bucketId);
        return ResponseEntity.ok(bucketService.updateBucket(bucketId, bucketDto));
    }

    @DeleteMapping(value = "/{bucketId}")
    public ResponseEntity<Void> deleteById(@NotNull @PathVariable Long bucketId) {;
        log.info("Deleting bucket by id: {}", bucketId);
        return ResponseEntity.ok(bucketService.deleteBucketById(bucketId));
    }
}
