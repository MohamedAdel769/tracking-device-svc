package com.IoT.tracking.device.control;

import com.IoT.tracking.device.boundary.TrackingDeviceRepository;
import com.IoT.tracking.device.config.exceptions.BusinessException;
import com.IoT.tracking.device.entity.TrackingDevice;
import com.IoT.tracking.device.entity.dto.TextResponse;
import com.IoT.tracking.device.entity.dto.TrackingDeviceDto;
import com.IoT.tracking.device.entity.enums.DeviceStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TrackingDeviceControl {
    private final TrackingDeviceRepository trackingDeviceRepo;
    private final Integer DEFAULT_TEMP;

    public TrackingDeviceControl(TrackingDeviceRepository trackingDeviceRepo,
                                 @Value("${temperature.default}") Integer default_temp) {
        this.trackingDeviceRepo = trackingDeviceRepo;
        DEFAULT_TEMP = default_temp;
    }

    public TextResponse addNewTrackingDevice(TrackingDeviceDto trackingDeviceDto) {
        TrackingDevice newTrackingDevice =
                TrackingDevice.builder()
                        .description(trackingDeviceDto.getDescription())
                        .keyFeatures(trackingDeviceDto.getKeyFeatures())
                        .pinCode(trackingDeviceDto.getPinCode())
                        .price(trackingDeviceDto.getPrice())
                        .name(trackingDeviceDto.getName())
                        .status(DeviceStatus.READY)
                        .temperature(DEFAULT_TEMP)
                        .configured(false)
                        .build();

        trackingDeviceRepo.save(newTrackingDevice);

        return new TextResponse(String.format("Device - %s added successfully", newTrackingDevice.getDeviceId()));
    }

    public TextResponse updateTrackingDevice(String deviceId, TrackingDeviceDto trackingDeviceDto) {
        TrackingDevice updatedTrackingDevice = trackingDeviceRepo.findById(deviceId)
                .orElseThrow(() -> new BusinessException("Device does not exists."));

        updatedTrackingDevice.setDescription(trackingDeviceDto.getDescription());
        updatedTrackingDevice.setKeyFeatures(trackingDeviceDto.getKeyFeatures());
        updatedTrackingDevice.setPrice(trackingDeviceDto.getPrice());
        updatedTrackingDevice.setName(trackingDeviceDto.getName());

        trackingDeviceRepo.save(updatedTrackingDevice);

        return new TextResponse(String.format("Device - %s updated successfully", updatedTrackingDevice.getDeviceId()));
    }

    public TextResponse deleteTrackingDevice(String deviceId) {
        TrackingDevice trackingDevice = trackingDeviceRepo.findById(deviceId)
                .orElseThrow(() -> new BusinessException("Device does not exists."));

        trackingDeviceRepo.delete(trackingDevice);

        return new TextResponse(String.format("Device - %s deleted successfully", trackingDevice.getDeviceId()));
    }

    public Page<TrackingDevice> getAllForSale(Pageable pageable) {
        return trackingDeviceRepo.findAllByConfiguredIsTrueOrderByPinCode(pageable);
    }

    public Page<TrackingDevice> getAll(Pageable pageable) {
        return trackingDeviceRepo.findAll(pageable);
    }

    public TextResponse deleteAll() {
        trackingDeviceRepo.deleteAll();
        return new TextResponse("Deleted");
    }
}
