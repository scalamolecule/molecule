package molecule.sql.h2.compliance.filter.one

import molecule.coreTests.spi.filter.one._
import molecule.coreTests.spi.filter.one.special._
import molecule.coreTests.spi.filter.one.types._
import molecule.sql.h2.setup.Test_h2_async

object Test_FilterOne_String_ extends FilterOne_String_ with Test_h2_async
object Test_FilterOne_Int extends FilterOne_Int with Test_h2_async
object Test_FilterOne_Long_ extends FilterOne_Long_ with Test_h2_async
object Test_FilterOne_Float_ extends FilterOne_Float_ with Test_h2_async
object Test_FilterOne_Double_ extends FilterOne_Double_ with Test_h2_async
object Test_FilterOne_Boolean extends FilterOne_Boolean with Test_h2_async
object Test_FilterOne_BigInt_ extends FilterOne_BigInt_ with Test_h2_async
object Test_FilterOne_BigDecimal_ extends FilterOne_BigDecimal_ with Test_h2_async
object Test_FilterOne_Date_ extends FilterOne_Date_ with Test_h2_async
object Test_FilterOne_Duration_ extends FilterOne_Duration_ with Test_h2_async
object Test_FilterOne_Instant_ extends FilterOne_Instant_ with Test_h2_async
object Test_FilterOne_LocalDate_ extends FilterOne_LocalDate_ with Test_h2_async
object Test_FilterOne_LocalTime_ extends FilterOne_LocalTime_ with Test_h2_async
object Test_FilterOne_LocalDateTime_ extends FilterOne_LocalDateTime_ with Test_h2_async
object Test_FilterOne_OffsetTime_ extends FilterOne_OffsetTime_ with Test_h2_async
object Test_FilterOne_OffsetDateTime_ extends FilterOne_OffsetDateTime_ with Test_h2_async
object Test_FilterOne_ZonedDateTime_ extends FilterOne_ZonedDateTime_ with Test_h2_async
object Test_FilterOne_UUID_ extends FilterOne_UUID_ with Test_h2_async
object Test_FilterOne_URI_ extends FilterOne_URI_ with Test_h2_async
object Test_FilterOne_Byte_ extends FilterOne_Byte_ with Test_h2_async
object Test_FilterOne_Short_ extends FilterOne_Short_ with Test_h2_async
object Test_FilterOne_Char_ extends FilterOne_Char_ with Test_h2_async

object Test_FilterOne_ref_ extends FilterOne_ref_ with Test_h2_async
object Test_FilterOne_id extends FilterOne_id with Test_h2_async

object Test_FilterOneSpecial_Number extends FilterOneSpecial_Number with Test_h2_async
object Test_FilterOneSpecial_String extends FilterOneSpecial_String with Test_h2_async

object Test_FilterRefOne extends FilterRefOne with Test_h2_async
