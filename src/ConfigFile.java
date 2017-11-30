import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
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
	 * Creates a new TwitterStreamFactory object to be used to create a new TwitterStream
	 * object and connect the config settings to the Streaming API code.
	 */
	private static TwitterStreamFactory createStreamFactory() {
		ConfigurationBuilder cb = configure();
		TwitterStreamFactory tsf = new TwitterStreamFactory(cb.build());
		
		return tsf;
	}
	
	/*
	 * Creates new Twitter object to be used to make api calls
	 */
	public static Twitter createTwitterObject() {
		TwitterFactory tf = createFactory();
		Twitter twitter = tf.getInstance();
		
		return twitter;
	}
	
	/*
	 * Creates new TwitterStream object to connect the configuration and OAuth information
	 * to the Streaming API classes.
	 */
	public static TwitterStream createStream() {
		TwitterStreamFactory tsf = createStreamFactory();
		TwitterStream twitterStream = tsf.getInstance();
		
		return twitterStream;
	}
}