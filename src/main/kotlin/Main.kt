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
    val comments: Comments = Comments(0, true),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val isFavorite: Boolean = true,
    val markedAsAds: Boolean = false,
    val postponedId: Int = 678,
    val geo: Geo = Geo(),
    val fromId: Int = 9087,
    val createdBy: Int = 4567,
    val friendsOnly: Boolean = false,
    val replyOwnerId: Int = 56840,
    val replyPostId: Int = 3569,
    val attachments: Array<Attachment> = emptyArray()
)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 789
)
data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canPublish: Boolean = true,
)

data class Geo(
    val type: String = "forest",
    val coordinates: String = "37°25'19.07\"С, 122°05'06.24\"З"
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

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String
)

data class AudioAttachment(
    val audio: Audio
): Attachment {
    override val type: String = "audio"
    override fun toString(): String {
        return "type: $type and audio: $audio"
    }
}


data class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String
)

data class LinkAttachment(
    val link: Link
): Attachment {
    override val type: String = "link"
    override fun toString(): String {
        return "type: $type and link: $link"
    }
}


data class File(
    val id: Int = 5678,
    val ownerId: Int,
    val title: String = "My dog",
    val size: Int = 12678,
    val ext: String = "png"

)
data class FileAttachment(
    val file: File
): Attachment {
    override val type: String = "file"
    override fun toString(): String {
        return "type: $type and file: $file"
    }
}

class PostNotFoundException(message: String) : RuntimeException(message)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var lastId = 0

     fun createComment(postId: Int, comment: Comments): Comments {
        val post = posts.find { it.id == postId } ?: throw PostNotFoundException("Post $postId is not found")
        comments += comment
        return comment
    }


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