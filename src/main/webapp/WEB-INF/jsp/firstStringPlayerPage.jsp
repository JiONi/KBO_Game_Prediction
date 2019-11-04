<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html lang="ko">
<head>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
<div id="area">
    <form id="firstStringPlayerList" name="firstStringPlayerList">
        <select name="teamId">
            <option value="">팀 선택</option>
            <c:forEach var="team" items="${teamList}">
                <option value="${team.id}">${team.name}</option>
            </c:forEach>
        </select>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
        <input type="number" name="backNoList" value="" />
        <input type="number" name="backNoList" value="" /><br/>
    </form>
    <button type="button" id="submitFirstStringPlayer" onclick="updateFirstString()">엔트리 목록 등록하기</button>
</div>
</body>
</html>
<script>

    function updateFirstString(){
        var teamName = $("select[name='teamId']").find(".selected").text();
        $.ajax({
            url : '/updateFirstStringYn',
            type : 'post',
            data : $("#firstStringPlayerList").serialize(),
            success : function(){
                alert("1군 엔트리가 등록되었습니다.");
                $("input[name=backNoList]").val("");
            },
            error : function(error){
                alert(error);
            }
        })
    }
</script>