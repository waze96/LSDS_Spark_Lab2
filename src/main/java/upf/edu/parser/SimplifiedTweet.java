package upf.edu.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Optional;

public class SimplifiedTweet {

	private static Gson parser = new Gson();
	private static JsonParser jsonParser = new JsonParser();

	private final long tweetId;			  // the id of the tweet ('id')
	private final String text;  		      // the content of the tweet ('text')
	private final long userId;			  // the user id ('user->id')
	private final String userName;		  // the user name ('user'->'name')
	private final String language;          // the language of a tweet ('lang')
	private final long timestampMs;		  // seconds from epoch ('timestamp_ms')  

	public static Gson getParser() {
		return parser;
	}
	
	public long getTweetId() {
		return tweetId;
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

	public SimplifiedTweet(long tweetId, String text, long userId, String userName,
	                         String language, long timestampMs) {
		
		this.tweetId=tweetId;
		this.text=text;
		this.userId=userId;
		this.userName=userName;
		this.language=language;
		this.timestampMs=timestampMs;	
	}

	/**
	 * Returns a {@link SimplifiedTweet} from a JSON String.
	 * If parsing fails, for any reason, return an {@link Optional#empty()}
	 *
	 * @param jsonStr
	 * @return an {@link Optional} of a {@link SimplifiedTweet}
	 */
	public static Optional<SimplifiedTweet> fromJson(String jsonStr)  {
		try {
			JsonElement je = jsonParser.parse(jsonStr);
			JsonObject json = je.getAsJsonObject();
			
			long tweetId = json.get("id").getAsLong();
			String text = json.get("text").getAsString();
			long userId = json.get("user").getAsJsonObject().get("id").getAsLong(); 
			String userName = json.get("user").getAsJsonObject().get("name").getAsString();
			String language = json.get("lang").getAsString();
			long timestampMs = json.get("timestamp_ms").getAsLong();
			return Optional.of(new SimplifiedTweet(tweetId, text, userId, userName, language, timestampMs));
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
