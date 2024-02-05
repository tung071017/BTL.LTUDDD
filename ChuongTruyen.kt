package com.example.hetcuu.data

import java.io.Serializable

class ChuongTruyen (
    var previous : ChuongTruyen?, var tenChuong : String
    , var noiDung : String, var next : ChuongTruyen?) :Serializable {

}