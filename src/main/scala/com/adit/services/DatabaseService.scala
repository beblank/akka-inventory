package services

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import models.db.DAO
import slick.jdbc.JdbcProfile

class DatabaseService(jdbcUrl:String, user:String, password:String, driver:JdbcProfile){
    private val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(jdbcUrl)
    hikariConfig.setUsername(user)
    hikariConfig.setPassword(password)

    val dataSource = new HikariDataSource(hikariConfig)

    private val flywayService = new FlywayService(dataSource)
    flywayService.migrateDatabaseSchema()

    import driver.api._
    val db = Database.forDataSource(dataSource, Some(10))

    val daoType = DAO(driver)
    db.createSession()
}

