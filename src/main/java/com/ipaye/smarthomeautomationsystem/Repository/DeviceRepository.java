package com.ipaye.smarthomeautomationsystem.Repository;

import com.ipaye.smarthomeautomationsystem.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByType(String type);
}
