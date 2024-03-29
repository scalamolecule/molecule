package molecule.sql.h2.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.any._
import molecule.sql.h2.setup.TestAsync_h2

object AggrOne_String extends AggrOne_String_ with TestAsync_h2
object AggrOne_Int extends AggrOne_Int with TestAsync_h2
object AggrOne_Long extends AggrOne_Long_ with TestAsync_h2
object AggrOne_Float extends AggrOne_Float_ with TestAsync_h2
object AggrOne_Double extends AggrOne_Double_ with TestAsync_h2
object AggrOne_Boolean extends AggrOne_Boolean with TestAsync_h2
object AggrOne_BigInt extends AggrOne_BigInt_ with TestAsync_h2
object AggrOne_BigDecimal extends AggrOne_BigDecimal_ with TestAsync_h2
object AggrOne_Date extends AggrOne_Date_ with TestAsync_h2
object AggrOne_Duration extends AggrOne_Duration_ with TestAsync_h2
object AggrOne_Instant extends AggrOne_Instant_ with TestAsync_h2
object AggrOne_LocalDate extends AggrOne_LocalDate_ with TestAsync_h2
object AggrOne_LocalTime extends AggrOne_LocalTime_ with TestAsync_h2
object AggrOne_LocalDateTime extends AggrOne_LocalDateTime_ with TestAsync_h2
object AggrOne_OffsetTime extends AggrOne_OffsetTime_ with TestAsync_h2
object AggrOne_OffsetDateTime extends AggrOne_OffsetDateTime_ with TestAsync_h2
object AggrOne_ZonedDateTime extends AggrOne_ZonedDateTime_ with TestAsync_h2
object AggrOne_UUID extends AggrOne_UUID_ with TestAsync_h2
object AggrOne_URI extends AggrOne_URI_ with TestAsync_h2
object AggrOne_Byte extends AggrOne_Byte_ with TestAsync_h2
object AggrOne_Short extends AggrOne_Short_ with TestAsync_h2
object AggrOne_Char extends AggrOne_Char_ with TestAsync_h2

object AggrOne_ref extends AggrOne_ref_ with TestAsync_h2
