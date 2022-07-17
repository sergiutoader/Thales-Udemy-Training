package com.semanticsquare.thrillio.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
	
	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);
		
		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink)bookmark);
		}
		
		return result;
	}
	
	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		return getAllWebLinks().stream()
				.filter(weblink -> weblink.getDownloadStatus() == downloadStatus)
				.collect(Collectors.toList());
	}
}
