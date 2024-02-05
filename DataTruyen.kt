package com.example.hetcuu.data

import android.util.Log
import com.example.hetcuu.activity.LoginActivity
import com.example.hetcuu.linkedlist.LinkedList


class DataTruyen {
    companion object {

        private lateinit var TruyenDeCu: ArrayList<Truyen>
        private lateinit var TruyenCapNhat : ArrayList<Truyen>
        private var TruyenTheoDoi : ArrayList<Truyen> = ArrayList()

        private fun setTruyenDeCu() {
            setTruyenCapNhat()
            TruyenDeCu = ArrayList()
            var tempTruyen = TruyenCapNhat
            for (i in 0..3) {
                var tempid = (0..tempTruyen.size-1).random()
                TruyenDeCu.add(tempTruyen[tempid])
                tempTruyen.remove(tempTruyen[tempid])
            }
        }

        private fun setTruyenCapNhat() {
            TruyenCapNhat = ArrayList()
            var listChuongTruyen : LinkedList<ChuongTruyen> = LinkedList()
            listChuongTruyen.addElement(
                "Chương 1: Cửu U Bí Lục ",
                "Thiên Ma Phong đài cao, một vị hắc bào nam tử chính nín thở ngồi ngay ngắn ở phía trên.\n")
            listChuongTruyen.addElement(
                "Chương 2: Ma Hoàng trọng sinh",
                "Đêm như mực ảm đạm, trăng sáng bị âm trầm mây đen che lấp, lộ không ra mảy may hào quang.\n")

            for (i in 3..1311){

                var tempName = "Chương $i:"
                listChuongTruyen.addElement(tempName,"Nội dung $tempName")

            }

            TruyenCapNhat.add(
                    Truyen("https://cdn.truyenfull.com/medias/covers/32/32650-dai-quan-gia-la-ma-hoang-c_cover_large.jpg",
                        "ĐẠI QUẢN GIA LÀ MA HOÀNG",
                        "Dạ Kiêu",
                        "Chương 1311: Đại kết cục",
                        false,
                        tags = listOf("Huyền Huyễn","Trọng Sinh"),
                        listChuongTruyen)
            )

            var nullChuongTruyen : LinkedList<ChuongTruyen> = LinkedList()
            TruyenCapNhat.add(
                Truyen("https://static.cdnno.com/poster/trach-nhat-phi-thang/300.jpg?1649217741",
                    "TRẠCH NHẬT PHI THĂNG",
                    "Thạch Trư",
                    "Chương 956: Phản ra Đạo Minh",
                    false,
                    tags = listOf("Tiên Hiệp","Huyền Huyễn"),
                    listChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://api.bachngocsach.vip/storage/story_img/small_Kph3EgcZb3ClhOTjjTuHI18oKazgYATcta00wYZC.webp",
                    "BÁN TIÊN",
                    "Dược Thiên Sầu",
                    "Chương 1081: Chưởng môn cao kiến",
                    false,
                    tags = listOf("Tiên Hiệp"),
                    nullChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://static.cdnno.com/poster/de-ba/300.jpg?1585205580",
                    "ĐẾ BÁ",
                    "Yếm Bút Tiêu Sinh",
                    "Chương 6231: Tính may mắn",
                    false,
                    tags = listOf("Tiên Hiệp"),
                    nullChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://static.cdnno.com/poster/tien-gia-1/300.jpg?1665814288",
                    "TIÊN GIẢ",
                    "Vong Ngữ",
                    "Chương 576: Vây khốn",
                    false,
                    tags = listOf("Tiên Hiệp"),
                    nullChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://static.cdnno.com/poster/gia-toc-tu-tien-bat-dau-tro-thanh-tran-toc-phap-khi/300.jpg?1686753402",
                    "HUYỀN GIÁM TIÊN TỘC",
                    "Quý Việt Nhân",
                    "Chương 515: Mây khói",
                    false,
                    tags = listOf("Tiên Hiệp"),
                    nullChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://static.cdnno.com/poster/yeu-long-co-de/300.jpg?1585206123",
                    "YÊU LONG CỔ ĐẾ",
                    "Diêu Vọng Nam Sơn",
                    "Chương 6305: Tạ Thượng Trung",
                    false,
                    tags = listOf("Tiên Hiệp","Huyền Huyễn"),
                    nullChuongTruyen)
            )
            TruyenCapNhat.add(
                Truyen("https://truyenchu.vn/uploads/Images/default-image.jpg",
                    "YÊU HẬU MỊ QUỐC",
                    "Quyết Tuyệt",
                    "Chương 34: Tướng Quân",
                    false,
                    tags = listOf( "Kiếm Hiệp", "Ngôn Tình", "Đô Thị", "Hệ Thống", "Huyền Huyễn", "Dị Năng", "Quân Sự", "Dã Sử", "Xuyên Không", "Xuyên Nhanh", "Trọng Sinh", "Sủng", "Cung Đấu", "Nữ Cường", "Hài Hước"),
                    nullChuongTruyen)
            )

        }

        fun setTruyenTheoDoi(){

            val login : LoginActivity = LoginActivity()
            val user : User = login.user
            val followId = user.followId
            Log.d("FollowId",user.followId)
            for (i in 0..TruyenCapNhat.size)
            if (i.toString() in followId){
                TruyenTheoDoi.add(TruyenCapNhat[i])
            }

        }

        fun setTruyenTheoDoi(truyen : Truyen) {
            truyen.isFollow = true
            TruyenTheoDoi.add(truyen)
        }

        fun getTruyenTheoDoi(): ArrayList<Truyen> {
            setTruyenTheoDoi()
            return TruyenTheoDoi
        }

        fun getTruyenDeCu(): ArrayList<Truyen> {
            setTruyenDeCu()
            return TruyenDeCu
        }

        fun getTruyenCapNhat(): ArrayList<Truyen> {
            setTruyenCapNhat()
            return TruyenCapNhat
        }


    }
}