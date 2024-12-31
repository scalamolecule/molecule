package molecule.sql.h2.compliance.validation.save

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.insert.FormatConstants
import molecule.coreTests.spi.validation.save._
import molecule.sql.h2.setup.Api_h2_async

class FormatConstants extends MUnitSuite {
  FormatConstants(this, Api_h2_async)
}

class FormatVariables extends MUnitSuite {
  FormatVariables(this, Api_h2_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_h2_async)
}
class TypesOne extends MUnitSuite {
  TypesOne(this, Api_h2_async)
}
class TypesOneOpt extends MUnitSuite {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeq extends MUnitSuite {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOpt extends MUnitSuite {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSet extends MUnitSuite {
  TypesSet(this, Api_h2_async)
}
class TypesSetOpt extends MUnitSuite {
  TypesSetOpt(this, Api_h2_async)
}
