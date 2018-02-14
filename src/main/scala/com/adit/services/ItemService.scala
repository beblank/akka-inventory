package services

import models.Item
import scala.concurrent.{ExecutionContext, Future}

class ItemService(val databaseService:DatabaseService)(implicit val executionContext:ExecutionContext){
    import databaseService._
    import databaseService.driver.api._

    val items = TableQuery[daoType.Items]

    def getItems():Future[Seq[Item]] = db.run(items.result)
    def getItem(sku:String):Future[Item] = db.run(items.filter(_.sku === sku).result.headOption)
    def createItem(item:Item):Future[Item] = db.run(items returning items.map(_.sku) into ((item, sku) => item.copy(sku=Some(sku))) += item)
    def updateItem(sku:String, toUpdate:Item):Future[Option[Item]] = getItem(sku).flatMap{
        case Some(item) => {
            val updateItem = Item(item.sku, toUpdate.name, toUpdate.qty)
            db.run(items.filter(_.sku === sku).update(updateItem)).map(_ => Some(updateItem))
        }
        case None => Future.successful(None)
    }
}