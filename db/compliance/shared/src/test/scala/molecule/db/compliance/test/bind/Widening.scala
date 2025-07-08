package molecule.db.compliance.test.bind

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Widening(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Byte" - types { implicit conn =>
    for {
      _ <- Entity.byte(byte1).save.transact
      eq = Entity.byte(?).a1.query

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==> byte1)
      _ <- eq(short1).get.map(_.head ==> byte1)
      _ <- eq(int1).get.map(_.head ==> byte1)
      _ <- eq(long1).get.map(_.head ==> byte1)
      _ <- eq(bigInt1).get.map(_.head ==> byte1)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Byte."
        }
    } yield ()
  }

  "Short" - types { implicit conn =>
    for {
      _ <- Entity.short(short1).save.transact
      eq = Entity.short(?).a1.query

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==> short1)
      _ <- eq(short1).get.map(_.head ==> short1)
      _ <- eq(int1).get.map(_.head ==> short1)
      _ <- eq(long1).get.map(_.head ==> short1)
      _ <- eq(bigInt1).get.map(_.head ==> short1)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Short."
        }
    } yield ()
  }

  "Int" - types { implicit conn =>
    for {
      _ <- Entity.int(int1).save.transact
      eq = Entity.int(?).a1.query

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==> int1)
      _ <- eq(short1).get.map(_.head ==> int1)
      _ <- eq(int1).get.map(_.head ==> int1)
      _ <- eq(long1).get.map(_.head ==> int1)
      _ <- eq(bigInt1).get.map(_.head ==> int1)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Int."
        }
    } yield ()
  }

  "Long" - types { implicit conn =>
    for {
      _ <- Entity.long(long1).save.transact
      eq = Entity.long(?).a1.query

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==> long1)
      _ <- eq(short1).get.map(_.head ==> long1)
      _ <- eq(int1).get.map(_.head ==> long1)
      _ <- eq(long1).get.map(_.head ==> long1)
      _ <- eq(bigInt1).get.map(_.head ==> long1)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Long."
        }
    } yield ()
  }


  "BigInt" - types { implicit conn =>
    for {
      _ <- Entity.bigInt(bigInt1).save.transact
      eq = Entity.bigInt(?).a1.query

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==> bigInt1)
      _ <- eq(short1).get.map(_.head ==> bigInt1)
      _ <- eq(int1).get.map(_.head ==> bigInt1)
      _ <- eq(long1).get.map(_.head ==> bigInt1)
      _ <- eq(bigInt1).get.map(_.head ==> bigInt1)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type BigInt."
        }
    } yield ()
  }


  "Float" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.float(1.1f).save.transact
      eq = Entity.float(?).a1.query

      // Comparing Float with Float ok
      _ <- eq(1.1f).get.map(_.head ==~ 1.1f)

      // To avoid rounding imprecision, comparing Double with Float is not allowed
      _ <- eq(1.1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Double but should be of type Float."
        }
    } yield ()
  }

  "Float, no decimals" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.float(1.0f).save.transact
      eq = Entity.float(?).a1.query

      // Comparing Float with Float ok
      _ <- eq(1.0f).get.map(_.head ==~ 1.0f)

      // Whole number types converted to decimal type if possible
      _ <- eq(byte1).get.map(_.head ==~ 1.0f)
      _ <- eq(short1).get.map(_.head ==~ 1.0f)
      _ <- eq(int1).get.map(_.head ==~ 1.0f)
      _ <- eq(long1).get.map(_.head ==~ 1.0f)
      _ <- eq(bigInt1).get.map(_.head ==> 1.0f)

      // Other types rejected
      _ <- eq("a").get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `a` is of type String but should be of type Float."
        }
    } yield ()
  }


  "Double" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.double(1.1).save.transact
      eq = Entity.double(?).a1.query

      // Comparing Double with Double ok
      _ <- eq(1.1).get.map(_.head ==~ 1.1)

      // To avoid rounding imprecision, comparing Float with Double is not allowed
      _ <- eq(1.1f).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Double."
        }
    } yield ()
  }

  "Double, no decimals" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.double(1.0).save.transact
      eq = Entity.double(?).a1.query

      // Comparing Double with Double ok
      _ <- eq(1.0).get.map(_.head ==~ 1.0f)

      // Accepted types
      _ <- eq(byte1).get.map(_.head ==~ 1.0)
      _ <- eq(short1).get.map(_.head ==~ 1.0)
      _ <- eq(int1).get.map(_.head ==~ 1.0)
      _ <- eq(long1).get.map(_.head ==~ 1.0)
      _ <- eq(bigInt1).get.map(_.head ==> 1.0)

      // Other types rejected
      _ <- eq(float1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Float but should be of type Double."
        }
    } yield ()
  }


  "BigDecimal" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    for {
      _ <- Entity.bigDecimal(BigDecimal("1.1")).save.transact
      eq = Entity.bigDecimal(?).a1.query

      // Comparing BigDecimal with BigDecimal ok
      _ <- eq(BigDecimal("1.1")).get.map(_.head ==~ BigDecimal("1.1"))

      // To avoid rounding imprecision, comparing Double with BigDecimal is not allowed
      _ <- eq(1.1).get
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "First bind value `1.1` is of type Double but should be of type BigDecimal."
        }
    } yield ()
  }

  "BigDecimal, no decimals" - types { implicit conn =>
    if (database == "sqlite")
      for {
        _ <- Entity.bigDecimal(BigDecimal("1.0")).save.transact
        eq = Entity.bigDecimal(?).a1.query

        // Comparing BigDecimal with BigDecimal ok
        _ <- eq(BigDecimal("1.0")).get.map(_.head ==~ BigDecimal("1.0"))

        // No match since BigDecimal is saved as text in Sqlite ("1.0", not "1")
        //      _ <- eq(byte1).get.map(_.head ==> BigDecimal("1.0"))
        //      _ <- eq(short1).get.map(_.head ==> BigDecimal("1.0"))
        //      _ <- eq(int1).get.map(_.head ==> BigDecimal("1.0"))
        //      _ <- eq(long1).get.map(_.head ==> BigDecimal("1.0"))
        //      _ <- eq(bigInt1).get.map(_.head ==> BigDecimal("1.0"))

        // Other types rejected
        _ <- eq(float1).get
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==> "First bind value `1.1` is of type Float but should be of type BigDecimal."
          }
      } yield ()
    else
      for {
        _ <- Entity.bigDecimal(BigDecimal("1.0")).save.transact
        eq = Entity.bigDecimal(?).a1.query

        // Comparing BigDecimal with BigDecimal ok
        _ <- eq(BigDecimal("1.0")).get.map(_.head ==~ BigDecimal("1.0"))

        // Accepted types
        _ <- eq(byte1).get.map(_.head ==> BigDecimal("1.0"))
        _ <- eq(short1).get.map(_.head ==> BigDecimal("1.0"))
        _ <- eq(int1).get.map(_.head ==> BigDecimal("1.0"))
        _ <- eq(long1).get.map(_.head ==> BigDecimal("1.0"))
        _ <- eq(bigInt1).get.map(_.head ==> BigDecimal("1.0"))

        // Other types rejected
        _ <- eq(float1).get
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==> "First bind value `1.1` is of type Float but should be of type BigDecimal."
          }
      } yield ()
  }
}
