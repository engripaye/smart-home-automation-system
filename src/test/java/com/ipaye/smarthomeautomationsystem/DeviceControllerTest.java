package com.ipaye.smarthomeautomationsystem;

import com.ipaye.smarthomeautomationsystem.Controller.DeviceController;
import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Service.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    // test case 1

    @Test
    void getDeviceById(){

        Device device = new Device();
        device.setName("New Device");
        device.setType("Type A");
        device.setId(1L);
        when(deviceService.findById(1L)).thenReturn(Optional.of(device));

        Device result =deviceController.getDeviceById(1L).getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Type A", result.getType());

        verify(deviceService, times(1)).findById(1L);

    }

    // test case 2

    @Test
    void createDevice(){

        Device device = new Device();
        device.setName("New Device");
        device.setType("Type A");
        device.setId(1L);
        when(deviceService.saveDevice(device)).thenReturn(device);

        Device result =deviceController.createDevice(device);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("New Device", result.getName());
        verify(deviceService, times(1)).saveDevice(device);
    }


    // CASE 3
    @Test
    void deleteDevice(){

        deviceController.deleteDevice(1L);

        verify(deviceService, times(1)).deleteDeviceById(1L);
    }

      // CASE 4

    @Test
    void deleteDeviceInvalidId(){

        deviceController.deleteDevice(999L);

        verify(deviceService, times(1)).deleteDeviceById(999L);
    }






}
