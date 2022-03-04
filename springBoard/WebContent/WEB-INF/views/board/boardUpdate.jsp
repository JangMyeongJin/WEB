<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#update").on("click",function(){
			var $frm = $j('.boardUpdate :input');
			var param = $frm.serialize()+"&boardType="+ $j("#boardType").val();
			//serialize() - 여러개의 데이터를 get방식으로 바꿈
			
			$j.ajax({
			    url : "/board/${board.boardType}/${board.boardNum}/boardUpdateAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("수정완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do?pageNo=1";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	});
	

</script>
</head>
<body>
<form class="boardUpdate">
<table align="center">
<tr>
			<td align="right">
			<button type="button" id="update">수정</button>
			</td>
		</tr>
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td  align="center">
						Type
					</td>
					<td>
						<select id="boardType">
							<c:forEach items="${boardCodeList}" var="codeList">
								<option value="${codeList.codeID}" <c:choose><c:when test="${codeList.codeID == boardType}">selected </c:when></c:choose> >${codeList.codeName}</option>
							</c:forEach>
							</select>
					</td>
				</tr>
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					<input name="boardTitle" type="text" size="50" value="${board.boardTitle}">
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					<textarea name="boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
					수정
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr align="right">
		<td>
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>
</form>	
</body>
</html>