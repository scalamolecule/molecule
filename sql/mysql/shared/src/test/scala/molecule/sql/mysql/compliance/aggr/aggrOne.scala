package molecule.sql.mysql.compliance.aggr

import molecule.coreTests.spi.aggr.one.any._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrOne_String extends AggrOne_String_ with TestAsync_mysql
object AggrOne_Int extends AggrOne_Int with TestAsync_mysql
object AggrOne_Long extends AggrOne_Long_ with TestAsync_mysql
object AggrOne_Float extends AggrOne_Float_ with TestAsync_mysql
object AggrOne_Double extends AggrOne_Double_ with TestAsync_mysql
object AggrOne_Boolean extends AggrOne_Boolean with TestAsync_mysql
object AggrOne_BigInt extends AggrOne_BigInt_ with TestAsync_mysql
object AggrOne_BigDecimal extends AggrOne_BigDecimal_ with TestAsync_mysql
object AggrOne_Date extends AggrOne_Date_ with TestAsync_mysql
object AggrOne_Duration extends AggrOne_Duration_ with TestAsync_mysql
object AggrOne_Instant extends AggrOne_Instant_ with TestAsync_mysql
object AggrOne_LocalDate extends AggrOne_LocalDate_ with TestAsync_mysql
object AggrOne_LocalTime extends AggrOne_LocalTime_ with TestAsync_mysql
object AggrOne_LocalDateTime extends AggrOne_LocalDateTime_ with TestAsync_mysql
object AggrOne_OffsetTime extends AggrOne_OffsetTime_ with TestAsync_mysql
object AggrOne_OffsetDateTime extends AggrOne_OffsetDateTime_ with TestAsync_mysql
object AggrOne_ZonedDateTime extends AggrOne_ZonedDateTime_ with TestAsync_mysql
object AggrOne_UUID extends AggrOne_UUID_ with TestAsync_mysql
object AggrOne_URI extends AggrOne_URI_ with TestAsync_mysql
object AggrOne_Byte extends AggrOne_Byte_ with TestAsync_mysql
object AggrOne_Short extends AggrOne_Short_ with TestAsync_mysql
object AggrOne_Char extends AggrOne_Char_ with TestAsync_mysql
object AggrOne_ref extends AggrOne_ref_ with TestAsync_mysql
