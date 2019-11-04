package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.GameTeamPlayerId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="GAME_TEAM_PITCHER")
public class GameTeamPitcher {

    @EmbeddedId
    private GameTeamPlayerId id;

    @MapsId(value="playerId")
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;  //소속 팀

    @MapsId(value="gameId")
    @ManyToOne
    @JoinColumns({@JoinColumn(name="GAME_DATE"),
            @JoinColumn(name="TEAM_ID")})
    private GameTeam gameTeam;

    @Column(name="APPEARANCE_INNING")
    private int appearanceInning;       //등판 이닝

    @Column(name="FIRST_FACED_TURN")
    private int firstFacedTurn;         //첫 상대 타순

    @Column(name="INNINGS_PITCHED", precision = 5, scale = 1)
    private BigDecimal inningsPitched;         //이닝 수

    @Column(name="BATTERS_FACED")
    private int battersFaced;           //타자 수

    @Column(name="AT_BAT")
    private int atBat;                  //타수

   @Column(name="NUMBERS_OF_PITCHES")
    private int numbersOfPitched;       //투구 수

    @Column(name="HIT")
    private int hit;                    //피안타

    @Column(name="BASES_ON_BALLS")
    private int basesOnBalls;           //볼넷 수

    @Column(name="HOMERUN")
    private int homerun;                //피홈런

    @Column(name="STRIKE_OUT")
    private int strikeOut;              //삼진

    @Column(name="RUNS")
    private int runs;                   //실점

    @Column(name="EARNED_RUNS")
    private int earnedRuns;             //자책점

    @Column(name="GAME_RESULT")
    private String gameResult;          //게임 결과



    public GameTeamPlayerId getId(){
        return this.id;
    }
    public void setId(GameTeamPlayerId id){
        this.id = id;
    }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) {
        this.player = player;
        if(!this.player.getGameTeamPitchers().contains(this)){
            this.player.setGameTeamPitchers(this);
        }
    }
    public GameTeam getGameTeam() { return this.gameTeam; }
    public void setGameTeam(GameTeam gameTeam) {
        this.gameTeam = gameTeam;
        if(!this.gameTeam.getGameTeamPitchers().contains(this)){
            this.gameTeam.setGameTeamPitchers(this);
        }
    }
    public int getAppearanceInning(){
        return this.appearanceInning;
    }
    public void setAppearanceInning(int appearanceInning){
        this.appearanceInning = appearanceInning;
    }
    public int getFirstFacedTurn(){
        return this.firstFacedTurn;
    }
    public void setFirstFacedTurn(int firstFacedTurn){
        this.firstFacedTurn = firstFacedTurn;
    }
    public BigDecimal getInningsPitched(){
        return this.inningsPitched;
    }
    public void setInningsPitched(BigDecimal inningsPitched){
        this.inningsPitched = inningsPitched;
    }
    public int getBattersFaced(){
        return this.battersFaced;
    }
    public void setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
    }
    public int getAtBat() { return this.atBat; }
    public void setAtBat(int atBat) { this.atBat = atBat; }
    public int getNumbersOfPitched(){
        return this.numbersOfPitched;
    }
    public void setNumbersOfPitched(int numbersOfPitched){
        this.numbersOfPitched = numbersOfPitched;
    }
    public int getHit(){
        return this.hit;
    }
    public void setHit(int hit){
        this.hit = hit;
    }
    public int getBasesOnBalls(){
        return this.basesOnBalls;
    }
    public void setBasesOnBalls(int basesOnBalls){
        this.basesOnBalls = basesOnBalls;
    }
    public int getHomerun(){
        return this.homerun;
    }
    public void setHomerun(int homerun){
        this.homerun = homerun;
    }
    public int getStrikeOut(){
        return this.strikeOut;
    }
    public void setStrikeOut(int strikeOut){
        this.strikeOut = strikeOut;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getEarnedRuns(){
        return this.earnedRuns;
    }
    public void setEarnedRuns(int earnedRuns){
        this.earnedRuns = earnedRuns;
    }
    public String getGameResult() { return this.gameResult; }
    public void setGameResult(String gameResult) { this.gameResult = gameResult; }
}
