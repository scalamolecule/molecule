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

case class AggrNum_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[Short] = tolerantShortEquality(toleranceShort)
    val sumAll = ((short1 + short2 + short2 + short3 + short4) * 100 / short100).asInstanceOf[Short]
    val sum1   = ((short1 + short2) * 100 / short100).asInstanceOf[Short]
    val sum2   = ((short2 + short3 + short4) * 100 / short100).asInstanceOf[Short]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + short1).asInstanceOf[Short]
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // 1 attribute
      _ <- Entity.short(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.short(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum)(short1).query.get.map(_ ==> Nil)

      _ <- Entity.short(sum).not(short1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum).<=(short1).query.get.map(_ ==> Nil)

      _ <- Entity.short(sum).>(short1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.short(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.short(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.short(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(sum)(short1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(sum).not(short1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.short(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(sum).<=(short1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(sum).>(short1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.short(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(sum).>=(sum2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = short2.toString.toDouble // middle number
    val median1   = ((short1 + short2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = short5.toString.toDouble // middle number
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short5),
        (2, short9),
      )).transact

      // 1 attribute
      _ <- Entity.short(median).query.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.short(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.short(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = short2.toString.toDouble // middle number
      val median1   = ((short1 + short2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = short5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short5),
          (2, short9),
        )).transact

        // 1 attribute
        _ <- Entity.short(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.short(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.short(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.short(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.short(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(median).>=(median2).query.get.map(_ ==> List(b))
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((short1 + short2 + short2 + short3 + short4).toDouble * 100 / 500.0)
    val avg1   = ((short1 + short2).toDouble * 100 / 200.0)
    val avg2   = ((short2 + short3 + short4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // 1 attribute
      _ <- Entity.short(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.short(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.short(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.short(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.short(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.short(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.short(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.short(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.short(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.short(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.short(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(avg).>=(avg2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(short1, short2, short2, short3, short4)
    val variance1   = varianceOf(short1, short2)
    val variance2   = varianceOf(short2, short3, short4)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // 1 attribute
      _ <- Entity.short(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.short(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.short(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(short1, short2, short2, short3, short4)
      val variance1   = varianceOf(short1, short2)
      val variance2   = varianceOf(short2, short3, short4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // 1 attribute
        _ <- Entity.short(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.short(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.short(variance)(variance1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(variance).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(variance).not(variance1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.short(variance).<(variance2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(variance).<=(variance2).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(variance).<=(variance1).query.get.map(_ ==> List(a))

        _ <- Entity.i.a1.short(variance).>(variance1).query.get.map(_ ==> List(b))
        _ <- Entity.i.a1.short(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(variance).>=(variance1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(variance).>=(variance2).query.get.map(_ ==> List(b))
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(short1, short2, short2, short3, short4)
    val stddev1   = stdDevOf(short1, short2)
    val stddev2   = stdDevOf(short2, short3, short4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // 1 attribute
      _ <- Entity.short(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.short(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.short(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(short1, short2, short2, short3, short4)
      val stddev1   = stdDevOf(short1, short2)
      val stddev2   = stdDevOf(short2, short3, short4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.short.insert(List(
          (1, short1),
          (1, short2),
          (2, short2),
          (2, short3),
          (2, short4),
        )).transact

        // 1 attribute
        _ <- Entity.short(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.short(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.short(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.short(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.short(stddev)(stddev1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(stddev).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(stddev).not(stddev1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.short(stddev).<(stddev2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.short(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(stddev).<=(stddev2).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(stddev).<=(stddev1).query.get.map(_ ==> List(a))

        _ <- Entity.i.a1.short(stddev).>(stddev1).query.get.map(_ ==> List(b))
        _ <- Entity.i.a1.short(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.short(stddev).>=(stddev1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.short(stddev).>=(stddev2).query.get.map(_ ==> List(b))
      } yield ()
    }
  }
}