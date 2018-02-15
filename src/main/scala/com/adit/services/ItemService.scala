package services

import models.Item
import models.ItemIn
import scala.concurrent.{ExecutionContext, Future}
import java.sql.Timestamp

class ItemService(val databaseService:DatabaseService)(implicit val executionContext:ExecutionContext){
    import databaseService._
    import databaseService.driver.api._

    val items = TableQuery[daoType.Items]
    val itemsIn = TableQuery[daoType.ItemsIn]

    def getItems():Future[Seq[Item]] = db.run(items.result)
    def getItem(id:Long):Future[Option[Item]] = db.run(items.filter(_.id === id).result.headOption)
    def createItem(item:Item):Future[Item] = db.run(items returning items.map(_.id) into ((item, id) => item.copy(id=Some(id))) += item)
    def updateItem(id:Long, toUpdate:Item):Future[Option[Item]] = getItem(id).flatMap{
        case Some(item) => {
            val updateItem = Item(item.id, toUpdate.sku, toUpdate.name, toUpdate.qty)
            db.run(items.filter(_.id === id).update(updateItem)).map(_ => Some(updateItem))
        }
        case None => Future.successful(None)
    }

    def getItemsIn():Future[Seq[ItemIn]] = db.run(itemsIn.result)
    def getItemIn(id:Long):Future[Option[ItemIn]] = db.run(itemsIn.filter(_.id === id).result.headOption)
    def createItemIn(itemIn:ItemIn):Future[ItemIn] = db.run(itemsIn returning itemsIn.map(_.id) into 
        ((itemIn, id) => itemIn.copy(id=Some(id) )) += itemIn)
    def updateItemIn(id:Long, toUpdate:ItemIn):Future[Option[ItemIn]] = getItemIn(id).flatMap{
        case Some(itemIn) => {
            val updateItemIn = ItemIn(itemIn.id, toUpdate.time, toUpdate.sku, toUpdate.name, toUpdate.qty_pesan, 
                    toUpdate.qyt_terima, toUpdate.harga_beli, toUpdate.total, toUpdate.kwitansi, toUpdate.notes)
            db.run(itemsIn.filter(_.id === id).update(updateItemIn)).map(_ => Some(updateItemIn))
        }
        case None => Future.successful(None)
    }
}