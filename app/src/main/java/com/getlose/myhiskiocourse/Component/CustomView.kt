package com.getlose.myhiskiocourse.Component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.getlose.myhiskiocourse.R

/**
 *
 * 課程6_4 自定義View：CustomView
 *
 * */
class CustomView: View{
    //畫筆
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null){
        init(context, null)
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    //設定畫筆屬性
    private fun init(context: Context, attrs: AttributeSet?) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f

        //自定義屬性attrs.xml
        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomView,
                0, 0
            )

            //從Layout上 取得預設值(在attrs.xml有設定crossColor屬性)
            paint.color = attributes.getColor(R.styleable.CustomView_crossColor, Color.BLACK)

        }
    }

    //取得customview的長度高度，才知道onDraw要畫多高多寬
    //有可能使用者自己填長寬，有可能寫wrap_content
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        //取得測量後的寬度包含padding值
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        //取得測量後的高度包含padding值
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        // MeasureSpec.AT_MOST => Wrap_Content
        // MeasureSpec.EXACTLY => 固定大小 例100dp，March_Parent
        // MeasureSpec.UNSPECIFIED => 大小未定

        if (widthSpecMode == MeasureSpec.AT_MOST) {
            //wrap_content
        }
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            //指定大小
        }
        if (widthSpecMode == MeasureSpec.UNSPECIFIED) {
            //未定大小
        }
        //設定自訂view的寛或高長度
        setMeasuredDimension(widthSpecSize,heightSpecSize)
    }

    //畫十字的view
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //十字的寬度
        //width是從onMeasure取得
        //paddingLeft,paddingRight是在view有設定padding屬性
        val myWidth = (width - paddingLeft - paddingRight).toFloat()
        //十字的長度
        //height
        //paddingTop,paddingBottom是在view有設定padding屬性
        val myHeight = (height - paddingTop - paddingBottom).toFloat()

        //垂直線
        canvas?.drawLine(
            width.toFloat() / 2,
            paddingTop.toFloat(),
            width.toFloat() / 2,
            myHeight + paddingTop,
            paint
        )

        //水平線
        canvas?.drawLine(
            0f + paddingLeft,
            height.toFloat() / 2,
            myWidth + paddingLeft,
            height.toFloat() / 2,
            paint
        )
    }

}