package com.getlose.myhiskiocourse.Component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.getlose.myhiskiocourse.R


//參考 custom view
//1.https://medium.com/evan-android-note/android-tdd-%E7%B3%BB%E5%88%97-14-%E4%BD%BF%E7%94%A8custom-view-components%E6%8F%90%E5%8D%87%E5%8F%AF%E6%B8%AC%E8%A9%A6%E6%80%A7-c9b5cb4d8e22
//2.https://medium.com/@ya.coding/android-%E5%AE%A2%E8%A3%BD%E5%8C%96%E5%85%83%E4%BB%B6-%E7%B0%A1%E6%98%93%E7%89%88-5334681926ff

//《Android》『Shape、Selector、layer-list』- 三種佈局資源檔的差異與交互內嵌的使用方式
//1.https://xnfood.com.tw/android-shape-selector-layer-list/
//selector => https://xnfood.com.tw/android-selector/
//shape => https://xnfood.com.tw/android-shape/
//layer-list => https://xnfood.com.tw/android-layer-list/

class NumberSelectView : LinearLayout {
    private lateinit var addButton: Button
    private lateinit var minusButton: Button
    private lateinit var valueTextView: TextView
    //最小值
    private var minValue: Int = 0
    //最大值
    private var maxValue: Int = 0
    //預設值
    private var defaultValue: Int = 0
    //目前數值
    var textValue: Int = 0
    private var listener: NumberSelectListener? = null
    interface NumberSelectListener {
        fun onValueChange(value: Int)
    }
    constructor(context: Context) : super(context) {
        init(context, null)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }
    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.component_number_select, this)
        descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
        this.addButton = findViewById(R.id.addButton)
        this.minusButton = findViewById(R.id.minusButton)
        this.valueTextView = findViewById(R.id.valueTextView)
        this.textValue = 0
        this.maxValue = Integer.MAX_VALUE
        this.minValue = 0
        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.NumberSelect,
                0, 0
            )
            //從Layout上 取得預設值
            this.maxValue = attributes.getInt(R.styleable.NumberSelect_max_value, this.maxValue)
            this.minValue = attributes.getInt(R.styleable.NumberSelect_min_value, this.minValue)
            this.defaultValue = attributes.getInt(R.styleable.NumberSelect_default_value, 0)
            this.valueTextView.text = defaultValue.toString()
            this.textValue = defaultValue
        }
        //點下「+」Button，將TextValue數字+1，並呼叫listener.onValueChange
        this.addButton.setOnClickListener {
            addTextValue()
            if (listener != null) {
                listener!!.onValueChange(textValue)
            }
        }
        //點下「-」Button，將TextValue數字-1，並呼叫listener.onValueChange
        this.minusButton.setOnClickListener {
            minusTextValue()
            if (listener != null) {
                listener!!.onValueChange(textValue)
            }
        }
    }

    private fun addTextValue() {
        if (this.textValue < this.maxValue) {
            this.textValue++
            this.valueTextView.text = this.textValue.toString()
        }
    }
    private fun minusTextValue() {
        if (this.textValue > this.minValue) {
            this.textValue--
            this.valueTextView.text = this.textValue.toString()
        }
    }

}