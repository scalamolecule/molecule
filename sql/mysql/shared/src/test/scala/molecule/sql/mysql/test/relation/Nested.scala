package molecule.sql.mysql.test.relation

import molecule.coreTests.test.relation.nested._
import molecule.sql.mysql.setup.TestAsync_mysql

object NestedBasic extends NestedBasic with TestAsync_mysql
object NestedExpr extends NestedExpr with TestAsync_mysql
object NestedLevels extends NestedLevels with TestAsync_mysql
object NestedOptional extends NestedOptional with TestAsync_mysql
object NestedRef extends NestedRef with TestAsync_mysql
object NestedSemantics extends NestedSemantics with TestAsync_mysql
object NestedTypes extends NestedTypes with TestAsync_mysql

