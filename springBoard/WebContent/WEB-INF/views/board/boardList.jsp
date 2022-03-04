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


	$j(function(){
		$j("#total").click(function(){
			
			var chk = $j("input[id='total']").is(":checked");
			
			if(chk){
				$j("input[id='boardType']").each(function(e){
					$j(this).prop("checked",true);
				});
				
			}else{
				$j("input[id='boardType']").each(function(e){
					$j(this).prop("checked",false);
				});
			}
		});
	});

	function checkBoxChange(){
		var chk = $j("input[id='boardType']:checked").length;
		
		if(chk==4){
			$j("input[id=total]").prop("checked",true);
		}else{
			$j("input[id=total]").prop("checked",false);
		}
		
	}
	
	$j(document).ready(function(){
		$j("#type").click(function(){
			
			var param = [];
			var chkArray = [];
			$j("input[name='boardType']:checked").each(function(i){
				if(this.checked){
					chkArray={
							boardType : this.value
					};
				}
				param.push(chkArray);
			});
			var chkArrayJson = JSON.stringify(param);
			
			console.log(chkArrayJson);
			
			$j.ajax({
			    url : "/board/boardTypeListAjax.do",
			    dataType: "text",
			    type:"POST",
			    data : chkArrayJson,
			    contentType:"application/json;charset=UTF-8",
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert("조회완료");
			    	
			    	var html = jQuery('<div>').html(data);
			    	var countDiv = html.find("div#countAjaxDiv").html();
			    	var boardDiv = html.find("div#boardAjaxDiv").html();
			    	
			    	$j("#countDiv").html(countDiv);
			    	$j("#boardDiv").html(boardDiv);
			    	//https://velog.io/@hwarkhada/ajax%EB%A1%9C-%EB%B0%9B%EC%95%84%EC%98%A8-data-%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%83%88%EB%A1%9C%EA%B3%A0%EC%B9%A8-%EC%97%86%EC%9D%B4-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
			    	/*var source = "<table id='boardTable' border = '1' align='center'>" +
									"<tr>" +
										"<td width='80' align='center'>" +
											"Type" +
										"</td>" +
										"<td width='40' align='center'>" +
											"No"+
										"</td>"+
										"<td width='300' align='center'>"+
											"Title"+
										"</td>"+
									"</tr>"+
									"<c:forEach items='${boardList}' var='list'>"+
										"<tr>"+
											"<td align='center'>"+
												"${list.codeName}"+
											"</td>"+
											"<td align='center'>"+
												"${list.boardNum}"+
											"</td>"+
											"<td>"+
												"<a href = '/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}'>${list.boardTitle}</a>"+
											"</td>"+
										"</tr>"+	
									"</c:forEach>"+
								"</table>";*/
								
			    	//$j("#boardTable").empty();
			    	//$j("#boardTable").html(source);
			    },
			    error: function(errorThrown)
			    {
			    	alert("조회실패");
			    }
			});
			
		});
	});
	
	
</script>
<body>
<div id="countDiv" class="countDiv">
	<table  align="center" style="width:440px">
		<tr>
			<td align="left">
				<a href="/board/login.do">login</a>
				<a href="/board/join.do">join</a>	
			</td>
			<td align="right">
				total : ${totalCnt}
			</td>
		</tr>
	</table>
</div>
	<div id="boardDiv">
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
</body>
</html>