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
    val attachments: Array<Attachment> = emptyArray()

    )

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canPublish: Boolean = true,
)

interface Attachment {
    val type: String
}

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String

)
data class PhotoAttachment(
    val photo: Photo
): Attachment {
    override val type: String = "photo"
    override fun toString(): String {
        return "type: $type and photo: $photo"
    }
}

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int

)
data class VideoAttachment(
    val video: Video
): Attachment {
    override val type: String = "video"
    override fun toString(): String {
        return "type: $type and video: $video"
    }
}

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

    WallService.add(Post(1, 156, 202020, "hello!", "manager", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(3, 2, "predprosmotr1", "polnorazmer2")), VideoAttachment(Video(5, 34, "Cute Dog", 2)))))
    WallService.add(Post(2, 241, 212020, "hi!", "Alex", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(13, 78, "predprosmotr2", "polnorazmer2")), VideoAttachment(Video(55, 39, "Cute Cat", 1)))))
    WallService.update(Post(2, 241, 212020, "Hi!", "Mary", true, true, false, attachments = arrayOf(PhotoAttachment(Photo(24, 90, "predprosmotr3", "polnorazmer3")))))
    WallService.printPosts()
}