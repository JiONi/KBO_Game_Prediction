<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html lang="ko">
<head>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <script src="/static/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/static/css/jquery-ui.min.css">
</head>
<body>
<div id="area">
    <form id="recordTable">
        <input type="hidden" name="homeTeam" value="${homeTeam}"/>
        <input type="hidden" name="awayTeam" value="${awayTeam}"/>
        <div>
            경기 일자 : <input type="text" name="gameDate" id="datePicker"/>
        </div>
        <div style="margin-bottom:30px;">
            <select name="gameSect">
                <option value="">시즌 구분 선택</option>
                <option value="RS">정규 시즌</option>
                <option value="PS">포스트 시즌</option>
            </select>
        </div>
        <h2>홈 팀 경기 타자 기록</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('home', 'hitter')">홈팀 타자 추가</button>
            <button type="button" onclick="addInning()">경기 이닝 추가</button>
        </div>
        <table id="homeTeamHitterRecordBoard">
            <tr>
                <td>
                    타순
                </td>
                <td>
                    선수 / 이닝
                </td>
                <c:forEach begin="1" end="9" varStatus="i">
                    <td>
                        ${i.current}회
                    </td>
                </c:forEach>
                <td id="atBatCount" class="playerRecord">
                    타수
                </td>
                <td id="hitCount">
                    안타
                </td>
                <td id="runsBattedIn">
                    타점
                </td>
                <td id="runs">
                    득점
                </td>
                <td>
                    실책
                </td>
                <td>
                    도루 성공
                </td>
                <td>
                    도루 실패
                </td>
            </tr>
            <c:forEach begin="0" end="8" varStatus="i">
                <tr>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].hitterTurn" style="width:50px;" />
                    </td>
                    <td>
                        <select name="homeHitterList[${i.index}].playerId" id="homeTeamHitter">
                            <option value="">선수 선택</option>
                            <c:forEach var="homeTeamHitter" items="${homeTeamHitter}" >
                                <option value="${homeTeamHitter.id}">${homeTeamHitter.name} (${homeTeamHitter.backNo})
                                    <c:if test="${homeTeamHitter.position eq 'IN_FIELDER'}">내야수</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'OUT_FIELDER'}">외야수</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'PTICHER'}">투수</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'CATCHER'}">포수</c:if></option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:forEach begin="0" end="8">
                        <td>
                            <input type="text" name="homeHitterList[${i.index}].atBat" value="" style="width:60px;"/>
                        </td>
                    </c:forEach>
                    <td class="playerRecord">
                        <input type="number" name="homeHitterList[${i.index}].atBatCount" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].hit" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].runsBattedIn" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].runs" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].errors" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].stolenBase" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].caughtStealing" value="" style="width:60px;"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>원정 팀 경기 타자 기록</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('away','hitter')">원정팀 타자 추가</button>
            <button type="button" onclick="addInning()">경기 이닝 추가</button>
        </div>
        <table id="awayTeamHitterRecordBoard">
            <tr>
                <td>
                    타순
                </td>
                <td>
                    선수 / 이닝
                </td>
                <c:forEach begin="1" end="9" varStatus="i">
                    <td>
                        ${i.current}회
                    </td>
                </c:forEach>
                <td class="playerRecord">
                    타수
                </td>
                <td>
                    안타
                </td>
                <td>
                    타점
                </td>
                <td>
                    득점
                </td>
                <td>
                    실책
                </td>
                <td>
                    도루 성공
                </td>
                <td>
                    도루 실패
                </td>
            </tr>
            <c:forEach begin="0" end="8" varStatus="i">
                <tr>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].hitterTurn" style="width:50px;" />
                    </td>
                    <td>
                        <select name="awayHitterList[${i.index}].playerId">
                            <option value="">선수 선택</option>
                            <c:forEach var="awayTeamHitter" items="${awayTeamHitter}" >
                                <option value="${awayTeamHitter.id}">${awayTeamHitter.name} (${awayTeamHitter.backNo})
                                    <c:if test="${awayTeamHitter.position eq 'IN_FIELDER'}">내야수</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'OUT_FIELDER'}">외야수</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'PTICHER'}">투수</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'CATCHER'}">포수</c:if></option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:forEach begin="0" end="8">
                        <td>
                            <input type="text" name="awayHitterList[${i.index}].atBat" value="" style="width:60px;"/>
                        </td>
                    </c:forEach>
                    <td class="playerRecord">
                        <input type="number" name="awayHitterList[${i.index}].atBatCount" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].hit" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].runsBattedIn" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].runs" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].errors" value="" style="width:60px;" />
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].stolenBase" value="" style="width:60px;"/>
                    </td>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].caughtStealing" value="" style="width:60px;"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>홈 팀 경기 투수 기록</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('home','pitcher')">홈팀 투수 추가</button>
        </div>
        <table id="homeTeamPitcherRecordBoard">
            <tr>
                <td>선수명</td>
                <td>등판</td>
                <td>결과</td>
                <td>이닝</td>
                <td>타자</td>
                <td>투구수</td>
                <td>타수</td>
                <td>피안타</td>
                <td>홈런</td>
                <td>4사구</td>
                <td>삼진</td>
                <td>실점</td>
                <td>자책</td>
            </tr>
            <tr>
                <td>
                    <select name="homePitcherList[0].playerId">
                        <option value="">선수 선택</option>
                        <c:forEach var="player" items="${homeTeamPitcher}">
                            <option value="${player.id}">${player.name} (${player.backNo})</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="homePitcherList[0].appearance" style="width:60px;" value=""/></td>
                <td><input type="text" name="homePitcherList[0].result" style="width:60px;" value=""/></td>
                <td><input type="text" name="homePitcherList[0].innings" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].battersFaced" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].numbersOfPitches" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].atBatCount" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].hit" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].homerun" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].basesOnBalls" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].strikeOut" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].runs" style="width:60px;" value=""/></td>
                <td><input type="number" name="homePitcherList[0].earnedRuns" style="width:60px;" value=""/></td>
            </tr>
        </table>
        <h2>원정 팀 경기 투수 기록</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('away','pitcher')">원정팀 투수 추가</button>
        </div>
        <table id="awayTeamPitcherRecordBoard">
            <tr>
                <td>선수명</td>
                <td>등판</td>
                <td>결과</td>
                <td>이닝</td>
                <td>타자</td>
                <td>투구수</td>
                <td>타수</td>
                <td>피안타</td>
                <td>홈런</td>
                <td>4사구</td>
                <td>삼진</td>
                <td>실점</td>
                <td>자책</td>
            </tr>
            <tr>
                <td>
                    <select name="awayPitcherList[0].playerId">
                        <option value="">선수 선택</option>
                        <c:forEach var="player" items="${awayTeamPitcher}">
                            <option value="${player.id}">${player.name} (${player.backNo})</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="awayPitcherList[0].appearance" style="width:60px;" value=""/></td>
                <td><input type="text" name="awayPitcherList[0].result" style="width:60px;" value="" /></td>
                <td><input type="text" name="awayPitcherList[0].innings" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].battersFaced" style="width:60px;" value="" /></td>
                <td><input type="number" name="awayPitcherList[0].numbersOfPitches" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].atBatCount" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].hit" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].homerun" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].basesOnBalls" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].strikeOut" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].runs" style="width:60px;" value=""/></td>
                <td><input type="number" name="awayPitcherList[0].earnedRuns" style="width:60px;" value=""/></td>
            </tr>
        </table>
        <button type="button" onclick="goSaveRecord();">게임결과 저장하기</button>
    </form>
