package com.semanticsquare.thrillio;

import java.util.List;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + "is is browsing items ...");


		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				boolean isBookmarked = getBookmarkDecision(bookmark);
				if (isBookmarked) {
					BookmarkController.getInstance().saveUserBookmark(user, bookmark);
					System.out.println("New item bookmarked --- " + bookmark);
				}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.EDITOR)) {
					
					// Mark as kid friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
						String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}						
					}
					
					// Sharing
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						
						boolean isShared = getShareDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		double randomVal = Math.random();
		return randomVal < 0.4 ? KidFriendlyStatus.APPROVED
				: (randomVal >= 0.5 && randomVal < 0.8 ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN);
	}

	// TODO: Take input via console instead of simulating user input
	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5;
	}

	private static boolean getShareDecision() {
		return Math.random() < 0.5;		
	}
}
