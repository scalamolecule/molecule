package molecule.sql.h2.compliance.filter.one

import molecule.coreTests.spi.filter.one.number._
import molecule.sql.h2.setup.Test_h2_async


object Test_FilterOneSpecial_Int extends FilterOneInteger_Int with Test_h2_async
object Test_FilterOneSpecial_Long_ extends FilterOneInteger_Long_ with Test_h2_async
object Test_FilterOneSpecial_BigInt_ extends FilterOneInteger_BigInt_ with Test_h2_async
object Test_FilterOneSpecial_Byte_ extends FilterOneInteger_Byte_ with Test_h2_async
object Test_FilterOneSpecial_Short_ extends FilterOneInteger_Short_ with Test_h2_async


