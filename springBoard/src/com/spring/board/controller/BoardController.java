package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardCodeVo;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.TypeVo;
import com.spring.board.vo.UserInfoVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,TypeVo typeVo,BoardCodeVo boardCodeVo) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<TypeVo> typeVoList = new ArrayList<TypeVo>();
		List<BoardCodeVo> boardCodeList = new ArrayList<BoardCodeVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(typeVo.getPageNo() == 0){
			typeVo.setPageNo(page);
		}
		//typeVoList.add(typeVo);
		
		typeVo.setSelectAll(true);
		
		boardList = boardService.SelectBoardList(typeVo);
		boardCodeList = boardService.selectBoardCode(boardCodeVo);
		totalCnt = boardService.selectBoardCnt();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardCodeList",boardCodeList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		
		return "board/boardList";
	}
	
	//HashMap을 이용하여 request받기
	/*@RequestMapping(value = "/board/boardTypeList.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardTypeList(Locale locale, Model model,TypeVo typeVo,BoardCodeVo boardCodeVo,
								@RequestParam HashMap<String,String> boardTypeMap) throws Exception{
		int page = 1;
		int totalCnt = 0;
		
		List<TypeVo> typeVoList = new ArrayList<TypeVo>();
		List<TypeVo> typeVoCntList = new ArrayList<TypeVo>();
		List<BoardCodeVo> boardCodeList = new ArrayList<BoardCodeVo>();
		
		for(String key : boardTypeMap.keySet()) {
			TypeVo getTypeVo = new TypeVo();
			getTypeVo.setBoardType(boardTypeMap.get(key));
			
			System.out.println(boardTypeMap.get(key));
			typeVoList.add(getTypeVo);
		}
		
		typeVoList = boardService.selectBoardTypeList(typeVoList);
		totalCnt = boardService.selectTypeCnt(typeVoList);
		boardCodeList = boardService.selectBoardCode(boardCodeVo);
		
		model.addAttribute("boardList", typeVoList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("boardCodeList",boardCodeList);
		model.addAttribute("pageNo", page);
		model.addAttribute("typeVo",typeVo);
		
		HashMap<String,String>result = new HashMap<String,String>();
		CommonUtil commonUtil = new CommonUtil();
		result.put("success", "Y");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}*/
	
	@RequestMapping(value = "/board/boardTypeListAjax.do", method = RequestMethod.POST)
	public String boardTypeListAjax(Locale locale, Model model,TypeVo typeVo,BoardCodeVo boardCodeVo,
								@RequestBody List<Map<String,String>> boardType) throws Exception{
		
		int page = 1;
		int totalCnt = 0;
		
		List<TypeVo> typeVoList = new ArrayList<TypeVo>();
		List<TypeVo> typeVoData = new ArrayList<TypeVo>();
		List<BoardCodeVo> boardCodeList = new ArrayList<BoardCodeVo>();
		
		for(Map<String, String> Map : boardType) {
			
			for(String key : Map.keySet()) {
				TypeVo getTypeVo = new TypeVo();
				getTypeVo.setBoardType(Map.get(key));
				
				typeVoList.add(getTypeVo);
			}
			
		}
		typeVoData = boardService.selectBoardTypeList(typeVoList);
		totalCnt = boardService.selectTypeCnt(typeVoList);
		boardCodeList = boardService.selectBoardCode(boardCodeVo);
		
		model.addAttribute("boardList", typeVoData);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("boardCodeList",boardCodeList);
		model.addAttribute("pageNo", page);
		model.addAttribute("typeVo",typeVo);
			
		return "board/boardTypeList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model,BoardCodeVo boardCodeVo) throws Exception{

		List<BoardCodeVo> boardCodeList = new ArrayList<BoardCodeVo>();
		
		boardCodeList = boardService.selectBoardCode(boardCodeVo);
		
		model.addAttribute("boardCodeList",boardCodeList);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model
			,@RequestParam("boardType")String boardType
			,@RequestParam("boardNum")int boardNum
			,BoardCodeVo boardCodeVo) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		List<BoardCodeVo> boardCodeList = new ArrayList<BoardCodeVo>();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		boardCodeList = boardService.selectBoardCode(boardCodeVo);
		
		model.addAttribute("boardCodeList",boardCodeList);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,@RequestBody List<TypeVo> typeVoList) throws Exception{
		
		int resultCnt=0;
		resultCnt = boardService.boardInsert(typeVoList);
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale,BoardVo boardVo ) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardDeleteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDeleteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardDelete(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/login.do")
	public String login() throws Exception{
		
		return "/board/login";
	}
	
	@RequestMapping(value = "/board/join.do", method = RequestMethod.POST)
	public String join(Locale locale, Model model, UserInfoVo userInfoVo) throws Exception{
		
		List<BoardCodeVo> codeList = new ArrayList<BoardCodeVo>();
		
		codeList = boardService.selectBoardPhone();
		
		model.addAttribute("codeList",codeList);
		
		
		return "/board/join";
	}
	@RequestMapping(value = "/board/join.do", method = RequestMethod.POST)
	public String userInsert(Locale locale, UserInfoVo userInfoVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.userInsert(userInfoVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/join.do", method = RequestMethod.POST)
	public String chkUserId(Locale locale, UserInfoVo userInfoVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.userInsert(userInfoVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
}
