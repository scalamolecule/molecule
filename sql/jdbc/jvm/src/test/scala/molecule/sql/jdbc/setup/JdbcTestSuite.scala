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

    lazy val schemaRefs =
      """CREATE TABLE Tx (
        |  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  created  BIGINT,
        |  updated  BIGINT,
        |  myTxAttr INT,
        |  category BIGINT,
        |  oneA     BIGINT,
        |  oneB     BIGINT,
        |  oneC     BIGINT,
        |  oneD     BIGINT,
        |  oneE     BIGINT,
        |  oneF     BIGINT,
        |  oneG     BIGINT,
        |  oneH     BIGINT
        |);
        |
        |CREATE TABLE Tx_tags_C (
        |  Tx_id BIGINT,
        |  C_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyA_A (
        |  Tx_id BIGINT,
        |  A_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyB_B (
        |  Tx_id BIGINT,
        |  B_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyC_C (
        |  Tx_id BIGINT,
        |  C_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyD_D (
        |  Tx_id BIGINT,
        |  D_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyE_E (
        |  Tx_id BIGINT,
        |  E_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyF_F (
        |  Tx_id BIGINT,
        |  F_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyG_G (
        |  Tx_id BIGINT,
        |  G_id  BIGINT
        |);
        |
        |CREATE TABLE Tx_manyH_H (
        |  Tx_id BIGINT,
        |  H_id  BIGINT
        |);
        |
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
        |CREATE TABLE A (
        |  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx   BIGINT,
        |  i    INT,
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
        |CREATE TABLE B (
        |  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
        |  tx   BIGINT,
        |  i    INT,
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
        |  tx BIGINT,
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
        |  tx BIGINT,
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
        |  tx BIGINT,
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
        |  tx BIGINT,
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
        |  tx BIGINT,
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
        |  tx BIGINT,
        |  i  INT,
        |  s  LONGVARCHAR
        |);""".stripMargin
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
      //      sch,
      //      schemaRefs,
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
