package molecule.sql.mysql.setup

import java.sql.DriverManager
import java.util.Properties
import com.dimafeng.testcontainers.MySQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM
import scala.util.Random


object Connection_mysql {
  val n         = Random.nextInt().abs
  val url       = s"jdbc:tc:mysql:8.1://localhost:3306/test$n?allowMultiQueries=true&autoReconnect=true&user=root&password="
  val container = MySQLContainer()
  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val recreateSchema =
    s"""drop database if exists test$n;
       |create database test$n;
       |use test$n;
       |""".stripMargin


  def recreationStmt(schema: Schema): String = {
    recreateSchema + schema.sqlSchema_mysql
  }

  def proxy(schema: Schema) = {
    JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_mysql,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mysql,
      useTestContainer = true
    )
  }

  val conn_Types      = JdbcConn_JVM(proxy(TypesSchema), sqlConn)
  val conn_Refs       = JdbcConn_JVM(proxy(RefsSchema), sqlConn)
  val conn_Uniques    = JdbcConn_JVM(proxy(UniquesSchema), sqlConn)
  val conn_Validation = JdbcConn_JVM(proxy(ValidationSchema), sqlConn)
  val conn_Partitions = JdbcConn_JVM(proxy(PartitionsSchema), sqlConn)

  val recreateStmt_Types      = recreationStmt(TypesSchema)
  val recreateStmt_Refs       = recreationStmt(RefsSchema)
  val recreateStmt_Uniques    = recreationStmt(UniquesSchema)
  val recreateStmt_Validation = recreationStmt(ValidationSchema)
  val recreateStmt_Partitions = recreationStmt(PartitionsSchema)
}
