package com.spring.board.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.TypeVo;
import com.spring.board.vo.UserInfoVo;
import com.spring.board.vo.BoardCodeVo;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(TypeVo typeVo) throws Exception {
		// TODO Auto-generated method stub
		List<TypeVo> typeVoList = new ArrayList<TypeVo>();
		return sqlSession.selectList("board.boardTypeList",typeVoList);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotal");
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public List<BoardCodeVo> selectBoardCode(BoardCodeVo boardCodeVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardCode", boardCodeVo);
	}
	
	@Override
	public List<BoardCodeVo> selectBoardPhone() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardPhone");
	}
	
	@Override
	public int boardInsert(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardInsert", typeVoList);
	}
	
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("board.boardUpdate", boardVo);
	}
	
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("board.boardDelete", boardVo);
	}
	
	//type
	@Override
	public int selectTypeCnt(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTypeCnt", typeVoList);
	}
	@Override
	public List<TypeVo> selectBoardTypeList(List<TypeVo> typeVoList) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardTypeList",typeVoList);
	}
	
	
	//user
	@Override
	public int userInsert(UserInfoVo userInfoVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.userInsert",userInfoVo);
	}
	@Override
	public int chkUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.chkUserId",userId);
	}
}
