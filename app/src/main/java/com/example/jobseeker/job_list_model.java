package com.example.jobseeker;

public class job_list_model {public job_list_model(String assi_id, String name,String datee,String jwork) {

    this.jobid=assi_id;
    this.job=name;
    this.date=datee;
    this.worker=jwork;

}

    public job_list_model(){}

    String jobid,job,date,worker;
    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }
}
