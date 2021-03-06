package molecule
import molecule.datomic.api.in3_out10._
import molecule.datomic.base.facade.Conn
import molecule.datomic.peer.facade.Datomic_Peer
import molecule.tests.core.base.schema.CoreTestSchema
import org.specs2.mutable.Specification


//class AdHocTest extends molecule.setup.TestSpec with Helpers {
class AdHocTest extends Specification {


  //  "Simple hyperedge" in new GraphSetup {
  //
  //    import molecule.tests.examples.datomic.dayOfDatomic.dsl.Graph._
  //
  //    // User 1 Roles in Group 2
  //    User.name_("User1")
  //      .RoleInGroup.Group.name_("Group2")
  //      ._RoleInGroup.Role.name.inspectGet
  //
  //
  //    ok
  //  }
  //
  //    "adhoc" in new BidirectionalSetup {
  //      import molecule.tests.core.bidirectionals.dsl.Bidirectional._
  //      Person.name("Ann").Buddies.e(gus)
  //    }

  //    "self-join" >> {
  //      import molecule.tests.core.ref.dsl.SelfJoin._
  //      implicit val conn: Conn = Datomic_Peer.recreateDbFrom(SelfJoinSchema)
  //
  //
  //
  //
  //      ok
  //    }

  //      "bidirectional" >> {
  //        import molecule.tests.core.bidirectionals.dsl.Bidirectional._
  //        implicit val conn: Conn = Datomic_Peer.recreateDbFrom(BidirectionalSchema)
  //
  //
  //        ok
  //      }
  //
  //
  //
  //
  //  "adhoc" in new CoreSetup {
  //    import molecule.tests.core.base.dsl.CoreTest._
  //

  //  }
  //
  //  "Insert resolves to correct partitions" in new PartitionSetup {
  //    import molecule.tests.core.schemaDef.dsl.PartitionTest._
  //    m(lit_Book.title.Author.name._lit_Book.Reviewers * gen_Person.name).inspectGet
  //  }
  //
  //
  //    "Insert resolves to correct partitions" in new ModernGraph2Setup {
  //
  //      import molecule.tests.examples.gremlin.gettingStarted.dsl.ModernGraph2._
  //
  //      m(Person.name.Knows * Person.name).inspectGet
  //    }
  //
  //
  //    "txCount" >> {
  //
  //      import molecule.core.util.testing.TxCount._
  //      implicit val conn: Conn = Datomic_Peer.recreateDbFrom(TxCountSchema)
  //      TxCount.db("x").basisT(42L).save
  //
  //      ok
  //    }

  "core" >> {
    import molecule.tests.core.base.dsl.CoreTest._
    implicit val conn: Conn = Datomic_Peer.recreateDbFrom(CoreTestSchema)

    Ns.int(42).Ref1.int1(43).save

    val doe = 7

    val x = m(Ns.int.Ref1.int1).apply { self =>
//    val x = m(Ns.int).apply { self =>
      val bar = doe
      val v1  = bar + bar
      def a1 = v1 + 1
      def a2() = a1 + 1
      def a3(i: Int) = a2() + i
      def a4[A]() = a3(2)
      def a5[A](v: A): String = v.toString
      def a6(i: Int): Int = i + a1
      def a7(i: Int, s: String) = s + (i + self.int)
      def a8(ii: List[Int]) = ii.length
      def a9(ii: List[Conn]) = ii.length
    }

    x.v1 === 14
    x.a1 === 15
    x.a2() === 16
    x.a3(1) === 17
    x.a4() === 18
    x.a5(1) === "1"
    x.a5(true) === "true"
    x.a6(1) === 16
    x.a7(1, "hej") === "hej43"
    x.a8(List(1, 2, 3)) === 3
    x.a9(List(conn)) === 1

    x.int === 42
    x.Ref1.int1 === 43

    ok
  }

  //
  //
  //  "A first query" in new SeattleSetup {
  //    import molecule.tests.examples.datomic.seattle.dsl.Seattle._
  //
  //    // A Community-name molecule
  //    val communities = m(Community. e.name_)
  //
  //    // We have 150 communities
  //    communities.get.size === 150
  //  }

  //  "adhoc" in new BidirectionalSetup {
  //import molecule.tests.core.bidirectionals.dsl.Bidirectional._
  //
  //  }

  //  "adhoc" in new PartitionSetup {

  //  }


  //  "example adhoc" in new SeattleSetup {
  //
  //
  //    //    ok
  //  }


  //  "example adhoc" in new SocialNewsSetup {
  //
  //
  //
  //    ok
  //  }
}
