// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        NsSet.n.a1.bytes.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bytes(byte0).query.get ==> List()
        NsSet.n.a1.bytes(byte1).query.get ==> List(a)
        NsSet.n.a1.bytes(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes(byte3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes(Seq(byte0)).query.get ==> List()
        NsSet.n.a1.bytes(Seq(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(byte3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bytes(byte1, byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes(byte1, byte3).query.get ==> List(a, b)
        NsSet.n.a1.bytes(byte2, byte3).query.get ==> List(a, b)
        NsSet.n.a1.bytes(byte1, byte2, byte3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes(Seq(byte1, byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(byte1, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(byte1, byte2, byte3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bytes(Set(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes(Set(byte1, byte2)).query.get ==> List(a)
        NsSet.n.a1.bytes(Set(byte1, byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes(Set(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Set(byte2, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes(Set(byte2, byte3, byte4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes(Seq(Set(byte1))).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2))).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes(Seq(Set(byte2))).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(Set(byte2, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes(Seq(Set(byte2, byte3, byte4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bytes(Set(byte1, byte2), Set(byte0)).query.get ==> List(a)
        NsSet.n.a1.bytes(Set(byte1, byte2), Set(byte0, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2), Set(byte0))).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2), Set(byte0, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(a)
        NsSet.n.a1.bytes(Seq.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes(Set.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes(Seq.empty[Set[Byte]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bytes.not(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(byte1).query.get ==> List(b)
        NsSet.n.a1.bytes.not(byte2).query.get ==> List()
        NsSet.n.a1.bytes.not(byte3).query.get ==> List(a)
        NsSet.n.a1.bytes.not(byte4).query.get ==> List(a)
        NsSet.n.a1.bytes.not(byte5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes.not(Seq(byte0)).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Seq(byte1)).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq(byte2)).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes.not(Seq(byte4)).query.get ==> List(a)
        NsSet.n.a1.bytes.not(Seq(byte5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bytes.not(byte1, byte2).query.get ==> List()
        NsSet.n.a1.bytes.not(byte1, byte3).query.get ==> List()
        NsSet.n.a1.bytes.not(byte1, byte4).query.get ==> List()
        NsSet.n.a1.bytes.not(byte1, byte5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes.not(Seq(byte1, byte2)).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(byte1, byte3)).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(byte1, byte4)).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(byte1, byte5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bytes.not(Set(byte1)).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Set(byte1, byte2)).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Set(byte1, byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Set(byte2)).query.get ==> List()
        NsSet.n.a1.bytes.not(Set(byte2, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes.not(Set(byte2, byte3, byte4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bytes.not(Seq(Set(byte1))).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2))).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Seq(Set(byte2))).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(Set(byte2, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes.not(Seq(Set(byte2, byte3, byte4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bytes.not(Set(byte1, byte2), Set(byte0)).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Set(byte1, byte2), Set(byte0, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes.not(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2), Set(byte0))).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2), Set(byte0, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes.not(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bytes.not(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(b)
        NsSet.n.a1.bytes.not(Seq.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Set.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Seq.empty[Set[Byte]]).query.get ==> List(a, b)
        NsSet.n.a1.bytes.not(Seq(Set.empty[Byte])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bytes.==(Set(byte1)).query.get ==> List()
        NsSet.n.a1.bytes.==(Set(byte1, byte2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.bytes.==(Set(byte1, byte2, byte3)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes.==(Seq(Set(byte1))).query.get ==> List()
        NsSet.n.a1.bytes.==(Seq(Set(byte1, byte2))).query.get ==> List(a)
        NsSet.n.a1.bytes.==(Seq(Set(byte1, byte2, byte3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes.==(Set(byte1), Set(byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes.==(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes.==(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes.==(Seq(Set(byte1), Set(byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes.==(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes.==(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes.==(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(a)
        NsSet.n.a1.bytes.==(Set.empty[Byte], Set(byte1, byte2)).query.get ==> List(a)
        NsSet.n.a1.bytes.==(Set.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes.==(Seq.empty[Set[Byte]]).query.get ==> List()
        NsSet.n.a1.bytes.==(Seq(Set.empty[Byte])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bytes.!=(Set(byte1)).query.get ==> List(a, b)
        NsSet.n.a1.bytes.!=(Set(byte1, byte2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bytes.!=(Set(byte1, byte2, byte3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes.!=(Seq(Set(byte1))).query.get ==> List(a, b)
        NsSet.n.a1.bytes.!=(Seq(Set(byte1, byte2))).query.get ==> List(b)
        NsSet.n.a1.bytes.!=(Seq(Set(byte1, byte2, byte3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes.!=(Set(byte1), Set(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes.!=(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes.!=(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes.!=(Seq(Set(byte1), Set(byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes.!=(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes.!=(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bytes.!=(Seq(Set(byte1, byte2), Set.empty[Byte])).query.get ==> List(b)
        NsSet.n.a1.bytes.!=(Set.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes.!=(Seq.empty[Set[Byte]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        NsSet.n.bytes.insert(List(a, b)).transact

        NsSet.n.a1.bytes.<(byte0).query.get ==> List()
        NsSet.n.a1.bytes.<(byte1).query.get ==> List()
        NsSet.n.a1.bytes.<(byte2).query.get ==> List(a)
        NsSet.n.a1.bytes.<(byte3).query.get ==> List(a, b)

        NsSet.n.a1.bytes.<=(byte0).query.get ==> List()
        NsSet.n.a1.bytes.<=(byte1).query.get ==> List(a)
        NsSet.n.a1.bytes.<=(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes.<=(byte3).query.get ==> List(a, b)

        NsSet.n.a1.bytes.>(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes.>(byte1).query.get ==> List(a, b)
        NsSet.n.a1.bytes.>(byte2).query.get ==> List(b)
        NsSet.n.a1.bytes.>(byte3).query.get ==> List(b)

        NsSet.n.a1.bytes.>=(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes.>=(byte1).query.get ==> List(a, b)
        NsSet.n.a1.bytes.>=(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes.>=(byte3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        NsSet.n.a1.bytes_.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bytes_(byte0).query.get ==> List()
        NsSet.n.a1.bytes_(byte1).query.get ==> List(a)
        NsSet.n.a1.bytes_(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(byte3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes_(Seq(byte0)).query.get ==> List()
        NsSet.n.a1.bytes_(Seq(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(byte3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bytes_(byte1, byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(byte1, byte3).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(byte2, byte3).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(byte1, byte2, byte3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_(Seq(byte1, byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(byte1, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(byte1, byte2, byte3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bytes_(Set(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes_(Set(byte1, byte2)).query.get ==> List(a)
        NsSet.n.a1.bytes_(Set(byte1, byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes_(Set(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Set(byte2, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes_(Set(byte2, byte3, byte4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes_(Seq(Set(byte1))).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2))).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes_(Seq(Set(byte2))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(Set(byte2, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes_(Seq(Set(byte2, byte3, byte4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bytes_(Set(byte1, byte2), Set(byte0)).query.get ==> List(a)
        NsSet.n.a1.bytes_(Set(byte1, byte2), Set(byte0, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes_(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2), Set(byte0))).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2), Set(byte0, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes_(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(a)
        NsSet.n.a1.bytes_(Seq.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes_(Set.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes_(Seq.empty[Set[Byte]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bytes_.not(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(byte1).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(byte2).query.get ==> List()
        NsSet.n.a1.bytes_.not(byte3).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(byte4).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(byte5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_.not(Seq(byte0)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Seq(byte1)).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq(byte2)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(Seq(byte4)).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(Seq(byte5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bytes_.not(byte1, byte2).query.get ==> List()
        NsSet.n.a1.bytes_.not(byte1, byte3).query.get ==> List()
        NsSet.n.a1.bytes_.not(byte1, byte4).query.get ==> List()
        NsSet.n.a1.bytes_.not(byte1, byte5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes_.not(Seq(byte1, byte2)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(byte1, byte3)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(byte1, byte4)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(byte1, byte5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bytes_.not(Set(byte1)).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Set(byte1, byte2)).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Set(byte1, byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Set(byte2)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Set(byte2, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(Set(byte2, byte3, byte4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bytes_.not(Seq(Set(byte1))).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2))).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Seq(Set(byte2))).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(Set(byte2, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes_.not(Seq(Set(byte2, byte3, byte4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bytes_.not(Set(byte1, byte2), Set(byte0)).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Set(byte1, byte2), Set(byte0, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes_.not(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2), Set(byte0))).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2), Set(byte0, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes_.not(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bytes_.not(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(b)
        NsSet.n.a1.bytes_.not(Seq.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Set.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Seq.empty[Set[Byte]]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.not(Seq(Set.empty[Byte])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bytes_.==(Set(byte1)).query.get ==> List()
        NsSet.n.a1.bytes_.==(Set(byte1, byte2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.bytes_.==(Set(byte1, byte2, byte3)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes_.==(Seq(Set(byte1))).query.get ==> List()
        NsSet.n.a1.bytes_.==(Seq(Set(byte1, byte2))).query.get ==> List(a)
        NsSet.n.a1.bytes_.==(Seq(Set(byte1, byte2, byte3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes_.==(Set(byte1), Set(byte2, byte3)).query.get ==> List()
        NsSet.n.a1.bytes_.==(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes_.==(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_.==(Seq(Set(byte1), Set(byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes_.==(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes_.==(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes_.==(Set(byte1, byte2), Set.empty[Byte]).query.get ==> List(a)
        NsSet.n.a1.bytes_.==(Set.empty[Byte]).query.get ==> List()
        NsSet.n.a1.bytes_.==(Seq.empty[Set[Byte]]).query.get ==> List()
        NsSet.n.a1.bytes_.==(Seq(Set.empty[Byte])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bytes_.!=(Set(byte1)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.!=(Set(byte1, byte2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bytes_.!=(Set(byte1, byte2, byte3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1, byte2))).query.get ==> List(b)
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1, byte2, byte3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes_.!=(Set(byte1), Set(byte2, byte3)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.!=(Set(byte1, byte2), Set(byte2, byte3)).query.get ==> List(b)
        NsSet.n.a1.bytes_.!=(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1), Set(byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bytes_.!=(Seq(Set(byte1, byte2), Set.empty[Byte])).query.get ==> List(b)
        NsSet.n.a1.bytes_.!=(Set.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.!=(Seq.empty[Set[Byte]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        NsSet.n.a1.bytes_.<(byte0).query.get ==> List()
        NsSet.n.a1.bytes_.<(byte1).query.get ==> List()
        NsSet.n.a1.bytes_.<(byte2).query.get ==> List(a)
        NsSet.n.a1.bytes_.<(byte3).query.get ==> List(a, b)

        NsSet.n.a1.bytes_.<=(byte0).query.get ==> List()
        NsSet.n.a1.bytes_.<=(byte1).query.get ==> List(a)
        NsSet.n.a1.bytes_.<=(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.<=(byte3).query.get ==> List(a, b)

        NsSet.n.a1.bytes_.>(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.>(byte1).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.>(byte2).query.get ==> List(b)
        NsSet.n.a1.bytes_.>(byte3).query.get ==> List(b)

        NsSet.n.a1.bytes_.>=(byte0).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.>=(byte1).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.>=(byte2).query.get ==> List(a, b)
        NsSet.n.a1.bytes_.>=(byte3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        NsSet.n.a1.bytes_?.query.get ==> List(a, b, c)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bytes_?(Some(byte0)).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(byte3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes_?(Some(Seq(byte0))).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(Seq(byte1))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Seq(byte2))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(byte3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bytes_?(Some(Seq(byte1, byte2))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(byte1, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(byte1, byte2, byte3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bytes_?(Some(Set(byte1))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Set(byte1, byte2))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Set(byte1, byte2, byte3))).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(Set(byte2))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Set(byte2, byte3))).query.get ==> List(b)
        NsSet.n.a1.bytes_?(Some(Set(byte2, byte3, byte4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2, byte3)))).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte2)))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte2, byte3)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte2, byte3, byte4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2), Set(byte0)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2), Set(byte0, byte3)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes_?(Some(Seq.empty[Byte])).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(Set.empty[Byte])).query.get ==> List()
        NsSet.n.a1.bytes_?(Some(Seq.empty[Set[Byte]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.bytes_?(Option.empty[Byte]).query.get ==> List(c)
        NsSet.n.a1.bytes_?(Option.empty[Seq[Byte]]).query.get ==> List(c)
        NsSet.n.a1.bytes_?(Option.empty[Set[Byte]]).query.get ==> List(c)
        NsSet.n.a1.bytes_?(Option.empty[Seq[Set[Byte]]]).query.get ==> List(c)
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bytes_?.not(Some(byte0)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(byte1)).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(byte2)).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(byte3)).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(byte4)).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(byte5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_?.not(Some(Seq(byte0))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Seq(byte1))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Seq(byte2))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(Seq(byte4))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(Seq(byte5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bytes_?.not(Some(Seq(byte1, byte2))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(byte1, byte3))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(byte1, byte4))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(byte1, byte5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bytes_?.not(Some(Set(byte1))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Set(byte1, byte2))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Set(byte1, byte2, byte3))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Set(byte2))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Set(byte2, byte3))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(Set(byte2, byte3, byte4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2, byte3)))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte2)))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte2, byte3)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte2, byte3, byte4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2), Set(byte0)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2), Set(byte0, byte3)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get ==> List()
        NsSet.n.a1.bytes_?.not(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bytes_?.not(Some(Seq.empty[Byte])).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Set.empty[Byte])).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Seq.empty[Set[Byte]])).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Some(Seq(Set.empty[Byte]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.bytes_?.not(Option.empty[Byte]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Option.empty[Seq[Byte]]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Option.empty[Set[Byte]]).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.not(Option.empty[Seq[Set[Byte]]]).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bytes_?.==(Some(Set(byte1))).query.get ==> List()
        NsSet.n.a1.bytes_?.==(Some(Set(byte1, byte2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.bytes_?.==(Some(Set(byte1, byte2, byte3))).query.get ==> List()
        // Same as
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1)))).query.get ==> List()
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1, byte2)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1, byte2, byte3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1), Set(byte2, byte3)))).query.get ==> List()
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get ==> List(a)
        NsSet.n.a1.bytes_?.==(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bytes_?.==(Some(Set.empty[Byte])).query.get ==> List()
        NsSet.n.a1.bytes_?.==(Some(Seq.empty[Set[Byte]])).query.get ==> List()
        NsSet.n.a1.bytes_?.==(Some(Seq(Set.empty[Byte]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.bytes_?.==(Option.empty[Set[Byte]]).query.get ==> List(c)
        NsSet.n.a1.bytes_?.==(Option.empty[Seq[Set[Byte]]]).query.get ==> List(c)
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bytes_?.!=(Some(Set(byte1))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.!=(Some(Set(byte1, byte2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bytes_?.!=(Some(Set(byte1, byte2, byte3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1)))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1, byte2)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1, byte2, byte3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1), Set(byte2, byte3)))).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bytes_?.!=(Some(Seq(Set(byte1, byte2), Set.empty[Byte]))).query.get ==> List(b)
        NsSet.n.a1.bytes_?.!=(Some(Set.empty[Byte])).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.!=(Some(Seq.empty[Set[Byte]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.bytes_?.==(Option.empty[Set[Byte]]).query.get ==> List(c)
        NsSet.n.a1.bytes_?.==(Option.empty[Seq[Set[Byte]]]).query.get ==> List(c)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        NsSet.n.bytes_?.insert(a, b, c).transact

        NsSet.n.a1.bytes_?.<(Some(byte0)).query.get ==> List()
        NsSet.n.a1.bytes_?.<(Some(byte1)).query.get ==> List()
        NsSet.n.a1.bytes_?.<(Some(byte2)).query.get ==> List(a)
        NsSet.n.a1.bytes_?.<(Some(byte3)).query.get ==> List(a, b)

        NsSet.n.a1.bytes_?.<=(Some(byte0)).query.get ==> List()
        NsSet.n.a1.bytes_?.<=(Some(byte1)).query.get ==> List(a)
        NsSet.n.a1.bytes_?.<=(Some(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.<=(Some(byte3)).query.get ==> List(a, b)

        NsSet.n.a1.bytes_?.>(Some(byte0)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>(Some(byte1)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>(Some(byte2)).query.get ==> List(b)
        NsSet.n.a1.bytes_?.>(Some(byte3)).query.get ==> List(b)

        NsSet.n.a1.bytes_?.>=(Some(byte0)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>=(Some(byte1)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>=(Some(byte2)).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>=(Some(byte3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.bytes_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.bytes_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}