package com.khb.hu.springcourse.hr.dto;

import org.hibernate.envers.RevisionType;

import java.util.Date;

public class HistoryData<T> {
    private T data;
    private RevisionType revType;
    private int revision;
    private Date date;


    public HistoryData(T data, RevisionType revType, int revision, Date date) {
        this.data = data;
        this.revType = revType;
        this.revision = revision;
        this.date = date;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RevisionType getRevType() {
        return revType;
    }

    public void setRevType(RevisionType revType) {
        this.revType = revType;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
