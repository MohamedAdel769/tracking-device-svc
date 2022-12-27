package com.IoT.tracking.device.entity;

import com.IoT.tracking.device.entity.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("TrackingDevice")
public class TrackingDevice {
    @Id
    private String deviceId;
    @Indexed(unique = true)
    private Integer pinCode;
    private String name;
    private DeviceStatus status;
    private Integer temperature;
    private Boolean configured;
    private String description;
    private Double price;
    private List<String> keyFeatures;
}
