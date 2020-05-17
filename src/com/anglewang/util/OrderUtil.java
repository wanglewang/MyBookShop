package com.anglewang.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * ������ʽ��D20190312-12345
 *
 */
public class OrderUtil {
	
	private static AtomicLong  al;                //ʹ��CAS��֤ԭ�Ӽ�һ
	
	static{
		//��һ������̱���������ᵼ�¶����ų�ͻ��������һ������Ҫ�������������Ϊ��ʼֵ
		//al = new AtomicLong(oi.getOrderAinitialValue()+1);    //ʵ����Ӫ����
		al = new AtomicLong(new Date().getTime());              //Ϊ�˿������㣬ģ������
	}
	
	/**
	 * ����һ���µĶ������
	 * @return
	 */
	public static String createNewOrderNo(){		
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);		
		int month = rightNow.get(Calendar.MONTH) + 1;	
		String sMonth,sDay; 
		if(month<10){
			sMonth =  "0" + Integer.toString(month);
		}else{
			sMonth = Integer.toString(month);
		}
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		if(day<10){
			sDay = "0" + Integer.toString(day);
		}else{
			sDay = Integer.toString(day);
		}
		long xh = al.getAndIncrement();                            //�߲�������£���ˮ��Ҳ�����ͻ
		String dno = "D" + year + sMonth + sDay + "-" + xh;
		
		return dno;
	}

}
