package eu.vomanor.gloobus.service;

import eu.vomanor.gloobus.domain.Device;
import eu.vomanor.gloobus.repository.DeviceRepository;
import jakarta.inject.Singleton;

@Singleton
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Iterable<Device> getAll() {
       return deviceRepository.findAll();
    }

    public void create(String name) {
        var group = new Device();
        group.setName(name);
        deviceRepository.save(group);
    }
}
