package com.IoT.tracking.device.boundary;

import com.IoT.tracking.device.entity.TrackingDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrackingDeviceRepository extends MongoRepository<TrackingDevice, String>,
        PagingAndSortingRepository<TrackingDevice, String> {
    Page<TrackingDevice> findAllByConfiguredIsTrueOrderByPinCode(Pageable pageable);
}
