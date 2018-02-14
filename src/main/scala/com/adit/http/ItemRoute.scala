package http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directive._
import akka.http.scaladsl.model.StatusCodes._
import models.Item
import services.ItemService
import spray.json.DefaultJsonProtocol

trait Protocols extends SprayJsonSupport with DefaultJsonProtocol{
    implicit val itemFormat = jsonFormat3(Item)
}