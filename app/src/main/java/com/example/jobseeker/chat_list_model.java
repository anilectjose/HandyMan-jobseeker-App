package com.example.jobseeker;

public class chat_list_model {
    public chat_list_model(String assi_id, String name) {

        this.chat_id=assi_id;
        this.msg=name;

    }

    public chat_list_model(){}

    String chat_id,msg;

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
}
