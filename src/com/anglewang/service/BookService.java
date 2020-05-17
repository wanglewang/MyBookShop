package com.anglewang.service;

import java.util.List;
import java.util.Set;

import com.anglewang.dao.BookDao;
import com.anglewang.entity.Book;
import com.anglewang.exception.InputEmptyException;

public class BookService {
	
	/**
	 * 提取购物车中的图书信息
	 * @param isbns
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBookList(Set<String> bookIdSet) throws Exception {
		List<Book> books = null;
		
		if(bookIdSet == null) {
			throw new Exception("bookIdSet入参不能为空");
		}	
		if(bookIdSet.size() >0) {
			BookDao dao = new BookDao();
			try {
				books = dao.getBookList(bookIdSet);	
			} finally {
				dao.closeConnection();
			}	
		}
		
		return books;		
	}
	
	/**
	 * 提取图书详情信息
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String bookId)throws Exception {
		if(bookId==null) {
			throw new InputEmptyException("入参bookId不能为空");
		}
		BookDao dao = new BookDao();
		try {
			return dao.getBookInfo(bookId);	
		} finally {
			dao.closeConnection();
		}		
	}
	
	/**
	 * 获得主页图书列表
	 * @return
	 * @throws Exception
	 */
	public List<Book> getMainBooks( ) throws Exception {
		BookDao dao = new BookDao();
		try {
			return dao.getMainBooks();	
		} finally {
			dao.closeConnection();
		}		
	}

}
