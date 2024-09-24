package molecule.sql.postgres.compliance.validation.types.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_TypesOne extends TypesOne with Test_postgres_async
object Test_TypesOneOpt extends TypesOneOpt with Test_postgres_async
object Test_TypesSeq extends TypesSeq with Test_postgres_async
object Test_TypesSeqOpt extends TypesSeqOpt with Test_postgres_async
object Test_TypesSet extends TypesSet with Test_postgres_async
object Test_TypesSetOpt extends TypesSetOpt with Test_postgres_async
