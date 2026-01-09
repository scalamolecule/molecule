package molecule.db.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.h2.setup.DbProviders_h2
import molecule.db.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  "basic" - types {
    import molecule.db.compliance.domains.dsl.Types.*

    //    val List(a, b) = Entity.int.insert(1, 2).transact.ids
    //    Entity.int(3).save.transact
    //    Entity.int.a1.query.get ==> List(1, 2, 3)
    //    Entity(a).int(10).update.transact
    //    Entity(b).delete.transact
    //    Entity.int.a1.query.get ==> List(3, 10)

//    Person.name.height.>(Person.weight_)
//    Person.name.age.>(Person.age(avg))

//    Ref.s.sub(Entity.id(count).ref_(Ref.id_)).query.inspect
//    Entity.s.sub(Ref.id(count).entity_(Entity.id_)).query.inspect

  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.s.Bb.*(B.i).insert(
  //      ("a", List(1)),
  //    ).transact
  //
  //    A.s.a1.Bb.*?(B.i).query.get ==> List(
  //      ("a", List(1)),
  //    )
  //
  //  }

}
