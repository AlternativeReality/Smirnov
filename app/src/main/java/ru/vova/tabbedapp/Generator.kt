package ru.vova.tabbedapp

private const val TAG = "myLogs"

object Generator {


    data class DataApi(

            val id: String,
            val description: String,
            val votes: Int,
            val author: String,
            val gifURL: String,
            val gifSize: String,
            val previewURL: String,
            val videoURL: String,
            val videoPath: String,
            val videoSize: String,
            val type: String,
            val width: Int,
            val height: Int,
            val commentsCount: Int,
            val fileSize: Int,
            val canVote: Boolean
    )
    data class ResultApi(
        val result: List<DataApi>,
        val totalCount:Int
    )

}
