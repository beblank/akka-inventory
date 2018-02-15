import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import http.ItemRoute
import services.{DatabaseService, ItemService}
import slick.jdbc.MySQLProfile
import akka.http.scaladsl.Http
import scala.io.StdIn

object MainApp extends App{
    implicit val system = ActorSystem()
    implicit val executor = system.dispatcher
    implicit val materializer = ActorMaterializer()

    val config = ConfigFactory.load()
    val logger = Logging(system, getClass)

    val dbConfig = config.getConfig("inventorydb")
    val DatabaseService = new DatabaseService(
        dbConfig.getString("url"), 
        dbConfig.getString("user"), 
        dbConfig.getString("password"), 
        MySQLProfile)

    val itemService = new ItemService(DatabaseService)
    val ItemRoute = new ItemRoute(itemService)
    val route = ItemRoute.route

    val port = if (sys.env.contains("PORT")) sys.env("PORT").toInt else config.getInt("http.port")
    val bindingFuture = Http().bindAndHandle(route, "localhost", port)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
}