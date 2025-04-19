/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit data model file in `molecule.graphql.client.dataModel/`
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.schema

import molecule.base.api.Schema
import molecule.base.ast.*


trait StarwarsSchema_Mysql extends Schema {

  override val sqlSchema_mysql: String =
    """
      |CREATE TABLE IF NOT EXISTS Character_ (
      |  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  name_     LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  appearsIn JSON
      |);
      |
      |CREATE TABLE IF NOT EXISTS Character_friends_Character (
      |  Character_1_id BIGINT,
      |  Character_2_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Droid (
      |  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  name_           LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  appearsIn       JSON,
      |  primaryFunction LONGTEXT COLLATE utf8mb4_0900_as_cs
      |);
      |
      |CREATE TABLE IF NOT EXISTS Droid_friends_Character (
      |  Droid_id     BIGINT,
      |  Character_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human (
      |  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  name_      LONGTEXT COLLATE utf8mb4_0900_as_cs,
      |  appearsIn  JSON,
      |  homePlanet LONGTEXT COLLATE utf8mb4_0900_as_cs
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human_friends_Character (
      |  Human_id     BIGINT,
      |  Character_id BIGINT
      |);
      |""".stripMargin


  override val sqlReserved_mysql: Option[Reserved] = Some(Reserved(
    Array(true, false, false),
    Array(
      false, true, false, false,
      false, true, false, false, false,
      false, true, false, false, false
    )
  ))
}