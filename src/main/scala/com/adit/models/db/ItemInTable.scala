package models.db

import models.ItemIn
import slick.jdbc.JdbcProfile
import java.sql.Date

case class DAOIn(val driver:JdbcProfile){
    import driver.api._

    class ItemsIn(tag:Tag) extends Table[ItemIn](tag, "ITEMS"){
        def id = column[Long]("item_in_id", O.PrimaryKey, O.AutoInc)
        def time = column[Date]("item_in_time")
        def sku = column[String]("item_in_sku")
        def name = column[String]("item_in_name")
        def qty_pesan = column[Int]("item_in_qty_pesan")
        def qty_terima = column[Int]("item_in_qty_terima")
        def harga_beli = column[Int]("item_in_harga_beli")
        def total = column[Int]("item_in_total")
        def kwitansi = column[String]("item_in_kwitansi")
        def notes = column[String]("item_in_notes")

        override def * = (id.?, time, sku, name, qty_pesan, qty_terima, harga_beli,
                            total, kwitansi, notes) <> ((ItemIn.apply _).tupled, ItemIn.unapply)
    }
}