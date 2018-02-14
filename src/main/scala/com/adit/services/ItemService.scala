package services

import models.Item
import scala.concurrent.{ExecutionContext, Future}

class ItemService(val databaseService:DatabaseService)(implicit val executionContext:ExecutionContext){
    import databaseService._
    import databaseService.driver.api._

    val items = TableQuery[daoType.Items]

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
}