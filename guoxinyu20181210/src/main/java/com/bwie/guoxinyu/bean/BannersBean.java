package com.bwie.guoxinyu.bean;

import java.util.List;

public class BannersBean {



    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private MiaoshaBean miaosha;
        private TuijianBean tuijian;
        private List<BannerBean> banner;
        private List<FenleiBean> fenlei;

        public MiaoshaBean getMiaosha() {
            return miaosha;
        }

        public void setMiaosha(MiaoshaBean miaosha) {
            this.miaosha = miaosha;
        }

        public TuijianBean getTuijian() {
            return tuijian;
        }

        public void setTuijian(TuijianBean tuijian) {
            this.tuijian = tuijian;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<FenleiBean> getFenlei() {
            return fenlei;
        }

        public void setFenlei(List<FenleiBean> fenlei) {
            this.fenlei = fenlei;
        }

        public static class MiaoshaBean {

            private String name;
            private int time;
            private List<ListBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {

                private double bargainPrice;
                private String createtime;
                private String detailUrl;
                private String images;
                private int itemtype;
                private int pid;
                private double price;
                private int pscid;
                private int salenum;
                private int sellerid;
                private String subhead;
                private String title;

                public double getBargainPrice() {
                    return bargainPrice;
                }

                public void setBargainPrice(double bargainPrice) {
                    this.bargainPrice = bargainPrice;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public int getItemtype() {
                    return itemtype;
                }

                public void setItemtype(int itemtype) {
                    this.itemtype = itemtype;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getPscid() {
                    return pscid;
                }

                public void setPscid(int pscid) {
                    this.pscid = pscid;
                }

                public int getSalenum() {
                    return salenum;
                }

                public void setSalenum(int salenum) {
                    this.salenum = salenum;
                }

                public int getSellerid() {
                    return sellerid;
                }

                public void setSellerid(int sellerid) {
                    this.sellerid = sellerid;
                }

                public String getSubhead() {
                    return subhead;
                }

                public void setSubhead(String subhead) {
                    this.subhead = subhead;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class TuijianBean {

            private String name;
            private List<ListBeanX> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {


                private double bargainPrice;
                private String createtime;
                private String detailUrl;
                private String images;
                private int itemtype;
                private int pid;
                private double price;
                private int pscid;
                private int salenum;
                private int sellerid;
                private String subhead;
                private String title;

                public double getBargainPrice() {
                    return bargainPrice;
                }

                public void setBargainPrice(double bargainPrice) {
                    this.bargainPrice = bargainPrice;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public int getItemtype() {
                    return itemtype;
                }

                public void setItemtype(int itemtype) {
                    this.itemtype = itemtype;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getPscid() {
                    return pscid;
                }

                public void setPscid(int pscid) {
                    this.pscid = pscid;
                }

                public int getSalenum() {
                    return salenum;
                }

                public void setSalenum(int salenum) {
                    this.salenum = salenum;
                }

                public int getSellerid() {
                    return sellerid;
                }

                public void setSellerid(int sellerid) {
                    this.sellerid = sellerid;
                }

                public String getSubhead() {
                    return subhead;
                }

                public void setSubhead(String subhead) {
                    this.subhead = subhead;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class BannerBean {
            private int aid;
            private String createtime;
            private String icon;
            private Object productId;
            private String title;
            private int type;
            private String url;
            public String hasImage(){
                return icon.replace("https","http");
            }
            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
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

            public Object getProductId() {
                return productId;
            }

            public void setProductId(Object productId) {
                this.productId = productId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class FenleiBean {


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
}
