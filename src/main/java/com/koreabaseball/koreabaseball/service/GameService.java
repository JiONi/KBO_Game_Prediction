package com.koreabaseball.koreabaseball.service;

import com.koreabaseball.koreabaseball.DTO.GameRecordPlayerDTO;
import com.koreabaseball.koreabaseball.entity.*;
import com.koreabaseball.koreabaseball.entity.id.GameId;
import com.koreabaseball.koreabaseball.entity.id.GamePlayerId;
import com.koreabaseball.koreabaseball.entity.id.GameRecordId;
import com.koreabaseball.koreabaseball.entity.id.GameTeamPlayerId;
import com.koreabaseball.koreabaseball.repository.GameRepository;
import com.koreabaseball.koreabaseball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class GameService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    GameRepository gameRepository;

    public void saveGameRecord(GameRecordPlayerDTO gameRecordPlayerDTO, String gameDate, String gameSect, String homeTeam, String awayTeam){
        GameTeam homeGameTeam = saveGameTeam(gameDate, gameSect, homeTeam, true);
        GameTeam awayGameTeam = saveGameTeam(gameDate, gameSect, awayTeam, false);

        saveGameRecordHitter(gameRecordPlayerDTO.getHomeHitterList(), gameDate, homeGameTeam);
        saveGameRecordHitter(gameRecordPlayerDTO.getAwayHitterList(), gameDate, awayGameTeam);

        saveGameRecordPitcher(gameRecordPlayerDTO.getHomePitcherList(), gameDate, homeGameTeam);
        saveGameRecordPitcher(gameRecordPlayerDTO.getAwayPitcherList(), gameDate, awayGameTeam);

        saveGameTeamRecord(gameRecordPlayerDTO.getHomeHitterList(), gameRecordPlayerDTO.getAwayPitcherList(), gameDate, homeTeam, homeGameTeam);
        saveGameTeamRecord(gameRecordPlayerDTO.getAwayHitterList(), gameRecordPlayerDTO.getHomePitcherList(), gameDate, awayTeam, awayGameTeam);
    }

    public GameTeam saveGameTeam(String gameDate, String gameSect, String teamId, boolean homeYn){
        GameTeam gameTeam = setGameTeam(gameDate, gameSect, teamId, homeYn);
        gameRepository.saveGameTeam(gameTeam);
        return gameTeam;
    }

    public void saveGameRecordHitter(List<GameRecordPlayerDTO> hitterList, String gameDate, GameTeam gameTeam){
        List<GameTeamHitter> gameTeamHitterList = new ArrayList<>();

        for(GameRecordPlayerDTO record : hitterList){
            gameTeamHitterList.add(setGameTeamHitter(record, gameDate, gameTeam));
        }
        gameRepository.saveGameTeamHitterList(gameTeamHitterList);
    }

    public void saveGameRecordPitcher(List<GameRecordPlayerDTO> pitcherList, String gameDate, GameTeam gameTeam){
        List<GameTeamPitcher> gameTeamPitcherList = new ArrayList<>();

        for(GameRecordPlayerDTO record : pitcherList){
            gameTeamPitcherList.add(setGameTeamPitcher(record, gameDate, gameTeam));
        }
        gameRepository.saveGameTeamPitcherList(gameTeamPitcherList);
    }

    public void saveGameTeamRecord(List<GameRecordPlayerDTO> hitterList, List<GameRecordPlayerDTO> pitcherList, String gameDate, String teamId, GameTeam gameTeam){
        List<GameTeamRecord> recordList = setGameTeamRecord(hitterList, pitcherList, gameDate, teamId, gameTeam);

        gameRepository.saveGameTeamRecordList(recordList);  //홈팀 레코드 저장
    }

    public List<GameTeamRecord> setGameTeamRecord(List<GameRecordPlayerDTO> hitterRecordList, List<GameRecordPlayerDTO> pitcherRecordList, String gameDate, String teamId, GameTeam gameTeam){
        int inning = 1;
        int hitterTurn = 1;
        int totalInning = hitterRecordList.get(0).getAtBat().size();
        int pitcherIndex = 0;
        int nextPitcherInning = Integer.parseInt(pitcherRecordList.get(pitcherIndex+1).getAppearance().split("\\.")[0]);
        int nextPitcherHitterTurn = Integer.parseInt(pitcherRecordList.get(pitcherIndex+1).getAppearance().split("\\.")[1]);
        int totalPitcher = pitcherRecordList.size();
        int totalRecordCount = 0;

        HashMap<Integer, GameRecordPlayerDTO> hitterTurnMap = new HashMap<>();
        List<GameTeamRecord> gameRecordList = new ArrayList<>();

        int putHitterTurn = 1;
        while(putHitterTurn < 10){
            for(GameRecordPlayerDTO hitter : hitterRecordList){
                if(putHitterTurn == hitter.getHitterTurn()){
                    hitterTurnMap.put(putHitterTurn, hitter);
                    break;
                }
            }
            putHitterTurn++;
        }

        while(inning <= totalInning){
            GameRecordPlayerDTO hitter = hitterTurnMap.get(hitterTurn);

            if("".equals(hitter.getAtBat().get(inning-1))){        //타순에 해당하는 선수에게 이닝 결과가 없다면 이닝이 종료 되었거나 선수 교체 된 상황.
                int nextHitter = hitterTurn+1;
                GameRecordPlayerDTO changeHitter = changeHitterTurnMap(hitter, hitterRecordList);

                if(nextHitter > 9){
                    nextHitter = 1;
                }

                if(changeHitter.getAtBat() != null && !"".equals(changeHitter.getAtBat().get(inning-1))){  //해당 타순에 교체된 선수에게 이닝 결과가 있는 경우에는 선수 교체 상황.
                    hitterTurnMap.replace(hitterTurn, changeHitter);
                    hitter = changeHitter;
                }else{                                                                                  //이닝 종료 된 상황
                    inning++;
                    continue;
                }
            }

            if((inning == nextPitcherInning && hitterTurn == nextPitcherHitterTurn) && pitcherIndex != totalPitcher-1){     //투수 교체 상황
                pitcherIndex++;
                if(pitcherIndex < totalPitcher-1){
                    nextPitcherInning = Integer.parseInt(pitcherRecordList.get(pitcherIndex+1).getAppearance().split("\\.")[0]);
                    nextPitcherHitterTurn = Integer.parseInt(pitcherRecordList.get(pitcherIndex+1).getAppearance().split("\\.")[1]);
                }
            }

            GameTeamRecord gameTeamRecord = new GameTeamRecord();
            gameTeamRecord.setId(setGameRecordId(gameDate, teamId, hitter.getPlayerId(), totalRecordCount));
            gameTeamRecord.setInning(inning);
            gameTeamRecord.setPitcher(playerRepository.findOne(pitcherRecordList.get(pitcherIndex).getPlayerId()));
            gameTeamRecord.setHitter(playerRepository.findOne(hitter.getPlayerId()));
            gameTeamRecord.setGameTeam(gameTeam);

            String atBat = hitter.getAtBat().get(inning-1);

            if(atBat.contains("/")){        //선수가 한 이닝에 2번 이상 등판한 경우
                hitter.getAtBat().set(inning-1, atBat.split("/")[1]);
                atBat = atBat.split("/")[0];
            }else{
                hitter.getAtBat().set(inning-1, "");
            }

            gameTeamRecord.setResult(getAtBatResult(atBat));

            gameRecordList.add(gameTeamRecord);
            totalRecordCount++;

            hitterTurn++;
            if(hitterTurn == 10){
                hitterTurn = 1;
            }
        }

        return gameRecordList;
    }

    private GameTeam setGameTeam(String gameDate, String gameSect, String teamId, boolean homeYn){
        GameTeam gameTeam = new GameTeam();
        GameId gameId = new GameId(gameDate,teamId);
        gameTeam.setId(gameId);
        gameTeam.setHomeYn(homeYn);
        gameTeam.setSeasonCode(gameDate.split("-")[0]+gameSect);

        int maxGameGroupTurn = gameRepository.getGameGroupTurn(gameDate);
        gameTeam.setGameGroupTurn(maxGameGroupTurn+1);

        return gameTeam;
    }

    private GameTeamPlayerId setGameTeamPlayerId(Player p, String gameDate){
        GameId gameId = new GameId(gameDate, p.getTeam().getId());
        GameTeamPlayerId gameTeamPlayerId = new GameTeamPlayerId(gameId, p.getId());
        return gameTeamPlayerId;
    }

    private GameRecordId setGameRecordId(String gameDate, String teamId, int hitterId, int hitterRecordTurn){
        GameId gameId = new GameId(gameDate,teamId);
        GameRecordId gameRecordId = new GameRecordId(gameId, hitterId, hitterRecordTurn);
        return gameRecordId;
    }

    private GameTeamHitter setGameTeamHitter(GameRecordPlayerDTO record, String gameDate, GameTeam gameTeam){
        Player player = playerRepository.findOne(record.getPlayerId());
        GameTeamHitter gameTeamHitter = new GameTeamHitter();

        gameTeamHitter.setId(setGameTeamPlayerId(player, gameDate));
        gameTeamHitter.setPlayer(player);
        gameTeamHitter.setHitTurn(record.getHitterTurn());
        gameTeamHitter.setAtBat(record.getAtBatCount());
        gameTeamHitter.setHit(record.getHit());
        gameTeamHitter.setRunsBattedIn(record.getRunsBattedIn());
        gameTeamHitter.setRuns(record.getRuns());
        gameTeamHitter.setErrors(record.getErrors());
        gameTeamHitter.setStolenBase(record.getStolenBase());
        gameTeamHitter.setCaughtStealing(record.getCaughtStealing());
        gameTeamHitter.setGameTeam(gameTeam);

        return gameTeamHitter;
    }

    private GameTeamPitcher setGameTeamPitcher(GameRecordPlayerDTO record, String gameDate, GameTeam gameTeam) {
        Player player = playerRepository.findOne(record.getPlayerId());

        GameTeamPitcher gameTeamPitcher = new GameTeamPitcher();
        gameTeamPitcher.setId(setGameTeamPlayerId(player, gameDate));
        gameTeamPitcher.setPlayer(player);
        gameTeamPitcher.setAppearanceInning(Integer.parseInt(record.getAppearance().split("\\.")[0]));
        gameTeamPitcher.setFirstFacedTurn(Integer.parseInt(record.getAppearance().split("\\.")[1]));
        gameTeamPitcher.setInningsPitched(BigDecimal.valueOf(Double.parseDouble(record.getInnings())));
        gameTeamPitcher.setBattersFaced(record.getBattersFaced());
        gameTeamPitcher.setNumbersOfPitched(record.getNumbersOfPitches());
        gameTeamPitcher.setAtBat(record.getAtBatCount());
        gameTeamPitcher.setHit(record.getHit());
        gameTeamPitcher.setHomerun(record.getHomerun());
        gameTeamPitcher.setBasesOnBalls(record.getBasesOnBalls());
        gameTeamPitcher.setStrikeOut(record.getStrikeOut());
        gameTeamPitcher.setRuns(record.getRuns());
        gameTeamPitcher.setEarnedRuns(record.getEarndRuns());
        gameTeamPitcher.setGameResult(record.getResult());
        gameTeamPitcher.setGameTeam(gameTeam);

        return gameTeamPitcher;
    }

    private String getAtBatResult(String atBat){
        String result = "";
        if(atBat.endsWith("안")){
            result = "HIT"; //안타
        }else if(atBat.endsWith("희비")){
            result = "SCF"; //희생 플라이
        }else if(atBat.endsWith("희번")){
            result = "SCB"; //희생 번트
        }else if(atBat.endsWith("2")){
            result = "TWOBASE";  //2루타
        }else if(atBat.endsWith("3")){
            result = "THREEBASE";   //3루타
        }else if(atBat.equals("4구")){
            result = "BASESONBALLS";    //볼넷
        }else if(atBat.equals("사구")){
            result = "HITBYPITCHED";    //몸에 맞는 볼
        }else if(atBat.equals("삼진")){
            result = "STRIKEOUT";       //삼진
        }else if(atBat.endsWith("병")){
            result = "DOUBLEPLAY";      //병살
        }else if(atBat.endsWith("홈")){
            result = "HOMERUN";         //홈런
        }else if(atBat.equals("고4")){
            result = "INTENTBALL";      //고의4구
        }else if(atBat.endsWith("실")){
            result = "ERROR";           //실책으로 출루
        }else{
            result = "OUT";
        }
        return result;
    }

    private GameRecordPlayerDTO changeHitterTurnMap(GameRecordPlayerDTO originalHitter, List<GameRecordPlayerDTO> hitterList){
        GameRecordPlayerDTO changeHitter = originalHitter;
        for(int i=0; i<hitterList.size(); i++){
            if(i < hitterList.size() -1){
                if(originalHitter.equals(hitterList.get(i)) && hitterList.get(i+1).getHitterTurn() == originalHitter.getHitterTurn()){
                    changeHitter = hitterList.get(i+1);
                    break;
                }
            }
        }
        return changeHitter;
    }
}
