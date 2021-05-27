package com.example.jobseeker;

public class chatname_list_model {
    public chatname_list_model(String chat_id, String msg,String duedate,String ew2) {

        this.chat_id=chat_id;
        this.msg=msg;
        this.duedate=duedate;
        this.tw2=ew2;

    }

    public chatname_list_model(){}

    String chat_id,msg,duedate,tw,tw2;
    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getTw2() {
        return tw2;
    }

    public void setTw2(String tw2) {
        this.tw2 = tw2;
    }
}
