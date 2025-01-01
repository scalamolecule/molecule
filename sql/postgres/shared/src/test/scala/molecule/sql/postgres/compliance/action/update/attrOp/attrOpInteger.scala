package molecule.sql.postgres.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.number._
import molecule.sql.postgres.setup.Api_postgres_async


class AttrOpInteger_Int extends Test {
  AttrOpInteger_Int(this, Api_postgres_async)
}
class AttrOpInteger_Long_ extends Test {
  AttrOpInteger_Long_(this, Api_postgres_async)
}
class AttrOpInteger_BigInt extends Test {
  AttrOpInteger_BigInt(this, Api_postgres_async)
}
class AttrOpInteger_Byte_ extends Test {
  AttrOpInteger_Byte_(this, Api_postgres_async)
}
class AttrOpInteger_Short_ extends Test {
  AttrOpInteger_Short_(this, Api_postgres_async)
}

