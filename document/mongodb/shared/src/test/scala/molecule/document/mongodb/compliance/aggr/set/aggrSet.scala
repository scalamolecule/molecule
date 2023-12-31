package molecule.document.mongodb.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.any._
import molecule.document.mongodb.setup.TestAsync_mongodb

object AggrSet_String extends AggrSet_String_ with TestAsync_mongodb
object AggrSet_Int extends AggrSet_Int with TestAsync_mongodb
object AggrSet_Long extends AggrSet_Long_ with TestAsync_mongodb
object AggrSet_Float extends AggrSet_Float_ with TestAsync_mongodb
object AggrSet_Double extends AggrSet_Double_ with TestAsync_mongodb
object AggrSet_Boolean extends AggrSet_Boolean with TestAsync_mongodb
object AggrSet_BigInt extends AggrSet_BigInt_ with TestAsync_mongodb
object AggrSet_BigDecimal extends AggrSet_BigDecimal_ with TestAsync_mongodb
object AggrSet_Date extends AggrSet_Date_ with TestAsync_mongodb
object AggrSet_Duration extends AggrSet_Duration_ with TestAsync_mongodb
object AggrSet_Instant extends AggrSet_Instant_ with TestAsync_mongodb
object AggrSet_LocalDate extends AggrSet_LocalDate_ with TestAsync_mongodb
object AggrSet_LocalTime extends AggrSet_LocalTime_ with TestAsync_mongodb
object AggrSet_LocalDateTime extends AggrSet_LocalDateTime_ with TestAsync_mongodb
object AggrSet_OffsetTime extends AggrSet_OffsetTime_ with TestAsync_mongodb
object AggrSet_OffsetDateTime extends AggrSet_OffsetDateTime_ with TestAsync_mongodb
object AggrSet_ZonedDateTime extends AggrSet_ZonedDateTime_ with TestAsync_mongodb
object AggrSet_UUID extends AggrSet_UUID_ with TestAsync_mongodb
object AggrSet_URI extends AggrSet_URI_ with TestAsync_mongodb
object AggrSet_Byte extends AggrSet_Byte_ with TestAsync_mongodb
object AggrSet_Short extends AggrSet_Short_ with TestAsync_mongodb
object AggrSet_Char extends AggrSet_Char_ with TestAsync_mongodb

// In MongoDB embedded documents don't have entity ids
//object AggrSet_ref extends AggrSet_ref with TestAsync_mongodb