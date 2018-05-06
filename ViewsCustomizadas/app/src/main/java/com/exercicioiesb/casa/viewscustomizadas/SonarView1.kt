package com.exercicioiesb.casa.viewscustomizadas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*

class SonarView1 @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    var altura: Int = 0
    var largura: Int = 0
    var padding: Int = 0
    var fontSize: Float = 0f
    var numeralSpacing: Int = 0
    var handTruncation: Int = 0
    var hourHandTruncation: Int = 0
    var radius: Int = 0
    var paint = Paint()
    var isInit: Boolean = false;
    var numbers: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    var rect = Rect()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(!isInit){
            inicializarSonar()
        }

        canvas.drawColor(Color.BLACK)
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f, height / 2f, 12f, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        paint.setColor(Color.WHITE)
        paint.setStrokeWidth(5f)
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        canvas.drawCircle(width/2f, height/2f, radius + padding -10f, paint)
    }

    private fun drawNumeral(canvas: Canvas) {
        paint.textSize = fontSize

        for(number in numbers){
            var tmp: String = number.toString()
            paint.getTextBounds(tmp, 0, tmp.length, rect)
            var angle: Double = Math.PI / 6 * (number - 3)
            var x: Float = (width / 2 + Math.cos(angle) * radius - rect.width() / 2).toFloat()
            var y: Float = (height / 2 + Math.sin(angle) * radius - rect.height() / 2).toFloat()
            canvas.drawText(tmp, x, y, paint)
        }
    }

    private fun drawHands(canvas: Canvas, loc: Double, isHour: Boolean) {
        var angle: Double = Math.PI * loc / 30 - Math.PI / 2
        var handRadius: Int = when(isHour) {
            true -> radius - handTruncation - hourHandTruncation
            false -> radius - handTruncation
        }
        canvas.drawLine(width / 2f, height /2f, (width / 2f + Math.cos(angle) * handRadius).toFloat(), (height / 2f + Math.sin(angle) * handRadius).toFloat(), paint)

    }

    private fun drawHands(canvas: Canvas) {
        var c : Calendar = Calendar.getInstance()
        var hour: Float = c.get(Calendar.HOUR_OF_DAY).toFloat()
        hour = when (hour>12){
            true -> hour -12
            false -> hour
        }
        drawHands(canvas, (hour + c.get(Calendar.MINUTE) / 60) * 5.0, true)
        drawHands(canvas, c.get(Calendar.MINUTE).toDouble(), false)
        drawHands(canvas, c.get(Calendar.SECOND).toDouble(), false)
    }


    fun inicializarSonar(){
        this.altura = height
        this.largura = width
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
        var min: Int = Math.min(altura, largura)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        isInit = true
    }


}
