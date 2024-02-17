package it.epicode.w6weeklyProject.services;

import it.epicode.w6weeklyProject.exceptions.NotFoundException;
import it.epicode.w6weeklyProject.models.objects.Employee;
import it.epicode.w6weeklyProject.models.requests.EmployeeRequest;
import it.epicode.w6weeklyProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository  employeeRepository;
    @Autowired
    private JavaMailSenderImpl  javaMailSender;

    public Page<Employee> getAll(Pageable pageable) {

        return employeeRepository.findAll(pageable);

    }

    public Employee getById(int id) {

        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with id " + id + " not found."));

    }

    public Employee save(EmployeeRequest employeeRequest) {

        Employee  employee   = new Employee();

        employee.setName     (employeeRequest.getName());
        employee.setSurname  (employeeRequest.getSurname());
        employee.setUsername (employeeRequest.getUsername());
        employee.setEmail    (employeeRequest.getEmail());
        sendEmail            (employeeRequest.getEmail());

        return employeeRepository.save(employee);

    }

    public Employee update(int id, EmployeeRequest employeeRequest) {

        Employee employee = getById(id);

        employee.setName     (employeeRequest.getName());
        employee.setSurname  (employeeRequest.getSurname());
        employee.setUsername (employeeRequest.getUsername());
        employee.setEmail    (employeeRequest.getEmail());

        return employeeRepository.save(employee);

    }

    public void delete(int id) {

        Employee employee = getById(id);
        employeeRepository.delete(employee);

    }

    public Employee updatePfp(int id, String url) {

        Employee employee = getById(id);
        employee.setPfp(url);

        return employeeRepository.save(employee);
    }

    public void sendEmail (String email) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo       (email);
        message.setSubject  ("New employee registration");
        message.setText     ("New employee registration successfully completed");
        javaMailSender.send (message);

    }

}
