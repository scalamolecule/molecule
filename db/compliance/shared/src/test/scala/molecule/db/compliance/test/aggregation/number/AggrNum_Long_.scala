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

case class AggrNum_Long_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[Long] = tolerantLongEquality(toleranceLong)
    val sumAll = ((long1 + long2 + long2 + long3 + long4) * 100 / long100).asInstanceOf[Long]
    val sum1   = ((long1 + long2) * 100 / long100).asInstanceOf[Long]
    val sum2   = ((long2 + long3 + long4) * 100 / long100).asInstanceOf[Long]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + long1).asInstanceOf[Long]
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // 1 attribute
      _ <- Entity.long(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.long(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum)(long1).query.get.map(_ ==> Nil)

      _ <- Entity.long(sum).not(long1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum).<=(long1).query.get.map(_ ==> Nil)

      _ <- Entity.long(sum).>(long1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.long(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.long(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.long(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(sum)(long1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(sum).not(long1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.long(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(sum).<=(long1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(sum).>(long1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.long(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(sum).>=(sum2).query.get.map(_ ==> List(b))

      // Include aggregated attribute too
      _ <- Entity.long.a1.long(sum).query.get.map(_ ==> List(
        (long1, long1),
        (long2, long2 + long2),
        (long3, long3),
        (long4, long4),
      ))
      _ <- Entity.long(sum).long.a1.query.get.map(_ ==> List(
        (long1, long1),
        (long2 + long2, long2),
        (long3, long3),
        (long4, long4),
      ))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = long2.toString.toDouble // middle number
    val median1   = ((long1 + long2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = long5.toString.toDouble // middle number
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long5),
        (2, long9),
      )).transact

      // 1 attribute
      _ <- Entity.long(median).query.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.long(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.long(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = long2.toString.toDouble // middle number
      val median1   = ((long1 + long2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = long5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long5),
          (2, long9),
        )).transact

        // 1 attribute
        _ <- Entity.long(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.long(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.long(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.long(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.long(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.long(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.long(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.long(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.long(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.long(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.long(median).>=(median2).query.get.map(_ ==> List(b))


        // Include aggregated attribute too
        _ <- Entity.long.a1.long(median).query.get.map { res =>
          res(0)._1 ==> long1
          res(0)._2 ==~ long1

          res(1)._1 ==> long2
          res(1)._2 ==~ (long2 + long2).toDouble * 100 / 200.0

          res(2)._1 ==> long5
          res(2)._2 ==~ long5

          res(3)._1 ==> long9
          res(3)._2 ==~ long9
        }

        _ <- Entity.long.long(median).d1.query.get.map { res =>
          res(0)._1 ==> long9
          res(0)._2 ==~ long9

          res(1)._1 ==> long5
          res(1)._2 ==~ long5

          res(2)._1 ==> long2
          res(2)._2 ==~ (long2 + long2).toDouble * 100 / 200.0

          res(3)._1 ==> long1
          res(3)._2 ==~ long1
        }
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((long1 + long2 + long2 + long3 + long4).toDouble * 100 / 500.0)
    val avg1   = ((long1 + long2).toDouble * 100 / 200.0)
    val avg2   = ((long2 + long3 + long4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // 1 attribute
      _ <- Entity.long(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.long(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.long(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.long(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.long(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.long(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.long(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.long(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.long(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.long(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.long(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(avg).>=(avg2).query.get.map(_ ==> List(b))


      // Include aggregated attribute too
      _ <- Entity.long.a1.long(avg).query.get.map { res =>
        res(0)._1 ==> long1
        res(0)._2 ==~ long1

        res(1)._1 ==> long2
        res(1)._2 ==~ (long2 + long2).toDouble * 100 / 200.0

        res(2)._1 ==> long3
        res(2)._2 ==~ long3

        res(3)._1 ==> long4
        res(3)._2 ==~ long4
      }

      _ <- Entity.long.long(avg).d1.query.get.map { res =>
        res(0)._1 ==> long4
        res(0)._2 ==~ long4

        res(1)._1 ==> long3
        res(1)._2 ==~ long3

        res(2)._1 ==> long2
        res(2)._2 ==~ (long2 + long2).toDouble * 100 / 200.0

        res(3)._1 ==> long1
        res(3)._2 ==~ long1
      }
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(long1, long2, long2, long3, long4)
    val variance1   = varianceOf(long1, long2)
    val variance2   = varianceOf(long2, long3, long4)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // 1 attribute
      _ <- Entity.long(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.long(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.long(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(long1, long2, long2, long3, long4)
      val variance1   = varianceOf(long1, long2)
      val variance2   = varianceOf(long2, long3, long4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // 1 attribute
        _ <- Entity.long(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.long(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes

        _ <- Entity.i.a1.long(variance)(variance1).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.long(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(variance).not(1.0).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.long(variance).not(variance1).query.get.map(_.head._2 ==~ variance2)

        _ <- Entity.i.a1.long(variance).<(variance2).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.long(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(variance).<=(variance2).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.long(variance).<=(variance1).query.get.map(_.head._2 ==~ variance1)

        _ <- Entity.i.a1.long(variance).>(variance1).query.get.map(_.head._2 ==~ variance2)
        _ <- Entity.i.a1.long(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(variance).>=(variance1).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.long(variance).>=(variance2).query.get.map(_.head._2 ==~ variance2)


        // Include aggregated attribute too
        _ <- Entity.long.a1.long(variance).query.get.map { res =>
          res(0)._1 ==> long1
          res(0)._2 ==~ 0

          res(1)._1 ==> long2
          res(1)._2 ==~ varianceOf(long2, long2) // always 0

          res(2)._1 ==> long3
          res(2)._2 ==~ 0

          res(3)._1 ==> long4
          res(3)._2 ==~ 0
        }

        _ <- Entity.long.a2.long(variance).d1.query.get.map { res =>
          res(0)._1 ==> long1
          res(0)._2 ==~ varianceOf(long2, long2) // always 0

          res(1)._1 ==> long2
          res(1)._2 ==~ 0

          res(2)._1 ==> long3
          res(2)._2 ==~ 0

          res(3)._1 ==> long4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(long1, long2, long2, long3, long4)
    val stddev1   = stdDevOf(long1, long2)
    val stddev2   = stdDevOf(long2, long3, long4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // 1 attribute
      _ <- Entity.long(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.long(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.long(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(long1, long2, long2, long3, long4)
      val stddev1   = stdDevOf(long1, long2)
      val stddev2   = stdDevOf(long2, long3, long4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // 1 attribute
        _ <- Entity.long(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.long(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.long(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.long(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.long(stddev)(stddev1).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.long(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(stddev).not(1.0).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.long(stddev).not(stddev1).query.get.map(_.head._2 ==~ stddev2)

        _ <- Entity.i.a1.long(stddev).<(stddev2).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.long(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(stddev).<=(stddev2).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.long(stddev).<=(stddev1).query.get.map(_.head._2 ==~ stddev1)

        _ <- Entity.i.a1.long(stddev).>(stddev1).query.get.map(_.head._2 ==~ stddev2)
        _ <- Entity.i.a1.long(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.long(stddev).>=(stddev1).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.long(stddev).>=(stddev2).query.get.map(_.head._2 ==~ stddev2)


        // Include aggregated attribute too
        _ <- Entity.long.a1.long(stddev).query.get.map { res =>
          res(0)._1 ==> long1
          res(0)._2 ==~ 0

          res(1)._1 ==> long2
          res(1)._2 ==~ stdDevOf(long2, long2) // always 0

          res(2)._1 ==> long3
          res(2)._2 ==~ 0

          res(3)._1 ==> long4
          res(3)._2 ==~ 0
        }

        _ <- Entity.long.a2.long(stddev).d1.query.get.map { res =>
          res(0)._1 ==> long1
          res(0)._2 ==~ stdDevOf(long2, long2) // always 0

          res(1)._1 ==> long2
          res(1)._2 ==~ 0

          res(2)._1 ==> long3
          res(2)._2 ==~ 0

          res(3)._1 ==> long4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }
}