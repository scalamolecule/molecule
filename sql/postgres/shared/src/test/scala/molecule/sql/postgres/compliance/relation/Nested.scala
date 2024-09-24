package molecule.sql.postgres.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.postgres.setup.{Test_postgres_async, TestSuite_postgres_array}
import molecule.sql.postgres.spi.Spi_postgres_async

object Test_NestedBasic extends NestedBasic with Test_postgres_async
object Test_NestedExpr extends NestedExpr with Test_postgres_async
object Test_NestedLevels extends NestedLevels with Test_postgres_async
object Test_NestedOptional extends NestedOptional with Test_postgres_async
object Test_NestedRef extends NestedRef with Test_postgres_async
object Test_NestedSemantics extends NestedSemantics with Test_postgres_async
object Test_NestedTypes extends NestedTypes with TestSuite_postgres_array with Spi_postgres_async

