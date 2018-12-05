package hu.iit.me.controller;

import hu.iit.me.controller.Exception.IDAlreadyExistException;
import hu.iit.me.controller.Exception.JobAlreadyExistException;
import hu.iit.me.controller.Exception.ListIsEmptyException;
import hu.iit.me.controller.Exception.WrongSalaryException;
import hu.iit.me.controller.service.HRJobSettingsService;
import hu.iit.me.controller.service.JobDataService;
import hu.iit.me.converter.Converter;
import hu.iit.me.dto.JobDataXSD;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
@RequestMapping("/hr")
public class HRJobSettingsController {

    private HRJobSettingsService hrJobSettingsService;
    private JobDataService jobDataService;

    public HRJobSettingsController(HRJobSettingsService hrJobSettingsService, JobDataService jobDataService) {
        this.hrJobSettingsService = hrJobSettingsService;
        this.jobDataService = jobDataService;
    }

    @PostMapping(value="/addJob")
    public @ResponseBody Collection<JobDataXSD> addNewJob(@RequestBody JobDataXSD newjob) throws JobAlreadyExistException, IDAlreadyExistException, WrongSalaryException, ListIsEmptyException {
        hrJobSettingsService.addNewJob(Converter.unmarshal(newjob));
        return Converter.marshalList(jobDataService.listJobData());
    }

}
