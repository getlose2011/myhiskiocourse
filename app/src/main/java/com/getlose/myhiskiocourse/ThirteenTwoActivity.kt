package com.getlose.myhiskiocourse

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.getlose.myhiskiocourse.databinding.ActivityThirteenTwoBinding
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ThirteenTwoActivity : AppCompatActivity() {

    private val TAG = ThirteenTwoActivity::class.java.simpleName
    private lateinit var binding : ActivityThirteenTwoBinding
    private val url = "https://picsum.photos/300"

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityThirteenTwoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //載入圖片用原本的url
        binding.button2.setOnClickListener {
            Glide
                .with(this)
                .load(url)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.default_image)
                .into(binding.imageView2)

            binding.textView2.text = ""
        }

        binding.button3.setOnClickListener {
           Glide
               .with(this)
               .asBitmap()
               .load(url)
               .skipMemoryCache(true)
               .diskCacheStrategy(DiskCacheStrategy.NONE)
               .into(object : CustomTarget<Bitmap>() {
                   override fun onResourceReady(
                       bitmap: Bitmap,
                       transition: Transition<in Bitmap>?
                   ) {
                       var path = saveImage(bitmap,"test.png")

                       binding.textView2.text = "路徑=>$path"
                   }

                   override fun onLoadCleared(placeholder: Drawable?) {

                   }

               })
        }
    }

    //存檔
    private fun saveImage(bitmap:Bitmap,imageName:String):String?{
        //取得路徑
        //image => 資料夾名稱
        val dir: File = ContextWrapper(applicationContext).getDir("image", Context.MODE_PRIVATE)
        val path = File(dir,imageName)

        //創建輸出流，寫入圖片
        var fileOutputStream:FileOutputStream?=null
        try{
            fileOutputStream = FileOutputStream(path)
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fileOutputStream)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            fileOutputStream?.close()
        }
        //路徑
        return dir.absolutePath
    }
}