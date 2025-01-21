package com.ipaye.smarthomeautomationsystem.Controller;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getDevices(){
        return deviceService.getAllDevices();
    }

    @PutMapping("/{id}/status")
    public Device updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload){
        String status = payload.get("status");
        return deviceService.updateDeviceStatus(id, status);
    }

}
