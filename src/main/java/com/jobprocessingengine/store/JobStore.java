package com.jobprocessingengine.store;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface JobStore {

    public boolean save(Job job);

    public Job findById(String id);

    public JobStatus getStatus(String id);

    public boolean updateStatus(String id, JobStatus newStatus);
}
