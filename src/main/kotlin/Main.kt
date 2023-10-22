data class Post(
    var id: Int,
    val ownerId: Int,
    val date: Int,
    val text: String? = null,
    val copyright: String? = null,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val likes: Likes = Likes(0, true, true, true),
    val isPinned: Boolean = false,

    )

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canPublish: Boolean = true,
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy()
                return true
            }
        }
        return false
    }

    fun printPosts() {
        for (post in posts) {
            print(post)
            print(' ')
        }
        println()
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}

fun main() {

    WallService.add(Post(1, 156, 202020, "hello!", "manager", true, true, false))
    WallService.add(Post(2, 241, 212020, "hi!", "Alex", true, true, false))
    WallService.update(Post(2, 241, 212020, "Hi!", "Mary", true, true, false))
    WallService.printPosts()
}