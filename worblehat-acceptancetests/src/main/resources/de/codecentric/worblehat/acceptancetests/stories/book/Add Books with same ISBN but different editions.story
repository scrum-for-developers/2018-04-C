Meta:
@themes Book

Narrative:
In order to add new books to the library
As a librarian
I want to add books through the website

Scenario:

Given an empty library
When a librarian adds a book with title <title>, author <author>, edition <edition>, year <year> and isbn <isbn>
And a librarian adds a book with title <title>, author <author>, edition <edition2>, year <year> and isbn <isbn2>
Then the library contains <copies> of the book with <isbn> and <edition>
And the library contains <copies2> of the book with <isbn2> and <edition2>


Examples:

| isbn       | author           | title     | edition   | year  | edition2  |     isbn2  | copies | copies2 |
| 0552131075 | Terry Pratchett  | Sourcery  | 1         | 1989  |    1      | 0552131075 |   2    |    2    |
| 0552131075 | Terry Pratchett  | Sourcery  | 1         | 1989  |    2      | 0552131075 |   1    |    0    |
| 0552131075 | Terry Pratchett  | Sourcery  | 1         | 1989  |    2      | 123456789X |   1    |    1    |


