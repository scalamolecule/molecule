package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.jdbc.facade.{JdbcConn_JVM, JdbcHandler_JVM}
import scala.util.Random
import scala.util.control.NonFatal


trait JdbcTestSuite extends CoreTestSuite with BaseHelpers {

  override val platform = "Jdbc jvm"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    //    println(schema.sqlSchema("h2"))
    lazy val sch =
      """CREATE TABLE A (
        |  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i    INT,
        |  ii   INT ARRAY,
        |  s    LONGVARCHAR,
        |  bool BOOLEAN,
        |  a    BIGINT,
        |  b    BIGINT,
        |  b1   BIGINT,
        |  b2   BIGINT,
        |  c    BIGINT,
        |  d    BIGINT,
        |  ownB BIGINT
        |);
        |
        |CREATE TABLE A_aa_A (
        |  A_1_id BIGINT,
        |  A_2_id BIGINT
        |);
        |
        |CREATE TABLE A_bb_B (
        |  A_id BIGINT,
        |  B_id BIGINT
        |);
        |
        |CREATE TABLE A_cc_C (
        |  A_id BIGINT,
        |  C_id BIGINT
        |);
        |
        |CREATE TABLE A_dd_D (
        |  A_id BIGINT,
        |  D_id BIGINT
        |);
        |
        |CREATE TABLE A_ownBb_B (
        |  A_id BIGINT,
        |  B_id BIGINT
        |);
        |
        |CREATE TABLE B (
        |  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i    INT,
        |  ii   INT ARRAY,
        |  s    LONGVARCHAR,
        |  a    BIGINT,
        |  b    BIGINT,
        |  c    BIGINT,
        |  c1   BIGINT,
        |  d    BIGINT,
        |  ownC BIGINT
        |);
        |
        |CREATE TABLE B_aa_A (
        |  B_id BIGINT,
        |  A_id BIGINT
        |);
        |
        |CREATE TABLE B_bb_B (
        |  B_1_id BIGINT,
        |  B_2_id BIGINT
        |);
        |
        |CREATE TABLE B_cc_C (
        |  B_id BIGINT,
        |  C_id BIGINT
        |);
        |
        |CREATE TABLE B_dd_D (
        |  B_id BIGINT,
        |  D_id BIGINT
        |);
        |
        |CREATE TABLE B_ownCc_C (
        |  B_id BIGINT,
        |  C_id BIGINT
        |);
        |
        |CREATE TABLE C (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  ii INT ARRAY,
        |  a  BIGINT,
        |  d  BIGINT
        |);
        |
        |CREATE TABLE C_dd_D (
        |  C_id BIGINT,
        |  D_id BIGINT
        |);
        |
        |CREATE TABLE D (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  e  BIGINT,
        |  e1 BIGINT
        |);
        |
        |CREATE TABLE D_ee_E (
        |  D_id BIGINT,
        |  E_id BIGINT
        |);
        |
        |CREATE TABLE E (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  f  BIGINT
        |);
        |
        |CREATE TABLE E_ff_F (
        |  E_id BIGINT,
        |  F_id BIGINT
        |);
        |
        |CREATE TABLE F (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  g  BIGINT
        |);
        |
        |CREATE TABLE F_gg_G (
        |  F_id BIGINT,
        |  G_id BIGINT
        |);
        |
        |CREATE TABLE G (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR,
        |  h  BIGINT
        |);
        |
        |CREATE TABLE G_hh_H (
        |  G_id BIGINT,
        |  H_id BIGINT
        |);
        |
        |CREATE TABLE H (
        |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  i  INT,
        |  s  LONGVARCHAR
        |);
        |""".stripMargin

    val proxy = JdbcProxy(
      url,
                  schema.sqlSchema("h2"),
//      sch,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
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
