package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.sqlite.setup.{TestAsync_sqlite, TestSuiteArray_sqlite}
import molecule.sql.sqlite.spi.SpiAsync_sqlite

object Test_NestedBasic extends NestedBasic with TestAsync_sqlite
object Test_NestedExpr extends NestedExpr with TestAsync_sqlite
object Test_NestedLevels extends NestedLevels with TestAsync_sqlite
object Test_NestedOptional extends NestedOptional with TestAsync_sqlite
object Test_NestedRef extends NestedRef with TestAsync_sqlite
object Test_NestedSemantics extends NestedSemantics with TestAsync_sqlite
object Test_NestedTypes extends NestedTypes with TestSuiteArray_sqlite with SpiAsync_sqlite

