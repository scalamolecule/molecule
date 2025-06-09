package molecule.db.compliance.test.action.update

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import scala.collection.immutable.*

case class Basics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Update entity with id" - types { implicit conn =>
    for {
      id <- Entity.i(1).save.transact.map(_.id)
      _ <- Entity.i.query.get.map(_ ==> List(1))

      _ <- Entity(id).i(2).update.transact
      _ <- Entity.i.query.get.map(_ ==> List(2))
    } yield ()
  }


  "Can't update optional values" - types { implicit conn =>
    for {
      _ <- Entity(42).int_?(Some(1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.int_?)"
        }

      _ <- Entity(42).intSet_?(Some(Set(1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intSet_?)"
        }

      _ <- Entity(42).intSeq_?(Some(Seq(1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intSeq_?)"
        }

      _ <- Entity(42).intMap_?(Some(Map("a" -> 1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intMap_?)"
        }
    } yield ()
  }


  "Subtypes - Seq" - types { implicit conn =>
    for {
      _ <- Entity.s("Seq").intSeq(Seq(1, 2, 3)).save.transact // default LinearSeq -> List
      _ <- Entity.s("IndexedSeq").intSeq(IndexedSeq(1, 2, 3)).save.transact // default Vector
      _ <- Entity.s("Vector").intSeq(Vector(1, 2, 3)).save.transact
      _ <- Entity.s("ArraySeq").intSeq(ArraySeq(1, 2, 3)).save.transact
      _ <- Entity.s("NumericRange").intSeq(NumericRange(1, 4, 1)).save.transact // start, end, step
      _ <- Entity.s("Range").intSeq(1 to 3).save.transact
      _ <- Entity.s("LinearSeq").intSeq(LinearSeq(1, 2, 3)).save.transact // default List
      _ <- Entity.s("List").intSeq(List(1, 2, 3)).save.transact
      _ <- Entity.s("LazyList").intSeq(LazyList(1, 2, 3)).save.transact
      _ <- Entity.s("Queue").intSeq(Queue(1, 2, 3)).save.transact

      // Default Seq implementation List always returned
      _ <- Entity.s_("Seq").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("IndexedSeq").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("Vector").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("ArraySeq").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("NumericRange").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("Range").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("LinearSeq").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("List").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("LazyList").intSeq.query.get.map(_.head ==> List(1, 2, 3))
      _ <- Entity.s_("Queue").intSeq.query.get.map(_.head ==> List(1, 2, 3))

      // Or we can use the super type Seq
      _ <- Entity.s_("Seq").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("IndexedSeq").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("Vector").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("ArraySeq").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("NumericRange").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("Range").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("LinearSeq").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("List").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("LazyList").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
      _ <- Entity.s_("Queue").intSeq.query.get.map(_.head ==> Seq(1, 2, 3))
    } yield ()
  }

  "Subtypes - Set" - types { implicit conn =>
    for {
      _ <- Entity.s("Set").intSet(Set(1, 2, 3)).save.transact // default HashSet
      _ <- Entity.s("HashSet").intSet(HashSet(1, 2, 3)).save.transact
      _ <- Entity.s("ListSet").intSet(ListSet(1, 2, 3)).save.transact
      _ <- Entity.s("SortedSet").intSet(SortedSet(1, 2, 3)).save.transact
      _ <- Entity.s("TreeSet").intSet(TreeSet(1, 2, 3)).save.transact
      _ <- Entity.s("BitSet").intSet(BitSet(1, 2, 3)).save.transact

      // Default Seq implementation List always returned
      _ <- Entity.s_("Set").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))
      _ <- Entity.s_("HashSet").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))
      _ <- Entity.s_("ListSet").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))
      _ <- Entity.s_("SortedSet").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))
      _ <- Entity.s_("TreeSet").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))
      _ <- Entity.s_("BitSet").intSet.query.get.map(_.head ==> HashSet(1, 2, 3))

      // Or we can use the super type Set
      _ <- Entity.s_("Set").intSet.query.get.map(_.head ==> Set(1, 2, 3))
      _ <- Entity.s_("HashSet").intSet.query.get.map(_.head ==> Set(1, 2, 3))
      _ <- Entity.s_("ListSet").intSet.query.get.map(_.head ==> Set(1, 2, 3))
      _ <- Entity.s_("SortedSet").intSet.query.get.map(_.head ==> Set(1, 2, 3))
      _ <- Entity.s_("TreeSet").intSet.query.get.map(_.head ==> Set(1, 2, 3))
      _ <- Entity.s_("BitSet").intSet.query.get.map(_.head ==> Set(1, 2, 3))
    } yield ()
  }

  "Subtypes - Map" - types { implicit conn =>
    for {
      _ <- Entity.s("Map").intMap(Map("a" -> 1, "b" -> 2, "c" -> 3)).save.transact // default SeqMap -> HashMap
      _ <- Entity.s("HashMap").intMap(HashMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact
      _ <- Entity.s("SortedMap").intMap(SortedMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact
      _ <- Entity.s("TreeMap").intMap(TreeMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact
      _ <- Entity.s("SeqMap").intMap(SeqMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact
      _ <- Entity.s("ListMap").intMap(ListMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact
      _ <- Entity.s("VectorMap").intMap(VectorMap("a" -> 1, "b" -> 2, "c" -> 3)).save.transact

      // Default Map implementation HashMap always returned
      _ <- Entity.s_("Map").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("HashMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("SortedMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("TreeMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("SeqMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("ListMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("VectorMap").intMap.query.get.map(_.head ==> HashMap("a" -> 1, "b" -> 2, "c" -> 3))

      // Or we can use the super type Map
      _ <- Entity.s_("Map").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("HashMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("SortedMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("TreeMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("SeqMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("ListMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
      _ <- Entity.s_("VectorMap").intMap.query.get.map(_.head ==> Map("a" -> 1, "b" -> 2, "c" -> 3))
    } yield ()
  }
}