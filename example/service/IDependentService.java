package com.example.service;

import java.util.List;

import com.example.demo.Dependent;

public interface IDependentService {
 List<Dependent> findAllDependents(String personId);

}
