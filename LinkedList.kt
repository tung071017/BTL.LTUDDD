package com.example.hetcuu.linkedlist

import com.example.hetcuu.data.ChuongTruyen
import java.io.Serializable

class LinkedList<T : Any> : Serializable {
    var head : ChuongTruyen? = null
    var size : Int = 0

    fun isEmpty() : Boolean{
        return size == 0
    }

    fun addElement (name: String,data: String){
        val tempNode = ChuongTruyen(null,name,data,null)
        if (isEmpty()){
            head = tempNode
            size+=1
        }
        else{
            var runNode = head
            while (runNode!!.next != null){
                runNode = runNode.next
            }
            runNode.next = tempNode
            tempNode.previous = runNode
            size+=1
        }
    }

    fun getNode(id : Int): ChuongTruyen? {
        var runNode = head
        for (i in 1..id)
            runNode = runNode!!.next
        return runNode
    }


    override fun toString(): String {
        return if(isEmpty()){
            "Empty bro"
        } else {
            head.toString()
        }
    }
}