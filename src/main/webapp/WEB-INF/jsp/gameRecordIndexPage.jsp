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
            <option value="">Ȩ��</option>
            <c:forEach var="team" items="${teamList}">
                <option value="${team.id}">${team.shortName}</option>
            </c:forEach>
        </select>
        VS
        <select name="awayTeam">
            <option value="">������</option>
            <c:forEach var="team" items="${teamList}">
                <option value="${team.id}">${team.shortName}</option>
            </c:forEach>
        </select>
    </form>
</div>
<div>
    <button type="button" onclick="goRecord();" >��� ����Ϸ� ����</button>
</div>
</body>
</html>
<script>
    function goRecord(){
        var homeTeam = $("select[name='homeTeam']").val();
        var awayTeam = $("select[name='awayTeam']").val();

        if(homeTeam == ''){
            alert("Ȩ���� �������ּ���.");
            return;
        }
        if(awayTeam == ''){
            alert("�������� �������ּ���.");
            return;
        }
        if(homeTeam == awayTeam){
            alert("Ȩ���� �������� ���� �� �����ϴ�.");
            return;
        }

        $("#gameTeamForm").submit();
    }
</script>