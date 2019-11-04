package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.GameId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="GAME_TEAM")
public class GameTeam {

    @EmbeddedId
    private GameId id;

    @OneToMany(mappedBy = "gameTeam")
    private List<GameTeamHitter> gameTeamHitters = new ArrayList<>();

    @OneToMany(mappedBy = "gameTeam")
    private List<GameTeamPitcher> gameTeamPitchers = new ArrayList<>();

    @OneToMany(mappedBy = "gameTeam")
    private List<GameTeamRecord> gameTeamRecords = new ArrayList<>();

    @Column(name="GAME_GROUP_TURN")
    private int gameGroupTurn;      //경기 그룹 순번

    @Column(name="HOME_YN")
    private boolean homeYn;         //홈 원정 구분

    @Column(name="SEASON_CODE")
    private String seasonCode;      //정규시즌, 포스트시즌

    public GameId getId(){ return this.id; }
    public void setId(GameId id){
        this.id = id;
    }
    public List<GameTeamHitter> getGameTeamHitters() { return this.gameTeamHitters; }
    public void setGameTeamHitters(GameTeamHitter gameTeamHitter) { this.gameTeamHitters.add(gameTeamHitter); }
    public List<GameTeamPitcher> getGameTeamPitchers() { return this.gameTeamPitchers; }
    public void setGameTeamPitchers(GameTeamPitcher gameTeamPitcher) { this.gameTeamPitchers.add(gameTeamPitcher); }
    public List<GameTeamRecord> getGameTeamRecords() { return this.gameTeamRecords; }
    public void setGameTeamRecords(GameTeamRecord gameTeamRecord) { this.gameTeamRecords.add(gameTeamRecord); }
    public int getGameGroupTurn(){
        return this.gameGroupTurn;
    }
    public void setGameGroupTurn(int gameGroupTurn){
        this.gameGroupTurn = gameGroupTurn;
    }
    public boolean getHomeYn(){
        return this.homeYn;
    }
    public void setHomeYn(boolean homeYn){
        this.homeYn = homeYn;
    }
    public String getSeasonCode(){ return this.seasonCode; }
    public void setSeasonCode(String seasonCode) { this.seasonCode = seasonCode; }
}
