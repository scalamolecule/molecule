package molecule.document.mongodb.compliance.filter

import molecule.coreTests.spi.filter.FilterOne_id
import molecule.coreTests.spi.filter.one._
import molecule.coreTests.spi.filter.one.special._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FilterOne_String_ extends FilterOne_String_ with TestAsync_mongodb
object FilterOne_Int extends FilterOne_Int with TestAsync_mongodb
object FilterOne_Long_ extends FilterOne_Long_ with TestAsync_mongodb
object FilterOne_Float_ extends FilterOne_Float_ with TestAsync_mongodb
object FilterOne_Double_ extends FilterOne_Double_ with TestAsync_mongodb
object FilterOne_Boolean extends FilterOne_Boolean with TestAsync_mongodb
object FilterOne_BigInt_ extends FilterOne_BigInt_ with TestAsync_mongodb
object FilterOne_BigDecimal_ extends FilterOne_BigDecimal_ with TestAsync_mongodb
object FilterOne_Date_ extends FilterOne_Date_ with TestAsync_mongodb
object FilterOne_Duration_ extends FilterOne_Duration_ with TestAsync_mongodb
object FilterOne_Instant_ extends FilterOne_Instant_ with TestAsync_mongodb
object FilterOne_LocalDate_ extends FilterOne_LocalDate_ with TestAsync_mongodb
object FilterOne_LocalTime_ extends FilterOne_LocalTime_ with TestAsync_mongodb
object FilterOne_LocalDateTime_ extends FilterOne_LocalDateTime_ with TestAsync_mongodb
object FilterOne_OffsetTime_ extends FilterOne_OffsetTime_ with TestAsync_mongodb
object FilterOne_OffsetDateTime_ extends FilterOne_OffsetDateTime_ with TestAsync_mongodb
object FilterOne_ZonedDateTime_ extends FilterOne_ZonedDateTime_ with TestAsync_mongodb
object FilterOne_UUID_ extends FilterOne_UUID_ with TestAsync_mongodb
object FilterOne_URI_ extends FilterOne_URI_ with TestAsync_mongodb
object FilterOne_Byte_ extends FilterOne_Byte_ with TestAsync_mongodb
object FilterOne_Short_ extends FilterOne_Short_ with TestAsync_mongodb
object FilterOne_Char_ extends FilterOne_Char_ with TestAsync_mongodb
object FilterOne_ref_ extends FilterOne_ref_ with TestAsync_mongodb

object FilterOneSpecial_Number extends FilterOneSpecial_Number with TestAsync_mongodb
object FilterOneSpecial_String extends FilterOneSpecial_String with TestAsync_mongodb

object FilterOne_id extends FilterOne_id with TestAsync_mongodb
