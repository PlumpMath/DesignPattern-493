package adapter;

import java.util.Enumeration;
import java.util.Iterator;
/**
 * 
* @ClassName: EnumerationIterator
* @Description: ������������˴�Enumeration��Iterator�����乤��
* @author Jayshawn
* @date 2017��3��27�� ����11:15:53
* 
* @param <E>
 */
public class EnumerationIterator<E> implements Iterator<E> {
	Enumeration<E> enumeration;
	
	public EnumerationIterator(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}
	
	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public E next() {
		return enumeration.nextElement();
	}

}
