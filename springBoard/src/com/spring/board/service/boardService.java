package com.spring.board.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.board.vo.BoardCodeVo;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.TypeVo;
import com.spring.board.vo.UserInfoVo;

public interface boardService {

	public String selectTest() throws Exception; //Exception - 예외처리

	public List<BoardVo> SelectBoardList(TypeVo typeVo) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;
	
	public List<BoardCodeVo> selectBoardCode(BoardCodeVo boardCodeVo) throws Exception;
	
	public List<BoardCodeVo> selectBoardPhone() throws Exception;

	public int selectBoardCnt() throws Exception;
	
	
	//insert,update,delete
	public int boardInsert(List<TypeVo> typeVoList) throws Exception;
	
	public int boardUpdate(BoardVo boardVo) throws Exception;

	public int boardDelete(BoardVo boardVo) throws Exception;
	
	
	//type
	public int selectTypeCnt(List<TypeVo> typeVoList) throws Exception;
	
	public List<TypeVo> selectBoardTypeList(List<TypeVo> typeVoList) throws Exception;


	//user
	public int userInsert(UserInfoVo userInfoVo) throws Exception;
	public int chkUserId(String userId) throws Exception;
}
