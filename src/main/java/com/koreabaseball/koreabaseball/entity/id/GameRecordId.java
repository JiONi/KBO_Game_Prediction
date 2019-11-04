package com.koreabaseball.koreabaseball.entity.id;

import com.koreabaseball.koreabaseball.entity.id.GameId;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Embeddable
public class GameRecordId implements Serializable {

    @EqualsAndHashCode.Include
    private GameId gameId;

    private int hitterId;               //타자 ID

    @Column(name="HITTER_RECORD_TURN")
    private int hitterRecordTurn;       //타자 기록 순번

    public GameId getId(){
        return this.gameId;
    }
    public void setId(GameId id){
        this.gameId = id;
    }
    public int getHitterId(){
        return this.hitterId;
    }
    public void setHitterId(int hitterId){
        this.hitterId = hitterId;
    }
    public int getHitterRecordTurn(){
        return this.hitterRecordTurn;
    }
    public void setHitterRecordTurn(int hitterRecordTurn){
        this.hitterRecordTurn = hitterRecordTurn;
    }

    public GameRecordId(GameId gameId, int hitterId, int hitterRecordTurn){
        this.gameId = gameId;
        this.hitterId = hitterId;
        this.hitterRecordTurn = hitterRecordTurn;
    }
}
