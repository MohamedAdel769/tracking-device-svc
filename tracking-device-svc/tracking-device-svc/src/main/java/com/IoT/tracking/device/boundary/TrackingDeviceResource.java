package com.IoT.tracking.device.boundary;

import com.IoT.tracking.device.control.TrackingDeviceControl;
import com.IoT.tracking.device.entity.TrackingDevice;
import com.IoT.tracking.device.entity.dto.TextResponse;
import com.IoT.tracking.device.entity.dto.TrackingDeviceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tracking-device")
public class TrackingDeviceResource {
    private final TrackingDeviceControl trackingDeviceCtrl;

    public TrackingDeviceResource(TrackingDeviceControl trackingDeviceCtrl) {
        this.trackingDeviceCtrl = trackingDeviceCtrl;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TextResponse> addTrackingDevice(@Valid @RequestBody TrackingDeviceDto trackingDeviceDto) {
        return ResponseEntity.ok(trackingDeviceCtrl.addNewTrackingDevice(trackingDeviceDto));
    }

    @PutMapping(value = "/{deviceId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TextResponse> updateTrackingDevice(@PathVariable("deviceId") String deviceId,
                                                             @Valid @RequestBody TrackingDeviceDto trackingDeviceDto) {
        return ResponseEntity.ok(trackingDeviceCtrl.updateTrackingDevice(deviceId, trackingDeviceDto));
    }

    @DeleteMapping(value = "/{deviceId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TextResponse> deleteTrackingDevice(@PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(trackingDeviceCtrl.deleteTrackingDevice(deviceId));
    }

    @GetMapping(value = "/for-sale", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TrackingDevice>> getAllDevicesForSale(Pageable pageable) {
        return ResponseEntity.ok(trackingDeviceCtrl.getAllForSale(pageable));
    }

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TrackingDevice>> getAllDevices(Pageable pageable) {
        return ResponseEntity.ok(trackingDeviceCtrl.getAll(pageable));
    }
}
