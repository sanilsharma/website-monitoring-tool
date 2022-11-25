package com.tool.websitemonitoringtool;


import com.tool.websitemonitoringtool.pojo.Check;
import com.tool.websitemonitoringtool.service.PollService;

public class PollTask {

    private final Check check;
    private final PollService pollService;

    public PollTask(Check check, PollService pollService) {
        this.check = check;
        this.pollService = pollService;
    }

    public Check getCheck() {
        return check;
    }

    public void startPolling() {
        pollService.pollGivenCheck(check);
    }
}