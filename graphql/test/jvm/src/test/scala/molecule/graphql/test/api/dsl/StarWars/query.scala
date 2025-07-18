//package molecule.graphql.test.api.dsl.StarWars
//
//import molecule.base.metaModel.*
//import molecule.core.dataModel.*
//import molecule.core.dataModel.Keywords.qm
//import molecule.graphql.test.api.dsl.StarWars.output.*
//
//object query extends query
//
//trait query {
//
//  MetaSegment(
//    "query",
//    List(
//      MetaEntity(
//        "hero",
//        List(
//          MetaAttribute("id", CardOne, "String", options = List("mandatory"))
//        ),
//        List("Character")
//      ),
//      MetaEntity(
//        "hero",
//        Nil,
//        List("Character")
//      ),
//      MetaEntity(
//        "character",
//        List(
//          MetaAttribute("id", CardOne, "String", options = List("mandatory"))
//        ),
//        List("Character")
//      ),
//    )
//  )
//
//  def hero(id: String) = new Character_0[Nothing](DataModel(List(
//    AttrOneTacString("hero", "id", Eq, Seq(id))
//  )))
//
//  def hero: Character_0[Nothing] = new Character_0[Nothing](DataModel(List(
//    AttrOneTacString("hero", "")
//  )))
//
//  def character(id: String) = new Character_0[Nothing](DataModel(List(
//    AttrOneTacString("Character", "id", Eq, Seq(id))
//  )))
//
//  def human(id: String) = new Human_0[Nothing](DataModel(List(
//    AttrOneTacString("Human", "id", Eq, Seq(id))
//  )))
//
//  def human(id: qm) = new Human_0[Nothing](DataModel(List(
//    AttrOneTacString("Human", "id", Eq)
//  )))
//
//  def droid(id: String) = new Droid_0[Nothing](DataModel(List(
//    AttrOneTacString("Droid", "id", Eq)
//  )))
//}
//
