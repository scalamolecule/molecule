package molecule.sql.h2.compliance.validation.insert

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.insert._
import molecule.sql.h2.setup.Api_h2_async

class FormatConstants extends Test {
  FormatConstants(this, Api_h2_async)
}

class FormatVariables extends Test {
  FormatVariables(this, Api_h2_async)
}
class Nested extends Test {
  Nested(this, Api_h2_async)
}
class Semantics extends Test {
  Semantics(this, Api_h2_async)
}
class TypesOne extends Test {
  TypesOne(this, Api_h2_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_h2_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_h2_async)
}
