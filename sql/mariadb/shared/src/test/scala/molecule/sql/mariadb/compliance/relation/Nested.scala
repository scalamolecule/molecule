package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mariadb.setup.{TestAsync_mariadb, TestSuiteArray_mariadb}
import molecule.sql.mariadb.spi.SpiAsync_mariadb

object NestedBasic extends NestedBasic with TestAsync_mariadb
object NestedExpr extends NestedExpr with TestAsync_mariadb
object NestedLevels extends NestedLevels with TestAsync_mariadb
object NestedOptional extends NestedOptional with TestAsync_mariadb
object NestedRef extends NestedRef with TestAsync_mariadb
object NestedSemantics extends NestedSemantics with TestAsync_mariadb
object NestedTypes extends NestedTypes with TestSuiteArray_mariadb with SpiAsync_mariadb

