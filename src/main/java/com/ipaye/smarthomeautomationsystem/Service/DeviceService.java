package com.ipaye.smarthomeautomationsystem.Service;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Device updateDeviceStatus(Long id, String status){
        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if(optionalDevice.isPresent()){
            Device device = optionalDevice.get();
            device.setStatus(status);
            return deviceRepository.save(device);
        }else {
            throw new IllegalArgumentException("Device not found with id: " + id);
        }

    }

    public List<Device> getDevicesByType(String type) {

        return deviceRepository.findByType(type);
    }
}
