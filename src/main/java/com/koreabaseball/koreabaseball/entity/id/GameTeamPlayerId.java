package com.koreabaseball.koreabaseball.entity.id;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class GameTeamPlayerId implements Serializable {

    @EqualsAndHashCode.Include
    private GameId gameId;

    private int playerId;

    public GameId getId(){
        return this.gameId;
    }
    public void setId(GameId id){
        this.gameId = id;
    }
    public int getPlayerId() { return this.playerId; }
    public void setPlayerId(int playerId){ this.playerId = playerId; }

    public GameTeamPlayerId(GameId gameId, int playerId){
        this.gameId = gameId;
        this.playerId = playerId;
    }
    public GameTeamPlayerId(){

    }
}
