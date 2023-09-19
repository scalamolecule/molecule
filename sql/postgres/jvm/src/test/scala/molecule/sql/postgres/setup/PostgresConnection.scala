package molecule.sql.postgres.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.PostgreSQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM


object PostgresConnection {

  val url = "jdbc:tc:postgresql:15://localhost:5432/test?preparedStatementCacheQueries=0"

  val container = PostgreSQLContainer()
  // Fort fast reuse, set testcontainers.reuse.enable=true in ~/.testcontainers.properties
  // See https://callistaenterprise.se/blogg/teknik/2020/10/09/speed-up-your-testcontainers-tests/

  // These don't seem necessary:
  //  container.container.withReuse(true)
  //  container.container.withDatabaseName("integration-tests-db")
  //  container.container.withUsername("sa")
  //  container.container.withPassword("sa");


  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val sqlSchema_postgres1: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id          BIGSERIAL PRIMARY KEY,
      |  i           INTEGER,
      |  ii          INTEGER ARRAY,
      |  s           TEXT COLLATE ucs_basic,
      |  u           INTEGER,
      |  string      TEXT COLLATE ucs_basic,
      |  int         INTEGER,
      |  long        BIGINT,
      |  float       decimal,
      |  double      double precision,
      |  boolean     BOOLEAN,
      |  bigInt      DECIMAL,
      |  bigDecimal  DECIMAL,
      |  date        DATE,
      |  uuid        UUID,
      |  uri         VARCHAR,
      |  byte        SMALLINT,
      |  short       SMALLINT,
      |  char        CHAR(1),
      |  ref         BIGINT,
      |  other       BIGINT,
      |  strings     TEXT ARRAY,
      |  ints        INTEGER ARRAY,
      |  longs       BIGINT ARRAY,
      |  floats      decimal ARRAY,
      |  doubles     double precision ARRAY,
      |  booleans    BOOLEAN ARRAY,
      |  bigInts     DECIMAL ARRAY,
      |  bigDecimals DECIMAL ARRAY,
      |  dates       DATE ARRAY,
      |  uuids       UUID ARRAY,
      |  uris        VARCHAR ARRAY,
      |  bytes       SMALLINT ARRAY,
      |  shorts      SMALLINT ARRAY,
      |  chars       CHAR(1) ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      |  id          BIGSERIAL PRIMARY KEY,
      |  i           INTEGER,
      |  s           TEXT COLLATE ucs_basic,
      |  string      TEXT COLLATE ucs_basic,
      |  int         INTEGER,
      |  long        BIGINT,
      |  float       DECIMAL,
      |  double      DECIMAL,
      |  boolean     BOOLEAN,
      |  bigInt      DECIMAL,
      |  bigDecimal  DECIMAL,
      |  date        DATE,
      |  uuid        UUID,
      |  uri         VARCHAR,
      |  byte        SMALLINT,
      |  short       SMALLINT,
      |  char        CHAR(1),
      |  ii          INTEGER ARRAY,
      |  ss          TEXT ARRAY,
      |  strings     TEXT ARRAY,
      |  ints        INTEGER ARRAY,
      |  longs       BIGINT ARRAY,
      |  floats      DECIMAL ARRAY,
      |  doubles     DECIMAL ARRAY,
      |  booleans    BOOLEAN ARRAY,
      |  bigInts     DECIMAL ARRAY,
      |  bigDecimals DECIMAL ARRAY,
      |  dates       DATE ARRAY,
      |  uuids       UUID ARRAY,
      |  uris        VARCHAR ARRAY,
      |  bytes       SMALLINT ARRAY,
      |  shorts      SMALLINT ARRAY,
      |  chars       CHAR(1) ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref_nss_Ns (
      |  Ref_id BIGINT,
      |  Ns_id  BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Other (
      |  id BIGSERIAL PRIMARY KEY,
      |  i  INTEGER,
      |  s  TEXT COLLATE ucs_basic,
      |  ii INTEGER ARRAY,
      |  ss TEXT ARRAY
      |);
      |
      |CREATE OR REPLACE FUNCTION _final_median(numeric[])
      |   RETURNS numeric AS
      |$$
      |   SELECT AVG(val)
      |   FROM (
      |     SELECT val
      |     FROM unnest($1) val
      |     ORDER BY 1
      |     LIMIT  2 - MOD(array_upper($1, 1), 2)
      |     OFFSET CEIL(array_upper($1, 1) / 2.0) - 1
      |   ) sub;
      |$$
      |LANGUAGE 'sql' IMMUTABLE;
      |
      |CREATE AGGREGATE median(numeric) (
      |  SFUNC=array_append,
      |  STYPE=numeric[],
      |  FINALFUNC=_final_median,
      |  INITCOND='{}'
      |);
      |
      |""".stripMargin
  //      println(sqlSchema_postgres1)

  def recreationStmt(schema: Schema): String = {
    //    s"""DROP SCHEMA IF EXISTS public CASCADE;
    //       |CREATE SCHEMA public;
    //       |$sqlSchema_postgres1
    //       |""".stripMargin

    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |${schema.sqlSchema_postgres}
       |""".stripMargin
  }

  def proxy(schema: Schema) = JdbcProxy(
    url,
    schema.sqlSchema_postgres,
    schema.metaSchema,
    schema.nsMap,
    schema.attrMap,
    schema.uniqueAttrs
  )

  val conn_Types      = JdbcConn_JVM(proxy(TypesSchema), sqlConn)
  val conn_Refs       = JdbcConn_JVM(proxy(RefsSchema), sqlConn)
  val conn_Uniques    = JdbcConn_JVM(proxy(UniquesSchema), sqlConn)
  val conn_Validation = JdbcConn_JVM(proxy(ValidationSchema), sqlConn)

  val recreateStmt_Types      = recreationStmt(TypesSchema)
  val recreateStmt_Refs       = recreationStmt(RefsSchema)
  val recreateStmt_Uniques    = recreationStmt(UniquesSchema)
  val recreateStmt_Validation = recreationStmt(ValidationSchema)
}
