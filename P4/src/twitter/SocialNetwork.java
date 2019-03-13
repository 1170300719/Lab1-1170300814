/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
	
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	String name=null;
    	String[] word=null;
        Map<String, Set<String>> user=new HashMap<String, Set<String>>();
        Set<String>  notexist=new HashSet<String>();
        for(Tweet onlyuser: tweets)
        {
        	word=onlyuser.getText().split("[^0-9a-zA-Z-_@]");//检索一个数字或字母开始
        	for(String hi:word)
        	{
        		if(hi.startsWith("@")&&hi.length()>1)//寻找符合条件的字符串
        		{
        			name=hi.substring(1);//去掉@剩下的是名字
        			if(user.containsKey(onlyuser.getAuthor()))
        			{
        				if(Extract.notExist(name, user.get(onlyuser.getAuthor())))
        				{
        					user.get(onlyuser.getAuthor()).add(name);
        				}
        				else 
        				{
							notexist.add(name);
							user.put(onlyuser.getAuthor(),notexist);
						}
        			}
        		}
        		if(!user.containsKey(onlyuser.getAuthor()))
        		{
        			Set<String> notexist1=new  HashSet<String>();
        			user.put(onlyuser.getAuthor(), notexist1);
        		}
        	}
        }
        return user;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        List<String> influence=new ArrayList<String>();
        Map<String, Integer> influcemap= new HashMap<String, Integer>();
        String  name=null;
        int max=0;
        for(String followman:followsGraph.keySet())
{
	for(String name2: followsGraph.get(followman))
	{
		if(influcemap.containsKey(name2))
		{
			Integer kiss=influcemap.get(name2);
			kiss++;
			influcemap.put(name2, kiss);
		}
		else
		{
			influcemap.put(name2, 0);
		}
	}
}
        while(!influcemap.keySet().isEmpty())
        {
        	for(String name1:influcemap.keySet())
        	{
        		if(influcemap.get(name1)>max)
        		{
        			max=influcemap.get(name1);
        			name=name1;
        		}
        	}
        	influence.add(name);
        	influcemap.remove(name);
        }
        return influence;
    }

}
