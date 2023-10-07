package molecule.sql.mariadb.test.relation

import molecule.coreTests.test.relation.nested._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object NestedBasic extends NestedBasic with TestAsync_mariadb
object NestedExpr extends NestedExpr with TestAsync_mariadb
object NestedLevels extends NestedLevels with TestAsync_mariadb
object NestedOptional extends NestedOptional with TestAsync_mariadb
object NestedRef extends NestedRef with TestAsync_mariadb
object NestedSemantics extends NestedSemantics with TestAsync_mariadb
object NestedTypes extends NestedTypes with TestAsync_mariadb

