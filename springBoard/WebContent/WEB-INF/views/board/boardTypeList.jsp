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

</script>
<body>
<div id="countAjaxDiv" class="countDiv">
	<table  align="center" style="width:440px">
		<tr>
			<td align="right">
				total : ${totalCnt}
			</td>
		</tr>
	</table>
</div>
	<div id="boardAjaxDiv">
			<table id="boardTable" border = "1" align="center">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center" id="codeNameTd">
							
							${list.codeName}
						</td>
						<td align="center" id="boardNumTd">
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}"> ${list.boardTitle} </a>
						</td>
					</tr>	
				</c:forEach>
			</table>
	</div>
	<div id="codeAjaxDiv">
	<table align="center" style="width:450px">
		<tr>
			<td align="right">
				<a href ="/board/boardWrite.do">글쓰기</a>
				<a href="/board/boardList.do">List</a>
				
			</td>
		</tr>
		<tr>
			<td align="left">
				<label><input type="checkbox" id="total" name="boardTypeTotal" value="TOTAL" checked>전체</label>
			<c:forEach items="${boardCodeList}" var="codeList">
				<label><input type="checkbox"  id="boardType" name="boardType" value="${codeList.codeID}" onClick="javascript:checkBoxChange();" checked >${codeList.codeName}</label>
			</c:forEach>
				<button type="button" id="type">조회</button>
			</td>
		</tr>
	</table>
</div>
</body>
</html>