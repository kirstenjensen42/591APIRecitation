import java.util.List;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterCaller {
	
	public static final Twitter twitter = ConfigFile.createTwitterObject();

	public static void main(String[] args) {
		
		
		try {
			
			Query query = new Query("#netneutrality");
			query.setLang("en");
			
			QueryResult result = twitter.search(query);
			
			printStatuses(result.getTweets());
			
		} catch (TwitterException e) {
			
		}
		
		try {
			ResponseList<Status> statuses = twitter.getUserTimeline("swapneel");
			
			printStatuses(statuses);
			
		} catch (Exception e) {
			
		}
		
		
		
		
			

	}
	
	public static void printStatuses(List<Status> statuses) {
		
		ArrayList<TwitterProperty> tweets = new ArrayList<TwitterProperty>();

		for (Status status : statuses) {
			tweets.add(buildProperty(status));
		}
		
		for (TwitterProperty t : tweets) {
			System.out.println("Name: " + t.getName());
			System.out.println("Text: " + t.getText());
			System.out.println("Location: " + t.getLocation());
			System.out.println("");
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

}
