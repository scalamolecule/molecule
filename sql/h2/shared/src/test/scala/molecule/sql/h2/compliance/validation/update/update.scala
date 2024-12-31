package molecule.sql.h2.compliance.validation.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.update._
import molecule.sql.h2.setup.Api_h2_async

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
