import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {


    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addIdIsNotZero() {
        val postForFunAddTest = Post(6,134, 202120, "How are you!", "Jean", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(56, 89, "predprosmotr130", "polnorazmer680"))))
        val result = WallService.add(postForFunAddTest)
        assertNotEquals(result.id, 0)
    }


    @Test
    fun updateExistingId() {
        WallService.add(Post(1, 154, 202020, "I bought a book", "Ivan", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(13, 78, "predprosmotr68", "polnorazmer68")), VideoAttachment(Video(85, 3, "Bookhaul", 10)))))
        WallService.add(Post(2, 288, 212020, "I have read this book", "Pat", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(13, 78, "predprosmotr02", "polnorazmer02")), VideoAttachment(Video(50, 3978, "Review", 8)))))
        val result = WallService.update(Post(2, 300, 212020, "I didn't finish this book", "Jay", true, true, false))
        assertTrue(result)

    }

    @Test
    fun updateNonExistingId() {
        WallService.add(Post(34, 256, 291123, "I got new book", "Ivan", true, true, true, Likes(2, true, true, true), false))
        WallService.add(Post(58, 367, 121212, "I finished the book", "Jay", true, true, true, Likes(23, true, true, true), false, attachments = arrayOf(VideoAttachment(Video(8509, 389, "Opinion", 5)))))
        val result = WallService.update(Post(20, 200, 231020, "I dropped the book", "Pat", true, true, true, Likes(12, true, true, true), false, attachments = arrayOf(VideoAttachment(Video(8098, 390, "Opinion", 6)))))
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(23, comment = Comments(23, true))
    }

    @Test
    fun newCommentCreatedSuccessfully() {
        WallService.add(Post(1,134, 202120, "How are you!", "Jean", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(56, 89, "predprosmotr130", "polnorazmer680")))))
        WallService.createComment(1, Comments(34, true))
    }

}