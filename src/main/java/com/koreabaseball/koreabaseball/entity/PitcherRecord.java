package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.RecordId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PITCHER_RECORD")
public class PitcherRecord {
    @EmbeddedId
    private RecordId id;

    @MapsId(value = "playerId")
    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;

    @Column(name = "AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal avg = BigDecimal.ZERO;      //피안타율

    @Column(name = "LEFT_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal leftAvg = BigDecimal.ZERO;  //좌타 피안타율

    @Column(name = "RIGHT_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal rightAvg = BigDecimal.ZERO; //우타 피안타율

    @Column(name = "SWITCH_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal switchAvg = BigDecimal.ZERO;

    @Column(name = "ERA", nullable = false, precision = 5, scale = 2, columnDefinition = "Decimal(5,2) default '0.00'")
    private BigDecimal era = BigDecimal.ZERO;      //평균자책점 (자책점*9 / (총 이닝 수))

    @Column(name = "AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int atBat;

    @Column(name = "HOMERUN", nullable = false, columnDefinition = "integer default 0")
    private int homerun;  //피홈런 갯수

    @Column(name = "GAME_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int gameCount;  //게임 수

    @Column(name = "COMPLETE_GAME", nullable = false, columnDefinition = "integer default 0")
    private int completeGame;   //완투승 수

    @Column(name = "SHUTOUT_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int shutoutCount;   //완봉승 수

    @Column(name = "WIN_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int winCount;       //승리 수

    @Column(name = "LOSE_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int loseCount;      //패배 수

    @Column(name = "SAVE_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int saveCount;      //세이브 수

    @Column(name = "HOLD_COUNT", nullable = false, columnDefinition = "integer default 0")
    private int holdCount;      //홀드 수

    @Column(name = "WINNING_PERCENTAGE", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal winningPercentage = BigDecimal.ZERO;    //승률

    @Column(name = "TOTAL_BATTERS_FACED", nullable = false, columnDefinition = "integer default 0")
    private int totalBattersFaced;  //타자 수

    @Column(name = "NUMBERS_OF_PITCHED", nullable = false, columnDefinition = "integer default 0")
    private int numbersOfPitches;   //투구 수

    @Column(name = "INNINGS_PITCHED", nullable = false, precision = 5, scale = 1, columnDefinition = "Decimal(5,1) default '0.0'")
    private BigDecimal inningsPitched = BigDecimal.ZERO;     //이닝 수

    @Column(name = "HITS", nullable = false, columnDefinition = "integer default 0")
    private int hit;               //피안타 수

    @Column(name = "TWO_BASE_HIT", nullable = false, columnDefinition = "integer default 0")
    private int twoBaseHit;         //2루타 수

    @Column(name = "THREE_BASE_HIT", nullable = false, columnDefinition = "integer default 0")
    private int threeBaseHit;       //3루타 수

    @Column(name = "BASES_ON_BALLS", nullable = false, columnDefinition = "integer default 0")
    private int basesOnBalls;       //볼넷 수

    @Column(name = "INTENTIONAL_BALLS",nullable = false, columnDefinition = "integer default 0")
    private int intentionalBalls;   //고의사구 수

    @Column(name = "STRIKE_OUT", nullable = false, columnDefinition = "integer default 0")
    private int strikeOut;          //삼진 수

    @Column(name = "RUNS", nullable = false, columnDefinition = "integer default 0")
    private int runs;               //실점

    @Column(name = "LEFT_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int leftAtBat;          //좌타 상대 타수

    @Column(name = "LEFT_HIT", nullable = false, columnDefinition = "integer default 0")
    private int leftHit;            //좌타 상대 피안타 수

    @Column(name = "RIGHT_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int rightAtBat;         //우타 상대 타수

    @Column(name = "RIGHT_HIT", nullable = false, columnDefinition = "integer default 0")
    private int rightHit;           //우타 상대 안타 수

    @Column(name = "SWITCH_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int switchAtBat;

    @Column(name = "SWITCH_HIT", nullable = false, columnDefinition = "integer default 0")
    private int switchHit;

    @Column(name = "EARNED_RUNS", nullable = false, columnDefinition = "integer default 0")
    private int earnedRuns;         //자책점

    @Column(name = "BLOWN_SAVE", nullable = false, columnDefinition = "integer default 0")
    private int blownSave;          //블론세이브 수

    @Column(name = "WHIP", nullable = false, precision = 5, scale = 2, columnDefinition = "Decimal(5,2) default '0.00'")
    private BigDecimal whip = BigDecimal.ZERO;             //이닝 당 볼넷과 안타 허용 수. (볼넷+안타)/(이닝 수)

    @Column(name = "QUALITY_START", nullable = false, columnDefinition = "integer default 0")
    private int qualityStart;       //퀄리티스타트 수

    public RecordId getId(){
        return this.id;
    }

    public void setId(RecordId id){
        this.id = id;
    }

    public Player getPlayer() { return this.player; }

    public void setPlayer(Player player) {
        this.player = player;
        if(!this.player.getPitcherRecords().contains(this)){
            this.player.setPitcherRecords(this);
        }
    }

    public BigDecimal getAvg(){
        return this.avg;
    }

    public void setAvg(){
        this.avg = BigDecimal.valueOf(this.hit/this.atBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getLeftAvg(){
        return this.leftAvg;
    }

    public void setLeftAvg(){
        this.leftAvg = BigDecimal.valueOf(this.leftHit/this.leftAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getRightAvg(){
        return this.rightAvg;
    }

    public void setRightAvg(){
        this.rightAvg = BigDecimal.valueOf(this.rightHit/this.rightAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getSwitchAvg() {return this.switchAvg; }

    public void setSwitchAvg(){
        this.switchAvg = BigDecimal.valueOf(this.switchHit/this.switchAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public int getAtBat() { return this.atBat; }
    public void setAtBat(int atBat) { this.atBat = this.atBat + atBat; }

    public BigDecimal getEra(){
        return this.era;
    }

    public void setEra(){
        this.era = BigDecimal.valueOf((this.earnedRuns*9)/this.inningsPitched.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public int getHomerun(){
        return this.homerun;
    }

    public void setHomerun(int homerun){
        this.homerun = this.homerun + homerun;
    }

    public int getGameCount(){
        return this.gameCount;
    }

    public void setGameCount(){
        this.gameCount = this.gameCount + 1;
    }

    public int getCompleteGame(){
        return this.completeGame;
    }

    public void setCompleteGame(int completeGame){
        this.completeGame = this.completeGame + completeGame;
    }

    public int getShutoutCount(){
        return this.shutoutCount;
    }

    public void setShutoutCount(int shutoutCount){
        this.shutoutCount = this.shutoutCount + shutoutCount;
    }

    public int getWinCount(){
        return this.winCount;
    }

    public void setWinCount(int winCount){
        this.winCount = this.winCount + winCount;
    }

    public int getLoseCount(){
        return this.loseCount;
    }

    public void setLoseCount(int loseCount){
        this.loseCount = this.loseCount + loseCount;
    }

    public int getSaveCount(){
        return this.saveCount;
    }

    public void setSaveCount(int saveCount){
        this.saveCount = this.saveCount + saveCount;
    }

    public int getHoldCount(){
        return this.holdCount;
    }

    public void setHoldCount(int holdCount){
        this.holdCount = this.holdCount +holdCount;
    }

    public BigDecimal getWinningPercentage(){
        return this.winningPercentage;
    }

    public void setWinningPercentage(){
        this.winningPercentage = BigDecimal.valueOf((winCount)/(winCount+loseCount)).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public int getTotalBattersFaced(){
        return this.totalBattersFaced;
    }

    public void setTotalBattersFaced(int totalBattersFaced){
        this.totalBattersFaced = this.totalBattersFaced + totalBattersFaced;
    }

    public int getNumbersOfPitches(){
        return this.numbersOfPitches;
    }

    public void setNumbersOfPitches(int numbersOfPitches){
        this.numbersOfPitches = this.numbersOfPitches + numbersOfPitches;
    }

    public BigDecimal getInningsPitched(){
        return this.inningsPitched;
    }

    public void setInningsPitched(BigDecimal inningsPitched){
        BigDecimal totalInningsPitched = this.inningsPitched.add(inningsPitched);
        BigDecimal compareInning = BigDecimal.valueOf(totalInningsPitched.intValue()+0.9);
        if(totalInningsPitched.compareTo(compareInning) == 0){
            this.inningsPitched = totalInningsPitched.setScale(1,BigDecimal.ROUND_HALF_UP);
        }else{
            this.inningsPitched = totalInningsPitched;
        }

    }

    public int getHit(){
        return this.hit;
    }

    public void setHit(int hit){
        this.hit = this.hit + hit;
    }

    public int getTwoBaseHit(){
        return this.twoBaseHit;
    }

    public void setTwoBaseHit(int twoBaseHit){
        this.twoBaseHit = this.twoBaseHit + twoBaseHit;
    }

    public int getThreeBaseHit(){
        return this.threeBaseHit;
    }

    public void setThreeBaseHit(int threeBaseHit){
        this.threeBaseHit = this.threeBaseHit + threeBaseHit;
    }

    public int getBasesOnBalls(){
        return this.basesOnBalls;
    }

    public void setBasesOnBalls(int basesOnBalls){
        this.basesOnBalls = this.basesOnBalls + basesOnBalls;
    }

    public int getIntentionalBalls(){
        return this.intentionalBalls;
    }

    public void setIntentionalBalls(int intentionalBalls){
        this.intentionalBalls = this.intentionalBalls + intentionalBalls;
    }

    public int getStrikeOut(){
        return this.strikeOut;
    }

    public void setStrikeOut(int strikeOut){
        this.strikeOut = this.strikeOut + strikeOut;
    }

    public int getRuns(){
        return this.runs;
    }

    public void setRuns(int runs){
        this.runs = this.runs + runs;
    }

    public int getLeftAtBat(){
        return this.leftAtBat;
    }

    public void setLeftAtBat(int leftAtBat){
        this.leftAtBat = this.leftAtBat + leftAtBat;
    }

    public int getLeftHit(){
        return this.leftHit;
    }

    public void setLeftHit(int leftHit){
        this.leftHit = this.leftHit +leftHit;
    }

    public int getRightAtBat(){
        return this.rightAtBat;
    }

    public void setRightAtBat(int rightAtBat){
        this.rightAtBat = this.rightAtBat + rightAtBat;
    }

    public int getRightHit(){
        return this.rightHit;
    }

    public void setRightHit(int rightHit){
        this.rightHit = this.rightHit + rightHit;
    }

    public int getSwitchAtBat(){
        return this.switchAtBat;
    }

    public void setSwitchAtBat(int switchAtBat){
        this.switchAtBat = this.switchAtBat + switchAtBat;
    }

    public int getSwitchHit(){
        return this.switchHit;
    }

    public void setSwitchHit(int switchHit){
        this.switchHit = this.switchHit + switchHit;
    }

    public int getEarnedRuns(){
        return this.earnedRuns;
    }

    public void setEarnedRuns(int earnedRuns){
        this.earnedRuns = this.earnedRuns + earnedRuns;
    }

    public int getBlownSave(){
        return this.blownSave;
    }

    public void setBlownSave(int blownSave){
        this.blownSave = this.blownSave + blownSave;
    }

    public BigDecimal getWhip(){
        return this.whip;
    }

    public void setWhip(){
        this.whip = BigDecimal.valueOf((this.basesOnBalls+this.hit)/this.inningsPitched.doubleValue()).setScale(3,BigDecimal.ROUND_HALF_UP);
    }

    public int getQualityStart(){
        return this.qualityStart;
    }

    public void setQualityStart(int qualityStart){
        this.qualityStart = this.qualityStart + qualityStart;
    }


    public PitcherRecord(int playerId, String season, boolean homeYn){
        RecordId recordId = new RecordId(playerId, season, homeYn);
        setId(recordId);
    }

    public PitcherRecord(){

    }
}
