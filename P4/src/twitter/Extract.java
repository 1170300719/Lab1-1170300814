package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import twitter.Tweet;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant getstart=tweets.get(0).getTimestamp();
        Instant getend=tweets.get(1).getTimestamp();
        Timespan time;
        if(getstart.isBefore(getend))
        {
        	time=new Timespan(getstart, getend);
        }
        else {
			time=new Timespan(getend, getstart);
		}
        return time;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> ans = new HashSet<String>();
    	Iterator<Tweet> try1 = tweets.iterator();
    	Tweet t;
    	String str,str1,str2;
    	int a,b,c;
    	while ( try1.hasNext())
    	{
    		t = try1.next();
    		str = t.getText();
    		a = str.indexOf("@");
    		if ( a == -1 )
    			continue;
    		while ( a != -1)
    		{
    			str = str.substring(a+1);
        		b = str.indexOf(" ");
        		if ( b == -1 )
        		{
        			str1 = str;
        			str2 = str;
        		}else {
        			str1 = str.substring(0,b);
        			str2 = str.substring(b);
        		}
        		c = str1.indexOf(".");
        		if ( c == -1 )
        		{
        			ans.add(str1);
        		}
        		str = str2;
        		a = str.indexOf("@");
    		}
    	}
    	return ans;
    }

	public static boolean notExist(String name, Set<String> set) {//函数调用：不存在返回false
		
		return false;
	}

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
