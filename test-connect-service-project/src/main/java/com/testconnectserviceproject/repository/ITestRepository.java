package com.testconnectserviceproject.repository;

import com.testconnectserviceproject.model.entity.Test;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends PagingAndSortingRepository<Test,Long> {
}
