package com.example.hetcuu.data

import com.example.hetcuu.linkedlist.LinkedList
import java.io.Serializable

data class RecyclerTruyen (val viewType: Int) : Serializable {
    var rcvListTruyen :List<Truyen>? = null

    constructor(viewType: Int, rcvListTruyen: List<Truyen>) : this(viewType) {
        this.rcvListTruyen = rcvListTruyen
    }
}

data class Truyen(
    var image: String, var name: String, var author: String,
    var newchap: String, var isFollow: Boolean,
    var tags : List<String>,
    var listChuongTruyen: LinkedList<ChuongTruyen>) : Serializable {
}