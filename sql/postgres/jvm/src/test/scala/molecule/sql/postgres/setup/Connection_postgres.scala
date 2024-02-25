package molecule.sql.postgres.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.PostgreSQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM


object Connection_postgres {

  val url = "jdbc:tc:postgresql:15://localhost:5432/test?preparedStatementCacheQueries=0"

  val container = PostgreSQLContainer()
  // For fast reuse, set testcontainers.reuse.enable=true in ~/.testcontainers.properties
  // See https://callistaenterprise.se/blogg/teknik/2020/10/09/speed-up-your-testcontainers-tests/

  //  // These don't seem to make any difference:
  //  container.container.withReuse(true)
  //  container.container.withDatabaseName("test")
  ////  container.container.withUsername("sa")
  ////  container.container.withPassword("sa")
  //  container.container.withLabel("reuse.UUID", "e06d7a87-7d7d-472e-a047-e6c81f61d2a4");


  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)


  val recreateSchema =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin

  def recreationStmt(schema: Schema): String = {
    recreateSchema + schema.sqlSchema_postgres
  }

  def proxy(schema: Schema) = {
    JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_postgres,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_postgres,
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
