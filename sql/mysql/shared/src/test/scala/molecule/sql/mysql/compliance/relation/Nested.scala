package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mysql.setup.{Test_mysql_async, TestSuite_mysql_array}
import molecule.sql.mysql.spi.Spi_mysql_async

object Test_NestedBasic extends NestedBasic with Test_mysql_async
object Test_NestedExpr extends NestedExpr with Test_mysql_async
object Test_NestedLevels extends NestedLevels with Test_mysql_async
object Test_NestedOptional extends NestedOptional with Test_mysql_async
object Test_NestedRef extends NestedRef with Test_mysql_async
object Test_NestedSemantics extends NestedSemantics with Test_mysql_async
object Test_NestedTypes extends NestedTypes with TestSuite_mysql_array with Spi_mysql_async

