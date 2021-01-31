package ru.vova.testapp

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*

//import kotlinx.coroutines.experimental.Deferred
//import kotlinx.coroutines.experimental.async
import java.net.URL

private const val CHARACTER_DATA_API =
    "https://miro.medium.com/max/720/1*l0TB3UG7S8rSw3d5_yiVpw.png"
private const val TAG = "myLogs"

object Generator {
//    lateinit var gifUrl: String

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
//    val job = SupervisorJob()
//    val scope = CoroutineScope(Dispatchers.Default + job)
//    fun doWork(): Deferred<Int> = scope.async { Log.v(TAG, "POPA")}
//    fun loadData() = scope.launch {
//
//            doWork().await()
//
//    }
//   fun fromApiData()= scope.launch{
//       withContext(Dispatchers.Default) {
//           val apiData = URL(CHARACTER_DATA_API).readText()
//           var builder = Gson()
//           var simpleData: DataApi = Gson().fromJson<DataApi>(apiData, DataApi::class.java)
//           gifUrl = simpleData.gifURL
//           Log.v(TAG, simpleData.gifURL)
//       }
//       fun fromApiData(){
//
//               val apiData = URL(CHARACTER_DATA_API).readText()
//               var builder = Gson()
//               var simpleData: DataApi = Gson().fromJson<DataApi>(apiData, DataApi::class.java)
//               gifUrl = simpleData.gifURL
//               Log.v(TAG, simpleData.gifURL)
//           }




//    fun foo() = async {
//        val apiData = URL(CHARACTER_DATA_API).readText()
//        var builder = Gson()
//        var simpleData: DataApi = Gson().fromJson<DataApi>(apiData, DataApi::class.java)
//        gifUrl = simpleData.gifURL
//    }
//    fun fromApiData (): Deferred<Int> {
//return async {
//    val apiData = URL(CHARACTER_DATA_API).readText()
//    var builder  = Gson()
//    var simpleData:DataApi = Gson().fromJson<DataApi>(apiData,DataApi::class.java)
//    gifUrl =simpleData.gifURL
//    Log.v(TAG, simpleData.gifURL )
//}

}
