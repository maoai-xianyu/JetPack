package com.mao.jetpack.ui.coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mao.base.net.RetrofitFactory
import com.mao.jetpack.image.ImageSplitter
import com.mao.jetpack.model.DataModel
import com.mao.jetpack.model.ImageModel
import com.mao.jetpack.model.ResultModel
import com.mao.jetpack.nethttp.HttpKey
import com.mao.jetpack.ui.coroutine.repo.ImageRepository
import com.mao.jetpack.ui.coroutine.repo.JokeRepository
import com.mao.jetpack.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 *
 * @author zhangkun
 * @time 2022/3/25 15:28
 * @Description
 */
class CoroutineViewModel : ViewModel() {
    val image4x4First: MutableLiveData<Bitmap> = MutableLiveData()
    val image9x9Last: MutableLiveData<Bitmap> = MutableLiveData()

    val jokeOne: MutableLiveData<ResultModel> = MutableLiveData()

    val jokeList: MutableLiveData<List<DataModel>> = MutableLiveData()

    val stringField: ObservableField<ImageModel> = ObservableField<ImageModel>()

    init {
        stringField.set(
            ImageModel(
                "http://img5.mtime.cn/pi/2022/01/24/144146.59288160_1000X1000.jpg",
                "测试初始图片"
            )
        )
    }

    fun getName(): String = stringField.get()?.name ?: ""

    fun setName(str: String) {
        Logger.debug("双向绑定 $str")
        stringField.get()?.name = str
    }


    fun getSingleJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            // 处理协程的异常
            try {
                val singleJoke = JokeRepository().queryJoke(HttpKey.JOKE_KEY)
                singleJoke.result?.let {
                    jokeOne.postValue(singleJoke.result[0])
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getJokeList() {
        viewModelScope.launch {
            try {
                val list = JokeRepository().queryJokeList(HttpKey.JOKE_KEY, 1, 20)
                if (list.result != null) {
                    list.result.data?.let {
                        jokeList.postValue(list.result.data)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getImage4x4() {

        viewModelScope.launch {

            kotlin.runCatching {
                val rbody =
                    ImageRepository().downloadPicFromNet("https://img5.mtime.cn/pi/2022/01/24/144146.59288160_1000X1000.jpg")

                val bytes = rbody.bytes()

                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                val split = ImageSplitter.split(bitmap, 2, 2)

                image4x4First.postValue(split.first().bitmap)


            }

        }

    }

    fun getImage9x9() {

        viewModelScope.launch {

            kotlin.runCatching {
                val rbody =
                    ImageRepository().downloadPicFromNet("https://img5.mtime.cn/pi/2022/01/24/144146.59288160_1000X1000.jpg")

                val bytes = rbody.bytes()

                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                val split = ImageSplitter.split(bitmap, 3, 3)

                image9x9Last.postValue(split.last().bitmap)


            }
        }

    }

}

fun Bitmap.Bitmap2Bytes(): ByteArray {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos);
    return baos.toByteArray()
}