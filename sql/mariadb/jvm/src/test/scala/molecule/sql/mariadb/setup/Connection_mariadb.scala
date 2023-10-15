package molecule.sql.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM


object Connection_mariadb {

  val url       = "jdbc:tc:mariadb:latest:///test?allowMultiQueries=true"
  val container = MariaDBContainer()
  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val sqlSchema_mariadb: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i               INT,
      |  ii              JSON,
      |  s               LONGTEXT,
      |  u               INT,
      |  string          LONGTEXT,
      |  int_            INT,
      |  long_           BIGINT,
      |  float_          REAL,
      |  double_         DOUBLE,
      |  boolean         TINYINT(1),
      |  bigInt_         DECIMAL(65, 0),
      |  bigDecimal      DECIMAL(65, 30),
      |  date_           BIGINT,
      |  duration        TINYTEXT,
      |  instant         TINYTEXT,
      |  localDate       TINYTEXT,
      |  localTime_      TINYTEXT,
      |  localDateTime   TINYTEXT,
      |  offsetTime      TINYTEXT,
      |  offsetDateTime  TINYTEXT,
      |  zonedDateTime   TINYTEXT,
      |  uuid            TINYTEXT,
      |  uri             TEXT,
      |  byte            TINYINT,
      |  short           SMALLINT,
      |  char_           CHAR,
      |  ref             BIGINT,
      |  other           BIGINT,
      |  strings         JSON,
      |  ints            JSON,
      |  longs           JSON,
      |  floats          JSON,
      |  doubles         JSON,
      |  booleans        JSON,
      |  bigInts         JSON,
      |  bigDecimals     JSON,
      |  dates           JSON,
      |  durations       JSON,
      |  instants        JSON,
      |  localDates      JSON,
      |  localTimes      JSON,
      |  localDateTimes  JSON,
      |  offsetTimes     JSON,
      |  offsetDateTimes JSON,
      |  zonedDateTimes  JSON,
      |  uuids           JSON,
      |  uris            JSON,
      |  bytes           JSON,
      |  shorts          JSON,
      |  chars           JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      |  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i               INT,
      |  s               LONGTEXT,
      |  string          LONGTEXT,
      |  int_            INT,
      |  long_           BIGINT,
      |  float_          REAL,
      |  double_         DOUBLE,
      |  boolean         TINYINT(1),
      |  bigInt_         DECIMAL(65, 0),
      |  bigDecimal      DECIMAL(65, 30),
      |  date_           BIGINT,
      |  duration        TINYTEXT,
      |  instant         TINYTEXT,
      |  localDate       TINYTEXT,
      |  localTime_      TINYTEXT,
      |  localDateTime   TINYTEXT,
      |  offsetTime      TINYTEXT,
      |  offsetDateTime  TINYTEXT,
      |  zonedDateTime   TINYTEXT,
      |  uuid            TINYTEXT,
      |  uri             TEXT,
      |  byte            TINYINT,
      |  short           SMALLINT,
      |  char_           CHAR,
      |  ii              JSON,
      |  ss              JSON,
      |  strings         JSON,
      |  ints            JSON,
      |  longs           JSON,
      |  floats          JSON,
      |  doubles         JSON,
      |  booleans        JSON,
      |  bigInts         JSON,
      |  bigDecimals     JSON,
      |  dates           JSON,
      |  durations       JSON,
      |  instants        JSON,
      |  localDates      JSON,
      |  localTimes      JSON,
      |  localDateTimes  JSON,
      |  offsetTimes     JSON,
      |  offsetDateTimes JSON,
      |  zonedDateTimes  JSON,
      |  uuids           JSON,
      |  uris            JSON,
      |  bytes           JSON,
      |  shorts          JSON,
      |  chars           JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref_nss_Ns (
      |  Ref_id BIGINT,
      |  Ns_id  BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Other (
      |  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i       INT,
      |  s       LONGTEXT,
      |  ii      JSON,
      |  ss      JSON,
      |  select_ INT
      |);
      |""".stripMargin

  val recreateSchema =
    s"""drop database if exists test;
       |create database test;
       |use test;
       |""".stripMargin


  def recreationStmt(schema: Schema): String = {
    //        recreateSchema + sqlSchema_mariadb
    recreateSchema + schema.sqlSchema_mariadb
  }

  def proxy(schema: Schema) = {
    JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_mariadb,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mariadb,
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
