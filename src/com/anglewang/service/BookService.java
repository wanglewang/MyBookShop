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
	public List<Book> getBookList(Set<String> isbns) throws Exception {
		List<Book> books = null;
		
		if(isbns == null) {
			throw new Exception("isbns入参不能为空");
		}	
		if(isbns.size() >0) {
			BookDao dao = new BookDao();
			try {
				books = dao.getBookList(isbns);	
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
	public Book getBookInfo(String isbn)throws Exception {
		if(isbn==null) {
			throw new InputEmptyException("入参isbn不能为空");
		}
		BookDao dao = new BookDao();
		try {
			return dao.getBookInfo(isbn);	
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
