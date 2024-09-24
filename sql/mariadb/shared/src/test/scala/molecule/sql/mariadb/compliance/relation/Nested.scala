package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.mariadb.setup.{Test_mariadb_async, TestSuite_mariadb_array}
import molecule.sql.mariadb.spi.Spi_mariadb_async

object Test_NestedBasic extends NestedBasic with Test_mariadb_async
object Test_NestedExpr extends NestedExpr with Test_mariadb_async
object Test_NestedLevels extends NestedLevels with Test_mariadb_async
object Test_NestedOptional extends NestedOptional with Test_mariadb_async
object Test_NestedRef extends NestedRef with Test_mariadb_async
object Test_NestedSemantics extends NestedSemantics with Test_mariadb_async
object Test_NestedTypes extends NestedTypes with TestSuite_mariadb_array with Spi_mariadb_async

