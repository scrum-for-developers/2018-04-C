Meta:
@themes Book

Narrative:
In order to give back a single book
As a borrower
I want to return that book by specifying the email and the isbn

Scenario:
Given an empty library
And borrower <borrower1> has borrowed books <isbns1>
And borrower <borrower1> has borrowed books <isbn_ret>
And borrower <borrower2> has borrowed books <isbns2>
When borrower <borrower1> returns the book <isbn_ret>
Then books <isbn_ret> are not borrowed anymore by borrower <borrower1>
And books <isbns1> are still borrowed by borrower <borrower1>
And books <isbns2> are still borrowed by borrower <borrower2>

Examples:    
    
| borrower1       | isbns1                | isbn_ret   | borrower2       | isbns2                |
| user1@dings.com | 0321293533            | 123456789X |                 |                       |
| user1@dings.com | 0321293533            | 123456789X | user2@dings.com | 1234567962            |
| user1@dings.com | 0321293533 1234567962 | 123456789X |                 |                       |
| user1@dings.com | 0321293533 1234567962 | 123456789X | user2@dings.com | 7784484156 1126108624 |