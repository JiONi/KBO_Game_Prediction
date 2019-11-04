<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html lang="ko">
<head>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
<div id="area">
    <input type="text" id="playerId" name="playerId" placeholder="선수 ID를 입력하세요." value=""/>
    <button id="playerInfoCrawling" onclick="crawlingCall()" value = "선수 정보 가져오기">선수 정보 가져오기</button>
    <button id="teamInfoSave" onclick="teamInfoSave()" >전체 팀 정보 저장하기</button>
</div>
<div style="margin-top:60px;">
    <select name="yearData">
        <option value="">년도 선택</option>
        <c:forEach begin="2000" end="2030" varStatus="i">
            <option value="${i.current}">${i.current}</option>
        </c:forEach>
    </select>
    <select name="seasonData">
        <option value="">시즌 선택</option>
        <option value="RS">정규 시즌</option>
        <option value="PS">포스트 시즌</option>
    </select>
    <button id="insertPlayerRecord" type="button" onclick="savePlayerRecord()">시즌 기록 셋팅</button>
</div>
</body>

</html>
<script>
    $("#playerId").keydown(function(key) {
        if (key.keyCode == 13) {
            crawlingCall();
        }
    });

    function crawlingCall() {
        $.ajax({
            type: 'post',
            url: "/playerInfoCrawling",
            data: {"playerId": $("#playerId").val()},
            success: function (data) {
                alert("선수 정보 저장이 완료되었습니다.");
                $("#playerId").val("");
                $("#playerId").focus();

            },
            error : function(data){
                alert(data);
            }
        });
    }

    function teamInfoSave(){
        $.ajax({
            type:'post',
            url:"/teamInfoSave",
            success:function(data){
                alert("팀 정보가 모두 입력되었습니다.");
            },
            error:function(data){
                alert(data);
            }
        })
    }

    function savePlayerRecord(){
        $.ajax({
            type : 'post',
            url : '/insertPlayerRecord',
            data : {"year" : $("[name='yearData']").val(), "season" : $("[name='seasonData']").val()},
            success : function(){
                alert("시즌 기본 기록이 insert 되었습니다.");
            },
            error : function(){
                alert("실패");
            }
        })
    }
</script>