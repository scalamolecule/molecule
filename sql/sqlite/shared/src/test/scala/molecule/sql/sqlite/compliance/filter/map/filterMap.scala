package molecule.sql.sqlite.compliance.filter.map

import molecule.coreTests.spi.filter.map.MapSemantics
import molecule.coreTests.spi.filter.map.types._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_MapSemantics extends MapSemantics with Test_sqlite_async

object Test_FilterMap_String_ extends FilterMap_String_ with Test_sqlite_async
object Test_FilterMap_Int extends FilterMap_Int with Test_sqlite_async
object Test_FilterMap_Long_ extends FilterMap_Long_ with Test_sqlite_async
object Test_FilterMap_Float_ extends FilterMap_Float_ with Test_sqlite_async
object Test_FilterMap_Double_ extends FilterMap_Double_ with Test_sqlite_async
object Test_FilterMap_Boolean extends FilterMap_Boolean with Test_sqlite_async
object Test_FilterMap_BigInt_ extends FilterMap_BigInt_ with Test_sqlite_async
object Test_FilterMap_BigDecimal_ extends FilterMap_BigDecimal_ with Test_sqlite_async
object Test_FilterMap_Date_ extends FilterMap_Date_ with Test_sqlite_async
object Test_FilterMap_Duration_ extends FilterMap_Duration_ with Test_sqlite_async
object Test_FilterMap_Instant_ extends FilterMap_Instant_ with Test_sqlite_async
object Test_FilterMap_LocalDate_ extends FilterMap_LocalDate_ with Test_sqlite_async
object Test_FilterMap_LocalTime_ extends FilterMap_LocalTime_ with Test_sqlite_async
object Test_FilterMap_LocalDateTime_ extends FilterMap_LocalDateTime_ with Test_sqlite_async
object Test_FilterMap_OffsetTime_ extends FilterMap_OffsetTime_ with Test_sqlite_async
object Test_FilterMap_OffsetDateTime_ extends FilterMap_OffsetDateTime_ with Test_sqlite_async
object Test_FilterMap_ZonedDateTime_ extends FilterMap_ZonedDateTime_ with Test_sqlite_async
object Test_FilterMap_UUID_ extends FilterMap_UUID_ with Test_sqlite_async
object Test_FilterMap_URI_ extends FilterMap_URI_ with Test_sqlite_async
object Test_FilterMap_Byte_ extends FilterMap_Byte_ with Test_sqlite_async
object Test_FilterMap_Short_ extends FilterMap_Short_ with Test_sqlite_async
object Test_FilterMap_Char_ extends FilterMap_Char_ with Test_sqlite_async
