package molecule.sql.h2.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import scala.util.Random
import scala.util.control.NonFatal


trait TestSuite_h2 extends CoreTestSuite with BaseHelpers {

  override val database = "H2"
  override val platform = "jvm"

  val sqlSchema_h2: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      | id             BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i               INT,
      |  ii              INT ARRAY,
      |  s               LONGVARCHAR,
      |  u               INT,
      |  string          LONGVARCHAR,
      |  int             INT,
      |  long            BIGINT,
      |  float           REAL,
      |  double          DOUBLE PRECISION,
      |  boolean         BOOLEAN,
      |  bigInt          DECIMAL(100, 0),
      |  bigDecimal      DECIMAL(65535, 25),
      |  date            DATE,
      |  duration        VARCHAR,
      |  instant         VARCHAR,
      |  localDate       VARCHAR,
      |  localTime_      VARCHAR,
      |  localDateTime   VARCHAR,
      |  offsetTime      VARCHAR,
      |  offsetDateTime  VARCHAR,
      |  zonedDateTime   VARCHAR,
      |  uuid            UUID,
      |  uri             VARCHAR,
      |  byte            TINYINT,
      |  short           SMALLINT,
      |  char            CHAR,
      |  ref             BIGINT,
      |  other           LONGVARCHAR,
      |  strings         LONGVARCHAR ARRAY,
      |  ints            INT ARRAY,
      |  longs           BIGINT ARRAY,
      |  floats          REAL ARRAY,
      |  doubles         DOUBLE PRECISION ARRAY,
      |  booleans        BOOLEAN ARRAY,
      |  bigInts         DECIMAL(100, 0) ARRAY,
      |  bigDecimals     DECIMAL(65535, 25) ARRAY,
      |  dates           DATE ARRAY,
      |  durations       VARCHAR ARRAY,
      |  instants        VARCHAR ARRAY,
      |  localDates      VARCHAR ARRAY,
      |  localTimes      VARCHAR ARRAY,
      |  localDateTimes  VARCHAR ARRAY,
      |  offsetTimes     VARCHAR ARRAY,
      |  offsetDateTimes VARCHAR ARRAY,
      |  zonedDateTimes  VARCHAR ARRAY,
      |  uuids           UUID ARRAY,
      |  uris            VARCHAR ARRAY,
      |  bytes           TINYINT ARRAY,
      |  shorts          SMALLINT ARRAY,
      |  chars           CHAR ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      | id             BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i               INT,
      |  s               LONGVARCHAR,
      |  string          LONGVARCHAR,
      |  int             INT,
      |  long            BIGINT,
      |  float           REAL,
      |  double          DOUBLE PRECISION,
      |  boolean         BOOLEAN,
      |  bigInt          DECIMAL(100, 0),
      |  bigDecimal      DECIMAL(65535, 25),
      |  date            DATE,
      |  duration        VARCHAR,
      |  instant         VARCHAR,
      |  localDate       VARCHAR,
      |  localTime_      VARCHAR,
      |  localDateTime   VARCHAR,
      |  offsetTime      VARCHAR,
      |  offsetDateTime  VARCHAR,
      |  zonedDateTime   VARCHAR,
      |  uuid            UUID,
      |  uri             VARCHAR,
      |  byte            TINYINT,
      |  short           SMALLINT,
      |  char            CHAR,
      |  ii              INT ARRAY,
      |  ss              LONGVARCHAR ARRAY,
      |  strings         LONGVARCHAR ARRAY,
      |  ints            INT ARRAY,
      |  longs           BIGINT ARRAY,
      |  floats          REAL ARRAY,
      |  doubles         DOUBLE PRECISION ARRAY,
      |  booleans        BOOLEAN ARRAY,
      |  bigInts         DECIMAL(100, 0) ARRAY,
      |  bigDecimals     DECIMAL(65535, 25) ARRAY,
      |  dates           DATE ARRAY,
      |  durations       VARCHAR ARRAY,
      |  instants        VARCHAR ARRAY,
      |  localDates      VARCHAR ARRAY,
      |  localTimes      VARCHAR ARRAY,
      |  localDateTimes  VARCHAR ARRAY,
      |  offsetTimes     VARCHAR ARRAY,
      |  offsetDateTimes VARCHAR ARRAY,
      |  zonedDateTimes  VARCHAR ARRAY,
      |  uuids           UUID ARRAY,
      |  uris            VARCHAR ARRAY,
      |  bytes           TINYINT ARRAY,
      |  shorts          SMALLINT ARRAY,
      |  chars           CHAR ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref_nss_Ns (
      |  Ref_id BIGINT,
      |  Ns_id  BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Other (
      | id     BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i       INT,
      |  s       LONGVARCHAR,
      |  ii      INT ARRAY,
      |  ss      LONGVARCHAR ARRAY,
      |  select_ INT
      |);
      |
      |CREATE ALIAS IF NOT EXISTS has_String         FOR "molecule.sql.h2.functions.has_String";
      |CREATE ALIAS IF NOT EXISTS has_Int            FOR "molecule.sql.h2.functions.has_Int";
      |CREATE ALIAS IF NOT EXISTS has_Long           FOR "molecule.sql.h2.functions.has_Long";
      |CREATE ALIAS IF NOT EXISTS has_Float          FOR "molecule.sql.h2.functions.has_Float";
      |CREATE ALIAS IF NOT EXISTS has_Double         FOR "molecule.sql.h2.functions.has_Double";
      |CREATE ALIAS IF NOT EXISTS has_Boolean        FOR "molecule.sql.h2.functions.has_Boolean";
      |CREATE ALIAS IF NOT EXISTS has_BigInt         FOR "molecule.sql.h2.functions.has_BigInt";
      |CREATE ALIAS IF NOT EXISTS has_BigDecimal     FOR "molecule.sql.h2.functions.has_BigDecimal";
      |CREATE ALIAS IF NOT EXISTS has_Date           FOR "molecule.sql.h2.functions.has_Date";
      |CREATE ALIAS IF NOT EXISTS has_Duration       FOR "molecule.sql.h2.functions.has_Duration";
      |CREATE ALIAS IF NOT EXISTS has_Instant        FOR "molecule.sql.h2.functions.has_Instant";
      |CREATE ALIAS IF NOT EXISTS has_LocalDate      FOR "molecule.sql.h2.functions.has_LocalDate";
      |CREATE ALIAS IF NOT EXISTS has_LocalTime      FOR "molecule.sql.h2.functions.has_LocalTime";
      |CREATE ALIAS IF NOT EXISTS has_LocalDateTime  FOR "molecule.sql.h2.functions.has_LocalDateTime";
      |CREATE ALIAS IF NOT EXISTS has_OffsetTime     FOR "molecule.sql.h2.functions.has_OffsetTime";
      |CREATE ALIAS IF NOT EXISTS has_OffsetDateTime FOR "molecule.sql.h2.functions.has_OffsetDateTime";
      |CREATE ALIAS IF NOT EXISTS has_ZonedDateTime  FOR "molecule.sql.h2.functions.has_ZonedDateTime";
      |CREATE ALIAS IF NOT EXISTS has_UUID           FOR "molecule.sql.h2.functions.has_UUID";
      |CREATE ALIAS IF NOT EXISTS has_URI            FOR "molecule.sql.h2.functions.has_URI";
      |CREATE ALIAS IF NOT EXISTS has_Byte           FOR "molecule.sql.h2.functions.has_Byte";
      |CREATE ALIAS IF NOT EXISTS has_Short          FOR "molecule.sql.h2.functions.has_Short";
      |CREATE ALIAS IF NOT EXISTS has_Char           FOR "molecule.sql.h2.functions.has_Char";
      |
      |CREATE ALIAS IF NOT EXISTS hasNo_String         FOR "molecule.sql.h2.functions.hasNo_String";
      |CREATE ALIAS IF NOT EXISTS hasNo_Int            FOR "molecule.sql.h2.functions.hasNo_Int";
      |CREATE ALIAS IF NOT EXISTS hasNo_Long           FOR "molecule.sql.h2.functions.hasNo_Long";
      |CREATE ALIAS IF NOT EXISTS hasNo_Float          FOR "molecule.sql.h2.functions.hasNo_Float";
      |CREATE ALIAS IF NOT EXISTS hasNo_Double         FOR "molecule.sql.h2.functions.hasNo_Double";
      |CREATE ALIAS IF NOT EXISTS hasNo_Boolean        FOR "molecule.sql.h2.functions.hasNo_Boolean";
      |CREATE ALIAS IF NOT EXISTS hasNo_BigInt         FOR "molecule.sql.h2.functions.hasNo_BigInt";
      |CREATE ALIAS IF NOT EXISTS hasNo_BigDecimal     FOR "molecule.sql.h2.functions.hasNo_BigDecimal";
      |CREATE ALIAS IF NOT EXISTS hasNo_Date           FOR "molecule.sql.h2.functions.hasNo_Date";
      |CREATE ALIAS IF NOT EXISTS hasNo_Duration       FOR "molecule.sql.h2.functions.hasNo_Duration";
      |CREATE ALIAS IF NOT EXISTS hasNo_Instant        FOR "molecule.sql.h2.functions.hasNo_Instant";
      |CREATE ALIAS IF NOT EXISTS hasNo_LocalDate      FOR "molecule.sql.h2.functions.hasNo_LocalDate";
      |CREATE ALIAS IF NOT EXISTS hasNo_LocalTime      FOR "molecule.sql.h2.functions.hasNo_LocalTime";
      |CREATE ALIAS IF NOT EXISTS hasNo_LocalDateTime  FOR "molecule.sql.h2.functions.hasNo_LocalDateTime";
      |CREATE ALIAS IF NOT EXISTS hasNo_OffsetTime     FOR "molecule.sql.h2.functions.hasNo_OffsetTime";
      |CREATE ALIAS IF NOT EXISTS hasNo_OffsetDateTime FOR "molecule.sql.h2.functions.hasNo_OffsetDateTime";
      |CREATE ALIAS IF NOT EXISTS hasNo_ZonedDateTime  FOR "molecule.sql.h2.functions.hasNo_ZonedDateTime";
      |CREATE ALIAS IF NOT EXISTS hasNo_UUID           FOR "molecule.sql.h2.functions.hasNo_UUID";
      |CREATE ALIAS IF NOT EXISTS hasNo_URI            FOR "molecule.sql.h2.functions.hasNo_URI";
      |CREATE ALIAS IF NOT EXISTS hasNo_Byte           FOR "molecule.sql.h2.functions.hasNo_Byte";
      |CREATE ALIAS IF NOT EXISTS hasNo_Short          FOR "molecule.sql.h2.functions.hasNo_Short";
      |CREATE ALIAS IF NOT EXISTS hasNo_Char           FOR "molecule.sql.h2.functions.hasNo_Char";
      |""".stripMargin


  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url   = "jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_h2,
//      sqlSchema_h2,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_h2
    )
    var conn  = JdbcConn_JVM(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = JdbcHandler_JVM.recreateDb(proxy)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      if (conn.sqlConn != null) {
        conn.sqlConn.close()
      }
    }
  }
}
