package molecule.datalog.datomic.compliance.transaction.update.attrOp

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.attrOp.number._
import molecule.datalog.datomic.setup.Api_datomic_async


class AttrOpInteger_Int extends MUnitSuite {
  AttrOpInteger_Int(this, Api_datomic_async)
}
class AttrOpInteger_Long_ extends MUnitSuite {
  AttrOpInteger_Long_(this, Api_datomic_async)
}
class AttrOpInteger_BigInt extends MUnitSuite {
  AttrOpInteger_BigInt(this, Api_datomic_async)
}
class AttrOpInteger_Byte_ extends MUnitSuite {
  AttrOpInteger_Byte_(this, Api_datomic_async)
}
class AttrOpInteger_Short_ extends MUnitSuite {
  AttrOpInteger_Short_(this, Api_datomic_async)
}


