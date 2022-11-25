package com.tool.websitemonitoringtool.service;

import com.tool.websitemonitoringtool.pojo.Check;

public interface PollService {

    void pollGivenCheck(Check check);
}
