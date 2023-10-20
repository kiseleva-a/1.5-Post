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
        val postForFunAddTest = Post(6,134, 202120, "How are you!", "Jean", true, true, false)
        val result = WallService.add(postForFunAddTest)
        assertNotEquals(result.id, 0)
    }


    @Test
    fun updateExistingId() {
        WallService.add(Post(1, 154, 202020, "I bought a book", "Ivan", true, true, false))
        WallService.add(Post(2, 288, 212020, "I have read this book", "Pat", true, true, false))
        val result = WallService.update(Post(2, 300, 212020, "I didn't finish this book", "Jay", true, true, false))
        assertTrue(result)

    }

    @Test
    fun updateNonExistingId() {
        WallService.add(Post(34, 256, 291123, "I got new book", "Ivan", true, true, true, Likes(2, true, true, true), false))
        WallService.add(Post(58, 367, 121212, "I finished the book", "Jay", true, true, true, Likes(23, true, true, true), false))
        val result = WallService.update(Post(20, 200, 231020, "I dropped the book", "Pat", true, true, true, Likes(12, true, true, true), false))
        assertFalse(result)
    }
}