</div>
</body>

</html>

<script>
    $("#datePicker").datepicker({
        dateFormat: 'yy-mm-dd'
    });

    $('#datePicker').datepicker('setDate', 'today');

    var totalInning = 9;
    var totalHomeTeamHitter = 8;
    var totalAwayTeamHitter = 8;
    var totalHomeTeamPitcher = 0;
    var totalAwayTeamPitcher = 0;
    function goSaveRecord(){
        $.ajax({
            url : '/saveGameRecordHitter',
            type : 'post',
            data : $("#recordTable").serialize(),
            success : function(){
                alert("저장 성공");
            },
            error : function(){
                alert("실패");
            }
        })
    }

    function addPlayer(homeAway, playerSect){
        if(playerSect == 'hitter'){
            $("#"+homeAway+"TeamHitterRecordBoard").append($("#"+homeAway+"TeamHitterRecordBoard tr").eq(1).clone());
            if(homeAway == 'home'){
                totalHomeTeamHitter++;
                var totalPlayerIndex = totalHomeTeamHitter;
            }else{
                totalAwayTeamHitter++;
                var totalPlayerIndex = totalAwayTeamHitter;
            }
            var elm = $("#"+homeAway+"TeamHitterRecordBoard tr").last();
            elm.find("[name='"+homeAway+"HitterList[0].hitterTurn']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].hitterTurn");
            elm.find("[name='"+homeAway+"HitterList[0].playerId']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].playerId");
            elm.find("[name='"+homeAway+"HitterList[0].atBat']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].atBat");
            elm.find("[name='"+homeAway+"HitterList[0].atBatCount']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].atBatCount");
            elm.find("[name='"+homeAway+"HitterList[0].hit']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].hit");
            elm.find("[name='"+homeAway+"HitterList[0].runsBattedIn']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].runsBattedIn");
            elm.find("[name='"+homeAway+"HitterList[0].runs']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].runs");
            elm.find("[name='"+homeAway+"HitterList[0].errors']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].errors");
            elm.find("[name='"+homeAway+"HitterList[0].stolenBase']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].stolenBase");
            elm.find("[name='"+homeAway+"HitterList[0].caughtStealing']").attr("name",homeAway+"HitterList["+totalPlayerIndex+"].caughtStealing");
            elm.find("input").val("");
        }else{
            $("#"+homeAway+"TeamPitcherRecordBoard").append($("#"+homeAway+"TeamPitcherRecordBoard tr").eq(1).clone());
            if(homeAway == 'home'){
                totalHomeTeamPitcher++;
                var totalPlayerIndex = totalHomeTeamPitcher;
            }else{
                totalAwayTeamPitcher++;
                var totalPlayerIndex = totalAwayTeamPitcher;
            }
            var elm = $("#"+homeAway+"TeamPitcherRecordBoard tr").last();
            elm.find("[name='"+homeAway+"PitcherList[0].playerId']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].playerId");
            elm.find("[name='"+homeAway+"PitcherList[0].appearance']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].appearance");
            elm.find("[name='"+homeAway+"PitcherList[0].innings']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].innings");
            elm.find("[name='"+homeAway+"PitcherList[0].result']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].result");
            elm.find("[name='"+homeAway+"PitcherList[0].battersFaced']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].battersFaced");
            elm.find("[name='"+homeAway+"PitcherList[0].numbersOfPitches']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].numbersOfPitches");
            elm.find("[name='"+homeAway+"PitcherList[0].atBatCount']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].atBatCount");
            elm.find("[name='"+homeAway+"PitcherList[0].hit']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].hit");
            elm.find("[name='"+homeAway+"PitcherList[0].homerun']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].homerun");
            elm.find("[name='"+homeAway+"PitcherList[0].basesOnBalls']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].basesOnBalls");
            elm.find("[name='"+homeAway+"PitcherList[0].strikeOut']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].strikeOut");
            elm.find("[name='"+homeAway+"PitcherList[0].runs']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].runs");
            elm.find("[name='"+homeAway+"PitcherList[0].earnedRuns']").attr("name", homeAway+"PitcherList["+totalPlayerIndex+"].earnedRuns");

        }
    }

    function addInning(){
        if(totalInning == 12){
            alert("정규시즌은 최대 12회까지 진행됩니다.");
            return false;
        }
        totalInning += 1;
        $("#homeTeamHitterRecordBoard tr").each(function(index){
            if(index == 0){
                $(this).find(".playerRecord").before("<td>"+totalInning+"회</td>");
            }else{
                $(this).find(".playerRecord").before("<td><input type='text' name='homeHitterList["+(index-1)+"].atBat' value='' style='width:60px;'></td>");
            }
        });
        $("#awayTeamHitterRecordBoard tr").each(function(index){
            if(index == 0){
                $(this).find(".playerRecord").before("<td>"+totalInning+"회</td>");
            }else{
                $(this).find(".playerRecord").before("<td><input type='text' name='awayHitterList["+(index-1)+"].atBat' value='' style='width:60px;'></td>");
            }
        })
    }
</script>