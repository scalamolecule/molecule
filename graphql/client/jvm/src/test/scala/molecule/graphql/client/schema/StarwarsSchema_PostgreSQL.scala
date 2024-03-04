/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit data model file in `molecule.graphql.client.dataModel/`
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.schema

import molecule.base.api.Schema
import molecule.base.ast._


trait StarwarsSchema_PostgreSQL extends Schema {

  override val sqlSchema_postgres: String =
    """
      |CREATE TABLE IF NOT EXISTS Character (
      |  id        BIGSERIAL PRIMARY KEY,
      |  name      TEXT COLLATE ucs_basic,
      |  appearsIn TEXT ARRAY
      |);
      |
      |CREATE TABLE IF NOT EXISTS Character_friends_Character (
      |  Character_1_id BIGINT,
      |  Character_2_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Droid (
      |  id              BIGSERIAL PRIMARY KEY,
      |  name            TEXT COLLATE ucs_basic,
      |  appearsIn       TEXT ARRAY,
      |  primaryFunction TEXT COLLATE ucs_basic
      |);
      |
      |CREATE TABLE IF NOT EXISTS Droid_friends_Character (
      |  Droid_id     BIGINT,
      |  Character_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human (
      |  id         BIGSERIAL PRIMARY KEY,
      |  name       TEXT COLLATE ucs_basic,
      |  appearsIn  TEXT ARRAY,
      |  homePlanet TEXT COLLATE ucs_basic
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human_friends_Character (
      |  Human_id     BIGINT,
      |  Character_id BIGINT
      |);
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


  override val sqlReserved_postgres: Option[Reserved] = None
}