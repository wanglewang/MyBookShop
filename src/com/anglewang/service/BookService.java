package com.anglewang.service;

import java.util.List;
import java.util.Set;

import com.anglewang.dao.BookDao;
import com.anglewang.entity.Book;
import com.anglewang.exception.InputEmptyException;

public class BookService {
	
	/**
	 * ��ȡ���ﳵ�е�ͼ����Ϣ
	 * @param isbns
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBookList(Set<String> bookIdSet) throws Exception {
		List<Book> books = null;
		
		if(bookIdSet == null) {
			throw new Exception("bookIdSet��β���Ϊ��");
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
	 * ��ȡͼ��������Ϣ
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String bookId)throws Exception {
		if(bookId==null) {
			throw new InputEmptyException("���bookId����Ϊ��");
		}
		BookDao dao = new BookDao();
		try {
			return dao.getBookInfo(bookId);	
		} finally {
			dao.closeConnection();
		}		
	}
	
	/**
	 * �����ҳͼ���б�
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
