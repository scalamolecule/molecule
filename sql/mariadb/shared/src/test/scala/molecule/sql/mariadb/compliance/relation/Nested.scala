package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mariadb.setup.{TestAsync_mariadb, TestSuiteArray_mariadb}
import molecule.sql.mariadb.spi.SpiAsync_mariadb

object Test_NestedBasic extends NestedBasic with TestAsync_mariadb
object Test_NestedExpr extends NestedExpr with TestAsync_mariadb
object Test_NestedLevels extends NestedLevels with TestAsync_mariadb
object Test_NestedOptional extends NestedOptional with TestAsync_mariadb
object Test_NestedRef extends NestedRef with TestAsync_mariadb
object Test_NestedSemantics extends NestedSemantics with TestAsync_mariadb
object Test_NestedTypes extends NestedTypes with TestSuiteArray_mariadb with SpiAsync_mariadb

