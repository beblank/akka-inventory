package models.db

import models.Item
import slick.jdbc.JdbcProfile

case class DAO(val driver:JdbcProfile){
    import driver.api._

    class Items(tag:Tag) extends Table[Item](tag, "ITEMS"){
        def id = column[Long]("item_id", O.PrimaryKey, O.AutoInc)
        def sku = column[String]("item_sku")
        def name = column[String]("item_name")
        def qty = column[Int]("item_qty")

        override def * = (id.?, sku, name, qty) <> ((Item.apply _).tupled, Item.unapply)
    }
}