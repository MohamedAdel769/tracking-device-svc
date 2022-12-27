package com.IoT.tracking.device.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class TrackingDeviceDto {
    @NotNull
    @Range(min = 1000000, max = 9999999)
    private Integer pinCode;
    private String name;
    private String description;
    private Double price;
    private List<String> keyFeatures;
}
