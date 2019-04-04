package com.angelsheaven.demo.data.storage

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class FeedTest {

    private lateinit var feed:Feed

    @Test fun test_twoSameFeeds(){
        val firstFeed = Feed("http://abc.com"
            ,"Hello World"
            ,"http://abc.com"
            ,"Jason Nguyen"
            ,"This is a test"
            ,"http://abc.jpg")

        val secondFeed = Feed("http://abc.com"
            ,"Hello World"
            ,"http://abc.com"
            ,"Jason Nguyen"
            ,"This is a test"
            ,"http://abc.jpg")

        assertTrue(firstFeed == secondFeed)
    }

    @Test fun test_twoDifferentFeeds(){
        val firstFeed = Feed("http://ddc.com"
            ,"Hi World"
            ,"http://ddc.com"
            ,"Quan Nguyen"
            ,"This is a second test"
            ,"http://ddc.jpg")

        val secondFeed = Feed("http://abc.com"
            ,"Hello World"
            ,"http://abc.com"
            ,"Jason Nguyen"
            ,"This is a test"
            ,"http://abc.jpg")

        assertFalse(firstFeed == secondFeed)
    }


}