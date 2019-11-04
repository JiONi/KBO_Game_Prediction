<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html lang="ko">
<head>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
<div id="area">
    <form action="/getGameRecordPage" id="gameTeamForm">
        <select name="homeTeam">
            <option value="">홈팀</option>
            <c:forEach var="team" items="${teamList}">
                <option value="${team.id}">${team.shortName}</option>
            </c:forEach>
        </select>
        VS
        <select name="awayTeam">
            <option value="">원정팀</option>
            <c:forEach var="team" items="${teamList}">
                <option value="${team.id}">${team.shortName}</option>
            </c:forEach>
        </select>
    </form>
</div>
<div>
    <button type="button" onclick="goRecord();" >경기 기록하러 가기</button>
</div>
</body>
</html>
<script>
    function goRecord(){
        var homeTeam = $("select[name='homeTeam']").val();
        var awayTeam = $("select[name='awayTeam']").val();

        if(homeTeam == ''){
            alert("홈팀을 선택해주세요.");
            return;
        }
        if(awayTeam == ''){
            alert("원정팀을 선택해주세요.");
            return;
        }
        if(homeTeam == awayTeam){
            alert("홈팀과 원정팀은 같을 수 없습니다.");
            return;
        }

        $("#gameTeamForm").submit();
    }
</script>