package models

import java.sql.Date

case class ItemIn(id:Option[Long] =None,time:Date, sku: String, name:String, qty_pesan:Int, qyt_terima:Int, harga_beli:Int, total:Int, kwitansi:String, notes:String)