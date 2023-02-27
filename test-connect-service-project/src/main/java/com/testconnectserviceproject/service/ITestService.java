package com.testconnectserviceproject.service;

import com.testconnectserviceproject.model.entity.Test;

public interface ITestService extends IGeneralService<Test>{
    Test sendTest(Test test);
}
