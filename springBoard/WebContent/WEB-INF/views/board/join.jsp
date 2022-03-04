<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		$j("#id").cilck(function(){
			var id = $j("#id").val();
			
			$j.ajax({
				 url : "/board/join.do",
				    dataType: "JSON",
				    type:"POST",
				    data : { "userId" : id },
				    contentType:"application/json;charset=UTF-8",
				    success: function(data, textStatus, jqXHR)
				    {}
			});
		});
	});
	
	$j(document).ready(function(){
		$j("#join").click(function(){
			var $frm = $j('.userInfo :input');
			var param = $frm.serialize()
		})
	});
</script>
<body>
<table align="center" style="width:500px">
	<tr align="left">
		<td>
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>
<form class="userInfo">
	<table border="1" align="center" style="width:500px" class="boardTable">
		<tr >
			<td width="40%" align="center">
				id*
			</td>
			<td>
				<input type="text" name="userId"><button type="button">중복확인</button>
			</td>
		</tr>
		<tr >
			<td align="center">
				pw*
			</td>
			<td>
				<input type="password" name="userPw">
			</td>
		</tr>
		<tr>
			<td align="center">
				pw check*
			</td>
			<td>
				<input type="password">
			</td>
		</tr>
		<tr>
			<td align="center">
				name*
			</td>
			<td>
				<input type="text" name="userName">
			</td>
		<tr>
			<td align="center">
				phone*
			</td>
			<td >
				<select name="userPhone">
					<c:forEach items="${codeList}" var="codeList">
						<option value="${codeList.codeName}">${codeList.codeName}</option>
					</c:forEach>
				</select>
				-
				<input type="text" style="width:35px" name="userPhone">
				-
				<input type="text" style="width:35px" name="userPhone">
			</td>
		</tr>
		<tr>
			<td align="center">
				postNo
			</td>
			<td>
				<input type="text">
			</td>
		</tr>
		<tr>
			<td align="center">
				address
			</td>
			<td>
				<input type="text">
			</td>
		</tr>
		<tr>
			<td align="center">
				company
			</td>
			<td>
			 <input type="text">
			</td>
		</tr>
	</table>
	<table align="center" style="width:500px">
		<tr>
			<td align="right">
				<button type="button" id="join">join</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>