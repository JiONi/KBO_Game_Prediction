package com.koreabaseball.koreabaseball.entity;

import com.koreabaseball.koreabaseball.entity.id.GameTeamPlayerId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PLAYER")
public class Player {

    @Id
    @Column(name = "PLAYER_ID")
    private int id;  //선수 ID

    @Column(name = "PLAYER_NAME")
    private String name; //선수 이름

    @Column(name = "PLAYER_POSITION")
    private String position;    //선수 포지션. CATCHER : 포수, PICTHER : 투수, OUT_FIELDER : 외야수, IN_FIELDER : 내야수

    @Column(name = "PLAYER_BIRTH_YEAR")
    private int birthYear; //선수 생년

    @Column(name = "PLAYER_BIRTH_MONTH")
    private int birthMonth; //선수 생월

    @Column(name = "PLAYER_BIRTH_DAY")
    private int birthDay; //선수 생일

    @Column(name = "PLAYER_BACK_NO", columnDefinition = "integer default null")
    private int backNo;     //선수 등번호

    @Column(name = "PLAYER_HEIGHT")
    private int height;     //선수 키

    @Column(name = "PLAYER_WEIGHT")
    private int weight;     //선수 몸무게

    @Column(name = "PLAYER_HIT_POSITION")
    private String hitPosition;     //타석 포지션. LEFT : 좌타, RIGHT : 우타, SWITCH : 양타

    @Column(name = "PLAYER_PITCH_POSITION")
    private String pitchPosition;   //피칭 포지션. LEFT_OVER : 좌완, RIGHT_OVER : 우완, LEFT_UNDER : 좌언, RIGHT_UNDER : 우언

    @Column(name = "PLAYER_SECT_CODE")
    private String sectCode;    //선수 구분 코드(P : 투수, H : 타자)

    @Column(name = "FIRST_STRING_YN", columnDefinition = "boolean default false")
    private boolean firstStringYn;      //1군 선수 여부

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;  //소속 팀

    @OneToMany(mappedBy = "player")
    private List<PitcherRecord> pitcherRecords = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<HitterRecord> hitterRecords = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<GameTeamHitter> gameTeamHitters = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<GameTeamPitcher> gameTeamPitchers = new ArrayList<>();

    @OneToMany(mappedBy = "hitter")
    private List<GameTeamRecord> gameTeamRecords = new ArrayList<>();

    @OneToMany(mappedBy = "pitcher")
    private List<GameTeamRecord> gameTeamRecordsPitcher = new ArrayList<>();

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBirthDay(String birthDay){
        this.birthYear = Integer.parseInt(birthDay.split("년")[0]);
        this.birthMonth = Integer.parseInt(birthDay.split("년")[1].split("월")[0].trim());
        this.birthDay = Integer.parseInt(birthDay.split("월")[1].split("일")[0].trim());
    }
    public void setBackNo(int backNo){
        this.backNo = backNo;
    }
    public void setSectCode(String sectCode){
        this.sectCode = sectCode;
    }
    public void setTeam(Team team){
        this.team = team;
        team.getPlayers().add(this);
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setHitPosition(String hitPosition){
        this.hitPosition = hitPosition;
    }
    public void setPitchPosition(String pitchPosition){
        this.pitchPosition = pitchPosition;
    }
    public void setPosition(String position){ this.position = position; }

    public int getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getBirthDay(){
        String birthDay = this.birthYear+"."+this.birthMonth+"."+this.birthDay;
        return birthDay;
    }
    public int getBackNo() { return this.backNo; }
    public String getSectCode(){ return this.sectCode; }
    public Team getTeam() { return this.team; }
    public int getHeight() { return this.height; }
    public int getWeight() { return this.weight; }
    public String getPitchPosition() { return this.pitchPosition; }
    public String getPosition() { return this.position; }
    public String getHitPosition() { return this.hitPosition; }
    public List<HitterRecord> getHitterRecords() { return this.hitterRecords; }
    public void setHitterRecords(HitterRecord hitterRecord) { this.hitterRecords.add(hitterRecord); }
    public List<PitcherRecord> getPitcherRecords() { return this.pitcherRecords; }
    public void setPitcherRecords(PitcherRecord pitcherRecord) { this.pitcherRecords.add(pitcherRecord); }
    public List<GameTeamHitter> getGameTeamHitters() { return this.gameTeamHitters; }
    public void setGameTeamHitters(GameTeamHitter gameTeamHitter) { this.gameTeamHitters.add(gameTeamHitter); }
    public List<GameTeamPitcher> getGameTeamPitchers() { return this.gameTeamPitchers; }
    public void setGameTeamPitchers(GameTeamPitcher gameTeamPitcher) { this.gameTeamPitchers.add(gameTeamPitcher); }
    public List<GameTeamRecord> getGameTeamRecords() { return this.gameTeamRecords; }
    public void setGameTeamRecords(GameTeamRecord gameTeamRecord) { this.gameTeamRecords.add(gameTeamRecord); }
    public List<GameTeamRecord> getGameTeamRecordsPitcher() { return this.gameTeamRecordsPitcher; }
    public void setGameTeamRecordsPitcher(GameTeamRecord gameTeamrecordsPitcher) { this.gameTeamRecordsPitcher.add(gameTeamrecordsPitcher); }


    public HitterRecord getHitterRecordBySeasonAndHomeYn(String season, boolean homeYn){
        for(HitterRecord record : this.hitterRecords){
            if(record.getId().getSeason().equals(season)){
                if(record.getId().getHomeYn() == homeYn){
                    return record;
                }
            }
        }
        return null;
    }

    public PitcherRecord getPitcherRecordBySeasonAndHomeYn(String season, boolean homeYn){
        for(PitcherRecord record : this.pitcherRecords){
            if(record.getId().getSeason().equals(season)){
                if(record.getId().getHomeYn() == homeYn){
                    return record;
                }
            }
        }
        return null;
    }

    public List<GameTeamRecord> getGameTeamRecordByGameDate(String gameDate){
        List<GameTeamRecord> gameTeamRecordList = new ArrayList<>();
        for(GameTeamRecord record : this.gameTeamRecords){
            if(record.getId().getId().getGameDate().equals(gameDate)){
                gameTeamRecordList.add(record);
            }
        }
        return gameTeamRecordList;
    }

    public List<GameTeamRecord> getGameTeamRecordPitcherByGameDate(String gameDate){
        List<GameTeamRecord> gameTeamRecordList = new ArrayList<>();
        for(GameTeamRecord record : this.gameTeamRecordsPitcher){
            if(record.getId().getId().getGameDate().equals(gameDate)){
                gameTeamRecordList.add(record);
            }
        }
        return gameTeamRecordList;
    }

    public GameTeamHitter getGameTeamHitterByGameDate(String gameDate){
        for(GameTeamHitter gameTeamHitter : this.gameTeamHitters){
            if(gameTeamHitter.getId().getId().getGameDate().equals(gameDate)){
                return gameTeamHitter;
            }
        }
        return null;
    }

    public GameTeamPitcher getGameTeamPitcherByGameDate(String gameDate){
        for(GameTeamPitcher gameTeamPitcher : this.gameTeamPitchers){
            if(gameTeamPitcher.getId().getId().getGameDate().equals(gameDate)){
                return gameTeamPitcher;
            }
        }
        return null;
    }
}
