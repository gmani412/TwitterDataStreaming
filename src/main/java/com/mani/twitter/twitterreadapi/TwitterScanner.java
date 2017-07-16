package com.mani.twitter.twitterreadapi;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Manindar
 */
public class TwitterScanner {

    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("KShoNCgTxYWOtDkNftYNUYScK");
        cb.setOAuthConsumerSecret("OQqUqQFoJHnfb0bx5MKwQtrBSmF0iAQzXe1I5wJgiG83t6453o");
        cb.setOAuthAccessToken("881909923176562689-FFZxp9XpOVGf7aui822X44mNEqp8AmW");
        cb.setOAuthAccessTokenSecret("PFfUsXrasLdsris1z6iGo5n5Gi4d36diDKESdZmtJNgBU");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        FilterQuery fq = new FilterQuery();
        String keywords[] = {"India", "Modi"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);
    }
}
