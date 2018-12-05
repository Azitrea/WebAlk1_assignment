package hu.iit.me.controller.service;

import hu.iit.me.controller.Exception.IDAlreadyExistException;
import hu.iit.me.controller.Exception.JobAlreadyExistException;
import hu.iit.me.controller.Exception.WrongSalaryException;
import hu.iit.me.controller.dao.JobDataDAO;
import hu.iit.me.controller.model.JobData;
import org.springframework.beans.factory.annotation.Autowired;


public class HRJobSettingsServiceImpl implements HRJobSettingsService{

    @Autowired
    private JobDataDAO jobDataDAO;

    @Override
    public void addNewJob(JobData newjob) throws JobAlreadyExistException, IDAlreadyExistException, WrongSalaryException {

        if (newjob.getJobSalaryHuf() < 0){
            throw new WrongSalaryException();
        }

        for(JobData j : jobDataDAO.returnJobData()){
            if(j.getJobName().equals(newjob.getJobName())){
                throw new JobAlreadyExistException();
            }

            if(j.getJobID() == newjob.getJobID()){
                throw new IDAlreadyExistException();
            }
        }

        jobDataDAO.saveJob(newjob);
    }
}
