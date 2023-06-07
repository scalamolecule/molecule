package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.facade.{JdbcConn_jvm, JdbcHandler_jvm}
import scala.util.Random
import scala.util.control.NonFatal


trait JdbcTestSuite extends CoreTestSuite {

  override val platform = "Jdbc jvm"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
//      println(schema.sqlSchema("h2"))
    val sch =
  """CREATE TABLE Ns (
    |  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    |  i           INT,
    |  ii          INT,
    |  s           LONGVARCHAR,
    |  u           INT,
    |  string      LONGVARCHAR,
    |  int         INT,
    |  long        BIGINT,
    |  float       DOUBLE,
    |  double      DOUBLE,
    |  boolean     BOOLEAN,
    |  bigInt      DECIMAL,
    |  bigDecimal  DECIMAL(65535, 25),
    |  date        DATE,
    |  uuid        UUID,
    |  uri         VARCHAR,
    |  byte        TINYINT,
    |  short       SMALLINT,
    |  char        CHAR,
    |  ref         BIGINT,
    |  other       BIGINT,
    |  strings     LONGVARCHAR ARRAY,
    |  ints        INT ARRAY,
    |  longs       BIGINT ARRAY,
    |  floats      DOUBLE ARRAY,
    |  doubles     DOUBLE ARRAY,
    |  booleans    BOOLEAN ARRAY,
    |  bigInts     DECIMAL ARRAY,
    |  bigDecimals DECIMAL(65535, 25) ARRAY,
    |  dates       DATE ARRAY,
    |  uuids       UUID ARRAY,
    |  uris        VARCHAR ARRAY,
    |  bytes       TINYINT ARRAY,
    |  shorts      SMALLINT ARRAY,
    |  chars       CHAR ARRAY
    |);
    |
    |CREATE TABLE Ns_refs_Ref (
    |  Ns_id  BIGINT,
    |  Ref_id BIGINT
    |);
    |
    |CREATE TABLE Ref (
    |  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    |  i           INT,
    |  s           LONGVARCHAR,
    |  string      LONGVARCHAR,
    |  int         INT,
    |  long        BIGINT,
    |  float       DOUBLE,
    |  double      DOUBLE,
    |  boolean     BOOLEAN,
    |  bigInt      DECIMAL,
    |  bigDecimal  DECIMAL(65535, 25),
    |  date        DATE,
    |  uuid        UUID,
    |  uri         VARCHAR,
    |  byte        TINYINT,
    |  short       SMALLINT,
    |  char        CHAR,
    |  ii          INT,
    |  ss          LONGVARCHAR,
    |  strings     LONGVARCHAR,
    |  ints        INT,
    |  longs       BIGINT,
    |  floats      DOUBLE,
    |  doubles     DOUBLE,
    |  booleans    BOOLEAN,
    |  bigInts     DECIMAL,
    |  bigDecimals DECIMAL(65535, 25),
    |  dates       DATE,
    |  uuids       UUID,
    |  uris        VARCHAR,
    |  bytes       TINYINT,
    |  shorts      SMALLINT,
    |  chars       CHAR
    |);
    |
    |CREATE TABLE Ref_nss_Ns (
    |  Ref_id BIGINT,
    |  Ns_id  BIGINT
    |);
    |
    |CREATE TABLE Other (
    |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
    |  i  INT,
    |  s  LONGVARCHAR,
    |  ii INT,
    |  ss LONGVARCHAR
    |);""".stripMargin

    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
//      sch,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    var conn  = JdbcConn_jvm(proxy, null)
    try {
      Class.forName("org.h2.Driver")
      conn = JdbcHandler_jvm.recreateDb(proxy, url)
      test(conn)
    } catch {
      case NonFatal(exc) => throw exc
    } finally {
      conn.sqlConn.close()
    }
  }
}
