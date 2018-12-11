package com.example.recycleview_01.bean;

import java.util.List;

public class ContentBean {


    private String msg;
    private String code;
    private List<DataBean> data;
    private final String SUCCESS_CODE = "0";
    public boolean isSuccess(){
        return code.equals(SUCCESS_CODE);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int cid;
        private String createtime;
        private String icon;
        private int ishome;
        private String name;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIshome() {
            return ishome;
        }

        public void setIshome(int ishome) {
            this.ishome = ishome;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
