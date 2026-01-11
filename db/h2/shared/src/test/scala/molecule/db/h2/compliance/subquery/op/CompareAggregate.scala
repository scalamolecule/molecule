package molecule.db.h2.compliance.subquery.op

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class CompareAggregate extends MUnit with DbProviders_h2 with TestUtils {

  "mandatory 1" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (2, 1),
        (3, 1),
        (4, 1),
      ).transact

      _ <- Entity.i(Ref.i(count)).query.get.map(_ ==> List(
        (3, 3),
      ))
      _ <- Entity.i.not(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
        (4, 3),
      ))
      _ <- Entity.i.<(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
      ))
      _ <- Entity.i.<=(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
        (3, 3),
      ))
      _ <- Entity.i.>(Ref.i(count)).query.get.map(_ ==> List(
        (4, 3),
      ))
      _ <- Entity.i.>=(Ref.i(count)).query.get.map(_ ==> List(
        (3, 3),
        (4, 3),
      ))
    } yield ()
  }


  "mandatory n" - types {
    for {
      _ <- Entity.s.i.Ref.i.insert(
        ("a", 2, 1),
        ("b", 3, 1),
        ("c", 4, 1),
      ).transact

      _ <- Entity.s.i(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3, 3),
      ))
      _ <- Entity.s.i.not(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
        ("c", 4, 3),
      ))
      _ <- Entity.s.i.<(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
      ))
      _ <- Entity.s.i.<=(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
        ("b", 3, 3),
      ))
      _ <- Entity.s.i.>(Ref.i(count)).query.get.map(_ ==> List(
        ("c", 4, 3),
      ))
      _ <- Entity.s.i.>=(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3, 3),
        ("c", 4, 3),
      ))
    } yield ()
  }


  "tacit" - types {
    for {
      _ <- Entity.s.i.Ref.i.insert(
        ("a", 2, 1),
        ("b", 3, 1),
        ("c", 4, 1),
      ).transact

      _ <- Entity.s.i_(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3),
      ))
      _ <- Entity.s.i_.not(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
        ("c", 3),
      ))
      _ <- Entity.s.i_.<(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
      ))
      _ <- Entity.s.i_.<=(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 3),
      ))
      _ <- Entity.s.i_.>(Ref.i(count)).query.get.map(_ ==> List(
        ("c", 3),
      ))
      _ <- Entity.s.i_.>=(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3),
        ("c", 3),
      ))
    } yield ()
  }


  "count, countDistinct" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 1),
        (3, 1),
      ).transact

      _ <- Entity.i(Ref.i(count)).query.i.get.map(_ ==> List(
        (3, 3),
      ))

      _ <- Ref.i(countDistinct).query.i.get.map(_ ==> List(1))
      _ <- Entity.i(Ref.i(countDistinct)).query.i.get.map(_ ==> List(
        (1, 1),
      ))
    } yield ()
  }


  "min max" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- Entity.i(Ref.i(min)).query.get.map(_ ==> List(
        (1, 1),
      ))

      _ <- Entity.i(Ref.i(max)).query.get.map(_ ==> List(
        (3, 3),
      ))
    } yield ()
  }


  "sum" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (2, 1),
        (4, 2),
        (6, 3),
      ).transact

      _ <- Entity.i(Ref.i(sum)).query.get.map(_ ==> List(
        (6, 6),
      ))
    } yield ()
  }


  "avg" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (1.0, 1),
        (2.0, 2),
        (3.0, 3),
      ).transact

      _ <- Entity.double(Ref.i(avg)).query.get.map(_ ==> List(
        (2, 2),
      ))
    } yield ()
  }


  "median" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (1.0, 1),
        (2.0, 2),
        (3.0, 3),
      ).transact

      _ <- Entity.double(Ref.i(median)).query.get.map(_ ==> List(
        (2, 2),
      ))
    } yield ()
  }


  "variance" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (0.5, 1),
        (0.7, 2),
        (0.9, 3),
      ).transact

      _ <- Ref.i(variance).query.get.map(_ ==> List(2.0/3))
      _ <- Entity.double.<(Ref.i(variance)).query.get.map(_ ==> List(
        (0.5, 2.0/3),
      ))
    } yield ()
  }


  "stddev" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (0.5, 1),
        (0.7, 2),
        (0.9, 3),
      ).transact

      _ <- Ref.i(stddev).query.get.map(_ ==> List(0.816496580927726))
      _ <- Entity.double.>(Ref.i(stddev)).query.get.map(_ ==> List(
        (0.9, 0.816496580927726),
      ))
    } yield ()
  }
}
