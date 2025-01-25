package com.ipaye.smarthomeautomationsystem;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Repository.DeviceRepository;
import com.ipaye.smarthomeautomationsystem.Service.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void testFindByIdWhenDeviceExists(){

        Long deviceId = 1L;
        Device mockDevice = new Device();
        mockDevice.setId(deviceId);
        mockDevice.setType("Type A");
        mockDevice.setName("Test Device");
        mockDevice.setLocation("Lagos");


        Mockito.when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(mockDevice));

        Optional<Device> result = deviceService.findById(deviceId);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(deviceId, result.get().getId());
        Assertions.assertEquals("Test Device", result.get().getName());
        Mockito.verify(deviceRepository, Mockito.times(1)).findById(deviceId);
    }

    @Test
    void testFindByIdWhenDeviceDoesNotExist(){

        // Arrange

        Long deviceId =  2L;

        Mockito.when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        // Act

        Optional<Device> result = deviceService.findById(deviceId);

        // Assert
        Assertions.assertFalse(result.isPresent());
        Mockito.verify(deviceRepository, Mockito.times(1)).findById(deviceId);
    }

    @Test
    void testFindByIdWithInvalidId(){
        // Arrange
        Long invalidId = -1L;

        // Act
        Optional<Device> result = deviceService.findById(invalidId);

        // Assert
        Assertions.assertFalse(result.isPresent());
        Mockito.verify(deviceRepository, Mockito.times(1)).findById(invalidId);
    }

    @Test
    void testFindByIdAfterUpdate(){

        // Arrange
        Long deviceId = 1L;
        Device updatedDevice = new Device();
        updatedDevice.setId(deviceId);
        updatedDevice.setName("Updated Device");
        updatedDevice.setType("Type B");
        updatedDevice.setLocation("Abuja");

        Mockito.when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);

        Mockito.when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(updatedDevice));

        // Act
        deviceService.updateDevice(updatedDevice);
        Optional<Device> result = deviceService.findById(deviceId);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Updated Device", result.get().getName());
        Mockito.verify(deviceRepository, Mockito.times(1)).save(updatedDevice);
        Mockito.verify(deviceRepository, Mockito.times(1)).findById(deviceId);
    }

    @Test
    void findByIdAfterSavingDevice(){
        // Arrange
        Long deviceId = 2L;
        Device newDevice = new Device();
        newDevice.setId(deviceId);
        newDevice.setName("New Device");
        newDevice.setType("Type C");
        newDevice.setLocation("Zone A");

        Mockito.when(deviceRepository.save(newDevice)).thenReturn(newDevice);

        Mockito.when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(newDevice));

        // Act
        deviceService.saveDevice(newDevice);
        Optional<Device> result = deviceService.findById(deviceId);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("New Device", result.get().getName());
        Mockito.verify(deviceRepository, Mockito.times(1)).save(newDevice);
        Mockito.verify(deviceRepository, Mockito.times(1)).findById(deviceId);
    }
}
