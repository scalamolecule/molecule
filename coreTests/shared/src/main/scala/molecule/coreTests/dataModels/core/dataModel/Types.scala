package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Types extends DataModel(5) {

  trait Ns {
    val i  = oneInt
    val ii = setInt
    val s  = oneString
    val u  = oneInt.unique

    // Single values
    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar
    val ref            = one[Ref]
    val other          = one[Other]

    // Set
    val strings         = setString
    val ints            = setInt
    val longs           = setLong
    val floats          = setFloat
    val doubles         = setDouble
    val booleans        = setBoolean
    val bigInts         = setBigInt
    val bigDecimals     = setBigDecimal
    val dates           = setDate
    val durations       = setDuration
    val instants        = setInstant
    val localDates      = setLocalDate
    val localTimes      = setLocalTime
    val localDateTimes  = setLocalDateTime
    val offsetTimes     = setOffsetTime
    val offsetDateTimes = setOffsetDateTime
    val zonedDateTimes  = setZonedDateTime
    val uuids           = setUUID
    val uris            = setURI
    val bytes           = setByte
    val shorts          = setShort
    val chars           = setChar
    val refs            = many[Ref]

    // Seq
    val stringSeq         = seqString
    val intSeq            = seqInt
    val longSeq           = seqLong
    val floatSeq          = seqFloat
    val doubleSeq         = seqDouble
    val booleanSeq        = seqBoolean
    val bigIntSeq         = seqBigInt
    val bigDecimalSeq     = seqBigDecimal
    val dateSeq           = seqDate
    val durationSeq       = seqDuration
    val instantSeq        = seqInstant
    val localDateSeq      = seqLocalDate
    val localTimeSeq      = seqLocalTime
    val localDateTimeSeq  = seqLocalDateTime
    val offsetTimeSeq     = seqOffsetTime
    val offsetDateTimeSeq = seqOffsetDateTime
    val zonedDateTimeSeq  = seqZonedDateTime
    val uuidSeq           = seqUUID
    val uriSeq            = seqURI
    val byteSeq           = seqByte
    val shortSeq          = seqShort
    val charSeq           = seqChar

    // Map
    val stringMap         = mapString
    val intMap            = mapInt
    val longMap           = mapLong
    val floatMap          = mapFloat
    val doubleMap         = mapDouble
    val booleanMap        = mapBoolean
    val bigIntMap         = mapBigInt
    val bigDecimalMap     = mapBigDecimal
    val dateMap           = mapDate
    val durationMap       = mapDuration
    val instantMap        = mapInstant
    val localDateMap      = mapLocalDate
    val localTimeMap      = mapLocalTime
    val localDateTimeMap  = mapLocalDateTime
    val offsetTimeMap     = mapOffsetTime
    val offsetDateTimeMap = mapOffsetDateTime
    val zonedDateTimeMap  = mapZonedDateTime
    val uuidMap           = mapUUID
    val uriMap            = mapURI
    val byteMap           = mapByte
    val shortMap          = mapShort
    val charMap           = mapChar
  }

  trait Ref {
    val i = oneInt
    val s = oneString

    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar

    val ii = setInt
    val ss = setString

    val strings         = setString
    val ints            = setInt
    val longs           = setLong
    val floats          = setFloat
    val doubles         = setDouble
    val booleans        = setBoolean
    val bigInts         = setBigInt
    val bigDecimals     = setBigDecimal
    val dates           = setDate
    val durations       = setDuration
    val instants        = setInstant
    val localDates      = setLocalDate
    val localTimes      = setLocalTime
    val localDateTimes  = setLocalDateTime
    val offsetTimes     = setOffsetTime
    val offsetDateTimes = setOffsetDateTime
    val zonedDateTimes  = setZonedDateTime
    val uuids           = setUUID
    val uris            = setURI
    val bytes           = setByte
    val shorts          = setShort
    val chars           = setChar

    val nss = many[Ns]
  }

  trait Other {
    val i = oneInt
    val s = oneString

    val ii = setInt
    val ss = setString

    // 'select' is a reserved keyword in all sql databases.
    // Test if it correctly resolves to 'select_'
    val select = oneInt
  }
}