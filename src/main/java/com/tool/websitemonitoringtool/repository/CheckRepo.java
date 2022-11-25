package com.tool.websitemonitoringtool.repository;

import org.springframework.data.repository.CrudRepository;

import com.tool.websitemonitoringtool.pojo.Check;

public interface CheckRepo extends CrudRepository<Check, Integer> {
}

