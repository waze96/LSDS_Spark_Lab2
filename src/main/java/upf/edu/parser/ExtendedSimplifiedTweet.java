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
		try {
			JsonElement je = jsonParser.parse(jsonStr);
			JsonObject json = je.getAsJsonObject();
			
			long tweetId = json.get("id").getAsLong();
			String text = json.get("text").getAsString();
			long userId = json.get("user").getAsJsonObject().get("id").getAsLong(); 
			String userName = json.get("user").getAsJsonObject().get("name").getAsString();
			long followersCount = json.get("user").getAsJsonObject().get("followers_count").getAsLong(); 
			boolean isRetweeted = json.has("retweeted_status");
			
			long retweetedTweetId, retweetedUserId;
			String rtUserName;
			if(isRetweeted) {
				retweetedTweetId = Optional
						.ofNullable(json.get("retweeted_status"))
						.map(JsonElement::getAsJsonObject)
						.map(jo -> jo.get("id"))
						.map(JsonElement::getAsLong)
						.orElse(null);
				
				Optional<JsonObject> optionalUser = Optional
						.ofNullable(json.get("retweeted_status"))
						.map(JsonElement::getAsJsonObject)
						.map(jo -> jo.get("user"))
						.map(JsonElement::getAsJsonObject);
				retweetedUserId = optionalUser
						.map(jo -> jo.get("id"))
						.map(JsonElement::getAsLong)
						.orElse(null);
				rtUserName = optionalUser
						.map(jo -> jo.get("name"))
						.map(JsonElement::getAsString)
						.orElse(null); 
			} else {
				retweetedTweetId  = -1;
				retweetedUserId = -1; 
				rtUserName = "";
			}
			String language = json.get("lang").getAsString();
			long timestampMs = json.get("timestamp_ms").getAsLong();
			return Optional.of(new ExtendedSimplifiedTweet(tweetId, text, userId, userName, followersCount, language, isRetweeted, retweetedUserId, rtUserName, retweetedTweetId, timestampMs));
		} catch(Exception ex) {
			System.err.println("Error message: " + ex.getMessage());
			System.err.println("Unable to parse: " + jsonStr);
			return Optional.empty();
		}
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
