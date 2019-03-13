package P3;
import java.util.*;
public final class Person {//一些辅助方法
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
	
	public String getTheName() 
	{
		return this.name;
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