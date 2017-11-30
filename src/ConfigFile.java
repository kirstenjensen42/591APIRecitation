import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/*
 * Creates the necessary configuration options to allow TwitterAPI calls
 * to go out
 */
public class ConfigFile {
	
	/*
	 * Creates new ConfigurationBuilder and sets the OAuth keys\
	 * 
	 * Returns: ConfigurationBuilder object
	 */
	private static ConfigurationBuilder configure() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).
			setOAuthConsumerKey("").
			setOAuthConsumerSecret("").
			setOAuthAccessToken("").
			setOAuthAccessTokenSecret("");
		
		return cb;
	}
	
	/*
	 * Creates new Twitter Factory with Config credentials
	 * 
	 * Returns: TwitterFactory object
	 */
	private static TwitterFactory createFactory() {
		ConfigurationBuilder cb = configure();
		TwitterFactory tf = new TwitterFactory(cb.build());
		
		return tf;
	}
	
	
	/*
	 * Creates new Twitter object to be used to make api calls
	 */
	public static Twitter createTwitterObject() {
		TwitterFactory tf = createFactory();
		Twitter twitter = tf.getInstance();
		
		return twitter;
	}
	
}