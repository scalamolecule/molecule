package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object NestedBasic extends NestedBasic with TestAsync_mysql
object NestedExpr extends NestedExpr with TestAsync_mysql
object NestedLevels extends NestedLevels with TestAsync_mysql
object NestedOptional extends NestedOptional with TestAsync_mysql
object NestedRef extends NestedRef with TestAsync_mysql
object NestedSemantics extends NestedSemantics with TestAsync_mysql
object NestedTypes extends NestedTypes with TestSuiteArray_mysql with SpiAsync_mysql

