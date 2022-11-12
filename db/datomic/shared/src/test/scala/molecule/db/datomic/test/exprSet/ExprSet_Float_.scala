// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        NsSet.n.a1.floats.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.floats(float0).query.get ==> List()
        NsSet.n.a1.floats(float1).query.get ==> List(a)
        NsSet.n.a1.floats(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats(float3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats(Seq(float0)).query.get ==> List()
        NsSet.n.a1.floats(Seq(float1)).query.get ==> List(a)
        NsSet.n.a1.floats(Seq(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(float3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.floats(float1, float2).query.get ==> List(a, b)
        NsSet.n.a1.floats(float1, float3).query.get ==> List(a, b)
        NsSet.n.a1.floats(float2, float3).query.get ==> List(a, b)
        NsSet.n.a1.floats(float1, float2, float3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats(Seq(float1, float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(float1, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(float1, float2, float3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.floats(Set(float1)).query.get ==> List(a)
        NsSet.n.a1.floats(Set(float1, float2)).query.get ==> List(a)
        NsSet.n.a1.floats(Set(float1, float2, float3)).query.get ==> List()
        NsSet.n.a1.floats(Set(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Set(float2, float3)).query.get ==> List(b)
        NsSet.n.a1.floats(Set(float2, float3, float4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats(Seq(Set(float1))).query.get ==> List(a)
        NsSet.n.a1.floats(Seq(Set(float1, float2))).query.get ==> List(a)
        NsSet.n.a1.floats(Seq(Set(float1, float2, float3))).query.get ==> List()
        NsSet.n.a1.floats(Seq(Set(float2))).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(Set(float2, float3))).query.get ==> List(b)
        NsSet.n.a1.floats(Seq(Set(float2, float3, float4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.floats(Set(float1, float2), Set(float0)).query.get ==> List(a)
        NsSet.n.a1.floats(Set(float1, float2), Set(float0, float3)).query.get ==> List(a)
        NsSet.n.a1.floats(Set(float1, float2), Set(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats(Seq(Set(float1, float2), Set(float0))).query.get ==> List(a)
        NsSet.n.a1.floats(Seq(Set(float1, float2), Set(float0, float3))).query.get ==> List(a)
        NsSet.n.a1.floats(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats(Set(float1, float2), Set.empty[Float]).query.get ==> List(a)
        NsSet.n.a1.floats(Seq.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats(Set.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats(Seq.empty[Set[Float]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.floats.not(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(float1).query.get ==> List(b)
        NsSet.n.a1.floats.not(float2).query.get ==> List()
        NsSet.n.a1.floats.not(float3).query.get ==> List(a)
        NsSet.n.a1.floats.not(float4).query.get ==> List(a)
        NsSet.n.a1.floats.not(float5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats.not(Seq(float0)).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Seq(float1)).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq(float2)).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(float3)).query.get ==> List(a)
        NsSet.n.a1.floats.not(Seq(float4)).query.get ==> List(a)
        NsSet.n.a1.floats.not(Seq(float5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.floats.not(float1, float2).query.get ==> List()
        NsSet.n.a1.floats.not(float1, float3).query.get ==> List()
        NsSet.n.a1.floats.not(float1, float4).query.get ==> List()
        NsSet.n.a1.floats.not(float1, float5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats.not(Seq(float1, float2)).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(float1, float3)).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(float1, float4)).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(float1, float5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.floats.not(Set(float1)).query.get ==> List(b)
        NsSet.n.a1.floats.not(Set(float1, float2)).query.get ==> List(b)
        NsSet.n.a1.floats.not(Set(float1, float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Set(float2)).query.get ==> List()
        NsSet.n.a1.floats.not(Set(float2, float3)).query.get ==> List(a)
        NsSet.n.a1.floats.not(Set(float2, float3, float4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.floats.not(Seq(Set(float1))).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq(Set(float1, float2))).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq(Set(float1, float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Seq(Set(float2))).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(Set(float2, float3))).query.get ==> List(a)
        NsSet.n.a1.floats.not(Seq(Set(float2, float3, float4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.floats.not(Set(float1, float2), Set(float0)).query.get ==> List(b)
        NsSet.n.a1.floats.not(Set(float1, float2), Set(float0, float3)).query.get ==> List(b)
        NsSet.n.a1.floats.not(Set(float1, float2), Set(float2, float3)).query.get ==> List()
        NsSet.n.a1.floats.not(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats.not(Seq(Set(float1, float2), Set(float0))).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq(Set(float1, float2), Set(float0, float3))).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List()
        NsSet.n.a1.floats.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.floats.not(Set(float1, float2), Set.empty[Float]).query.get ==> List(b)
        NsSet.n.a1.floats.not(Seq.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Set.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Seq.empty[Set[Float]]).query.get ==> List(a, b)
        NsSet.n.a1.floats.not(Seq(Set.empty[Float])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.floats.==(Set(float1)).query.get ==> List()
        NsSet.n.a1.floats.==(Set(float1, float2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.floats.==(Set(float1, float2, float3)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats.==(Seq(Set(float1))).query.get ==> List()
        NsSet.n.a1.floats.==(Seq(Set(float1, float2))).query.get ==> List(a)
        NsSet.n.a1.floats.==(Seq(Set(float1, float2, float3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats.==(Set(float1), Set(float2, float3)).query.get ==> List()
        NsSet.n.a1.floats.==(Set(float1, float2), Set(float2, float3)).query.get ==> List(a)
        NsSet.n.a1.floats.==(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats.==(Seq(Set(float1), Set(float2, float3))).query.get ==> List()
        NsSet.n.a1.floats.==(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(a)
        NsSet.n.a1.floats.==(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats.==(Set(float1, float2), Set.empty[Float]).query.get ==> List(a)
        NsSet.n.a1.floats.==(Set.empty[Float], Set(float1, float2)).query.get ==> List(a)
        NsSet.n.a1.floats.==(Set.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats.==(Seq.empty[Set[Float]]).query.get ==> List()
        NsSet.n.a1.floats.==(Seq(Set.empty[Float])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.floats.!=(Set(float1)).query.get ==> List(a, b)
        NsSet.n.a1.floats.!=(Set(float1, float2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.floats.!=(Set(float1, float2, float3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats.!=(Seq(Set(float1))).query.get ==> List(a, b)
        NsSet.n.a1.floats.!=(Seq(Set(float1, float2))).query.get ==> List(b)
        NsSet.n.a1.floats.!=(Seq(Set(float1, float2, float3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats.!=(Set(float1), Set(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats.!=(Set(float1, float2), Set(float2, float3)).query.get ==> List(b)
        NsSet.n.a1.floats.!=(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats.!=(Seq(Set(float1), Set(float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats.!=(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(b)
        NsSet.n.a1.floats.!=(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.floats.!=(Seq(Set(float1, float2), Set.empty[Float])).query.get ==> List(b)
        NsSet.n.a1.floats.!=(Set.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats.!=(Seq.empty[Set[Float]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Set(float1, float2))
        val b = (2, Set(float2, float3, float4))
        NsSet.n.floats.insert(List(a, b)).transact

        NsSet.n.a1.floats.<(float0).query.get ==> List()
        NsSet.n.a1.floats.<(float1).query.get ==> List()
        NsSet.n.a1.floats.<(float2).query.get ==> List(a)
        NsSet.n.a1.floats.<(float3).query.get ==> List(a, b)

        NsSet.n.a1.floats.<=(float0).query.get ==> List()
        NsSet.n.a1.floats.<=(float1).query.get ==> List(a)
        NsSet.n.a1.floats.<=(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats.<=(float3).query.get ==> List(a, b)

        NsSet.n.a1.floats.>(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats.>(float1).query.get ==> List(a, b)
        NsSet.n.a1.floats.>(float2).query.get ==> List(b)
        NsSet.n.a1.floats.>(float3).query.get ==> List(b)

        NsSet.n.a1.floats.>=(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats.>=(float1).query.get ==> List(a, b)
        NsSet.n.a1.floats.>=(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats.>=(float3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        NsSet.n.a1.floats_.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.floats_(float0).query.get ==> List()
        NsSet.n.a1.floats_(float1).query.get ==> List(a)
        NsSet.n.a1.floats_(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats_(float3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats_(Seq(float0)).query.get ==> List()
        NsSet.n.a1.floats_(Seq(float1)).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(float3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.floats_(float1, float2).query.get ==> List(a, b)
        NsSet.n.a1.floats_(float1, float3).query.get ==> List(a, b)
        NsSet.n.a1.floats_(float2, float3).query.get ==> List(a, b)
        NsSet.n.a1.floats_(float1, float2, float3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_(Seq(float1, float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(float1, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(float1, float2, float3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.floats_(Set(float1)).query.get ==> List(a)
        NsSet.n.a1.floats_(Set(float1, float2)).query.get ==> List(a)
        NsSet.n.a1.floats_(Set(float1, float2, float3)).query.get ==> List()
        NsSet.n.a1.floats_(Set(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Set(float2, float3)).query.get ==> List(b)
        NsSet.n.a1.floats_(Set(float2, float3, float4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats_(Seq(Set(float1))).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq(Set(float1, float2))).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq(Set(float1, float2, float3))).query.get ==> List()
        NsSet.n.a1.floats_(Seq(Set(float2))).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(Set(float2, float3))).query.get ==> List(b)
        NsSet.n.a1.floats_(Seq(Set(float2, float3, float4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.floats_(Set(float1, float2), Set(float0)).query.get ==> List(a)
        NsSet.n.a1.floats_(Set(float1, float2), Set(float0, float3)).query.get ==> List(a)
        NsSet.n.a1.floats_(Set(float1, float2), Set(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_(Seq(Set(float1, float2), Set(float0))).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq(Set(float1, float2), Set(float0, float3))).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats_(Set(float1, float2), Set.empty[Float]).query.get ==> List(a)
        NsSet.n.a1.floats_(Seq.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats_(Set.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats_(Seq.empty[Set[Float]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.floats_.not(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(float1).query.get ==> List(b)
        NsSet.n.a1.floats_.not(float2).query.get ==> List()
        NsSet.n.a1.floats_.not(float3).query.get ==> List(a)
        NsSet.n.a1.floats_.not(float4).query.get ==> List(a)
        NsSet.n.a1.floats_.not(float5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_.not(Seq(float0)).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Seq(float1)).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq(float2)).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(float3)).query.get ==> List(a)
        NsSet.n.a1.floats_.not(Seq(float4)).query.get ==> List(a)
        NsSet.n.a1.floats_.not(Seq(float5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.floats_.not(float1, float2).query.get ==> List()
        NsSet.n.a1.floats_.not(float1, float3).query.get ==> List()
        NsSet.n.a1.floats_.not(float1, float4).query.get ==> List()
        NsSet.n.a1.floats_.not(float1, float5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats_.not(Seq(float1, float2)).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(float1, float3)).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(float1, float4)).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(float1, float5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.floats_.not(Set(float1)).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Set(float1, float2)).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Set(float1, float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Set(float2)).query.get ==> List()
        NsSet.n.a1.floats_.not(Set(float2, float3)).query.get ==> List(a)
        NsSet.n.a1.floats_.not(Set(float2, float3, float4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.floats_.not(Seq(Set(float1))).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2))).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Seq(Set(float2))).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(Set(float2, float3))).query.get ==> List(a)
        NsSet.n.a1.floats_.not(Seq(Set(float2, float3, float4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.floats_.not(Set(float1, float2), Set(float0)).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Set(float1, float2), Set(float0, float3)).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Set(float1, float2), Set(float2, float3)).query.get ==> List()
        NsSet.n.a1.floats_.not(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2), Set(float0))).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2), Set(float0, float3))).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List()
        NsSet.n.a1.floats_.not(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.floats_.not(Set(float1, float2), Set.empty[Float]).query.get ==> List(b)
        NsSet.n.a1.floats_.not(Seq.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Set.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Seq.empty[Set[Float]]).query.get ==> List(a, b)
        NsSet.n.a1.floats_.not(Seq(Set.empty[Float])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.floats_.==(Set(float1)).query.get ==> List()
        NsSet.n.a1.floats_.==(Set(float1, float2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.floats_.==(Set(float1, float2, float3)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats_.==(Seq(Set(float1))).query.get ==> List()
        NsSet.n.a1.floats_.==(Seq(Set(float1, float2))).query.get ==> List(a)
        NsSet.n.a1.floats_.==(Seq(Set(float1, float2, float3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats_.==(Set(float1), Set(float2, float3)).query.get ==> List()
        NsSet.n.a1.floats_.==(Set(float1, float2), Set(float2, float3)).query.get ==> List(a)
        NsSet.n.a1.floats_.==(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_.==(Seq(Set(float1), Set(float2, float3))).query.get ==> List()
        NsSet.n.a1.floats_.==(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(a)
        NsSet.n.a1.floats_.==(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats_.==(Set(float1, float2), Set.empty[Float]).query.get ==> List(a)
        NsSet.n.a1.floats_.==(Set.empty[Float]).query.get ==> List()
        NsSet.n.a1.floats_.==(Seq.empty[Set[Float]]).query.get ==> List()
        NsSet.n.a1.floats_.==(Seq(Set.empty[Float])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.floats_.!=(Set(float1)).query.get ==> List(a, b)
        NsSet.n.a1.floats_.!=(Set(float1, float2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.floats_.!=(Set(float1, float2, float3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_.!=(Seq(Set(float1))).query.get ==> List(a, b)
        NsSet.n.a1.floats_.!=(Seq(Set(float1, float2))).query.get ==> List(b)
        NsSet.n.a1.floats_.!=(Seq(Set(float1, float2, float3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats_.!=(Set(float1), Set(float2, float3)).query.get ==> List(a, b)
        NsSet.n.a1.floats_.!=(Set(float1, float2), Set(float2, float3)).query.get ==> List(b)
        NsSet.n.a1.floats_.!=(Set(float1, float2), Set(float2, float3, float4)).query.get ==> List()
        // Same as
        NsSet.n.a1.floats_.!=(Seq(Set(float1), Set(float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_.!=(Seq(Set(float1, float2), Set(float2, float3))).query.get ==> List(b)
        NsSet.n.a1.floats_.!=(Seq(Set(float1, float2), Set(float2, float3, float4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.floats_.!=(Seq(Set(float1, float2), Set.empty[Float])).query.get ==> List(b)
        NsSet.n.a1.floats_.!=(Set.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats_.!=(Seq.empty[Set[Float]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        NsSet.n.a1.floats_.<(float0).query.get ==> List()
        NsSet.n.a1.floats_.<(float1).query.get ==> List()
        NsSet.n.a1.floats_.<(float2).query.get ==> List(a)
        NsSet.n.a1.floats_.<(float3).query.get ==> List(a, b)

        NsSet.n.a1.floats_.<=(float0).query.get ==> List()
        NsSet.n.a1.floats_.<=(float1).query.get ==> List(a)
        NsSet.n.a1.floats_.<=(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats_.<=(float3).query.get ==> List(a, b)

        NsSet.n.a1.floats_.>(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats_.>(float1).query.get ==> List(a, b)
        NsSet.n.a1.floats_.>(float2).query.get ==> List(b)
        NsSet.n.a1.floats_.>(float3).query.get ==> List(b)

        NsSet.n.a1.floats_.>=(float0).query.get ==> List(a, b)
        NsSet.n.a1.floats_.>=(float1).query.get ==> List(a, b)
        NsSet.n.a1.floats_.>=(float2).query.get ==> List(a, b)
        NsSet.n.a1.floats_.>=(float3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        NsSet.n.a1.floats_?.query.get ==> List(a, b, c)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.floats_?(Some(float0)).query.get ==> List()
        NsSet.n.a1.floats_?(Some(float1)).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(float3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats_?(Some(Seq(float0))).query.get ==> List()
        NsSet.n.a1.floats_?(Some(Seq(float1))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Seq(float2))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(float3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.floats_?(Some(Seq(float1, float2))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(float1, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(float1, float2, float3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.floats_?(Some(Set(float1))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Set(float1, float2))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Set(float1, float2, float3))).query.get ==> List()
        NsSet.n.a1.floats_?(Some(Set(float2))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Set(float2, float3))).query.get ==> List(b)
        NsSet.n.a1.floats_?(Some(Set(float2, float3, float4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.floats_?(Some(Seq(Set(float1)))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2)))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2, float3)))).query.get ==> List()
        NsSet.n.a1.floats_?(Some(Seq(Set(float2)))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(Set(float2, float3)))).query.get ==> List(b)
        NsSet.n.a1.floats_?(Some(Seq(Set(float2, float3, float4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2), Set(float0)))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2), Set(float0, float3)))).query.get ==> List(a)
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats_?(Some(Seq.empty[Float])).query.get ==> List()
        NsSet.n.a1.floats_?(Some(Set.empty[Float])).query.get ==> List()
        NsSet.n.a1.floats_?(Some(Seq.empty[Set[Float]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.floats_?(Option.empty[Float]).query.get ==> List(c)
        NsSet.n.a1.floats_?(Option.empty[Seq[Float]]).query.get ==> List(c)
        NsSet.n.a1.floats_?(Option.empty[Set[Float]]).query.get ==> List(c)
        NsSet.n.a1.floats_?(Option.empty[Seq[Set[Float]]]).query.get ==> List(c)
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.floats_?.not(Some(float0)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(float1)).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(float2)).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(float3)).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(float4)).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(float5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_?.not(Some(Seq(float0))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Seq(float1))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Seq(float2))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(float3))).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(Seq(float4))).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(Seq(float5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.floats_?.not(Some(Seq(float1, float2))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(float1, float3))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(float1, float4))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(float1, float5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.floats_?.not(Some(Set(float1))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Set(float1, float2))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Set(float1, float2, float3))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Set(float2))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Set(float2, float3))).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(Set(float2, float3, float4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2, float3)))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float2)))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float2, float3)))).query.get ==> List(a)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float2, float3, float4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float0)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float0, float3)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get ==> List()
        NsSet.n.a1.floats_?.not(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.floats_?.not(Some(Seq.empty[Float])).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Set.empty[Float])).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Seq.empty[Set[Float]])).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Some(Seq(Set.empty[Float]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.floats_?.not(Option.empty[Float]).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Option.empty[Seq[Float]]).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Option.empty[Set[Float]]).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.not(Option.empty[Seq[Set[Float]]]).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.floats_?.==(Some(Set(float1))).query.get ==> List()
        NsSet.n.a1.floats_?.==(Some(Set(float1, float2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.floats_?.==(Some(Set(float1, float2, float3))).query.get ==> List()
        // Same as
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1)))).query.get ==> List()
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1, float2)))).query.get ==> List(a)
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1, float2, float3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1), Set(float2, float3)))).query.get ==> List()
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get ==> List(a)
        NsSet.n.a1.floats_?.==(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.floats_?.==(Some(Set.empty[Float])).query.get ==> List()
        NsSet.n.a1.floats_?.==(Some(Seq.empty[Set[Float]])).query.get ==> List()
        NsSet.n.a1.floats_?.==(Some(Seq(Set.empty[Float]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.floats_?.==(Option.empty[Set[Float]]).query.get ==> List(c)
        NsSet.n.a1.floats_?.==(Option.empty[Seq[Set[Float]]]).query.get ==> List(c)
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.floats_?.!=(Some(Set(float1))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.!=(Some(Set(float1, float2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.floats_?.!=(Some(Set(float1, float2, float3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1)))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1, float2)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1, float2, float3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1), Set(float2, float3)))).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1, float2), Set(float2, float3)))).query.get ==> List(b)
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1, float2), Set(float2, float3, float4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.floats_?.!=(Some(Seq(Set(float1, float2), Set.empty[Float]))).query.get ==> List(b)
        NsSet.n.a1.floats_?.!=(Some(Set.empty[Float])).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.!=(Some(Seq.empty[Set[Float]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.floats_?.==(Option.empty[Set[Float]]).query.get ==> List(c)
        NsSet.n.a1.floats_?.==(Option.empty[Seq[Set[Float]]]).query.get ==> List(c)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Some(Set(float1, float2)))
        val b = (2, Some(Set(float2, float3, float4)))
        val c = (3, None)
        NsSet.n.floats_?.insert(a, b, c).transact

        NsSet.n.a1.floats_?.<(Some(float0)).query.get ==> List()
        NsSet.n.a1.floats_?.<(Some(float1)).query.get ==> List()
        NsSet.n.a1.floats_?.<(Some(float2)).query.get ==> List(a)
        NsSet.n.a1.floats_?.<(Some(float3)).query.get ==> List(a, b)

        NsSet.n.a1.floats_?.<=(Some(float0)).query.get ==> List()
        NsSet.n.a1.floats_?.<=(Some(float1)).query.get ==> List(a)
        NsSet.n.a1.floats_?.<=(Some(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.<=(Some(float3)).query.get ==> List(a, b)

        NsSet.n.a1.floats_?.>(Some(float0)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>(Some(float1)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>(Some(float2)).query.get ==> List(b)
        NsSet.n.a1.floats_?.>(Some(float3)).query.get ==> List(b)

        NsSet.n.a1.floats_?.>=(Some(float0)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>=(Some(float1)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>=(Some(float2)).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>=(Some(float3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.floats_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.floats_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}