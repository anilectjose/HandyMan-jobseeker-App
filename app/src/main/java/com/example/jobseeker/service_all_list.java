package com.example.jobseeker;

public class service_all_list {public service_all_list(String Serv_id, String Sename, String Setype, String Setag, String sDue) {

    this.ser_id=Serv_id;
    this.SName=Sename;
    this.SType=Setype;
    this.STags=Setag;
    this.SDue=sDue;
}
    public service_all_list(){}

    String ser_id,SName,SType,STags,SDue;

    public String getSType() {
        return SType;
    }

    public void setSType(String sType) {
        this.SType = sType;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String sName) {
        this.SName = sName;
    }

    public String getSer_id() { return ser_id; }

    public void setSer_id(String ser_id) {
        this.ser_id = ser_id;
    }

    public String getSTags() { return STags; }

    public void setSTags(String sTags) {
        this.STags = sTags;
    }

    public String getSDue() {
        return SDue;
    }

    public void setSDue(String sDue) {
        this.SDue = sDue;
    }
}
