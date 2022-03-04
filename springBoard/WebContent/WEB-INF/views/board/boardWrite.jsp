<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		$j("#submit").on("click",function(){
			/*var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();*/
			
			var paramArray = [];
			$j('.boardTable').each(function(i){
				var param = {
						//jsp 자식요소 찾기
						boardType : $j(this).children().find("#boardType").val(),
						boardTitle : $j(this).children().find("#boardTitle").val(),
						boardComment : $j(this).children().find("#boardComment").val()
				}
				paramArray.push(param);
			});
			
			console.log(JSON.stringify(paramArray));
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : JSON.stringify(paramArray),
			    contentType: "application/json; UTF-8;",
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					location.href = "/board/boardList.do?pageNo=1";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	});
	
$j(document).ready(function(){
		$j("#remove").on("click",function(){
			
			if($j("input[name='removeChk']").is(":checked")){
				$j("input[name='removeChk']:checked").each(function(){
					
					//jsp 부모요소 찾기
					var chk = $j(this).parent().parent().parent().parent().parent();
					chk.remove();
				});
			}else{
				alert("삭제할 행을 선택");
			}
			
			
		});
	});
	
$j(document).ready(function(){
	$j("#append").on("click",function(){
		var source =	"<table border ='1' class='boardTable' align='center'><tr>"+
							"<td align='center'>"+
								"<label>Type<input type='checkbox' name='removeChk' id='chk'></label>"+
							"</td>"+
							"<td>"+
								"<select id='boardType' name='boardType'>"+
									"<c:forEach items='${boardCodeList}' var='codeList'>"+
										"<option value='${codeList.codeID}'>${codeList.codeName}</option>"+
									"</c:forEach>"+
								"</select>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td width='120' align='center'>"+
								"Title"+
										"</td>"+
										"<td width='400'>"+
											"<input name='boardTitle' id='boardTitle' type='text' size='50' value='${board.boardTitle}'>"+ 
										"</td>"+
									"</tr>"+
									"<tr>"+
										"<td height='300' align='center'>"+
											"Comment"+
										"</td>"+
										"<td valign='top'>"+
											"<textarea name='boardComment' id='boardComment' rows='20' cols='55'>${board.boardComment}</textarea>"+
										"</td>"+
									"</tr></table>";
		
		$j("#appendTable").after(source);
		
	});
});
	

</script>
<body>
	
		<table align="center" style="width:541px">
			<tr>
				<td align="right">
					<input type="button" id="append" value="행 추가">
					<input type="button" id="remove" value="행 삭제">
					<input id="submit" type="button" value="작성">
				</td>
			</tr>
		</table>
			<form class="boardWrite">
				<table border ="1" class="boardTable" id="appendTable" align="center"> 
					<tr>
						<td align="center">
							<label>Type<input type="checkbox" name="removeChk" id="chk"></label>
						</td>
						<td>
							<select id="boardType" name="boardType">
								<c:forEach items="${boardCodeList}" var="codeList">
									<option value="${codeList.codeID}">${codeList.codeName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="boardTitle" id="boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
							<textarea name="boardComment" id="boardComment" rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
				</table>
			</form>
				<table border ="1" align="center" style="width:535px">
					<tr>
						<td align="center" style="width:120px">
						Writer
						</td>
						<td>
						</td>
					</tr>
				</table>

<table align="center" style="width:541px">
	<tr>
		<td align="right">
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>
</body>
</html>