import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TwitterBot {
	
	
	
	//static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	//static StringBuilder sb = new StringBuilder();
	//static Random random = new Random();
	
	static String ip;
	//static File[] roots = File.listRoots();
	
	public static void main(String[] args) throws TwitterException, IOException {
		//getIP();
		filter();
		//for (File root : roots) {
			//post("77C453B0t v1.0 \n" + "Available processors (cores): " + Runtime.getRuntime().availableProcessors() + "\n" + "RAM Usage (MB): " + ((double)Runtime.getRuntime().freeMemory()  / 1048576) + "\n" + "Total space (GB): " + root.getTotalSpace() / 1073741824);
		//}
		
		//int i=0;
		//for (int i=0; i < 249; i++)
		//{
			
			//post("@User" + i);
		//}
		
		//for(int i=0; i<100; i++)
			//post(" ");
		//post("");
		//GetTimeline();
		//for (int i = 0; i < 20; i++) {
		    //char c = chars[random.nextInt(chars.length)];
		    //sb.append(c);
		    //post(sb.toString());
		//}
	}

	private static void post(String input) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = twitter.updateStatus(input);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	static void GetTimeline() throws TwitterException
	{
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	
	static void getPost(String in) throws TwitterException
	{
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query(in);
	    QueryResult result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	        if (status.getText() == "")
	        {
	        	post(status.getUser() + "test");
	        }
	    }
	}
	
	static void getIP() throws IOException
	{
		URL whatismyip = new URL("http://checkip.amazonaws.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		ip = in.readLine(); //you get the IP as a String
	}
	
	static void print(String input)
	{
		System.out.println(input);
	}
	
	static void filter()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");
        
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		
		StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");
                
                if (status.getText().contains("!info"))
                {
                	try {
                		post("@" + status.getUser().getScreenName() + "I am a Twitter Bot Developed by \n//C#[453]++! \n\nI can handle basic commands as of now.");
					} 		
                	catch (TwitterException e) 
                	{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                else if (status.getText().contains("!time"))
                {
                	try {
                		long timeInMillis = System.currentTimeMillis();
                		Calendar cal1 = Calendar.getInstance();
                		cal1.setTimeInMillis(timeInMillis);
                		SimpleDateFormat dateFormat = new SimpleDateFormat(
                		                                "dd/MM/yyyy hh:mm:ss a");
                		
						post("@" + status.getUser().getScreenName() + " The current date/time is: " + dateFormat.format(cal1.getTime()));
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                else if (status.getText().contains("!specs"))
                {
                	try {
                		
						post("@" + status.getUser().getScreenName() + " "); //TODO
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
        
        String keywords[] = {"@77C453B0t"}; //username

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);  

	}
}
