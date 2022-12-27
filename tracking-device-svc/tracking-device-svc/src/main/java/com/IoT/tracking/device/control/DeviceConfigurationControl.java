package com.IoT.tracking.device.control;

import com.IoT.tracking.device.boundary.TrackingDeviceRepository;
import com.IoT.tracking.device.config.exceptions.BusinessException;
import com.IoT.tracking.device.entity.TrackingDevice;
import com.IoT.tracking.device.entity.dto.TextResponse;
import com.IoT.tracking.device.entity.enums.DeviceStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class DeviceConfigurationControl {
    private final TrackingDeviceRepository trackingDeviceRepo;
    private final Integer MAX_TEMP;
    private final Integer MIN_TEMP;

    public DeviceConfigurationControl(TrackingDeviceRepository trackingDeviceRepo,
                                      @Value("${temperature.max}") Integer maxTemp,
                                      @Value("${temperature.min}") Integer minTemp) {
        this.trackingDeviceRepo = trackingDeviceRepo;
        MAX_TEMP = maxTemp;
        MIN_TEMP = minTemp;
    }

    public TextResponse activateTrackingDevice(String deviceId) {
        TrackingDevice trackingDevice = trackingDeviceRepo.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device does not exists with deviceId - " + deviceId));

        if (trackingDevice.getConfigured() != null && trackingDevice.getConfigured())
            throw new BusinessException("Device already activated.");

        trackingDevice.setStatus(DeviceStatus.ACTIVE);
        trackingDevice.setTemperature(ThreadLocalRandom.current().nextInt(MIN_TEMP, MAX_TEMP));
        trackingDevice.setConfigured(true);

        trackingDeviceRepo.save(trackingDevice);

        return new TextResponse("Device activated successfully.");
    }
}
