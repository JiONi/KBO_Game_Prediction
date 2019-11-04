package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.RecordId;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "HITTER_RECORD")
public class HitterRecord {

    @EmbeddedId
    private RecordId id;

    @MapsId(value = "playerId")
    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;

    @Column(name="AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal avg = BigDecimal.ZERO;      //타율

    @Column(name="LEFT_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal leftAvg = BigDecimal.ZERO;  //좌투 상대 타율

    @Column(name="RIGHT_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal rightAvg = BigDecimal.ZERO; //우투 상대 타율

    @Column(name="UNDER_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal underAvg = BigDecimal.ZERO; //언더 상대 타율

    @Column(name="HOMERUN", nullable = false, columnDefinition = "integer default 0")
    private int homerun;    //홈런 갯수

    @Column(name="GAME_COUNT",nullable = false, columnDefinition = "integer default 0")
    private int gameCount;  //경기 수

    @Column(name="PLATE_APPEARANCE", nullable = false, columnDefinition = "integer default 0")
    private int plateAppearance;    //타석

    @Column(name="AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int atBat;              //타수

    @Column(name="RUNS", nullable = false, columnDefinition = "integer default 0")
    private int runs;               //득점

    @Column(name="HIT", nullable = false, columnDefinition = "integer default 0")
    private int hit;                //안타 수

    @Column(name="LEFT_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int leftAtBat;          //좌투 상대 타수

    @Column(name="LEFT_HIT", nullable = false, columnDefinition = "integer default 0")
    private int leftHit;            //좌투 상대 안타 수

    @Column(name="RIGHT_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int rightAtBat;         //우투 상대 타수

    @Column(name="RIGHT_HIT", nullable = false, columnDefinition = "integer default 0")
    private int rightHit;           //우투 상대 안타 수

    @Column(name="UNDER_AT_BAT", nullable = false, columnDefinition = "integer default 0")
    private int underAtBat;         //언더 상대 타수

    @Column(name="UNDER_HIT", nullable = false, columnDefinition = "integer default 0")
    private int underHit;           //언더 상대 안타 수

    @Column(name="TWO_BASE_HIT", nullable = false, columnDefinition = "integer default 0")
    private int twoBaseHit;         //2루타 수

    @Column(name="THREE_BASE_HIT", nullable = false, columnDefinition = "integer default 0")
    private int threeBaseHit;       //3루타 수

    @Column(name="TOTAL_BASES", nullable = false, columnDefinition = "integer default 0")
    private int totalBases;         //총 루타 수

    @Column(name="RUNS_BATTED_IN", nullable = false, columnDefinition = "integer default 0")
    private int runsBattedIn;       //타점

    @Column(name="SACRIFICE_BUNTS", nullable = false, columnDefinition = "integer default 0")
    private int sacrificeBunts;     //희생 번트 수

    @Column(name="SACRIFICE_FLIES", nullable = false, columnDefinition = "integer default 0")
    private int sacrificeFlies;     //희생 플라이 수

    @Column(name="CAUGHT_STEALING", nullable = false, columnDefinition = "integer default 0")
    private int caughtStealing;     //도루 실패 수

    @Column(name="STOLEN_BASE", nullable = false, columnDefinition = "integer default 0")
    private int stolenBase;         //도루 성공 수

    @Column(name="STOLEN_BASE_ATTEMPTED", nullable = false, columnDefinition = "integer default 0")
    private int stolenBaseAttepted;     //도루 시도 횟수

    @Column(name="BASES_ON_BALLS", nullable = false, columnDefinition = "integer default 0")
    private int basesOnBalls;       //볼넷

    @Column(name="INTENTIONAL_BALLS", nullable = false, columnDefinition = "integer default 0")
    private int intentionalBalls;   //고의 사구

    @Column(name="HIT_BY_PITCH", nullable = false, columnDefinition = "integer default 0")
    private int hitByPitch;         //사구

    @Column(name="STRIKE_OUT", nullable = false, columnDefinition = "integer default 0")
    private int strikeOut;          //삼진

    @Column(name="DOUBLE_PLAY", nullable = false, columnDefinition = "integer default 0")
    private int doublePlay;         //병살타

    @Column(name="SLUGGING_AVG", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal sluggingAvg = BigDecimal.ZERO;        //장타율

    @Column(name="ON_BASE_PERCENTAGE", nullable = false, precision = 4, scale = 3, columnDefinition = "Decimal(4,3) default '0.000'")
    private BigDecimal onBasePercentage = BigDecimal.ZERO;     //출루율(안타+볼넷+사구)/(타수+볼넷+사구+희생플라이)

    @Column(name="ERRORS", nullable = false, columnDefinition = "integer default 0")
    private int errors;             //실책

    @Column(name="MULTI_HITS", nullable = false, columnDefinition = "integer default 0")
    private int multiHits;          //멀티 히트 횟수

    public RecordId getId(){
        return this.id;
    }

    public void setId(RecordId id){
        this.id = id;
    }

    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) {
        this.player = player;
        if(!this.player.getHitterRecords().contains(this)){
            this.player.setHitterRecords(this);
        }
    }

    public BigDecimal getAvg(){
        return this.avg;
    }

    public void setAvg(){
        this.avg = BigDecimal.valueOf(this.hit / this.atBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getLeftAvg(){
        return this.leftAvg;
    }

    public void setLeftAvg(){
        this.leftAvg = BigDecimal.valueOf(this.leftHit / this.leftAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getRightAvg(){
        return this.rightAvg;
    }

    public void setRightAvg(){
        this.rightAvg = BigDecimal.valueOf(this.rightHit / this.rightAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getUnderAvg(){
        return this.underAvg;
    }

    public void setUnderAvg(){
        this.underAvg = BigDecimal.valueOf(this.underHit / this.underAtBat).setScale(3, BigDecimal.ROUND_HALF_UP);
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

    public int getPlateAppearance(){
        return this.plateAppearance;
    }

    public void setPlateAppearance(int plateAppearance){
        this.plateAppearance = this.plateAppearance + plateAppearance;
    }

    public int getAtBat(){
        return this.atBat;
    }

    public void setAtBat(int atBat){
        this.atBat = this.atBat + atBat;
    }

    public int getLeftAtBat() { return this.leftAtBat; }

    public void setLeftAtBat(int leftAtBat) { this.leftAtBat = this.leftAtBat + leftAtBat; }

    public int getLeftHit() { return this.leftHit; }

    public void setLeftHit(int leftHit) { this.leftHit = this.leftHit + leftHit; }

    public int getRightAtBat() { return this.rightAtBat; }

    public void setRightAtBat(int rightAtBat) { this.rightAtBat = this.rightAtBat +rightAtBat; }

    public int getRightHit() { return this.rightHit; }

    public void setRightHit(int rightHit) { this.rightHit = this.rightHit + rightHit; }

    public int getUnderAtBat() { return this.underAtBat; }

    public void setUnderAtBat(int underAtBat) { this.underAtBat = this.underAtBat + underAtBat; }

    public int getUnderHit() { return this.underHit; }

    public void setUnderHit(int underHit) { this.underHit = this.underHit + underHit; }

    public int getRuns(){
        return this.runs;
    }

    public void setRuns(int runs){
        this.runs = this.runs + runs;
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

    public int getTotalBases(){
        return this.totalBases;
    }

    public void setTotalBases(int totalBases){
        this.totalBases = this.totalBases + totalBases;
    }

    public int getRunsBattedIn(){
        return this.runsBattedIn;
    }

    public void setRunsBattedIn(int runsBattedIn){
        this.runsBattedIn = this.runsBattedIn + runsBattedIn;
    }

    public int getSacrificeBunts(){
        return this.sacrificeBunts;
    }

    public void setSacrificeBunts(int sacrificeBunts){
        this.sacrificeBunts = this.sacrificeBunts + sacrificeBunts;
    }

    public int getSacrificeFlies(){
        return this.sacrificeFlies;
    }

    public void setSacrificeFlies(int sacrificeFlies){
        this.sacrificeFlies = this.sacrificeFlies + sacrificeFlies;
    }

    public int getCaughtStealing(){
        return this.caughtStealing;
    }

    public void setCaughtStealing(int caughtStealing){
        this.caughtStealing = this.caughtStealing + caughtStealing;
    }

    public int getStolenBase(){
        return this.stolenBase;
    }

    public void setStolenBase(int stolenBase){
        this.stolenBase = this.stolenBase + stolenBase;
    }

    public int getStolenBaseAttepted(){ return this.stolenBaseAttepted; }

    public void setStolenBaseAttepted(int stolenBaseAttepted) { this.stolenBaseAttepted = this.stolenBaseAttepted + stolenBaseAttepted; }

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

    public int getHitByPitch(){
        return this.hitByPitch;
    }

    public void setHitByPitch(int hitByPitch){
        this.hitByPitch = this.hitByPitch + hitByPitch;
    }

    public int getStrikeOut(){
        return this.strikeOut;
    }

    public void setStrikeOut(int strikeOut){
        this.strikeOut = this.strikeOut + strikeOut;
    }

    public int getDoublePlay(){
        return this.doublePlay;
    }

    public void setDoublePlay(int doublePlay){
        this.doublePlay = this.doublePlay + doublePlay;
    }

    public BigDecimal getSluggingAvg(){
        return this.sluggingAvg;
    }

    public void setSluggingAvg(){
        this.sluggingAvg = BigDecimal.valueOf(totalBases/atBat).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getOnBasePercentage(){
        return this.onBasePercentage;
    }
    public void setOnBasePercentage(){
        this.onBasePercentage = BigDecimal.valueOf((this.hit+this.basesOnBalls+this.hitByPitch)/(this.atBat+this.basesOnBalls+this.hitByPitch+this.sacrificeFlies)).setScale(3, BigDecimal.ROUND_HALF_UP);
        //출루율 = (안타+볼넷+몸에 맞은 공) / (타수+볼넷+몸에 맞은 공+희생플라이)
    }

    public int getErrors(){
        return this.errors;
    }
    public void setErrors(int errors){
        this.errors = errors;
    }

    public int getMultiHits(){
        return this.multiHits;
    }
    public void setMultiHits(int multiHits){
        this.multiHits = multiHits;
    }

    public HitterRecord(){

    }

    public HitterRecord(int playerId, String season, boolean homeYn){
        RecordId recordId = new RecordId(playerId, season, homeYn);
        setId(recordId);
    }
}
