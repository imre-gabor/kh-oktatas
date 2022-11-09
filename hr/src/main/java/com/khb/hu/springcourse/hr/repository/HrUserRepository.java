package com.khb.hu.springcourse.hr.repository;

import com.khb.hu.springcourse.hr.model.HrUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrUserRepository extends JpaRepository<HrUser, String> {
}
