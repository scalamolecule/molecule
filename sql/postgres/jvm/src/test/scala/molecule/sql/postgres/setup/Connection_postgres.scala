package molecule.sql.postgres.setup

import java.sql.DriverManager
import com.dimafeng.testcontainers.PostgreSQLContainer
import molecule.base.api.Schema
import molecule.core.marshalling.JdbcProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.sql.core.facade.JdbcConn_JVM


object Connection_postgres {

  val url = "jdbc:tc:postgresql:15://localhost:5432/test?preparedStatementCacheQueries=0"

  val container = PostgreSQLContainer()
  // Fort fast reuse, set testcontainers.reuse.enable=true in ~/.testcontainers.properties
  // See https://callistaenterprise.se/blogg/teknik/2020/10/09/speed-up-your-testcontainers-tests/

  //  // These don't seem to make any difference:
  //  container.container.withReuse(true)
  //  container.container.withDatabaseName("test")
  ////  container.container.withUsername("sa")
  ////  container.container.withPassword("sa")
  //  container.container.withLabel("reuse.UUID", "e06d7a87-7d7d-472e-a047-e6c81f61d2a4");


  Class.forName(container.driverClassName)
  val sqlConn = DriverManager.getConnection(url)

  val sqlSchema_postgres: String =
    """
      |CREATE TABLE IF NOT EXISTS Ns (
      |  id              BIGSERIAL PRIMARY KEY,
      |  i               INTEGER,
      |  ii              INTEGER ARRAY,
      |  s               TEXT COLLATE ucs_basic,
      |  u               INTEGER,
      |  string          TEXT COLLATE ucs_basic,
      |  int             INTEGER,
      |  long            BIGINT,
      |  float           DECIMAL,
      |  double          DOUBLE PRECISION,
      |  boolean         BOOLEAN,
      |  bigInt          DECIMAL,
      |  bigDecimal      DECIMAL,
      |  date            BIGINT,
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
      |  byte            SMALLINT,
      |  short           SMALLINT,
      |  char            CHAR(1),
      |  ref             BIGINT,
      |  other           BIGINT,
      |  strings         TEXT ARRAY,
      |  ints            INTEGER ARRAY,
      |  longs           BIGINT ARRAY,
      |  floats          DECIMAL ARRAY,
      |  doubles         DOUBLE PRECISION ARRAY,
      |  booleans        BOOLEAN ARRAY,
      |  bigInts         DECIMAL ARRAY,
      |  bigDecimals     DECIMAL ARRAY,
      |  dates           BIGINT ARRAY,
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
      |  bytes           SMALLINT ARRAY,
      |  shorts          SMALLINT ARRAY,
      |  chars           CHAR(1) ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ns_refs_Ref (
      |  Ns_id  BIGINT,
      |  Ref_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref (
      |  id              BIGSERIAL PRIMARY KEY,
      |  i               INTEGER,
      |  s               TEXT COLLATE ucs_basic,
      |  string          TEXT COLLATE ucs_basic,
      |  int             INTEGER,
      |  long            BIGINT,
      |  float           DECIMAL,
      |  double          DOUBLE PRECISION,
      |  boolean         BOOLEAN,
      |  bigInt          DECIMAL,
      |  bigDecimal      DECIMAL,
      |  date            BIGINT,
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
      |  byte            SMALLINT,
      |  short           SMALLINT,
      |  char            CHAR(1),
      |  ii              INTEGER ARRAY,
      |  ss              TEXT ARRAY,
      |  strings         TEXT ARRAY,
      |  ints            INTEGER ARRAY,
      |  longs           BIGINT ARRAY,
      |  floats          DECIMAL ARRAY,
      |  doubles         DOUBLE PRECISION ARRAY,
      |  booleans        BOOLEAN ARRAY,
      |  bigInts         DECIMAL ARRAY,
      |  bigDecimals     DECIMAL ARRAY,
      |  dates           BIGINT ARRAY,
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
      |  bytes           SMALLINT ARRAY,
      |  shorts          SMALLINT ARRAY,
      |  chars           CHAR(1) ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Ref_nss_Ns (
      |  Ref_id BIGINT,
      |  Ns_id  BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Other (
      |  id      BIGSERIAL PRIMARY KEY,
      |  i       INTEGER,
      |  s       TEXT COLLATE ucs_basic,
      |  ii      INTEGER ARRAY,
      |  ss      TEXT ARRAY,
      |  select_ INTEGER
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
      |""".stripMargin

  val recreateSchema =
    s"""DROP SCHEMA IF EXISTS public CASCADE;
       |CREATE SCHEMA public;
       |""".stripMargin

  def recreationStmt(schema: Schema): String = {
//    recreateSchema + sqlSchema_postgres
        recreateSchema + schema.sqlSchema_postgres
  }

  def proxy(schema: Schema) = {
    JdbcProxy(
      url,
      recreateSchema + schema.sqlSchema_postgres,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_postgres,
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
