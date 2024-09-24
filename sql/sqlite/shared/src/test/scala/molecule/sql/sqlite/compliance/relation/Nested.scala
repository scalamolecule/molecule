package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.sqlite.setup.{Test_sqlite_async, TestSuite_sqlite_array}
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Test_NestedBasic extends NestedBasic with Test_sqlite_async
object Test_NestedExpr extends NestedExpr with Test_sqlite_async
object Test_NestedLevels extends NestedLevels with Test_sqlite_async
object Test_NestedOptional extends NestedOptional with Test_sqlite_async
object Test_NestedRef extends NestedRef with Test_sqlite_async
object Test_NestedSemantics extends NestedSemantics with Test_sqlite_async
object Test_NestedTypes extends NestedTypes with TestSuite_sqlite_array with Spi_sqlite_async

