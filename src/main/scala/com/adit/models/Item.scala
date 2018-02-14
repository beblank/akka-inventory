package models

case class Item(id:Option[Long] =None, sku: String, name:String, qty:Int)