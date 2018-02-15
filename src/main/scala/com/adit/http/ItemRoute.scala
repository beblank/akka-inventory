package http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes._
import models.Item
import models.ItemIn
import services.ItemService
import spray.json._
import spray.json.DefaultJsonProtocol
import java.sql.Timestamp

trait Protocols extends SprayJsonSupport with DefaultJsonProtocol{
    implicit object TimestampFormat extends JsonFormat[Timestamp] {
        def write(obj: Timestamp) = JsNumber(obj.getTime)
            
        def read(json: JsValue) = json match {
            case JsNumber(time) => new Timestamp(time.toLong)

            case _ => throw new DeserializationException("Date expected")
        }
    }
    implicit val itemFormat = jsonFormat4(Item)
    implicit val itemInFormat = jsonFormat10(ItemIn)
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
            path(LongNumber){ id =>
                get {
                    complete{
                        itemService.getItem(id)
                    }
                } ~
                put {
                    entity(as[Item]){ itemUpdate => 
                        complete{
                            itemService.updateItem(id, itemUpdate)
                        }
                    }
                }
            } ~ 
            pathPrefix("in"){
                pathEndOrSingleSlash{
                    get{
                        complete{
                            itemService.getItemsIn()
                        }
                    } ~
                    post{
                        entity(as[ItemIn]){ itemCreate =>
                            complete{
                                itemService.createItemIn(itemCreate)
                            }
                        }
                    }
                }
            } ~ 
            path(LongNumber){ id =>
                get {
                    complete{
                        itemService.getItemIn(id)
                    }
                } ~
                put {
                    entity(as[ItemIn]){ itemUpdate => 
                        complete{
                            itemService.updateItemIn(id, itemUpdate)
                        }
                    }
                }
            }
        }
     } 
}