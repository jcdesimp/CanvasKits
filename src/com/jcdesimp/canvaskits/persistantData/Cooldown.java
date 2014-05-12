package com.jcdesimp.canvaskits.persistantData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * File created by jcdesimp on 5/2/14.
 */

@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name="ck_cooldown")
public class Cooldown {



    @Id
    private int id;


    private String pUUID ;

    private long endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpUUID() {
        return pUUID;
    }

    public void setpUUID(String pUUID) {
        this.pUUID = pUUID;
    }


    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}