package com.spring.board.dao;

import java.util.ArrayList;
import java.util.List;

import com.spring.board.vo.TypeVo;
import com.spring.board.vo.UserInfoVo;
import com.spring.board.vo.BoardCodeVo;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(TypeVo typeVo) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;
	
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
