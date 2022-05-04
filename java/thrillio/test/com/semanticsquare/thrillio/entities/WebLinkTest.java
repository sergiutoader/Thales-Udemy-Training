package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.managers.BookmarkManager;

public class WebLinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1: Porn in url -- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");
		
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in url - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2: Porn in title -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in title - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 3: adult in host -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaadult.com");
		
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For adult in host - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 4: adult in url but not in host part -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-adult/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For adult in urld but not in host part - isKidFriendlyEligible() must return true", isKidFriendlyEligible);
		
		// Test 5: adult in title only -- true 
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For adult in urld but not in host part - isKidFriendlyEligible() must return true", isKidFriendlyEligible);
	}

}
