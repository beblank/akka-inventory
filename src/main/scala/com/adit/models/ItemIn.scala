package models

import java.sql.Timestamp

case class ItemIn(id:Option[Long] =None,time:Timestamp, sku: String, name:String, qty_pesan:Int, 
        qyt_terima:Int, harga_beli:Int, total:Int, kwitansi:String, notes:String)