package molecule.sql.postgres.compliance.relation

import molecule.coreTests.compliance.relation.nested._
import molecule.sql.postgres.setup.TestAsync_postgres

object NestedBasic extends NestedBasic with TestAsync_postgres
object NestedExpr extends NestedExpr with TestAsync_postgres
object NestedLevels extends NestedLevels with TestAsync_postgres
object NestedOptional extends NestedOptional with TestAsync_postgres
object NestedRef extends NestedRef with TestAsync_postgres
object NestedSemantics extends NestedSemantics with TestAsync_postgres
object NestedTypes extends NestedTypes with TestAsync_postgres

