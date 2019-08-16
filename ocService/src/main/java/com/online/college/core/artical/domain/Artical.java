package com.online.college.core.artical.domain;


import com.online.college.common.orm.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Artical implements Serializable {

    private static final long serialVersionUID = -68528406716367757L;

    private Integer id;
    /**
     *文章标题
     **/
    private String title;

    /**
     *文章内容
     **/
    private String content;
    /**
     *作者
     **/
    private String auther;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除状态
     **/
    private Integer delflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
}
