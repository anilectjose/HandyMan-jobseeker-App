package com.example.jobseeker;

public class complaints_list_model {
    public complaints_list_model(String comp_id, String name,String subj, String content) {

        this.comp_id=comp_id;
        this.name=name;
        this.subj=subj;
        this.content=content;

    }

    public complaints_list_model(){}

    String comp_id,name,subj,content;

    public String getComp_id() {
        return comp_id;
    }

    public void setComp_id(String comp_id) {
        this.comp_id = comp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

