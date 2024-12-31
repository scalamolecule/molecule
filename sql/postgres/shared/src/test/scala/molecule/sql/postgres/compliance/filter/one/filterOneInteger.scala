package molecule.sql.postgres.compliance.filter.one

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filter.one.number._
import molecule.sql.postgres.setup.Api_postgres_async


class FilterOneInteger_Int extends MUnitSuite {
  FilterOneInteger_Int(this, Api_postgres_async)
}
class FilterOneInteger_Long_ extends MUnitSuite {
  FilterOneInteger_Long_(this, Api_postgres_async)
}
class FilterOneInteger_BigInt_ extends MUnitSuite {
  FilterOneInteger_BigInt_(this, Api_postgres_async)
}
class FilterOneInteger_Byte_ extends MUnitSuite {
  FilterOneInteger_Byte_(this, Api_postgres_async)
}
class FilterOneInteger_Short_ extends MUnitSuite {
  FilterOneInteger_Short_(this, Api_postgres_async)
}

