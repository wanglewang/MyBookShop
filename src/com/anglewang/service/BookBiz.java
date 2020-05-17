package com.anglewang.service;

import java.util.List;
import java.util.Set;

import com.anglewang.dao.BookDao;
import com.anglewang.entity.Book;
import com.anglewang.exception.InputEmptyException;

public class BookBiz {
	
	/**
	 * ��ȡ���ﳵ�е�ͼ����Ϣ
	 * @param isbns
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBookList(Set<String> isbns) throws Exception {
		List<Book> books = null;
		
		if(isbns == null) {
			throw new Exception("isbns��β���Ϊ��");
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
	 * ��ȡͼ��������Ϣ
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String isbn)throws Exception {
		if(isbn==null) {
			throw new InputEmptyException("���isbn����Ϊ��");
		}
		BookDao dao = new BookDao();
		try {
			return dao.getBookInfo(isbn);	
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