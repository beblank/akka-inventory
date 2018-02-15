package models.db

import models.Item
import org.scalatest.{BeforeAndAfter, FlatSpec}
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta._

import scala.concurrent.duration._
import scala.concurrent.Await

class ItemTableTest extends FlatSpec with BeforeAndAfter{
    var db:Database = _
    implicit var session:Session = _
    val daoType = DAO(MySQLProfile)
    val items = TableQuery[daoType.Items]

    before{
        db = Database.forConfig("testdb")
        session = db.createSession()
        items.delete
    }

    "Operations using item entity" should "work" in {
        createSchema()
        val tables = Await.result(db.run(MTable.getTables), 2 seconds)
        assert(tables.size == 1)
        Await.result(db.run(items += Item(sku = "SSI-D123", name="baju kotak", qty=12)), 2 seconds)
        Await.result(db.run(items += Item(sku = "SSI-D124", name="baju koko", qty=10)), 2 seconds)

        val q = for {
            i <- items
        } yield(i)
    }

    def createSchema() =
        Await.result(db.run((items.schema).create), 2 seconds)

    after { db.close }
}


