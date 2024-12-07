package molecule.sql.h2.compliance.filter.one

import molecule.coreTests.spi.filter.one.decimal._
import molecule.sql.h2.setup.Test_h2_async


object Test_FilterOneSpecial_Float_ extends FilterOneDecimal_Float_ with Test_h2_async
object Test_FilterOneSpecial_Double_ extends FilterOneDecimal_Double with Test_h2_async
object Test_FilterOneSpecial_BigDecimal_ extends FilterOneDecimal_BigDecimal_ with Test_h2_async

