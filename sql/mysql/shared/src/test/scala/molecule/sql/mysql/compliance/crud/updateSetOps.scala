package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.update.set.ops._
import molecule.sql.mysql.setup.TestAsync_mysql

object UpdateSetOps_String_ extends UpdateSetOps_String_ with TestAsync_mysql
object UpdateSetOps_Int extends UpdateSetOps_Int with TestAsync_mysql
object UpdateSetOps_Long_ extends UpdateSetOps_Long_ with TestAsync_mysql
object UpdateSetOps_Float_ extends UpdateSetOps_Float_ with TestAsync_mysql
object UpdateSetOps_Double_ extends UpdateSetOps_Double_ with TestAsync_mysql
object UpdateSetOps_BigInt_ extends UpdateSetOps_BigInt_ with TestAsync_mysql
object UpdateSetOps_BigDecimal_ extends UpdateSetOps_BigDecimal_ with TestAsync_mysql
object UpdateSetOps_Date_ extends UpdateSetOps_Date_ with TestAsync_mysql
object UpdateSetOps_Duration_ extends UpdateSetOps_Duration_ with TestAsync_mysql
object UpdateSetOps_Instant_ extends UpdateSetOps_Instant_ with TestAsync_mysql
object UpdateSetOps_LocalDate_ extends UpdateSetOps_LocalDate_ with TestAsync_mysql
object UpdateSetOps_LocalTime_ extends UpdateSetOps_LocalTime_ with TestAsync_mysql
object UpdateSetOps_LocalDateTime_ extends UpdateSetOps_LocalDateTime_ with TestAsync_mysql
object UpdateSetOps_OffsetTime_ extends UpdateSetOps_OffsetTime_ with TestAsync_mysql
object UpdateSetOps_OffsetDateTime_ extends UpdateSetOps_OffsetDateTime_ with TestAsync_mysql
object UpdateSetOps_ZonedDateTime_ extends UpdateSetOps_ZonedDateTime_ with TestAsync_mysql
object UpdateSetOps_UUID_ extends UpdateSetOps_UUID_ with TestAsync_mysql
object UpdateSetOps_URI_ extends UpdateSetOps_URI_ with TestAsync_mysql
object UpdateSetOps_Byte_ extends UpdateSetOps_Byte_ with TestAsync_mysql
object UpdateSetOps_Short_ extends UpdateSetOps_Short_ with TestAsync_mysql
object UpdateSetOps_Char_ extends UpdateSetOps_Char_ with TestAsync_mysql

object UpdateSetOps_ref_ extends UpdateSetOps_ref with TestAsync_mysql
