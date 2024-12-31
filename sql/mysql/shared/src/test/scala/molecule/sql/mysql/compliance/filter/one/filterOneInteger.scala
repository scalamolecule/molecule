package molecule.sql.mysql.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one.number._
import molecule.sql.mysql.setup.Api_mysql_async


class FilterOneInteger_Int extends Test {
  FilterOneInteger_Int(this, Api_mysql_async)
}
class FilterOneInteger_Long_ extends Test {
  FilterOneInteger_Long_(this, Api_mysql_async)
}
class FilterOneInteger_BigInt_ extends Test {
  FilterOneInteger_BigInt_(this, Api_mysql_async)
}
class FilterOneInteger_Byte_ extends Test {
  FilterOneInteger_Byte_(this, Api_mysql_async)
}
class FilterOneInteger_Short_ extends Test {
  FilterOneInteger_Short_(this, Api_mysql_async)
}

