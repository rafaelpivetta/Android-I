package com.exercicioiesb.casa.viewscustomizadas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import java.util.*

class SonarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    var heightY: Int = 0
    var widthX: Int = 0
    var padding: Int = 0
    var fontSize: Float = 0f
    var numeralSpacing: Int = 0
    var handTruncation: Int = 0
    var hourHandTruncation: Int = 0
    var radius: Int = 0
    var paint = Paint()
    var isInit: Boolean = false;
    var angulosPrincipais: IntArray = intArrayOf( 0, 1, 2, 3, 4, 5, 6, 7, 8 )
    var graus: IntArray = IntArray(180)
    var sentidoHorario = true
    var indiceGraus: Int = 0
    var rect = Rect()
    var rectF = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!isInit) {
            inicializarSonar()
        }

        canvas.drawColor(Color.BLACK)
        drawArc(canvas)
        drawCenter(canvas)
        drawAngles(canvas)
        drawArrow(canvas, graus.get(indiceGraus))


        if(sentidoHorario) {
            if (indiceGraus == (graus.size-1)){
                sentidoHorario = false
            }else{
                indiceGraus += 1
            }
        }else{
            if (indiceGraus == 0){
                sentidoHorario = true
            }else{
                indiceGraus -= 1
            }
        }

        postInvalidateDelayed(25)
    }


    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f, height / 2f, 6f, paint)
    }

    private fun drawArc(canvas: Canvas){
        paint.reset()
        paint.setColor(Color.GREEN)
        paint.setStrokeWidth(5f)
        paint.style = Paint.Style.STROKE

        rectF.set(padding.toFloat(), padding.toFloat(), (width-padding).toFloat(), height - padding.toFloat());
        canvas.drawArc(rectF, 180f, 180f, true, paint);
    }

    private fun drawAngles(canvas: Canvas) {

        paint.textSize = fontSize

        for(number in angulosPrincipais){
            var tmp: String = (number * 22.5f).toString()
            paint.getTextBounds(tmp, 0, tmp.length, rect)

            var angle: Double = Math.PI / 8 * ( number - 8 )
            var x: Float = ((width / 2) + (Math.cos(angle) * radius) - (rect.width() / 2)).toFloat()
            var y: Float = ((height / 2) + (Math.sin(angle) * radius) - (rect.height() / 2)).toFloat()

            canvas.drawText(tmp, x, y, paint)

//            var halfPI = Math.PI / 2
//            Log.i("angle", halfPI.toString()+" $x $y")
//            if(angle == (Math.PI / 2f)){
//                Log.i("angle half 1", angle.toString())
//            }

//            canvas.rotate(angle.toFloat(), 50 + x + rect.exactCenterX(), 50 + y + rect.exactCenterY());

        }
    }

    private fun drawArrow(canvas: Canvas, location: Double) {

        var angle: Double = Math.PI / graus.size * (location) - Math.PI

        var handRadius: Int = radius - handTruncation

        canvas.drawLine(width / 2f, height / 2f, (width / 2f + Math.cos(angle) * handRadius).toFloat(), (height / 2f + Math.sin(angle) * handRadius).toFloat(), paint)

    }

    private fun drawArrow(canvas: Canvas, numero: Int) {
        drawArrow(canvas, numero.toDouble())
    }


    fun inicializarSonar(){
        heightY = height
        widthX = width
        for (i in 1..graus.size) {
            graus.set(i-1, i)
        }
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
        var min: Int = Math.min(heightY, widthX)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        isInit = true
    }


}
