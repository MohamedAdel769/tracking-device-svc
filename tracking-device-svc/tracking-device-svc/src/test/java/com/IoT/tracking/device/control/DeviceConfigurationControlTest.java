package com.IoT.tracking.device.control;

import com.IoT.tracking.device.boundary.TrackingDeviceRepository;
import com.IoT.tracking.device.entity.TrackingDevice;
import com.IoT.tracking.device.entity.dto.TextResponse;
import com.IoT.tracking.device.entity.enums.DeviceStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class DeviceConfigurationControlTest {

    @MockBean
    TrackingDeviceRepository trackingDeviceRepo;

    @Autowired
    DeviceConfigurationControl deviceConfigurationCtrl;

    @Test
    void activateTrackingDeviceWhenNotConfiguredTest() {
        String deviceId = "769";
        TrackingDevice dummy = new TrackingDevice();
        dummy.setConfigured(false);

        when(trackingDeviceRepo.findById(any())).thenReturn(Optional.of(dummy));

        deviceConfigurationCtrl.activateTrackingDevice(deviceId);

        assertThat(dummy.getConfigured()).isTrue();
        assertThat(dummy.getStatus()).isEqualTo(DeviceStatus.ACTIVE);
        Mockito.verify(trackingDeviceRepo, Mockito.atMostOnce()).save(dummy);
    }
}