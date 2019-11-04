package com.koreabaseball.koreabaseball.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;      //팀 아이디

    @Column(name = "TEAM_NAME")
    private String name;    //팀 구단 이름

    @Column(name = "TEAM_SHORT_NAME")
    private String shortName;   //팀 이름

    @Column(name = "TEAM_HOME_GROUND")
    private String homeGround;  //팀 홈구장

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<Player>();

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getShortName(){
        return shortName;
    }
    public void setShortName(String name){
        this.shortName = name;
    }

    public String getHomeGround(){
        return homeGround;
    }
    public void setHomeGround(String homeGround){
        this.homeGround = homeGround;
    }

    public List<Player> getPlayers(){
        return players;
    }
}
