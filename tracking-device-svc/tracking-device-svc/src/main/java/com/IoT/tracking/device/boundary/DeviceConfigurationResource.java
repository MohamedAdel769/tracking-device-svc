package com.IoT.tracking.device.boundary;

import com.IoT.tracking.device.control.DeviceConfigurationControl;
import com.IoT.tracking.device.entity.dto.TextResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tracking-device/config")
public class DeviceConfigurationResource {
    private final DeviceConfigurationControl deviceConfigurationCtrl;

    public DeviceConfigurationResource(DeviceConfigurationControl deviceConfigurationCtrl) {
        this.deviceConfigurationCtrl = deviceConfigurationCtrl;
    }

    @PutMapping(value = "/activate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TextResponse> activateDevice(@RequestParam("deviceId") String deviceId) {
        return ResponseEntity.ok(deviceConfigurationCtrl.activateTrackingDevice(deviceId));
    }
}
