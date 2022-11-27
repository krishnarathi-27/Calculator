package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.item.*
import net.objecthunter.exp4j.ExpressionBuilder

private var canAddOperation = false
private var canAddDecimal = true
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun allClearAction(view: View)
    {
        inputText.text = ""
        outputText.text = ""
    }

    fun backspaceAction(view: View)
    {
        val length = inputText.text.length
        if(length>0)
        {
            inputText.text = inputText.text.subSequence(0,length-1)
        }
    }

    fun onButtonClicked(view: View)
    {
        if(view is Button)
        {
            if(view.text == ".")
            {
                if(canAddDecimal)
                    inputText.append(view.text)

                canAddDecimal=false
            }
            else
                inputText.append(view.text)
            canAddOperation = true
        }
    }

    fun operationAction(view: View)
    {
        if(view is Button && canAddOperation)
        {
            inputText.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun equalsAction(view: View)
    {
        val expression = ExpressionBuilder(inputText.text.toString()).build()
        val result = expression.evaluate()
        val longresult = result.toLong()

        if(result == longresult.toDouble())
        {
            outputText.text = longresult.toString()
        }
        else
        {
            outputText.text = result.toString()
        }
    }
}