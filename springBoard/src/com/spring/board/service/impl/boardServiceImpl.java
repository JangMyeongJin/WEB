package com.spring.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.TypeVo;
import com.spring.board.vo.UserInfoVo;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardCodeVo;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(TypeVo typeVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(typeVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	public List<BoardCodeVo> selectBoardCode(BoardCodeVo boardCodeVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardCode(boardCodeVo);
	}
	
	public List<BoardCodeVo> selectBoardPhone() throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardPhone();
	}
	
	public int boardInsert(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(typeVoList);
	}
	
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardUpdate(boardVo);
	}
	
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardDelete(boardVo);
	}
	
	
	//type
	@Override
	public int selectTypeCnt(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTypeCnt(typeVoList);
	}
	@Override
	public List<TypeVo> selectBoardTypeList(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardTypeList(typeVoList);
	}
	
	//user
	@Override
	public int userInsert(UserInfoVo userInfoVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.userInsert(userInfoVo);
	}
	@Override
	public int chkUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.chkUserId(userId);
	}

}
