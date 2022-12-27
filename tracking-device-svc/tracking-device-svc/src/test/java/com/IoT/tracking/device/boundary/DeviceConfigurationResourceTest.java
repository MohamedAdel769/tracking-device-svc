package com.IoT.tracking.device.boundary;

import com.IoT.tracking.device.control.DeviceConfigurationControl;
import com.IoT.tracking.device.entity.dto.TextResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DeviceConfigurationResourceTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    DeviceConfigurationControl deviceConfigurationCtrl;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @SneakyThrows
    void activateDeviceOkTest() {
        when(deviceConfigurationCtrl.activateTrackingDevice(any())).thenReturn(new TextResponse());

        mvc.perform(MockMvcRequestBuilders
                        .put("/tracking-device/config/activate")
                        .param("deviceId", "123"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void activateDeviceBadRequestTest() {
        when(deviceConfigurationCtrl.activateTrackingDevice(any())).thenReturn(new TextResponse());

        mvc.perform(MockMvcRequestBuilders
                        .put("/tracking-device/config/activate"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}