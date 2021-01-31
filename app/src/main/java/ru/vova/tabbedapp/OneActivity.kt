package ru.vova.tabbedapp


import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import kotlinx.android.synthetic.main.one.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import com.bumptech.glide.request.RequestListener as RequestListener1


private const val TAG = "myLogs"

class OneActivity : AppCompatActivity() {
    var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.one)
        getPrev.isEnabled = false
        getPrev.isClickable = false
        if (amIConnected()) {
            loadData()
        } else {
            mImageView.setImageResource(R.drawable.ic_cloud_off_24px)
            ImageViewText.text = getString(R.string.connectionErr)
        }
        getNext.setOnClickListener {
            Log.v(TAG, memList.size.toString())
            pBar.isVisible = true
            nextGif()

        }
        getPrev.setOnClickListener {
            position--
            showPrew()
            Log.v(TAG, position.toString())
        }
    }


    private fun nextGif() {
        position++
        if (position > memList.size) {
            position = memList.size
        }
        if (position == memList.size) {
            if (amIConnected()) {
                loadData()
            } else {
                mImageView.setImageResource(R.drawable.ic_cloud_off_24px)
                ImageViewText.text = getString(R.string.connectionErr)

            }
        } else
            setGif(memList[position])

    }

    private val memList = mutableListOf<Generator.DataApi>()
    private val scope = CoroutineScope(Dispatchers.IO)
    private val scope2 = CoroutineScope(Dispatchers.Main)
    var page = 0
    private fun loadData() = scope.launch {


        val apiData = URL("https://developerslife.ru/latest/$page?json=true").readText()
        val builder = Gson()
        val simpleData: Generator.ResultApi =
            builder.fromJson<Generator.ResultApi>(apiData, Generator.ResultApi::class.java)
        Log.v(TAG, simpleData.toString())
        if (simpleData.totalCount != 0) {
            memList.addAll(simpleData.result)
            setGif(simpleData.result[0])
            page++

        } else {
            err()
        }
    }

    private fun err() = scope2.launch {
        mImageView.setImageResource(R.drawable.ic_baseline_not_interested_24)
        ImageViewText.text = "раздел недоступен"
    }

    private fun setGif(obj: Generator.DataApi) = scope2.launch {

        if (obj.gifURL != "") {

            Glide.with(applicationContext).asGif().load(obj.gifURL).listener(object :
                RequestListener1<GifDrawable> {
                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    pBar.isVisible = false
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    pBar.isVisible = false
                    mImageView.setImageResource(R.drawable.ic_cloud_off_24px)
                    return false
                }

            }).into(mImageView)
        } else {
            mImageView.setImageResource(R.drawable.ic_baseline_not_interested_24)
            ImageViewText.text = getString(R.string.connectionErr)
        }

        ImageViewText.text = obj.description
        ImageViewText.isVisible = true
        if (obj == memList.first()) {
            getPrev.isEnabled = false
            getPrev.isClickable = false
        } else {
            getPrev.isEnabled = true
            getPrev.isClickable = true
        }
    }

    private fun showPrew() {

        val currentObj = memList[position]
        if (currentObj == memList.first()) {
            getPrev.isEnabled = false
            getPrev.isClickable = false
        }
        setGif(currentObj)
    }

    private fun amIConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}