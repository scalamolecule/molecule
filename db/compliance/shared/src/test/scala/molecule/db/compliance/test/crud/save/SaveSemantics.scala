package molecule.db.compliance.test.crud.save

import scala.collection.immutable.{ArraySeq, BitSet, HashMap, HashSet, LinearSeq, ListMap, ListSet, NumericRange, Queue, SeqMap, SortedMap, SortedSet, TreeMap, TreeSet, VectorMap}
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.DbProviders

case class SaveSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Attribute required for each entity" - refs {
    for {
      _ <- A.B.i(1).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to B"
        }

      _ <- A.Bb.i(1).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to Bb"
        }
    } yield ()
  }


  "Duplicate attributes not allowed, flat - Same entity" - refs {
    for {
      _ <- A.i(1).i(2).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }

  "Duplicate attributes not allowed, flat - After backref" - refs {
    for {
      _ <- A.i(1).B.i(2)._A.i(3).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }

      _ <- A.i(1).B.i(2).C.i(3)._B.i(4).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }

      _ <- A.i(1).B.i(2).C.i(3)._B._A.i(4).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }


  "Nested data can only be inserted, not saved" - refs {
    for {
      _ <- A.i(0).Bb.*(B.i(1)).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

      _ <- A.i(0).Bb.*?(B.i(1)).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

      // ok
      _ <- A.i.Bb.*(B.i).insert((0, List(1))).transact
      _ <- A.i.Bb.*?(B.i).insert((0, List(1))).transact
    } yield ()
  }


  "Subtypes - Seq" - types {
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

  "Subtypes - Set" - types {
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

  "Subtypes - Map" - types {
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
