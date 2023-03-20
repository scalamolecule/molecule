package molecule.base.dataModel

import java.util.Date
import molecule.DataModel

object Nss extends DataModel(3) {

  trait Ns {
    val int    = oneInt
    val string = oneString
    val ref1   = one[Ref1]
  }

  trait Ref1 {
    val str1 = oneString.descr("foo")
    val int1 = oneInt.unique.descr("bar").alias("hej")
  }

  trait Format {
    val single1 = oneInt.validate(_ > 2, "error...")
    val single2 = oneInt.validate((v: Int) => v.>(2),
      """Very elaborate error explanation
        |with multiple lines
        |""".stripMargin
    )
    val single3 = oneInt.validate((v: Int) => v.>(2),
      """Very elaborate error explanation
        |with multiple lines""".stripMargin
    )

    val multiLine  = oneInt.validate { v =>
      val data   = 22
      val result = data % 10
      v > result
    }
    val multiLine2 = oneInt.validate(
      { v =>
        val data   = 22
        val result = data % 10
        v > result
      },
      "Error message for multiline test"
    )
    val multiLine3 = oneInt.validate(
      { v =>
        val data   = 22
        val result = data % 10
        v > result
      },
      """Multiline error message
        |for multiline test""".stripMargin
    )

    val logic = oneInt.validate(v => v > 2 && v < 8 && v % 2 == 0)

    val withMsg = oneInt.validate(
      v => v >= 3 && v <= 9 && v % 2 == 1,
      "Value must be an odd number between 3 and 9"
    )

    val multipleMsgs = oneInt.validate {
      case v if v <= 2     => "Number must be bigger than 2"
      case v if v >= 10    => "Number must be smaller than 10"
      case v if v % 2 == 0 => "Number must be odd"
    }

    val multipleMsgs2 = oneInt.validate {
      case v if v <= 2     => "Number must be bigger than 2"
      case v if v >= 10    => "Number must be smaller than 10"
      case v if v % 2 == 0 =>
        """Number must
          |be odd""".stripMargin
    }
  }

  trait Type {
    val string     = oneString.validate(_ > "a")
    val int        = oneInt.validate(_ > 1)
    val long       = oneLong.validate(_ > 1L)
    val float      = oneFloat.validate(_ > 1.1f)
    val double     = oneDouble.validate(_ > 1.1)
    val boolean    = oneBoolean.validate(_ == true)
    val bigInt     = oneBigInt.validate(_ > BigInt(1))
    val bigDecimal = oneBigDecimal.validate(_ > BigDecimal(1.1))
    val date       = oneDate.validate(_.after(new Date(1234567)))
    val uuid       = oneUUID.validate(_.toString.contains("-"))
    val uri        = oneURI.validate(_.toString.length > 1)
    val byte       = oneByte.validate(_ > 1)
    val short      = oneShort.validate(_ > 1)
    val char       = oneChar.validate(_.toInt > 'a'.toInt)

    val strings     = setString.validate(_ > "a")
    val ints        = setInt.validate(_ > 1)
    val longs       = setLong.validate(_ > 1L)
    val floats      = setFloat.validate(_ > 1.1f)
    val doubles     = setDouble.validate(_ > 1.1)
    val booleans    = setBoolean.validate(_ == true)
    val bigInts     = setBigInt.validate(_ > BigInt(1))
    val bigDecimals = setBigDecimal.validate(_ > BigDecimal(1.1))
    val dates       = setDate.validate(_.after(new Date(1234567)))
    val uuids       = setUUID.validate(_.toString.contains("-"))
    val uris        = setURI.validate(_.toString.length > 1)
    val bytes       = setByte.validate(_ > 1)
    val shorts      = setShort.validate(_ > 1)
    val chars       = setChar.validate(_.toInt > 'a'.toInt)
  }

  trait Strings {
    val email        = oneString.email
    val emailWithMsg = oneString.email("Nice try. Please provide a real email")

    val regex        = oneString.regex("^[a-zA-Z0-9]+$")
    val regexWithMsg = oneString.regex(
      "^[a-zA-Z0-9]+$",
      "Username cannot contain special characters."
    )
  }

  trait Allowed {
    val luckyNumbers = oneInt.allowed(7, 9, 13)
  }


  // todo --------------------------------------

  //  trait AttrValue {
  //    val min1 = oneInt
  //    val mid1 = oneInt.validate(i => i > min1.value && i < max1.value)
  //    val max1 = oneInt
  //
  //    // Same as
  //    val min2 = oneInt.validate(_ < mid2.value)
  //    val mid2 = oneInt.validate(_ < max2.value)
  //    val max2 = oneInt
  //
  //    // Same as
  //    val min3 = oneInt.validate(_ < mid2.value)
  //    val mid3 = oneInt
  //    val max3 = oneInt.validate(_ > mid2.value)
  //
  //    val compareWithCalculation = oneInt.validate(_ > date.value.getTime.toInt)
  //    val date                   = oneDate
  //
  //    val int5 = oneInt.validate(_ > ints.value) // hmm, shouldn't be possible
  //    val ints = setInt
  //  }
  //
  //  trait Mandatory {
  //    val username = oneString.mandatory
  //    val email    = oneInt.mandatory
  //    val age      = oneString
  //  }
  //
  //  trait Require {
  //    // tuple enforcement
  //    val lat  = oneInt.require(long)
  //    val long = oneInt.descr("(will reversely also require `lat`")
  //
  //    // triple enforcement
  //    val x = oneInt.require(y, z)
  //    val y = oneInt.descr("(will reversely also require `x` and `z`")
  //    val z = oneInt.descr("(will reversely also require `x` and `y`")
  //
  //    // Asymmetric requirement
  //    val required  = oneString.descr("Doesn't need requiring attribute value")
  //    val requiring = oneString.require(required).descr("Needs required attribute value")
  //  }
}

