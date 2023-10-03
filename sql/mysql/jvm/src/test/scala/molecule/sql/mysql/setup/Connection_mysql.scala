package molecule.sql.mysql.setup

import java.sql.DriverManager
import java.util.Properties
import com.dimafeng.testcontainers.MySQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM


object Connection_mysql {

  val url       = "jdbc:tc:mysql:8.1://localhost:3306/test?allowMultiQueries=true"
  val container = MySQLContainer()
  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val sqlSchema_mysql: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i           INT,
      |  ii          JSON,
      |  s           LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  u           INT,
      |  string_     LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  int_        INT,
      |  long_       BIGINT,
      |  float_      REAL,
      |  double_     DOUBLE,
      |  boolean_    TINYINT(1),
      |  bigInt_     DECIMAL(65, 0),
      |  bigDecimal  DECIMAL(65, 30),
      |  date_       BIGINT,
      |  uuid        TINYTEXT,
      |  uri         TEXT,
      |  byte_       TINYINT,
      |  short       SMALLINT,
      |  char_       CHAR,
      |  ref         BIGINT,
      |  other       BIGINT,
      |  strings     JSON,
      |  ints        JSON,
      |  longs       JSON,
      |  floats      JSON,
      |  doubles     JSON,
      |  booleans    JSON,
      |  bigInts     JSON,
      |  bigDecimals JSON,
      |  dates       JSON,
      |  uuids       JSON,
      |  uris        JSON,
      |  bytes       JSON,
      |  shorts      JSON,
      |  chars       JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      |  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i           INT,
      |  s           LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  string_     LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  int_        INT,
      |  long_       BIGINT,
      |  float_      REAL,
      |  double_     DOUBLE,
      |  boolean_    TINYINT(1),
      |  bigInt_     DECIMAL(65, 0),
      |  bigDecimal  DECIMAL(65, 30),
      |  date_       BIGINT,
      |  uuid        TINYTEXT,
      |  uri         TEXT,
      |  byte_       TINYINT,
      |  short       SMALLINT,
      |  char_       CHAR,
      |  ii          JSON,
      |  ss          JSON,
      |  strings     JSON,
      |  ints        JSON,
      |  longs       JSON,
      |  floats      JSON,
      |  doubles     JSON,
      |  booleans    JSON,
      |  bigInts     JSON,
      |  bigDecimals JSON,
      |  dates       JSON,
      |  uuids       JSON,
      |  uris        JSON,
      |  bytes       JSON,
      |  shorts      JSON,
      |  chars       JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref_nss_Ns (
      |  Ref_id BIGINT,
      |  Ns_id  BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Other (
      |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i  INT,
      |  s  LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  ii JSON,
      |  ss JSON
      |);
      |""".stripMargin

  val recreateSchema =
    s"""drop database if exists test;
       |create database test;
       |use test;
       |""".stripMargin


  def recreationStmt(schema: Schema): String = {
    //    recreateSchema + sqlSchema_mysql
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

  val recreateStmt_Types      = recreationStmt(TypesSchema)
  val recreateStmt_Refs       = recreationStmt(RefsSchema)
  val recreateStmt_Uniques    = recreationStmt(UniquesSchema)
  val recreateStmt_Validation = recreationStmt(ValidationSchema)
}
