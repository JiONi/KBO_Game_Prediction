package com.koreabaseball.koreabaseball.DTO;

import java.util.ArrayList;
import java.util.List;

public class GameRecordPlayerDTO {

    private List<GameRecordPlayerDTO> homeHitterList = new ArrayList<GameRecordPlayerDTO>();
    private List<GameRecordPlayerDTO> awayHitterList = new ArrayList<GameRecordPlayerDTO>();
    private List<GameRecordPlayerDTO> homePitcherList = new ArrayList<GameRecordPlayerDTO>();
    private List<GameRecordPlayerDTO> awayPitcherList = new ArrayList<GameRecordPlayerDTO>();

    private int hitterTurn;
    private int playerId;
    private List<String> atBat;
    private int atBatCount;
    private int hit;
    private int runsBattedIn;
    private int runs;
    private int errors;
    private int stolenBase;
    private int caughtStealing;

    private String appearance;
    private int firstFacedBatters;
    private String result;
    private String innings;
    private int numbersOfPitches;
    private int battersFaced;
    private int homerun;
    private int basesOnBalls;
    private int strikeOut;
    private int earnedRuns;

    public List<GameRecordPlayerDTO> getHomeHitterList(){
        return this.homeHitterList;
    }
    public void setHomeHitterList(List<GameRecordPlayerDTO> gameRecordPlayerDTO){ this.homeHitterList = gameRecordPlayerDTO; }
    public List<GameRecordPlayerDTO> getAwayHitterList() { return this.awayHitterList; }
    public void setAwayHitterList(List<GameRecordPlayerDTO> gameRecordPlayerDTO) { this.awayHitterList = gameRecordPlayerDTO; }
    public List<GameRecordPlayerDTO> getHomePitcherList() { return this.homePitcherList; }
    public void setHomePitcherList(List<GameRecordPlayerDTO> gameRecordPlayerDTO) { this.homePitcherList = gameRecordPlayerDTO; }
    public List<GameRecordPlayerDTO> getAwayPitcherList() { return this.awayPitcherList; }
    public void setAwayPitcherList(List<GameRecordPlayerDTO> gamerecordPlayerDTO) { this.awayPitcherList = gamerecordPlayerDTO; }

    public int getHitterTurn(){
        return this.hitterTurn;
    }
    public void setHitterTurn(int hitterTurn){
        this.hitterTurn = hitterTurn;
    }
    public int getPlayerId(){
        return this.playerId;
    }
    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }
    public List<String> getAtBat(){
        return this.atBat;
    }
    public void setAtBat(List<String> atBat){
        this.atBat = atBat;
    }
    public int getAtBatCount(){
        return this.atBatCount;
    }
    public void setAtBatCount(int atBatCount){
        this.atBatCount = atBatCount;
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
    public int getErrors() { return this.errors; }
    public void setErrors(int errors) { this.errors = errors; }
    public int getStolenBase() { return this.stolenBase; }
    public void setStolenBase(int stolenBase) { this.stolenBase = stolenBase; }
    public int getCaughtStealing() { return this.caughtStealing; }
    public void setCaughtStealing(int caughtStealing) { this.caughtStealing = caughtStealing; }

    public String getAppearance() { return this.appearance; }
    public void setAppearance(String appearance) { this.appearance = appearance; }
    public int getFirstFacedBatters(){ return this.firstFacedBatters; }
    public void setFirstFacedBatters(int firstFacedBatters) { this.firstFacedBatters = firstFacedBatters; }
    public String getResult() { return this.result; }
    public void setResult(String result) { this.result = result; }
    public String getInnings() { return this.innings; }
    public void setInnings(String innings) { this.innings = innings; }
    public int getNumbersOfPitches() { return this.numbersOfPitches; }
    public void setNumbersOfPitches(int numbersOfPitches) { this.numbersOfPitches = numbersOfPitches; }
    public int getBattersFaced() { return this.battersFaced; }
    public void setBattersFaced(int battersFaced) { this.battersFaced = battersFaced; }
    public int getHomerun() { return this.homerun; }
    public void setHomerun(int homerun) { this.homerun = homerun; }
    public int getBasesOnBalls() { return this.basesOnBalls; }
    public void setBasesOnBalls(int basesOnBalls) { this.basesOnBalls = basesOnBalls; }
    public int getStrikeOut() { return this.strikeOut; }
    public void setStrikeOut(int strikeOut) { this.strikeOut = strikeOut; }
    public int getEarndRuns() { return this.earnedRuns; }
    public void setEarndRuns(int earnedRuns) { this.earnedRuns = earnedRuns; }
}
