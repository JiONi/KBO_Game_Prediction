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
            <option value="">�� ����</option>
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
    <button type="button" id="submitFirstStringPlayer" onclick="updateFirstString()">��Ʈ�� ��� ����ϱ�</button>
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
                alert("1�� ��Ʈ���� ��ϵǾ����ϴ�.");
                $("input[name=backNoList]").val("");
            },
            error : function(error){
                alert(error);
            }
        })
    }
</script>