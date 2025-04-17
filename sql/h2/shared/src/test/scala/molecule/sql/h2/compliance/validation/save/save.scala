package molecule.sql.h2.compliance.validation.save

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.save._
import molecule.sql.h2.setup.Api_h2_async

class FormatConstantsTest extends Test {
  FormatConstants(this, Api_h2_async)
}
class FormatVariablesTest extends Test {
  FormatVariables(this, Api_h2_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_h2_async)
}
class TypesOneTest extends Test {
  TypesOne(this, Api_h2_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_h2_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_h2_async)
}
