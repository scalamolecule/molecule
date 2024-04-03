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

  override val platform = "jvm"
  override val database = "H2"

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

  val sqlSchema_h2: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  i                 INT,
      |  iSet              INT ARRAY,
      |  iSeq              INT ARRAY,
      |  s                 LONGVARCHAR,
      |  u                 INT,
      |  string            LONGVARCHAR,
      |  int               INT,
      |  long              BIGINT,
      |  float             REAL,
      |  double            DOUBLE PRECISION,
      |  boolean           BOOLEAN,
      |  bigInt            DECIMAL(100, 0),
      |  bigDecimal        DECIMAL(65535, 25),
      |  date              BIGINT,
      |  duration          VARCHAR,
      |  instant           VARCHAR,
      |  localDate         VARCHAR,
      |  localTime_        VARCHAR,
      |  localDateTime     VARCHAR,
      |  offsetTime        VARCHAR,
      |  offsetDateTime    VARCHAR,
      |  zonedDateTime     VARCHAR,
      |  uuid              UUID,
      |  uri               VARCHAR,
      |  byte              TINYINT,
      |  short             SMALLINT,
      |  char              CHAR,
      |  ref               BIGINT,
      |  other             BIGINT,
      |  stringSet         LONGVARCHAR ARRAY,
      |  intSet            INT ARRAY,
      |  longSet           BIGINT ARRAY,
      |  floatSet          REAL ARRAY,
      |  doubleSet         DOUBLE PRECISION ARRAY,
      |  booleanSet        BOOLEAN ARRAY,
      |  bigIntSet         DECIMAL(100, 0) ARRAY,
      |  bigDecimalSet     DECIMAL(65535, 25) ARRAY,
      |  dateSet           BIGINT ARRAY,
      |  durationSet       VARCHAR ARRAY,
      |  instantSet        VARCHAR ARRAY,
      |  localDateSet      VARCHAR ARRAY,
      |  localTimeSet      VARCHAR ARRAY,
      |  localDateTimeSet  VARCHAR ARRAY,
      |  offsetTimeSet     VARCHAR ARRAY,
      |  offsetDateTimeSet VARCHAR ARRAY,
      |  zonedDateTimeSet  VARCHAR ARRAY,
      |  uuidSet           UUID ARRAY,
      |  uriSet            VARCHAR ARRAY,
      |  byteSet           TINYINT ARRAY,
      |  shortSet          SMALLINT ARRAY,
      |  charSet           CHAR ARRAY,
      |  stringSeq         LONGVARCHAR ARRAY,
      |  intSeq            INT ARRAY,
      |  longSeq           BIGINT ARRAY,
      |  floatSeq          REAL ARRAY,
      |  doubleSeq         DOUBLE PRECISION ARRAY,
      |  booleanSeq        BOOLEAN ARRAY,
      |  bigIntSeq         DECIMAL(100, 0) ARRAY,
      |  bigDecimalSeq     DECIMAL(65535, 25) ARRAY,
      |  dateSeq           BIGINT ARRAY,
      |  durationSeq       VARCHAR ARRAY,
      |  instantSeq        VARCHAR ARRAY,
      |  localDateSeq      VARCHAR ARRAY,
      |  localTimeSeq      VARCHAR ARRAY,
      |  localDateTimeSeq  VARCHAR ARRAY,
      |  offsetTimeSeq     VARCHAR ARRAY,
      |  offsetDateTimeSeq VARCHAR ARRAY,
      |  zonedDateTimeSeq  VARCHAR ARRAY,
      |  uuidSeq           UUID ARRAY,
      |  uriSeq            VARCHAR ARRAY,
      |  byteArray         VARBINARY,
      |  shortSeq          SMALLINT ARRAY,
      |  charSeq           CHAR ARRAY,
      |  stringMap         LONGVARCHAR ARRAY,
      |  intMap            JSON,
      |  longMap           BIGINT ARRAY,
      |  floatMap          REAL ARRAY,
      |  doubleMap         DOUBLE PRECISION ARRAY,
      |  booleanMap        BOOLEAN ARRAY,
      |  bigIntMap         DECIMAL(100, 0) ARRAY,
      |  bigDecimalMap     DECIMAL(65535, 25) ARRAY,
      |  dateMap           BIGINT ARRAY,
      |  durationMap       VARCHAR ARRAY,
      |  instantMap        VARCHAR ARRAY,
      |  localDateMap      VARCHAR ARRAY,
      |  localTimeMap      VARCHAR ARRAY,
      |  localDateTimeMap  VARCHAR ARRAY,
      |  offsetTimeMap     VARCHAR ARRAY,
      |  offsetDateTimeMap VARCHAR ARRAY,
      |  zonedDateTimeMap  VARCHAR ARRAY,
      |  uuidMap           UUID ARRAY,
      |  uriMap            VARCHAR ARRAY,
      |  byteMap           TINYINT ARRAY,
      |  shortMap          SMALLINT ARRAY,
      |  charMap           CHAR ARRAY
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
      |  s                 LONGVARCHAR,
      |  string            LONGVARCHAR,
      |  int               INT,
      |  long              BIGINT,
      |  float             REAL,
      |  double            DOUBLE PRECISION,
      |  boolean           BOOLEAN,
      |  bigInt            DECIMAL(100, 0),
      |  bigDecimal        DECIMAL(65535, 25),
      |  date              BIGINT,
      |  duration          VARCHAR,
      |  instant           VARCHAR,
      |  localDate         VARCHAR,
      |  localTime_        VARCHAR,
      |  localDateTime     VARCHAR,
      |  offsetTime        VARCHAR,
      |  offsetDateTime    VARCHAR,
      |  zonedDateTime     VARCHAR,
      |  uuid              UUID,
      |  uri               VARCHAR,
      |  byte              TINYINT,
      |  short             SMALLINT,
      |  char              CHAR,
      |  iSet              INT ARRAY,
      |  iSeq              INT ARRAY,
      |  sSet              LONGVARCHAR ARRAY,
      |  stringSet         LONGVARCHAR ARRAY,
      |  intSet            INT ARRAY,
      |  longSet           BIGINT ARRAY,
      |  floatSet          REAL ARRAY,
      |  doubleSet         DOUBLE PRECISION ARRAY,
      |  booleanSet        BOOLEAN ARRAY,
      |  bigIntSet         DECIMAL(100, 0) ARRAY,
      |  bigDecimalSet     DECIMAL(65535, 25) ARRAY,
      |  dateSet           BIGINT ARRAY,
      |  durationSet       VARCHAR ARRAY,
      |  instantSet        VARCHAR ARRAY,
      |  localDateSet      VARCHAR ARRAY,
      |  localTimeSet      VARCHAR ARRAY,
      |  localDateTimeSet  VARCHAR ARRAY,
      |  offsetTimeSet     VARCHAR ARRAY,
      |  offsetDateTimeSet VARCHAR ARRAY,
      |  zonedDateTimeSet  VARCHAR ARRAY,
      |  uuidSet           UUID ARRAY,
      |  uriSet            VARCHAR ARRAY,
      |  byteSet           TINYINT ARRAY,
      |  shortSet          SMALLINT ARRAY,
      |  charSet           CHAR ARRAY,
      |  stringSeq         LONGVARCHAR ARRAY,
      |  intSeq            INT ARRAY,
      |  longSeq           BIGINT ARRAY,
      |  floatSeq          REAL ARRAY,
      |  doubleSeq         DOUBLE PRECISION ARRAY,
      |  booleanSeq        BOOLEAN ARRAY,
      |  bigIntSeq         DECIMAL(100, 0) ARRAY,
      |  bigDecimalSeq     DECIMAL(65535, 25) ARRAY,
      |  dateSeq           BIGINT ARRAY,
      |  durationSeq       VARCHAR ARRAY,
      |  instantSeq        VARCHAR ARRAY,
      |  localDateSeq      VARCHAR ARRAY,
      |  localTimeSeq      VARCHAR ARRAY,
      |  localDateTimeSeq  VARCHAR ARRAY,
      |  offsetTimeSeq     VARCHAR ARRAY,
      |  offsetDateTimeSeq VARCHAR ARRAY,
      |  zonedDateTimeSeq  VARCHAR ARRAY,
      |  uuidSeq           UUID ARRAY,
      |  uriSeq            VARCHAR ARRAY,
      |  byteArray         VARBINARY,
      |  shortSeq          SMALLINT ARRAY,
      |  charSeq           CHAR ARRAY
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
      |  s       LONGVARCHAR,
      |  iSet    INT ARRAY,
      |  sSet    LONGVARCHAR ARRAY,
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
}
