package com.koreabaseball.koreabaseball.job;

import com.koreabaseball.koreabaseball.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaPagingItemReaderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private static final int chunkSize = 1000;

    @Bean
    public Job gameTeamHitterJob() {
        return jobBuilderFactory.get("gameTeamHitterJob")
                .start(gameTeamRecordHitterStep())
                .next(gameTeamRecordPitcherStep())
                .build();
    }

    /*@Bean
    public Step gameTeamHitterStep() {
        return stepBuilderFactory.get("gameTeamHitterStep")
                .<GameHitterRecordDTO, HitterRecord>chunk(chunkSize)
                .reader(gameTeamHitterReader(null))
                .processor(gameTeamHitterSetProcessor())
                .writer(hitterRecordWriter())
                .build();
    }*/

    @Bean
    public Step gameTeamRecordHitterStep() {
        return stepBuilderFactory.get("gameTeamRecordHitterStep")
                .<Player, HitterRecord>chunk(chunkSize)
                .reader(playerHitterListReader(null))
                .processor(hitterRecordSetProcessor(null))
                .writer(hitterRecordWriter())
                .build();
    }

    @Bean
    public Step gameTeamRecordPitcherStep() {
        return stepBuilderFactory.get("gameTeamRecordPitcherStep")
                .<Player, PitcherRecord>chunk(chunkSize)
                .reader(playerPitcherListReader(null))
                .processor(pitcherRecordSetProcessor(null))
                .writer(pitcherRecordWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Player> playerHitterListReader(@Value("#{jobParameters[requestDate]}") String requestDate) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("requestDate", requestDate);

        String queryString = String.format("SELECT p " +
                "FROM Player AS p " +
                "JOIN p.gameTeamRecords gtr ON gtr.id.gameId.gameDate = :requestDate GROUP BY p.id");


        return new JpaPagingItemReaderBuilder<Player>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString(queryString)
                .parameterValues(parameters)
                .build();

    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Player> playerPitcherListReader(@Value("#{jobParameters[requestDate]}") String requestDate) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("requestDate", requestDate);

        String queryString = String.format("SELECT p " +
                "FROM Player AS p " +
                "JOIN p.gameTeamRecordsPitcher gtr ON gtr.id.gameId.gameDate = :requestDate GROUP BY p.id");


        return new JpaPagingItemReaderBuilder<Player>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString(queryString)
                .parameterValues(parameters)
                .build();

    }

    @Bean
    @StepScope
    public ItemProcessor<Player, HitterRecord> hitterRecordSetProcessor(@Value("#{jobParameters[requestDate]}") String requestDate){
        return player -> {

            List<GameTeamRecord> gameTeamRecord = player.getGameTeamRecordByGameDate(requestDate);
            GameTeam gameTeam = gameTeamRecord.get(0).getGameTeam();
            GameTeamHitter gameTeamHitter = player.getGameTeamHitterByGameDate(requestDate);
            HitterRecord hitterRecord = player.getHitterRecordBySeasonAndHomeYn(gameTeam.getSeasonCode(), gameTeam.getHomeYn());

            hitterRecord.setGameCount();
            hitterRecord.setAtBat(gameTeamHitter.getAtBat());
            hitterRecord.setErrors(gameTeamHitter.getErrors());
            hitterRecord.setHit(gameTeamHitter.getHit());
            hitterRecord.setRuns(gameTeamHitter.getRuns());
            hitterRecord.setRunsBattedIn(gameTeamHitter.getRunsBattedIn());
            hitterRecord.setCaughtStealing(gameTeamHitter.getCaughtStealing());
            hitterRecord.setStolenBase(gameTeamHitter.getStolenBase());
            hitterRecord.setStolenBaseAttepted(gameTeamHitter.getStolenBase() + gameTeamHitter.getCaughtStealing());

            int totalBase = 0;
            int totalHit = 0;
            int pa = 0;
            for(GameTeamRecord record : gameTeamRecord){
                String pitchPosition = record.getPitcher().getPitchPosition();
                boolean atBatYn = true;
                boolean hitYn = false;

                if("SCF".equals(record.getResult())){
                    hitterRecord.setSacrificeFlies(1);
                    atBatYn = false;
                }else if("SCB".equals(record.getResult())){
                    hitterRecord.setSacrificeBunts(1);
                    atBatYn = false;
                }else if("TWOBASE".equals(record.getResult())){
                    hitterRecord.setTwoBaseHit(1);
                    totalBase += 2;
                    totalHit++;
                    hitYn = true;
                }else if("THREEBASE".equals(record.getResult())){
                    hitterRecord.setThreeBaseHit(1);
                    totalBase += 3;
                    totalHit++;
                    hitYn = true;
                }else if("BASESONBALLS".equals(record.getResult())){
                    hitterRecord.setBasesOnBalls(1);
                    atBatYn = false;
                }else if("HITBYPITCHED".equals(record.getResult())){
                    hitterRecord.setHitByPitch(1);
                    atBatYn = false;
                }else if("STRIKEOUT".equals(record.getResult())){
                    hitterRecord.setStrikeOut(1);
                }else if("DOUBLEPLAY".equals(record.getResult())) {
                    hitterRecord.setDoublePlay(1);
                }else if("HOMERUN".equals(record.getResult())){
                    hitterRecord.setHomerun(1);
                    totalBase += 4;
                    totalHit++;
                    hitYn = true;
                }else if("INTENTBALL".equals(record.getResult())){
                    hitterRecord.setIntentionalBalls(1);
                }else if("HIT".equals(record.getResult())){
                    totalBase += 1;
                    totalHit++;
                    hitYn = true;
                }else if("ERROR".equals(record.getResult())){
                    atBatYn = false;
                }
                pa++;

                if(atBatYn == true){
                    if("LEFT_OVER".equals(pitchPosition)){
                        hitterRecord.setLeftAtBat(1);
                    }else if("RIGHT_OVER".equals(pitchPosition)){
                        hitterRecord.setRightAtBat(1);
                    }else if("LEFT_UNDER".equals(pitchPosition) || "RIGHT_UNDER".equals(pitchPosition)){
                        hitterRecord.setUnderAtBat(1);
                    }
                    if(hitYn == true){
                        if("LEFT_OVER".equals(pitchPosition)){
                            hitterRecord.setLeftHit(1);
                        }else if("RIGHT_OVER".equals(pitchPosition)){
                            hitterRecord.setRightHit(1);
                        }else if("LEFT_UNDER".equals(pitchPosition) || "RIGHT_UNDER".equals(pitchPosition)){
                            hitterRecord.setUnderHit(1);
                        }
                    }
                }
            }

            hitterRecord.setTotalBases(totalBase);
            if(totalHit >= 2){
                hitterRecord.setMultiHits(1);
            }
            hitterRecord.setPlateAppearance(pa);
            hitterRecord.setAvg();              //타율 계산
            hitterRecord.setLeftAvg();          //좌투 타율 계산
            hitterRecord.setRightAvg();         //우투 타율 계산
            hitterRecord.setUnderAvg();         //언더 타율 계산
            hitterRecord.setSluggingAvg();      //장타율 계산
            hitterRecord.setOnBasePercentage(); //출루율 계산

            return hitterRecord;
        };
    }

    @Bean
    @StepScope
    public ItemProcessor<Player, PitcherRecord> pitcherRecordSetProcessor(@Value("#{jobParameters[requestDate]}") String requestDate){
        return player -> {
            List<GameTeamRecord> gameTeamRecordList = player.getGameTeamRecordPitcherByGameDate(requestDate);
            GameTeam gameTeam = gameTeamRecordList.get(0).getGameTeam();
            GameTeamPitcher gameTeamPitcher = player.getGameTeamPitcherByGameDate(requestDate);
            PitcherRecord pitcherRecord = player.getPitcherRecordBySeasonAndHomeYn(gameTeam.getSeasonCode(), !gameTeam.getHomeYn());

            pitcherRecord.setAtBat(gameTeamPitcher.getAtBat());
            pitcherRecord.setEarnedRuns(gameTeamPitcher.getEarnedRuns());
            pitcherRecord.setNumbersOfPitches(gameTeamPitcher.getNumbersOfPitched());
            pitcherRecord.setGameCount();
            pitcherRecord.setRuns(gameTeamPitcher.getRuns());
            pitcherRecord.setHit(gameTeamPitcher.getHit());
            pitcherRecord.setHomerun(gameTeamPitcher.getHomerun());
            pitcherRecord.setInningsPitched(gameTeamPitcher.getInningsPitched());
            pitcherRecord.setTotalBattersFaced(gameTeamPitcher.getBattersFaced());
            pitcherRecord.setStrikeOut(gameTeamPitcher.getStrikeOut());

            if("승".equals(gameTeamPitcher.getGameResult())){
                pitcherRecord.setWinCount(1);
            }else if("패".equals(gameTeamPitcher.getGameResult())){
                pitcherRecord.setLoseCount(1);
            }else if("홀드".equals(gameTeamPitcher.getGameResult())){
                pitcherRecord.setHoldCount(1);
            }else if("세".equals(gameTeamPitcher.getGameResult())){
                pitcherRecord.setSaveCount(1);
            }

            //선발 투수가 6이닝 이상 자책점 3점 이하로 실점하면 퀄리티스타트
            if(gameTeamPitcher.getAppearanceInning() == 1 && gameTeamPitcher.getFirstFacedTurn() == 1){
                if(gameTeamPitcher.getInningsPitched().compareTo(BigDecimal.valueOf(6)) == 1 || gameTeamPitcher.getInningsPitched().compareTo(BigDecimal.valueOf(6)) == 0){
                    if(gameTeamPitcher.getEarnedRuns() <= 3){
                        pitcherRecord.setQualityStart(1);
                    }
                }
            }


            for(GameTeamRecord record : gameTeamRecordList){
                String hitPosition = record.getHitter().getHitPosition();
                boolean atBatYn = true;
                boolean hitYn = false;

                if("SCF".equals(record.getResult()) || "SCB".equals(record.getResult())){
                    atBatYn = false;
                }else if("HIT".equals(record.getResult()) || "HOMERUN".equals(record.getResult())){
                    hitYn = true;
                }else if("TWOBASE".equals(record.getResult())){
                    pitcherRecord.setTwoBaseHit(1);
                    hitYn = true;
                }else if("THREEBASE".equals(record.getResult())){
                    pitcherRecord.setThreeBaseHit(1);
                    hitYn = true;
                }else if("INTENTBALL".equals(record.getResult())){
                    pitcherRecord.setIntentionalBalls(1);
                    atBatYn = false;
                }else if("BASESONBALLS".equals(record.getResult())){
                    pitcherRecord.setBasesOnBalls(1);
                    atBatYn = false;
                }else if("HITBYPITCHED".equals(record.getResult())){
                    atBatYn = false;
                }else if("ERROR".equals(record.getResult())){
                    atBatYn = false;
                }

                if(atBatYn == true){
                    if("LEFT".equals(hitPosition)){
                        pitcherRecord.setLeftAtBat(1);
                    }else if("RIGHT".equals(hitPosition)){
                        pitcherRecord.setRightAtBat(1);
                    }else if("SWITCH".equals(hitPosition)){
                        pitcherRecord.setSwitchAtBat(1);
                    }
                    if(hitYn == true){
                        if("LEFT".equals(hitPosition)){
                            pitcherRecord.setLeftHit(1);
                        }else if("RIGHT".equals(hitPosition)){
                            pitcherRecord.setRightHit(1);
                        }else if("SWITCH".equals(hitPosition)){
                            pitcherRecord.setSwitchHit(1);
                        }
                    }
                }
            }

            pitcherRecord.setAvg();                 //피안타율 계산
            pitcherRecord.setLeftAvg();             //좌타 상대 피안타율 계산
            pitcherRecord.setRightAvg();            //우타 상대 피안타율 계산
            pitcherRecord.setSwitchAvg();           //스위치 타자 상대 피안타율 계산
            pitcherRecord.setEra();                 //평균자책점 계산
            pitcherRecord.setWinningPercentage();   //승률 계산
            pitcherRecord.setWhip();                //WHIP 계산 (이닝당 안타+볼넷 허용률)

            return pitcherRecord;
        };
    }

    @Bean
    public JpaItemWriter<HitterRecord> hitterRecordWriter() {
        JpaItemWriter<HitterRecord> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

    @Bean
    public JpaItemWriter<PitcherRecord> pitcherRecordWriter(){
        JpaItemWriter<PitcherRecord> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}
