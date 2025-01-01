/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit domain definition file in `molecule.coreTests.domains/`
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.sql.postgres.setup

import molecule.base.api._


object ValidationSchema_postgres2 extends ValidationSchema2 with Schema_postgres {

  override val schemaData: List[String] = List(
    """
      |CREATE TABLE IF NOT EXISTS Strings (
      |  id           BIGSERIAL PRIMARY KEY,
      |  email        TEXT COLLATE ucs_basic,
      |  emailWithMsg TEXT COLLATE ucs_basic,
      |  regex        TEXT COLLATE ucs_basic,
      |  regexWithMsg TEXT COLLATE ucs_basic
      |);
      |
      |CREATE TABLE IF NOT EXISTS Strings_enums_Enum (
      |  Strings_id BIGINT,
      |  Enum_id    BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Enum (
      |  id           BIGSERIAL PRIMARY KEY,
      |  luckyNumber  INTEGER,
      |  luckyNumber2 INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS Type (
      |  id                BIGSERIAL PRIMARY KEY,
      |  string            TEXT COLLATE ucs_basic,
      |  int               INTEGER,
      |  long              BIGINT,
      |  float             DECIMAL,
      |  double            DOUBLE PRECISION,
      |  boolean           BOOLEAN,
      |  bigInt            DECIMAL,
      |  bigDecimal        DECIMAL,
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
      |  byte              SMALLINT,
      |  short             SMALLINT,
      |  char              CHAR(1),
      |  ref               BIGINT,
      |  stringSet         TEXT ARRAY,
      |  intSet            INTEGER ARRAY,
      |  longSet           BIGINT ARRAY,
      |  floatSet          DECIMAL ARRAY,
      |  doubleSet         DOUBLE PRECISION ARRAY,
      |  booleanSet        BOOLEAN ARRAY,
      |  bigIntSet         DECIMAL ARRAY,
      |  bigDecimalSet     DECIMAL ARRAY,
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
      |  byteSet           SMALLINT ARRAY,
      |  shortSet          SMALLINT ARRAY,
      |  charSet           CHAR(1) ARRAY,
      |  stringSeq         TEXT ARRAY,
      |  intSeq            INTEGER ARRAY,
      |  longSeq           BIGINT ARRAY,
      |  floatSeq          DECIMAL ARRAY,
      |  doubleSeq         DOUBLE PRECISION ARRAY,
      |  booleanSeq        BOOLEAN ARRAY,
      |  bigIntSeq         DECIMAL ARRAY,
      |  bigDecimalSeq     DECIMAL ARRAY,
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
      |  shortSeq          SMALLINT ARRAY,
      |  charSeq           CHAR(1) ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Type_refs_Strings (
      |  Type_id    BIGINT,
      |  Strings_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Constants (
      |  id                       BIGSERIAL PRIMARY KEY,
      |  noErrorMsg               INTEGER,
      |  errorMsg                 INTEGER,
      |  errorMsgWithValue        INTEGER,
      |  errorMsgWithValueQuoted  TEXT COLLATE ucs_basic,
      |  errorMsgWithValueQuoted2 TEXT COLLATE ucs_basic,
      |  multilineErrorMsg        INTEGER,
      |  multilineMsgWithValue    INTEGER,
      |  multilineMsgWithValue2   INTEGER,
      |  multiLine                INTEGER,
      |  multiLine2               INTEGER,
      |  multiLine3               INTEGER,
      |  logic                    INTEGER,
      |  multipleErrors           INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS Variables (
      |  id                BIGSERIAL PRIMARY KEY,
      |  int               INTEGER,
      |  noErrorMsg        INTEGER,
      |  int1              INTEGER,
      |  errorMsg          INTEGER,
      |  int2              INTEGER,
      |  errorMsgWithValue INTEGER,
      |  int3              INTEGER,
      |  multilineMsg      INTEGER,
      |  int4              INTEGER,
      |  multiLine         INTEGER,
      |  int5              INTEGER,
      |  multiLine2        INTEGER,
      |  int6              INTEGER,
      |  multiLine3        INTEGER,
      |  int7              INTEGER,
      |  logic             INTEGER,
      |  int8              INTEGER,
      |  str               TEXT COLLATE ucs_basic,
      |  intSet            INTEGER ARRAY,
      |  strs              TEXT ARRAY,
      |  multipleErrors    INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryAttr (
      |  id      BIGSERIAL PRIMARY KEY,
      |  name    TEXT COLLATE ucs_basic,
      |  hobbies TEXT ARRAY,
      |  age     INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefAB (
      |  id   BIGSERIAL PRIMARY KEY,
      |  i    INTEGER,
      |  refA BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefB (
      |  id   BIGSERIAL PRIMARY KEY,
      |  i    INTEGER,
      |  refB BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefsAB (
      |  id BIGSERIAL PRIMARY KEY,
      |  i  INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefsAB_refsA_RefA (
      |  MandatoryRefsAB_id BIGINT,
      |  RefA_id            BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefsB (
      |  id BIGSERIAL PRIMARY KEY,
      |  i  INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS MandatoryRefsB_refsB_RefB (
      |  MandatoryRefsB_id BIGINT,
      |  RefB_id           BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS RefA (
      |  id   BIGSERIAL PRIMARY KEY,
      |  i    INTEGER,
      |  refB BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS RefB (
      |  id BIGSERIAL PRIMARY KEY,
      |  i  INTEGER
      |);
      |
      |CREATE TABLE IF NOT EXISTS Require (
      |  id       BIGSERIAL PRIMARY KEY,
      |  i        INTEGER,
      |  username TEXT COLLATE ucs_basic,
      |  password TEXT COLLATE ucs_basic,
      |  x        INTEGER,
      |  y        INTEGER,
      |  z        INTEGER,
      |  int      INTEGER,
      |  refB     BIGINT,
      |  ref1     BIGINT,
      |  ref2     BIGINT
      |);
      |
      |-- Optional reference constraints to avoid orphan relationships (add manually)
      |-- ALTER TABLE Strings_enums_Enum         ADD CONSTRAINT _Strings_id           FOREIGN KEY (Strings_id        ) REFERENCES Strings         (id);
      |-- ALTER TABLE Strings_enums_Enum         ADD CONSTRAINT _Enum_id              FOREIGN KEY (Enum_id           ) REFERENCES Enum            (id);
      |-- ALTER TABLE Type                       ADD CONSTRAINT _ref                  FOREIGN KEY (ref               ) REFERENCES Strings         (id);
      |-- ALTER TABLE Type_refs_Strings          ADD CONSTRAINT _Type_id              FOREIGN KEY (Type_id           ) REFERENCES Type            (id);
      |-- ALTER TABLE Type_refs_Strings          ADD CONSTRAINT _Strings_id_2         FOREIGN KEY (Strings_id        ) REFERENCES Strings         (id);
      |-- ALTER TABLE MandatoryRefAB             ADD CONSTRAINT _refA                 FOREIGN KEY (refA              ) REFERENCES RefA            (id);
      |-- ALTER TABLE MandatoryRefB              ADD CONSTRAINT _refB                 FOREIGN KEY (refB              ) REFERENCES RefB            (id);
      |-- ALTER TABLE MandatoryRefsAB_refsA_RefA ADD CONSTRAINT _MandatoryRefsAB_id   FOREIGN KEY (MandatoryRefsAB_id) REFERENCES MandatoryRefsAB (id);
      |-- ALTER TABLE MandatoryRefsAB_refsA_RefA ADD CONSTRAINT _RefA_id              FOREIGN KEY (RefA_id           ) REFERENCES RefA            (id);
      |-- ALTER TABLE MandatoryRefsB_refsB_RefB  ADD CONSTRAINT _MandatoryRefsB_id    FOREIGN KEY (MandatoryRefsB_id ) REFERENCES MandatoryRefsB  (id);
      |-- ALTER TABLE MandatoryRefsB_refsB_RefB  ADD CONSTRAINT _RefB_id              FOREIGN KEY (RefB_id           ) REFERENCES RefB            (id);
      |-- ALTER TABLE RefA                       ADD CONSTRAINT _refB_2               FOREIGN KEY (refB              ) REFERENCES RefB            (id);
      |-- ALTER TABLE Require                    ADD CONSTRAINT _refB_3               FOREIGN KEY (refB              ) REFERENCES RefB            (id);
      |-- ALTER TABLE Require                    ADD CONSTRAINT _ref1                 FOREIGN KEY (ref1              ) REFERENCES RefB            (id);
      |-- ALTER TABLE Require                    ADD CONSTRAINT _ref2                 FOREIGN KEY (ref2              ) REFERENCES Enum            (id);
      |
      |CREATE OR REPLACE FUNCTION _final_median(numeric[])
      |   RETURNS numeric AS
      |$$
      |   SELECT AVG(val)
      |   FROM (
      |     SELECT DISTINCT val
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
  )


  // Indexes to lookup if entity/attribute names collides with db keyword

  override val reservedEntities: Array[Boolean] = Array(false, false, false, false, false, false, false, false, false, false, false, false, false)

  override val reservedAttributes: Array[Boolean] = Array(
    // Strings
    false, false, false, false, false, false,
    
    // Enum
    false, false, false,
    
    // Type
    false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
    
    // Constants
    false, false, false, false, false, false, false, false, false, false, false, false, false, false,
    
    // Variables
    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
    
    // MandatoryAttr
    false, false, false, false,
    
    // MandatoryRefAB
    false, false, false,
    
    // MandatoryRefB
    false, false, false,
    
    // MandatoryRefsAB
    false, false, false,
    
    // MandatoryRefsB
    false, false, false,
    
    // RefA
    false, false, false,
    
    // RefB
    false, false,
    
    // Require
    false, false, false, false, false, false, false, false, false, false, false
  )
}