package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.GameTeamPlayerId;

import javax.persistence.*;

@Entity
@Table(name="GAME_TEAM_HITTER")
public class GameTeamHitter {

    @EmbeddedId
    private GameTeamPlayerId id;

    @MapsId(value = "playerId")
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;  //소속 팀

    @MapsId(value="gameId")
    @ManyToOne
    @JoinColumns({@JoinColumn(name="GAME_DATE"),
                @JoinColumn(name="TEAM_ID")})
    private GameTeam gameTeam;

    @Column(name="HIT_TURN")
    private int hitTurn;        //타순

    @Column(name="AT_BAT")
    private int atBat;          //타수

    @Column(name="HIT")
    private int hit;            //안타

    @Column(name="RUNS_BATTED_IN")
    private int runsBattedIn;   //타점

    @Column(name="RUNS")
    private int runs;           //득점

    @Column(name="ERRORS")
    private int errors;         //실책

    @Column(name="STOLEN_BASE")
    private int stolenBase;     //도루 성공

    @Column(name="CAUGHT_STEALING")
    private int caughtStealing; //도루 실패

    public GameTeamPlayerId getId(){
        return this.id;
    }
    public void setId(GameTeamPlayerId id){
        this.id = id;
    }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) {
        this.player = player;
        if(!this.player.getGameTeamHitters().contains(this)){
            this.player.setGameTeamHitters(this);
        }
    }
    public GameTeam getGameTeam() { return this.gameTeam; }
    public void setGameTeam(GameTeam gameTeam) {
        this.gameTeam = gameTeam;
        if(!this.gameTeam.getGameTeamHitters().contains(this)){
            this.gameTeam.setGameTeamHitters(this);
        }
    }
    public int getHitTurn(){
        return this.hitTurn;
    }
    public void setHitTurn(int hitTurn){
        this.hitTurn = hitTurn;
    }
    public int getAtBat(){
        return this.atBat;
    }
    public void setAtBat(int atBat){
        this.atBat = atBat;
    }

    public int getHit(){
        return this.hit;
    }
    public void setHit(int hit){
        this.hit = hit;
    }
    public int getRunsBattedIn(){
        return this.runsBattedIn;
    }
    public void setRunsBattedIn(int runsBattedIn){
        this.runsBattedIn = runsBattedIn;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getErrors(){
        return this.errors;
    }
    public void setErrors(int errors){
        this.errors = errors;
    }
    public int getStolenBase() { return this.stolenBase; }
    public void setStolenBase(int stolenBase) { this.stolenBase = stolenBase; }
    public int getCaughtStealing() { return this.caughtStealing; }
    public void setCaughtStealing(int caughtStealing) { this.caughtStealing = caughtStealing; }
}
