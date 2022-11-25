package com.tool.websitemonitoringtool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tool.websitemonitoringtool.pojo.Check;
import com.tool.websitemonitoringtool.pojo.Poll;

public interface PollRepo extends CrudRepository<Poll, Long> {

	List<Poll> findAllByChk(Check chk);

	void deleteAllByChk(Check chk);
}