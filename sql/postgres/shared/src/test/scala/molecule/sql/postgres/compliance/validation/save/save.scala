package molecule.sql.postgres.compliance.validation.save

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.save._
import molecule.sql.postgres.setup.Api_postgres_async

class FormatConstants extends MUnitSuite {
  FormatConstants(this, Api_postgres_async)
}
class FormatVariables extends MUnitSuite {
  FormatVariables(this, Api_postgres_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_postgres_async)
}
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
