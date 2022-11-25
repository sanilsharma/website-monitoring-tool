package com.tool.websitemonitoringtool.service;

import com.tool.websitemonitoringtool.pojo.Check;

public interface CheckService {

	void save(Check check);

	void delete(Check check);
}