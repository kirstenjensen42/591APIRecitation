import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;

public class RestCaller {
    private static final Twitter twitter = ConfigFile.createTwitterObject();
    
    public static void main (String[] args) {
       	ArrayList<String> stList = new ArrayList<String>();
       	
       	ResponseList<Status> statuses = null;

       	try {
			statuses = twitter.getFavorites("kirstenjensen42");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
       	for (Status s : statuses) {
       		System.out.println(s.getText());
       	}
       	
//       	stList.add("@altonbrown");
       	
//       	for (TwitterProperty t : doSearch(stList)) {
//       		System.out.println("Sent: " + t.getSentDate() + "Text: " + t.getText());
//       	}
    	
    	// sendStatus("testing");
    }
    
    
    /*
     * Search tweets with passed in list of word filters. Only searches for tweets in
     * English.
     */
    public static ArrayList<TwitterProperty> doSearch(ArrayList<String> wordFilters) {
    	ArrayList<TwitterProperty> infoList = new ArrayList<TwitterProperty>();
    	
    	try {
    		for (String filter : wordFilters) {
    			Query query = new Query(filter);	// Build query for each filter
    			query.setLang("en");
    			
    			QueryResult result = twitter.search(query); 	// Search based on latest query
    			for (Status status : result.getTweets()) {
    				infoList.add(buildProperty(status));
    			}
    		}
    	} catch(TwitterException te) {
    		System.out.println("Error in searching for tweets:\n\t" + te.getMessage());
    	}
    	
    	return infoList;
    }
    
    public static void sendStatus(String message) {
    	try {
    		twitter.updateStatus(message);
    	} catch(TwitterException te) {
    		System.out.println("Error updating status:\n\t" + te.getMessage());
    	}
    }
    
    public static TwitterProperty buildProperty(Status status) {
    	TwitterProperty prop = new TwitterProperty();
    	
		prop.setSenderID(status.getUser().getId());
		prop.setSenderSN(status.getUser().getScreenName());
		prop.setLocation(status.getGeoLocation());
		prop.setName(status.getUser().getName());
		prop.setSentDate(status.getCreatedAt());
		prop.setText(status.getText());
		prop.setTweetID(status.getId());
		prop.setAmtFriends(status.getUser().getFollowersCount());
		prop.setStat(status);
		
		return prop;
    }
    
    public TwitterProperty buildProperty(DirectMessage dm) {
    	TwitterProperty prop = new TwitterProperty();
    	
    	prop.setSenderID(dm.getSenderId());
    	prop.setSenderSN(dm.getSenderScreenName());
    	prop.setName(dm.getSender().getName());
    	prop.setSentDate(dm.getCreatedAt());
    	prop.setText(dm.getText());
    	prop.setTweetID(dm.getId());
		
		return prop;
    }
}