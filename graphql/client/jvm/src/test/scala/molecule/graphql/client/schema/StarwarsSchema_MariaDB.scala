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


trait StarwarsSchema_MariaDB extends Schema {

  override val sqlSchema_mariadb: String =
    """
      |CREATE TABLE IF NOT EXISTS Character_ (
      |  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  name      LONGTEXT,
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
      |  name            LONGTEXT,
      |  appearsIn       JSON,
      |  primaryFunction LONGTEXT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Droid_friends_Character (
      |  Droid_id     BIGINT,
      |  Character_id BIGINT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human (
      |  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
      |  name       LONGTEXT,
      |  appearsIn  JSON,
      |  homePlanet LONGTEXT
      |);
      |
      |CREATE TABLE IF NOT EXISTS Human_friends_Character (
      |  Human_id     BIGINT,
      |  Character_id BIGINT
      |);
      |""".stripMargin


  override val sqlReserved_mariadb: Option[Reserved] = None
}