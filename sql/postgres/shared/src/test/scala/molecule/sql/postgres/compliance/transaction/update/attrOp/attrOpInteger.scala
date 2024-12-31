package molecule.sql.postgres.compliance.transaction.update.attrOp

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.attrOp.number._
import molecule.sql.postgres.setup.Api_postgres_async


class AttrOpInteger_Int extends MUnitSuite {
  AttrOpInteger_Int(this, Api_postgres_async)
}
class AttrOpInteger_Long_ extends MUnitSuite {
  AttrOpInteger_Long_(this, Api_postgres_async)
}
class AttrOpInteger_BigInt extends MUnitSuite {
  AttrOpInteger_BigInt(this, Api_postgres_async)
}
class AttrOpInteger_Byte_ extends MUnitSuite {
  AttrOpInteger_Byte_(this, Api_postgres_async)
}
class AttrOpInteger_Short_ extends MUnitSuite {
  AttrOpInteger_Short_(this, Api_postgres_async)
}

