package com.khb.hu.springcourse.hr.service;

import com.google.common.io.ByteStreams;
import com.khb.hu.springcourse.hr.api.model.EmployeeDto;
import com.khb.hu.springcourse.hr.dto.HistoryData;
import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.order.AuditOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Employee modify(Employee employee) {
        if(employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @Override
    public String saveImage(int id, InputStream is) throws IOException {
        employeeRepository.findById(id).get();

        Path folder = Path.of("/temp/kh-hr/employee/" + id);
        Files.createDirectories(folder);

        UUID uuid = UUID.randomUUID();
        try(FileOutputStream os = new FileOutputStream(folder + "/" + uuid)){
            ByteStreams.copy(is, os);
        }
        return uuid.toString();
    }

    @Override
    @Async
    public CompletableFuture<String> longRunning() {
        try {
            Thread.sleep(5000);
            System.out.println("longRunning is ready");
            return CompletableFuture.completedFuture("abcdef");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<HistoryData<Employee>> getHistoryById(int id) {
        return AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Employee.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList().stream().map(o ->{
                    Object[] objArray = (Object[]) o;
                    Employee emp = (Employee) objArray[0];
                    DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) objArray[1];
                    RevisionType revType = (RevisionType) objArray[2];
                    return new HistoryData<>(emp, revType, revisionEntity.getId(), revisionEntity.getRevisionDate());
                }).toList();
    }

    @Override
    @Transactional
    public Optional<Employee> getHistoryByIdAt(int id, Date at) {

        List resultList = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Employee.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .add(AuditEntity.revisionProperty("timestamp").le(at.getTime()))
                .addOrder(AuditEntity.revisionProperty("timestamp").desc())
                .setMaxResults(1)
                .getResultList();

        if(resultList.isEmpty())
            return Optional.empty();

        Object[] objArray = (Object[]) resultList.get(0);
        if((RevisionType)objArray[2] == RevisionType.DEL)
            return Optional.empty();

        return Optional.of((Employee)objArray[0]);
    }
}
