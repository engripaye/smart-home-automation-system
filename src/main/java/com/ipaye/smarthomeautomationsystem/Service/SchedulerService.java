package com.ipaye.smarthomeautomationsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Autowired
    private DeviceService deviceService;

    @Scheduled(cron="0 0 23 * * ?")
    public void turnOffLightAtNight(){
        System.out.println("Running scheduled task: Turning off all lights at night 11:00 PM");


        deviceService.getDevicesByType("Light").forEach(device -> {
            deviceService.updateDeviceStatus(device.getId(), "OFF");
        });
    }

}
