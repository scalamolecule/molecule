package molecule.sql.postgres.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_FlatRefs extends FlatRef with Test_postgres_async
object Test_FlatRefOpt extends FlatRefOpt with Test_postgres_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_postgres_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_postgres_async
