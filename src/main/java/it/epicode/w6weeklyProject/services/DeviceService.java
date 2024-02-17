package it.epicode.w6weeklyProject.services;

import it.epicode.w6weeklyProject.exceptions.NotFoundException;
import it.epicode.w6weeklyProject.models.objects.Device;
import it.epicode.w6weeklyProject.models.objects.Employee;
import it.epicode.w6weeklyProject.models.requests.DeviceRequest;
import it.epicode.w6weeklyProject.repositories.DeviceRepository;
import it.epicode.w6weeklyProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository    deviceRepository;
    @Autowired
    private EmployeeRepository  employeeRepository;

    public Page<Device> getAll(Pageable pageable) {

        return deviceRepository.findAll(pageable);

    }

    public Device getById(int id) {

        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Device with id " + id + " not found."));

    }

    public Device save(DeviceRequest deviceRequest) {
            // TODO set validations and controls for device status

        Employee    employee    = deviceRequest.getEmployeeId() == null ? null : employeeRepository.getById(deviceRequest.getEmployeeId());
        Device      device      = new Device();

        device.setDeviceStatus  (deviceRequest.getDeviceStatus());
        device.setDeviceType    (deviceRequest.getDeviceType());
        device.setEmployee      (employee);

        return deviceRepository.save(device);

    }


            //TODO add method for validations etc..
}
