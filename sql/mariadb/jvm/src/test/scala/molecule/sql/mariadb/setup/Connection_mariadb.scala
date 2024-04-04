package molecule.sql.mariadb.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.MariaDBContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM
import scala.util.Random


object Connection_mariadb {

  val n         = Random.nextInt().abs
  val url       = s"jdbc:tc:mariadb:latest:///test$n?allowMultiQueries=true&autoReconnect=true&user=root&password="
  val container = MariaDBContainer()
  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val recreateSchema =
    s"""drop database if exists test$n;
       |create database test$n;
       |use test$n;
       |""".stripMargin

  def recreationStmt(schema: Schema): String = {
    recreateSchema + schema.sqlSchema_mariadb
    //    recreateSchema + sqlSchema_mariadb
  }

  def proxy(schema: Schema) = {
    JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_mariadb,
      //      recreateSchema + sqlSchema_mariadb,
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
  val conn_Partitions = JdbcConn_JVM(proxy(PartitionsSchema), sqlConn)

  val recreateStmt_Types      = recreationStmt(TypesSchema)
  val recreateStmt_Refs       = recreationStmt(RefsSchema)
  val recreateStmt_Uniques    = recreationStmt(UniquesSchema)
  val recreateStmt_Validation = recreationStmt(ValidationSchema)
  val recreateStmt_Partitions = recreationStmt(PartitionsSchema)

  def sqlSchema_mariadb: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i                 INT,
      |  iSet              JSON,
      |  iSeq              JSON,
      |  iMap              JSON,
      |  s                 LONGTEXT,
      |  u                 INT,
      |  string            LONGTEXT,
      |  int_              INT,
      |  long_             BIGINT,
      |  float_            REAL,
      |  double_           DOUBLE,
      |  boolean           TINYINT(1),
      |  bigInt_           DECIMAL(65, 0),
      |  bigDecimal        DECIMAL(65, 30),
      |  date_             BIGINT,
      |  duration          TINYTEXT,
      |  instant           TINYTEXT,
      |  localDate         TINYTEXT,
      |  localTime_        TINYTEXT,
      |  localDateTime     TINYTEXT,
      |  offsetTime        TINYTEXT,
      |  offsetDateTime    TINYTEXT,
      |  zonedDateTime     TINYTEXT,
      |  uuid              TINYTEXT,
      |  uri               TEXT,
      |  byte              TINYINT,
      |  short             SMALLINT,
      |  char_             CHAR,
      |  ref               BIGINT,
      |  other             BIGINT,
      |  stringSet         JSON,
      |  intSet            JSON,
      |  longSet           JSON,
      |  floatSet          JSON,
      |  doubleSet         JSON,
      |  booleanSet        JSON,
      |  bigIntSet         JSON,
      |  bigDecimalSet     JSON,
      |  dateSet           JSON,
      |  durationSet       JSON,
      |  instantSet        JSON,
      |  localDateSet      JSON,
      |  localTimeSet      JSON,
      |  localDateTimeSet  JSON,
      |  offsetTimeSet     JSON,
      |  offsetDateTimeSet JSON,
      |  zonedDateTimeSet  JSON,
      |  uuidSet           JSON,
      |  uriSet            JSON,
      |  byteSet           JSON,
      |  shortSet          JSON,
      |  charSet           JSON,
      |  stringSeq         JSON,
      |  intSeq            JSON,
      |  longSeq           JSON,
      |  floatSeq          JSON,
      |  doubleSeq         JSON,
      |  booleanSeq        JSON,
      |  bigIntSeq         JSON,
      |  bigDecimalSeq     JSON,
      |  dateSeq           JSON,
      |  durationSeq       JSON,
      |  instantSeq        JSON,
      |  localDateSeq      JSON,
      |  localTimeSeq      JSON,
      |  localDateTimeSeq  JSON,
      |  offsetTimeSeq     JSON,
      |  offsetDateTimeSeq JSON,
      |  zonedDateTimeSeq  JSON,
      |  uuidSeq           JSON,
      |  uriSeq            JSON,
      |  byteArray         LONGBLOB,
      |  shortSeq          JSON,
      |  charSeq           JSON,
      |  stringMap         JSON,
      |  intMap            JSON,
      |  longMap           JSON,
      |  floatMap          JSON,
      |  doubleMap         JSON,
      |  booleanMap        JSON,
      |  bigIntMap         JSON,
      |  bigDecimalMap     JSON,
      |  dateMap           JSON,
      |  durationMap       JSON,
      |  instantMap        JSON,
      |  localDateMap      JSON,
      |  localTimeMap      JSON,
      |  localDateTimeMap  JSON,
      |  offsetTimeMap     JSON,
      |  offsetDateTimeMap JSON,
      |  zonedDateTimeMap  JSON,
      |  uuidMap           JSON,
      |  uriMap            JSON,
      |  byteMap           JSON,
      |  shortMap          JSON,
      |  charMap           JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      |  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i                 INT,
      |  s                 LONGTEXT,
      |  string            LONGTEXT,
      |  int_              INT,
      |  long_             BIGINT,
      |  float_            REAL,
      |  double_           DOUBLE,
      |  boolean           TINYINT(1),
      |  bigInt_           DECIMAL(65, 0),
      |  bigDecimal        DECIMAL(65, 30),
      |  date_             BIGINT,
      |  duration          TINYTEXT,
      |  instant           TINYTEXT,
      |  localDate         TINYTEXT,
      |  localTime_        TINYTEXT,
      |  localDateTime     TINYTEXT,
      |  offsetTime        TINYTEXT,
      |  offsetDateTime    TINYTEXT,
      |  zonedDateTime     TINYTEXT,
      |  uuid              TINYTEXT,
      |  uri               TEXT,
      |  byte              TINYINT,
      |  short             SMALLINT,
      |  char_             CHAR,
      |  iSet              JSON,
      |  iSeq              JSON,
      |  sSet              JSON,
      |  stringSet         JSON,
      |  intSet            JSON,
      |  longSet           JSON,
      |  floatSet          JSON,
      |  doubleSet         JSON,
      |  booleanSet        JSON,
      |  bigIntSet         JSON,
      |  bigDecimalSet     JSON,
      |  dateSet           JSON,
      |  durationSet       JSON,
      |  instantSet        JSON,
      |  localDateSet      JSON,
      |  localTimeSet      JSON,
      |  localDateTimeSet  JSON,
      |  offsetTimeSet     JSON,
      |  offsetDateTimeSet JSON,
      |  zonedDateTimeSet  JSON,
      |  uuidSet           JSON,
      |  uriSet            JSON,
      |  byteSet           JSON,
      |  shortSet          JSON,
      |  charSet           JSON,
      |  stringSeq         JSON,
      |  intSeq            JSON,
      |  longSeq           JSON,
      |  floatSeq          JSON,
      |  doubleSeq         JSON,
      |  booleanSeq        JSON,
      |  bigIntSeq         JSON,
      |  bigDecimalSeq     JSON,
      |  dateSeq           JSON,
      |  durationSeq       JSON,
      |  instantSeq        JSON,
      |  localDateSeq      JSON,
      |  localTimeSeq      JSON,
      |  localDateTimeSeq  JSON,
      |  offsetTimeSeq     JSON,
      |  offsetDateTimeSeq JSON,
      |  zonedDateTimeSeq  JSON,
      |  uuidSeq           JSON,
      |  uriSeq            JSON,
      |  byteArray         LONGBLOB,
      |  shortSeq          JSON,
      |  charSeq           JSON
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
      |  iSet    JSON,
      |  sSet    JSON,
      |  select_ INT
      |);
      |""".stripMargin
}
