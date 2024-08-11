package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object Test_NestedBasic extends NestedBasic with TestAsync_mysql
object Test_NestedExpr extends NestedExpr with TestAsync_mysql
object Test_NestedLevels extends NestedLevels with TestAsync_mysql
object Test_NestedOptional extends NestedOptional with TestAsync_mysql
object Test_NestedRef extends NestedRef with TestAsync_mysql
object Test_NestedSemantics extends NestedSemantics with TestAsync_mysql
object Test_NestedTypes extends NestedTypes with TestSuiteArray_mysql with SpiAsync_mysql

