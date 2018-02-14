package http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes._
import models.Item
import services.ItemService
import spray.json.DefaultJsonProtocol

trait Protocols extends SprayJsonSupport with DefaultJsonProtocol{
    implicit val itemFormat = jsonFormat3(Item)
}

class ItemRoute(val itemService:ItemService) extends Protocols{
    val route = logRequestResult("ItemRoutes"){
        pathPrefix("items"){
            pathEndOrSingleSlash{
                get{
                    complete{
                        itemService.getItems()
                    }
                } ~
                post{
                    entity(as[Item]){ itemCreate =>
                        complete{
                            itemService.createItem(itemCreate)
                        }
                    }
                }
            } ~ 
            pathPrefix(String){ sku =>
                get {
                    complete{
                        itemService.getItem(sku)
                    }
                } ~
                put {
                    entity(as[Item]){ itemUpdate => 
                        complete{
                            itemService.updateItem(sku, itemUpdate)
                        }
                    }
                }
            }
        }
    }
}