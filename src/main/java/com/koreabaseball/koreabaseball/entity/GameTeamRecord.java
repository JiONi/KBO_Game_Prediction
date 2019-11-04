package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.GameRecordId;

import javax.persistence.*;

@Entity
@Table(name="GAME_TEAM_RECORD")
public class GameTeamRecord {

    @EmbeddedId
    private GameRecordId id;

    @Column(name="INNING")
    private int inning;                 //이닝

    @ManyToOne
    @JoinColumn(name="PITCHER_ID", referencedColumnName = "PLAYER_ID")
    private Player pitcher;              //투수 ID

    @MapsId(value = "hitterId")
    @ManyToOne
    @JoinColumn(name="HITTER_ID", referencedColumnName = "PLAYER_ID")
    private Player hitter;

    @MapsId(value="gameId")
    @ManyToOne
    @JoinColumns({@JoinColumn(name="GAME_DATE"),
            @JoinColumn(name="TEAM_ID")})
    private GameTeam gameTeam;

    @Column(name="RESULT")
    private String result;              //결과


    public GameRecordId getId(){
        return this.id;
    }
    public void setId(GameRecordId id){
        this.id = id;
    }
    public int getInning(){
        return this.inning;
    }
    public void setInning(int inning){
        this.inning = inning;
    }
    public Player getPitcher(){
        return this.pitcher;
    }
    public void setPitcher(Player pitcher){
        this.pitcher = pitcher;
    }
    public String getResult(){
        return this.result;
    }
    public void setResult(String result){
        this.result = result;
    }
    public Player getHitter() { return this.hitter; }
    public void setHitter(Player hitter) {
        this.hitter = hitter;
        if(!this.hitter.getGameTeamRecords().contains(this)){
            this.hitter.setGameTeamRecords(this);
        }
    }
    public GameTeam getGameTeam() { return this.gameTeam; }
    public void setGameTeam(GameTeam gameTeam) {
        this.gameTeam = gameTeam;
        if(!this.gameTeam.getGameTeamRecords().contains(this)){
            this.gameTeam.setGameTeamRecords(this);
        }
    }
}
