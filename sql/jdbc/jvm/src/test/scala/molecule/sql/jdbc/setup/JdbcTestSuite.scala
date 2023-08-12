package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.facade.{JdbcConn_jvm, JdbcHandler_jvm}
import scala.util.Random
import scala.util.control.NonFatal


trait JdbcTestSuite extends CoreTestSuite with BaseHelpers {

  override val platform = "Jdbc jvm"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    //    println(schema.sqlSchema("h2"))
    lazy val sch =
      """CREATE TABLE Tx (
        |  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  created  BIGINT,
        |  updated  BIGINT,
        |  myTxAttr INT,
        |  oneNs    BIGINT,
        |  oneRef   BIGINT,
        |  oneOther BIGINT
        |);
        |
        |CREATE TABLE Tx_manyNs_Ns (
        |  Tx_id BIGINT,
        |  Ns_id BIGINT
        |);
        |
        |CREATE TABLE Tx_manyRef_Ref (
        |  Tx_id  BIGINT,
        |  Ref_id BIGINT
        |);
        |
        |CREATE TABLE Tx_manyOther_Other (
        |  Tx_id    BIGINT,
        |  Other_id BIGINT
        |);
        |
        |CREATE TABLE Ns (
        |  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx          BIGINT,
        |  i           INT,
        |  ii          INT ARRAY,
        |  s           LONGVARCHAR,
        |  u           INT,
        |  string      LONGVARCHAR,
        |  int         INT,
        |  long        BIGINT,
        |  float       DOUBLE,
        |  double      DOUBLE,
        |  boolean     BOOLEAN,
        |  bigInt      DECIMAL(100, 0),
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
        |  bigInts     DECIMAL(100, 0) ARRAY,
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
        |  tx          BIGINT,
        |  i           INT,
        |  s           LONGVARCHAR,
        |  string      LONGVARCHAR,
        |  int         INT,
        |  long        BIGINT,
        |  float       DOUBLE,
        |  double      DOUBLE,
        |  boolean     BOOLEAN,
        |  bigInt      DECIMAL(100, 0),
        |  bigDecimal  DECIMAL(65535, 25),
        |  date        DATE,
        |  uuid        UUID,
        |  uri         VARCHAR,
        |  byte        TINYINT,
        |  short       SMALLINT,
        |  char        CHAR,
        |  ii          INT ARRAY,
        |  ss          LONGVARCHAR ARRAY,
        |  strings     LONGVARCHAR ARRAY,
        |  ints        INT ARRAY,
        |  longs       BIGINT ARRAY,
        |  floats      DOUBLE ARRAY,
        |  doubles     DOUBLE ARRAY,
        |  booleans    BOOLEAN ARRAY,
        |  bigInts     DECIMAL(100, 0) ARRAY,
        |  bigDecimals DECIMAL(65535, 25) ARRAY,
        |  dates       DATE ARRAY,
        |  uuids       UUID ARRAY,
        |  uris        VARCHAR ARRAY,
        |  bytes       TINYINT ARRAY,
        |  shorts      SMALLINT ARRAY,
        |  chars       CHAR ARRAY
        |);
        |
        |CREATE TABLE Ref_nss_Ns (
        |  Ref_id BIGINT,
        |  Ns_id  BIGINT
        |);
        |
        |CREATE TABLE Other (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx BIGINT,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  ii INT ARRAY,
        |  ss LONGVARCHAR ARRAY
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
      if (conn.sqlConn != null) {
        conn.sqlConn.close()
      }
    }
  }

  def printQuery(q: String)(implicit conn: Conn): Unit = {
    val c             = conn.asInstanceOf[JdbcConn_jvm].sqlConn
    val statement     = c.createStatement()
    val resultSet     = statement.executeQuery(q)
    val rsmd          = resultSet.getMetaData
    val columnsNumber = rsmd.getColumnCount
    println("-----------------------------------------------------------------------------")
    println(q)
    while (resultSet.next) {
      var i = 1
      while (i <= columnsNumber) {
        val col         = rsmd.getColumnName(i)
        val columnValue = resultSet.getString(i)
        if (columnValue != null)
          println(col + padS(55, col) + columnValue)
        i += 1
      }
      println("--------------")
    }
  }
}
