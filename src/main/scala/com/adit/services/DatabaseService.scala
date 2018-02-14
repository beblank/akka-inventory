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

}

