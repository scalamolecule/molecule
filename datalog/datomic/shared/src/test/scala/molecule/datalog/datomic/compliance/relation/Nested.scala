package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.datalog.datomic.setup.{Test_datomic_async, TestSuite_datomic_array}
import molecule.datalog.datomic.spi.Spi_datomic_async

object NestedBasic extends NestedBasic with Test_datomic_async
object NestedExpr extends NestedExpr with Test_datomic_async
object NestedLevels extends NestedLevels with Test_datomic_async
object NestedOptional extends NestedOptional with Test_datomic_async
object NestedRef extends NestedRef with Test_datomic_async
object NestedSemantics extends NestedSemantics with Test_datomic_async
object NestedTypes extends NestedTypes with TestSuite_datomic_array with Spi_datomic_async
