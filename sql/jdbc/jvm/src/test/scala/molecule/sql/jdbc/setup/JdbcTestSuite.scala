package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
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
        |  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  created   BIGINT,
        |  updated   BIGINT,
        |  oneUnique BIGINT,
        |  oneRef    BIGINT,
        |  oneOther  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyUnique_Unique (
        |  Tx_id     BIGINT,
        |  Unique_id BIGINT
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
        |CREATE TABLE Uniques (
        |  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx         BIGINT,
        |  i          INT,
        |  s          LONGVARCHAR,
        |  string     LONGVARCHAR,
        |  int        INT,
        |  long       BIGINT,
        |  float      REAL,
        |  //float      DOUBLE,
        |  double     DOUBLE PRECISION,
        |  boolean    BOOLEAN,
        |  bigInt     DECIMAL(100, 0),
        |  bigDecimal DECIMAL(65535, 25),
        |  date       DATE,
        |  uuid       UUID,
        |  uri        VARCHAR,
        |  byte       TINYINT,
        |  short      SMALLINT,
        |  char       CHAR,
        |  ints       INT ARRAY,
        |  ref        BIGINT
        |);
        |
        |CREATE TABLE Uniques_refs_Ref (
        |  Unique_id BIGINT,
        |  Ref_id    BIGINT
        |);
        |
        |CREATE TABLE Ref (
        |  id  BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx  BIGINT,
        |  i   INT,
        |  s   LONGVARCHAR,
        |  int INT
        |);
        |
        |CREATE TABLE Other (
        |  id  BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx  BIGINT,
        |  i   INT,
        |  s   LONGVARCHAR,
        |  int INT
        |);
        |""".stripMargin

    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
//            sch,
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
}
