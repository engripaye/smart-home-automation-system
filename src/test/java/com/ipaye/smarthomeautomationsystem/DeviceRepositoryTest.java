package com.ipaye.smarthomeautomationsystem;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void testSaveDevice(){
        // Arrange
        Device device = new Device();
        device.setName("Test Device");
        device.setType("Type A");
        device.setLocation("Lagos");

        // Act
        Device savedDevice = deviceRepository.save(device);

        // Assert
        Assertions.assertNotNull(savedDevice.getId());
        Assertions.assertEquals("Test Device", savedDevice.getName());
        Assertions.assertEquals("Type A", savedDevice.getType());
        Assertions.assertEquals("Lagos", savedDevice.getLocation());
    }

    @Test
    void FindById(){
        // Arrange
        Device device = new Device();
        device.setName("Test Device");
        device.setType("Type C");
        Device savedDevice = deviceRepository.save(device);

        // Act
        Optional<Device> foundDevice = deviceRepository.findById(savedDevice.getId());

        // Assert
        Assertions.assertTrue(foundDevice.isPresent());
        Assertions.assertEquals("Test Device", foundDevice.get().getName());
        Assertions.assertEquals("Type C", foundDevice.get().getType());
    }

    @Test
    void findAllDevices(){
        // Arrange
        Device device1 = new Device();
        device1.setName("Device 1");
        device1.setType("Type A");

        Device device2 = new Device();
        device2.setName("Device 2");
        device2.setType("Type B");

        deviceRepository.save(device1);
        deviceRepository.save(device2);

        // Act
        List<Device> devices = deviceRepository.findAll();

        // assert
        Assertions.assertEquals(6, devices.size());
    }

    @Test
    void deletingDevices(){
        // arrange
        Device device = new Device();
        device.setName("Test Device");
        device.setType("Type B");
        Device savedDevice = deviceRepository.save(device);

        // act
        deviceRepository.deleteById(savedDevice.getId());
        Optional<Device> deletedDevice = deviceRepository.findById(savedDevice.getId());

        // assert
        Assertions.assertFalse(deletedDevice.isPresent());
    }

    @Test
    void updateDevices(){
        // arrange
        Device device = new Device();
        device.setName("Old Name");
        device.setType("Type B");
        device.setLocation("Nigeria");

        Device savedDevice = deviceRepository.save(device);

        // act
        savedDevice.setName("Updated Name");
        savedDevice.setLocation("United States");
        Device updatedDevice = deviceRepository.save(savedDevice);

        // assert
        Assertions.assertEquals("Updated Name", updatedDevice.getName());
        Assertions.assertEquals("United States", updatedDevice.getLocation());
    }
}
