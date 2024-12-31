package molecule.sql.postgres.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update._
import molecule.sql.postgres.setup.Api_postgres_async

class TypesOne extends Test {
  TypesOne(this, Api_postgres_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_postgres_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_postgres_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_postgres_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_postgres_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_postgres_async)
}
