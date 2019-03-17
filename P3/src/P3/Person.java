package P3;
import java.util.*;
public final class Person {//һЩ��������
	private String name;
	private LinkedList<Integer> SocialList = new LinkedList<>();
	 public Person(String name) 
	{
		this.name = name;
	}
	
	public void linkListAdd(int personPosition) 
	{
		this.SocialList.add(personPosition);
	}
	
	
	public int netWorkSize() 
	{
		return this.SocialList.size();
	}
	
	public boolean edgeExist(int personPosition) 
	{
		if (this.SocialList.contains(personPosition)) 
			return true;
		return false;
	}
	
	public int getSocial(int position) 
	{
		return this.SocialList.get(position);
	}
	
}