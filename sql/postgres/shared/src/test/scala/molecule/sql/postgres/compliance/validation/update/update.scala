package molecule.sql.postgres.compliance.validation.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.update._
import molecule.sql.postgres.setup.Api_postgres_async

class TypesOne extends MUnitSuite {
  TypesOne(this, Api_postgres_async)
}
class TypesOneOpt extends MUnitSuite {
  TypesOneOpt(this, Api_postgres_async)
}
class TypesSeq extends MUnitSuite {
  TypesSeq(this, Api_postgres_async)
}
class TypesSeqOpt extends MUnitSuite {
  TypesSeqOpt(this, Api_postgres_async)
}
class TypesSet extends MUnitSuite {
  TypesSet(this, Api_postgres_async)
}
class TypesSetOpt extends MUnitSuite {
  TypesSetOpt(this, Api_postgres_async)
}
