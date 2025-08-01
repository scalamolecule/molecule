// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrNum_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[BigDecimal] = tolerantBigDecimalEquality(toleranceBigDecimal)
    val sumAll = ((bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4) * 100 / bigDecimal100).asInstanceOf[BigDecimal]
    val sum1   = ((bigDecimal1 + bigDecimal2) * 100 / bigDecimal100).asInstanceOf[BigDecimal]
    val sum2   = ((bigDecimal2 + bigDecimal3 + bigDecimal4) * 100 / bigDecimal100).asInstanceOf[BigDecimal]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + bigDecimal1).asInstanceOf[BigDecimal]
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.bigDecimal(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum)(bigDecimal1).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(sum).not(bigDecimal1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum).<=(bigDecimal1).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(sum).>(bigDecimal1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigDecimal(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.bigDecimal(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.bigDecimal(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(sum)(bigDecimal1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(sum).not(bigDecimal1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigDecimal(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(sum).<=(bigDecimal1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(sum).>(bigDecimal1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigDecimal(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(sum).>=(sum2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = bigDecimal2.toString.toDouble // middle number
    val median1   = ((bigDecimal1 + bigDecimal2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = bigDecimal5.toString.toDouble // middle number
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal5),
        (2, bigDecimal9),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(median).query.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.bigDecimal(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigDecimal(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = bigDecimal2.toString.toDouble // middle number
      val median1   = ((bigDecimal1 + bigDecimal2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = bigDecimal5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal5),
          (2, bigDecimal9),
        )).transact

        // 1 attribute
        _ <- Entity.bigDecimal(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigDecimal(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.bigDecimal(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigDecimal(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigDecimal(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.bigDecimal(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigDecimal(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigDecimal(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigDecimal(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.bigDecimal(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigDecimal(median).>=(median2).query.get.map(_ ==> List(b))
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble * 100 / 500.0)
    val avg1   = ((bigDecimal1 + bigDecimal2).toDouble * 100 / 200.0)
    val avg2   = ((bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.bigDecimal(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigDecimal(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigDecimal(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.bigDecimal(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.bigDecimal(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigDecimal(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigDecimal(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigDecimal(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(avg).>=(avg2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
    val variance1   = varianceOf(bigDecimal1, bigDecimal2)
    val variance2   = varianceOf(bigDecimal2, bigDecimal3, bigDecimal4)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.bigDecimal(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigDecimal(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
      val variance1   = varianceOf(bigDecimal1, bigDecimal2)
      val variance2   = varianceOf(bigDecimal2, bigDecimal3, bigDecimal4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // 1 attribute
        _ <- Entity.bigDecimal(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigDecimal(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes

        _ <- Entity.i.a1.bigDecimal(variance)(variance1).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.bigDecimal(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(variance).not(1.0).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigDecimal(variance).not(variance1).query.get.map(_.head._2 ==~ variance2)

        _ <- Entity.i.a1.bigDecimal(variance).<(variance2).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.bigDecimal(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(variance).<=(variance2).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigDecimal(variance).<=(variance1).query.get.map(_.head._2 ==~ variance1)

        _ <- Entity.i.a1.bigDecimal(variance).>(variance1).query.get.map(_.head._2 ==~ variance2)
        _ <- Entity.i.a1.bigDecimal(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(variance).>=(variance1).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigDecimal(variance).>=(variance2).query.get.map(_.head._2 ==~ variance2)
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
    val stddev1   = stdDevOf(bigDecimal1, bigDecimal2)
    val stddev2   = stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.bigDecimal(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigDecimal(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
      val stddev1   = stdDevOf(bigDecimal1, bigDecimal2)
      val stddev2   = stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (1, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
          (2, bigDecimal4),
        )).transact

        // 1 attribute
        _ <- Entity.bigDecimal(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigDecimal(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigDecimal(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.bigDecimal(stddev)(stddev1).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.bigDecimal(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(stddev).not(1.0).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigDecimal(stddev).not(stddev1).query.get.map(_.head._2 ==~ stddev2)

        _ <- Entity.i.a1.bigDecimal(stddev).<(stddev2).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.bigDecimal(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(stddev).<=(stddev2).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigDecimal(stddev).<=(stddev1).query.get.map(_.head._2 ==~ stddev1)

        _ <- Entity.i.a1.bigDecimal(stddev).>(stddev1).query.get.map(_.head._2 ==~ stddev2)
        _ <- Entity.i.a1.bigDecimal(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigDecimal(stddev).>=(stddev1).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigDecimal(stddev).>=(stddev2).query.get.map(_.head._2 ==~ stddev2)
      } yield ()
    }
  }
}