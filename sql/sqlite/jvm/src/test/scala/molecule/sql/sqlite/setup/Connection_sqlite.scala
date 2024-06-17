//package molecule.sql.sqlite.setup
//
//import java.sql.{Connection, DriverManager}
//import molecule.base.api.Schema
//import molecule.core.marshalling.JdbcProxy
//import molecule.coreTests.dataModels.core.schema._
//import molecule.sql.core.facade.JdbcConn_JVM
//
//
//object Connection_sqlite {
//
//
//  val url = "jdbc:sqlite::memory:"
//  val sqlConn: Connection = DriverManager.getConnection(url)
//
//
//  val recreateSchema =
//    s"""DROP SCHEMA IF EXISTS public CASCADE;
//       |CREATE SCHEMA public;
//       |""".stripMargin
//
//  def recreationStmt(schema: Schema): String = {
//    recreateSchema + schema.sqlSchema_postgres
//  }
//
//  def proxy(schema: Schema) = {
//    JdbcProxy(
//      url,
//      recreateSchema + schema.sqlSchema_postgres,
//      schema.metaSchema,
//      schema.nsMap,
//      schema.attrMap,
//      schema.uniqueAttrs,
//      reserved = schema.sqlReserved_postgres,
//      useTestContainer = true
//    )
//  }
//
//  val conn_Types      = JdbcConn_JVM(proxy(TypesSchema), sqlConn)
//  val conn_Refs       = JdbcConn_JVM(proxy(RefsSchema), sqlConn)
//  val conn_Uniques    = JdbcConn_JVM(proxy(UniquesSchema), sqlConn)
//  val conn_Validation = JdbcConn_JVM(proxy(ValidationSchema), sqlConn)
//  val conn_Partitions = JdbcConn_JVM(proxy(PartitionsSchema), sqlConn)
//
//  val recreateStmt_Types      = recreationStmt(TypesSchema)
//  val recreateStmt_Refs       = recreationStmt(RefsSchema)
//  val recreateStmt_Uniques    = recreationStmt(UniquesSchema)
//  val recreateStmt_Validation = recreationStmt(ValidationSchema)
//  val recreateStmt_Partitions = recreationStmt(PartitionsSchema)
//}
