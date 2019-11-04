package com.koreabaseball.koreabaseball.DTO;

import com.koreabaseball.koreabaseball.entity.GameTeamHitter;
import com.koreabaseball.koreabaseball.entity.GameTeamRecord;
import com.koreabaseball.koreabaseball.entity.HitterRecord;
import com.koreabaseball.koreabaseball.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameHitterRecordDTO {

    private List<GameTeamRecord> gameTeamRecordList = new ArrayList<>();
    private HitterRecord hitterRecord;
    private GameTeamHitter gameTeamHitter;
    private Player player;

    public List<GameTeamRecord> getGameTeamRecordList() { return this.gameTeamRecordList; }
    public void setGameTeamRecordList(List<GameTeamRecord> gameTeamRecordList) { this.gameTeamRecordList = gameTeamRecordList; }
    public HitterRecord getHitterRecord(){ return this.hitterRecord; }
    public void setHitterRecord(HitterRecord hitterRecord) { this.hitterRecord = hitterRecord; }
    public GameTeamHitter getGameTeamHitter() { return this.gameTeamHitter; }
    public void setGameTeamHitter(GameTeamHitter gameTeamHitter) { this.gameTeamHitter = gameTeamHitter; }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) { this.player = player; }

    public GameHitterRecordDTO(HitterRecord hitterRecord, GameTeamHitter gameTeamHitter){
        this.hitterRecord = hitterRecord;
        this.gameTeamHitter = gameTeamHitter;
    }

    public GameHitterRecordDTO(HitterRecord hitterRecord, List<GameTeamRecord> gameTeamRecordList){
        this.hitterRecord = hitterRecord;
        this.gameTeamRecordList = gameTeamRecordList;
    }

    public GameHitterRecordDTO(Player player){
        this.player = player;
    }
}
