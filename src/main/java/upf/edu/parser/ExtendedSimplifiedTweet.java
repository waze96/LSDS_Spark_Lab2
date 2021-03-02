package upf.edu.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Optional;

public class ExtendedSimplifiedTweet {

	private static Gson parser = new Gson();
	private static JsonParser jsonParser = new JsonParser();

	private final long tweetId;			  // the id of the tweet ('id')
	private final String text;  		      // the content of the tweet ('text')
	private final long userId;			  // the user id ('user->id')
	private final String userName;		  // the user name ('user'->'name')
	private final long followersCount; // the number of followers (’user’->’followers_count’)
	private final String language;  // the language of a tweet (’lang’)
	private final boolean isRetweeted; // is it a retweet? (the object ’retweeted_status’ exists?)
	private final long retweetedUserId; // [if retweeted] (’retweeted_status’->’user’->’id’)
	private final long retweetedTweetId; // [if retweeted] (’retweeted_status’->’id’)
	private String retweetedUserName;
	private final long timestampMs;		  // seconds from epoch ('timestamp_ms')  

	
	public long getFollowersCount() {
		return followersCount;
	}

	public boolean isRetweeted() {
		return isRetweeted;
	}

	public Long getRetweetedUserId() {
		return retweetedUserId;
	}

	public Long getRetweetedTweetId() {
		return retweetedTweetId;
	}

	public static Gson getParser() {
		return parser;
	}
	
	public long getTweetId() {
		return tweetId;
	}
	
	public String getRetweetedUserName() {
		return retweetedUserName;
	}

	public String getText() {
		return text;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public long getTimestampMs() {
		return timestampMs;
	}

	public void setRetweetedUserName(String retweetedUserName) {
		this.retweetedUserName = retweetedUserName;
	}

	public ExtendedSimplifiedTweet(long tweetId, String text, long userId, String userName,
						long followersCount, String language, boolean isRetweeted, long retweetedUserId, 
						String retweetedUserName, long retweetedTweetId, long timestampMs) {
		
		this.tweetId=tweetId;
		this.text=text;
		this.userId=userId;
		this.userName=userName;
		this.followersCount = followersCount;
		this.language=language;
		this.isRetweeted = isRetweeted;
		this.retweetedUserId = retweetedUserId;
		this.retweetedUserName = retweetedUserName;
		this.retweetedTweetId = retweetedTweetId;
		this.timestampMs=timestampMs;	
	}

	/**
	 * Returns a {@link ExtendedSimplifiedTweet} from a JSON String.
	 * If parsing fails, for any reason, return an {@link Optional#empty()}
	 *
	 * @param jsonStr
	 * @return an {@link Optional} of a {@link ExtendedSimplifiedTweet}
	 */
	public static Optional<ExtendedSimplifiedTweet> fromJson(String jsonStr)  {
		if(!jsonStr.isEmpty()) {
			JsonElement je = jsonParser.parse(jsonStr);
			JsonObject tweet = je.getAsJsonObject();

			long tweetId, userId, timestampMs;			
			boolean isRetweeted;
			long followersCount, retweetedUserId, retweetedTweetId = 0;
			String text, userName, language, rtUserName;	
			ExtendedSimplifiedTweet t;

			if (tweet.has("id") && tweet.has("text") && tweet.has("lang") && tweet.has("timestamp_ms")) {  
				tweetId = tweet.get("id").getAsLong();
				timestampMs = tweet.get("timestamp_ms").getAsLong();
				text = tweet.get("text").getAsString();
				language = tweet.get("lang").getAsString();
				if (tweet.has("user")) {   
					JsonObject user = tweet.getAsJsonObject("user");
					if (user.has("id") && user.has("name")) {  
						userId = user.get("id").getAsLong(); 
						userName = user.get("name").getAsString();
						followersCount = user.get("followers_count").getAsLong(); 
						
						if (tweet.has("retweeted_status")){
							isRetweeted = true;
							JsonObject rt_stat = tweet.getAsJsonObject("retweeted_status");
							if (rt_stat.has("id")) {  
								retweetedTweetId = rt_stat.get("id").getAsLong(); 
							}
							if (rt_stat.has("user")) {   
								JsonObject rt_user = rt_stat.getAsJsonObject("user");
								if (rt_user.has("id") && rt_user.has("name")) { 
									rtUserName = rt_user.get("name").getAsString();
									retweetedUserId = rt_user.get("id").getAsLong(); 
									t = new ExtendedSimplifiedTweet(tweetId, text, userId, userName, followersCount, language, isRetweeted, retweetedUserId, rtUserName, retweetedTweetId, timestampMs);
									return Optional.of(t);
								}
							}
						}
						else {
							isRetweeted = false;
							retweetedTweetId = 0;
							retweetedUserId = 0;
							rtUserName = "";
							retweetedTweetId = 0; 
							t = new ExtendedSimplifiedTweet(tweetId, text, userId, userName, followersCount, language, isRetweeted, retweetedUserId, rtUserName, retweetedTweetId, timestampMs);
							return Optional.of(t);
							
						}
					}
				}
			}
		}
		return Optional.empty();	
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
