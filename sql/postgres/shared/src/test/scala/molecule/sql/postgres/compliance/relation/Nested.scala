package molecule.sql.postgres.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object Test_NestedBasic extends NestedBasic with TestAsync_postgres
object Test_NestedExpr extends NestedExpr with TestAsync_postgres
object Test_NestedLevels extends NestedLevels with TestAsync_postgres
object Test_NestedOptional extends NestedOptional with TestAsync_postgres
object Test_NestedRef extends NestedRef with TestAsync_postgres
object Test_NestedSemantics extends NestedSemantics with TestAsync_postgres
object Test_NestedTypes extends NestedTypes with TestSuiteArray_postgres with SpiAsync_postgres

