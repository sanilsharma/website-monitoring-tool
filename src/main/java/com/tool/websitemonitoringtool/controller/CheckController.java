package com.tool.websitemonitoringtool.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tool.websitemonitoringtool.pojo.Check;
import com.tool.websitemonitoringtool.service.CheckService;


@RestController
@RequestMapping("/api")
public class CheckController {

    private CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @PostMapping(value = "/checks")
    public void updateCheck(@RequestBody Check check) {
        checkService.save(check);
    }

//    @GetMapping(value = "/checks")
//    public List<CheckPoll> getChecks() {
//        return checkService.getAllOfGivenUser();
//    }

    @DeleteMapping(value = "/checks")
    public void deleteCheck(@RequestBody Check check) {
        checkService.delete(check);
    }
}
