package com.bpm.employee.repository;

import com.bpm.employee.entitys.ComputerSheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "ComputerSheetRepository")
public interface ComputerSheetRepository extends CrudRepository<ComputerSheet, Long> {

    public Optional<ComputerSheet> findByUserNameAndUserPassword(String userName, String userPassword);
    public List<ComputerSheet> findByMail(String mail);

}
