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
            ��� ���� : <input type="text" name="gameDate" id="datePicker"/>
        </div>
        <div style="margin-bottom:30px;">
            <select name="gameSect">
                <option value="">���� ���� ����</option>
                <option value="RS">���� ����</option>
                <option value="PS">����Ʈ ����</option>
            </select>
        </div>
        <h2>Ȩ �� ��� Ÿ�� ���</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('home', 'hitter')">Ȩ�� Ÿ�� �߰�</button>
            <button type="button" onclick="addInning()">��� �̴� �߰�</button>
        </div>
        <table id="homeTeamHitterRecordBoard">
            <tr>
                <td>
                    Ÿ��
                </td>
                <td>
                    ���� / �̴�
                </td>
                <c:forEach begin="1" end="9" varStatus="i">
                    <td>
                        ${i.current}ȸ
                    </td>
                </c:forEach>
                <td id="atBatCount" class="playerRecord">
                    Ÿ��
                </td>
                <td id="hitCount">
                    ��Ÿ
                </td>
                <td id="runsBattedIn">
                    Ÿ��
                </td>
                <td id="runs">
                    ����
                </td>
                <td>
                    ��å
                </td>
                <td>
                    ���� ����
                </td>
                <td>
                    ���� ����
                </td>
            </tr>
            <c:forEach begin="0" end="8" varStatus="i">
                <tr>
                    <td>
                        <input type="number" name="homeHitterList[${i.index}].hitterTurn" style="width:50px;" />
                    </td>
                    <td>
                        <select name="homeHitterList[${i.index}].playerId" id="homeTeamHitter">
                            <option value="">���� ����</option>
                            <c:forEach var="homeTeamHitter" items="${homeTeamHitter}" >
                                <option value="${homeTeamHitter.id}">${homeTeamHitter.name} (${homeTeamHitter.backNo})
                                    <c:if test="${homeTeamHitter.position eq 'IN_FIELDER'}">���߼�</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'OUT_FIELDER'}">�ܾ߼�</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'PTICHER'}">����</c:if>
                                    <c:if test="${homeTeamHitter.position eq 'CATCHER'}">����</c:if></option>
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
        <h2>���� �� ��� Ÿ�� ���</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('away','hitter')">������ Ÿ�� �߰�</button>
            <button type="button" onclick="addInning()">��� �̴� �߰�</button>
        </div>
        <table id="awayTeamHitterRecordBoard">
            <tr>
                <td>
                    Ÿ��
                </td>
                <td>
                    ���� / �̴�
                </td>
                <c:forEach begin="1" end="9" varStatus="i">
                    <td>
                        ${i.current}ȸ
                    </td>
                </c:forEach>
                <td class="playerRecord">
                    Ÿ��
                </td>
                <td>
                    ��Ÿ
                </td>
                <td>
                    Ÿ��
                </td>
                <td>
                    ����
                </td>
                <td>
                    ��å
                </td>
                <td>
                    ���� ����
                </td>
                <td>
                    ���� ����
                </td>
            </tr>
            <c:forEach begin="0" end="8" varStatus="i">
                <tr>
                    <td>
                        <input type="number" name="awayHitterList[${i.index}].hitterTurn" style="width:50px;" />
                    </td>
                    <td>
                        <select name="awayHitterList[${i.index}].playerId">
                            <option value="">���� ����</option>
                            <c:forEach var="awayTeamHitter" items="${awayTeamHitter}" >
                                <option value="${awayTeamHitter.id}">${awayTeamHitter.name} (${awayTeamHitter.backNo})
                                    <c:if test="${awayTeamHitter.position eq 'IN_FIELDER'}">���߼�</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'OUT_FIELDER'}">�ܾ߼�</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'PTICHER'}">����</c:if>
                                    <c:if test="${awayTeamHitter.position eq 'CATCHER'}">����</c:if></option>
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
        <h2>Ȩ �� ��� ���� ���</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('home','pitcher')">Ȩ�� ���� �߰�</button>
        </div>
        <table id="homeTeamPitcherRecordBoard">
            <tr>
                <td>������</td>
                <td>����</td>
                <td>���</td>
                <td>�̴�</td>
                <td>Ÿ��</td>
                <td>������</td>
                <td>Ÿ��</td>
                <td>�Ǿ�Ÿ</td>
                <td>Ȩ��</td>
                <td>4�籸</td>
                <td>����</td>
                <td>����</td>
                <td>��å</td>
            </tr>
            <tr>
                <td>
                    <select name="homePitcherList[0].playerId">
                        <option value="">���� ����</option>
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
        <h2>���� �� ��� ���� ���</h2>
        <div style="margin-bottom:20px;">
            <button type="button" onclick="addPlayer('away','pitcher')">������ ���� �߰�</button>
        </div>
        <table id="awayTeamPitcherRecordBoard">
            <tr>
                <td>������</td>
                <td>����</td>
                <td>���</td>
                <td>�̴�</td>
                <td>Ÿ��</td>
                <td>������</td>
                <td>Ÿ��</td>
                <td>�Ǿ�Ÿ</td>
                <td>Ȩ��</td>
                <td>4�籸</td>
                <td>����</td>
                <td>����</td>
                <td>��å</td>
            </tr>
            <tr>
                <td>
                    <select name="awayPitcherList[0].playerId">
                        <option value="">���� ����</option>
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
        <button type="button" onclick="goSaveRecord();">���Ӱ�� �����ϱ�</button>
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
                alert("���� ����");
            },
            error : function(){
                alert("����");
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
            alert("���Խ����� �ִ� 12ȸ���� ����˴ϴ�.");
            return false;
        }
        totalInning += 1;
        $("#homeTeamHitterRecordBoard tr").each(function(index){
            if(index == 0){
                $(this).find(".playerRecord").before("<td>"+totalInning+"ȸ</td>");
            }else{
                $(this).find(".playerRecord").before("<td><input type='text' name='homeHitterList["+(index-1)+"].atBat' value='' style='width:60px;'></td>");
            }
        });
        $("#awayTeamHitterRecordBoard tr").each(function(index){
            if(index == 0){
                $(this).find(".playerRecord").before("<td>"+totalInning+"ȸ</td>");
            }else{
                $(this).find(".playerRecord").before("<td><input type='text' name='awayHitterList["+(index-1)+"].atBat' value='' style='width:60px;'></td>");
            }
        })
    }
</script>