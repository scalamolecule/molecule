package molecule.sql.h2.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.h2.setup.{Test_h2_async, TestSuite_h2_array}
import molecule.sql.h2.spi.Spi_h2_async

object Test_NestedBasic extends NestedBasic with Test_h2_async
object Test_NestedExpr extends NestedExpr with Test_h2_async
object Test_NestedLevels extends NestedLevels with Test_h2_async
object Test_NestedOptional extends NestedOptional with Test_h2_async
object Test_NestedRef extends NestedRef with Test_h2_async
object Test_NestedSemantics extends NestedSemantics with Test_h2_async
object Test_NestedTypes extends NestedTypes with TestSuite_h2_array with Spi_h2_async

