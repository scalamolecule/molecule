package molecule.datalog.datomic.compliance.validation.insert

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.insert._
import molecule.datalog.datomic.setup.Api_datomic_async

class FormatConstants extends Test {
  FormatConstants(this, Api_datomic_async)
}
class FormatVariables extends Test {
  FormatVariables(this, Api_datomic_async)
}
class Nested extends Test {
  Nested(this, Api_datomic_async)
}
class Semantics extends Test {
  Semantics(this, Api_datomic_async)
}
class TypesOne extends Test {
  TypesOne(this, Api_datomic_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_datomic_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_datomic_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_datomic_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_datomic_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_datomic_async)
}
