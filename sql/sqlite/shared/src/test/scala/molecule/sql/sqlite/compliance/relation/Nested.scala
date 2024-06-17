package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.sqlite.setup.{TestAsync_sqlite, TestSuiteArray_sqlite}
import molecule.sql.sqlite.spi.SpiAsync_sqlite

object NestedBasic extends NestedBasic with TestAsync_sqlite
object NestedExpr extends NestedExpr with TestAsync_sqlite
object NestedLevels extends NestedLevels with TestAsync_sqlite
object NestedOptional extends NestedOptional with TestAsync_sqlite
object NestedRef extends NestedRef with TestAsync_sqlite
object NestedSemantics extends NestedSemantics with TestAsync_sqlite
object NestedTypes extends NestedTypes with TestSuiteArray_sqlite with SpiAsync_sqlite

