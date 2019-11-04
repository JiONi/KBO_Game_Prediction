<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html lang="ko">
<head>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
<div id="area">
    <input type="text" id="playerId" name="playerId" placeholder="���� ID�� �Է��ϼ���." value=""/>
    <button id="playerInfoCrawling" onclick="crawlingCall()" value = "���� ���� ��������">���� ���� ��������</button>
    <button id="teamInfoSave" onclick="teamInfoSave()" >��ü �� ���� �����ϱ�</button>
</div>
<div style="margin-top:60px;">
    <select name="yearData">
        <option value="">�⵵ ����</option>
        <c:forEach begin="2000" end="2030" varStatus="i">
            <option value="${i.current}">${i.current}</option>
        </c:forEach>
    </select>
    <select name="seasonData">
        <option value="">���� ����</option>
        <option value="RS">���� ����</option>
        <option value="PS">����Ʈ ����</option>
    </select>
    <button id="insertPlayerRecord" type="button" onclick="savePlayerRecord()">���� ��� ����</button>
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
                alert("���� ���� ������ �Ϸ�Ǿ����ϴ�.");
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
                alert("�� ������ ��� �ԷµǾ����ϴ�.");
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
                alert("���� �⺻ ����� insert �Ǿ����ϴ�.");
            },
            error : function(){
                alert("����");
            }
        })
    }
</script>