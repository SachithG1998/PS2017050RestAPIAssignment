package lk.ac.kln.stu.ps2017050restapi.model

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)