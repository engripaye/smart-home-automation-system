package com.ipaye.smarthomeautomationsystem.Controller;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import com.ipaye.smarthomeautomationsystem.Entity.DeviceRequest;
import com.ipaye.smarthomeautomationsystem.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getDevices(){

        return deviceService.getAllDevices();
    }

    @GetMapping("{/id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id){

        Optional<Device> device = deviceService.findById(id);

        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        
    }

    @PostMapping
    public ResponseEntity<String> addDevice(@Validated @RequestBody DeviceRequest deviceRequest){
        deviceService.addDevice(deviceRequest);
        return ResponseEntity.ok("Device added Successfully");
    }

    @PutMapping("/{id}")
    public Device updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload){
        String status = payload.get("status");
        return deviceService.updateDeviceStatus(id, status);
    }


    @PostMapping
    public Device createDevice(@RequestBody Device device) {

        return deviceService.saveDevice(device);
    }


    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable long id) {

        deviceService.deleteDeviceById(id);
    }
}
