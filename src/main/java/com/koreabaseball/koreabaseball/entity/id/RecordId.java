package com.koreabaseball.koreabaseball.entity.id;

import com.koreabaseball.koreabaseball.entity.Player;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class RecordId implements Serializable {

    private int playerId;

    @EqualsAndHashCode.Include
    @Column(name="SEASON")
    private String season;  //연도+시즌 ex)2019PS : 2019년 포스트시즌, 2019RS : 2019년 정규시즌

    @EqualsAndHashCode.Include
    @Column(name="HOME_YN")
    private Boolean homeYn; //홈, 원정 기록 여부 홈 : Y, 원정 : N

    public int getPlayerId(){
        return this.playerId;
    }
    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }
    public String getSeason(){
        return this.season;
    }
    public void setSeason(String season){
        this.season = season;
    }
    public boolean getHomeYn(){
        return this.homeYn;
    }
    public void setHomeYn(boolean homeYn){
        this.homeYn = homeYn;
    }

    public RecordId(int playerId, String season, boolean homeYn){
        this.playerId = playerId;
        this.season = season;
        this.homeYn = homeYn;
    }

    public RecordId(){

    }
}
