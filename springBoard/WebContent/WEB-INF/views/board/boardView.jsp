<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
<script type="text/javascript">

	function boardDelete(){
		$j(document).ready(function(){
			
			$j.ajax({
			    url : "/board/${boardType}/${boardNum}/boardDeleteAction.do",
			    dataType: "json",
			    type: "POST",
			    success: function(data, textStatus, jqXHR)
			    {
					alert("삭제완료");
					
					location.href = "/board/boardList.do?pageNo=1";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	}
	
</script>
</head>
<body>
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td  align="center">
						Type
					</td>
					<td>
						${board.codeName}
					</td>
				</tr>
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr  align="right">
		<td>
			<a href="javascript:boardDelete();">삭제</a>&nbsp;
			<a href="/board/boardUpdate.do?boardType=${board.boardType}&boardNum=${board.boardNum}">수정</a>&nbsp;
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>	
</body>
</html>