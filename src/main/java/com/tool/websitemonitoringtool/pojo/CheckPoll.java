package com.tool.websitemonitoringtool.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class CheckPoll {

    @JsonUnwrapped
    private final Check check;

    private final List<Poll> polls;

    public CheckPoll(Check check, List<Poll> polls) {
        this.check = check;
        this.polls = polls;
    }

    public Check getCheck() {
        return check;
    }

    public List<Poll> getPolls() {
        return polls;
    }
}
