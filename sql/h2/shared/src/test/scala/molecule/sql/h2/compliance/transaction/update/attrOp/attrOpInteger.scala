package molecule.sql.h2.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update.attrOp.number._
import molecule.sql.h2.setup.Api_h2_async


class AttrOpInteger_Int extends Test {
  AttrOpInteger_Int(this, Api_h2_async)
}
class AttrOpInteger_Long_ extends Test {
  AttrOpInteger_Long_(this, Api_h2_async)
}
class AttrOpInteger_BigInt extends Test {
  AttrOpInteger_BigInt(this, Api_h2_async)
}
class AttrOpInteger_Byte_ extends Test {
  AttrOpInteger_Byte_(this, Api_h2_async)
}
class AttrOpInteger_Short_ extends Test {
  AttrOpInteger_Short_(this, Api_h2_async)
}


