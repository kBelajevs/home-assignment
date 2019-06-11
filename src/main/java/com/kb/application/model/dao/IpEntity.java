package com.kb.application.model.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class IpEntity {

    @Id
    @GeneratedValue
    private long id;
    private String ip;
    private LocalDateTime createDateTime;

    public IpEntity() {
    }

    public IpEntity(String ip, LocalDateTime createDateTime) {
        this.ip = ip;
        this.createDateTime = createDateTime;
    }

    public IpEntity(long id, String ip, LocalDateTime createDateTime) {
        this.id = id;
        this.ip = ip;
        this.createDateTime = createDateTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
