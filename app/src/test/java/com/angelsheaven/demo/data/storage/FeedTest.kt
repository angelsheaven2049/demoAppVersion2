package com.angelsheaven.demo.data.storage

import com.angelsheaven.demo.data.Feed
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class FeedTest {

    @Test fun `test two feeds are same`(){
        val firstFeed = Feed(
            "http://abc.com"
            , "Hello World"
            , "http://abc.com"
            , "Jason Nguyen"
            , "This is a test"
            , "http://abc.jpg"
        )

        val secondFeed = Feed(
            "http://abc.com"
            , "Hello World"
            , "http://abc.com"
            , "Jason Nguyen"
            , "This is a test"
            , "http://abc.jpg"
        )

        assertTrue(firstFeed == secondFeed)
    }

    @Test fun `test two seeds are different`(){
        val firstFeed = Feed(
            "http://ddc.com"
            , "Hi World"
            , "http://ddc.com"
            , "Quan Nguyen"
            , "This is a second test"
            , "http://ddc.jpg"
        )

        val secondFeed = Feed(
            "http://abc.com"
            , "Hello World"
            , "http://abc.com"
            , "Jason Nguyen"
            , "This is a test"
            , "http://abc.jpg"
        )

        assertFalse(firstFeed == secondFeed)
    }


}