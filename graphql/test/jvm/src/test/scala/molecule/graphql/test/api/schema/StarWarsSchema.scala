///*
//* AUTO-GENERATED schema boilerplate code
//*
//* To change:
//* 1. edit domain definition file in `molecule.graphql.client.dataModel/`
//* 2. `sbt compile -Dmolecule=true`
//*/
//package molecule.graphql.test.api.schema
//
//import molecule.base.ast.*
//import molecule.base.ast.*
////import molecule.db.core.api.Schema
//
//
//trait StarWarsSchema extends Schema {
//
//  override val metaDomain: MetaDomain =
//    MetaDomain("molecule.graphql.client.dataModel", "Starwars", 6, List(
//      MetaSegment("", List(
//        MetaEntity("Character", List(
//          MetaAttribute("id"       , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("name"     , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("friends"  , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("appearsIn", CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            )))
//        ), List("Character", "Droid", "Human"), List(), List()),
//
//        MetaEntity("Droid", List(
//          MetaAttribute("id"             , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("name"           , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("friends"        , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("appearsIn"      , CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            ))),
//          MetaAttribute("primaryFunction", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
//        ), List(), List(), List()),
//
//        MetaEntity("Human", List(
//          MetaAttribute("id"        , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("name"      , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("friends"   , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("appearsIn" , CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            ))),
//          MetaAttribute("homePlanet", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
//        ), List(), List(), List())
//      ))
//    ))
//
//
//  override val entityMap: Map[String, MetaEntity] = Map(
//    "Character" ->
//      MetaEntity("Character", List(
//        MetaAttribute("id"       , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("name"     , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("friends"  , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("appearsIn", CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            )))
//      ), List("Character", "Droid", "Human"), List(), List()),
//
//    "Droid" ->
//      MetaEntity("Droid", List(
//        MetaAttribute("id"             , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("name"           , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("friends"        , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("appearsIn"      , CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            ))),
//        MetaAttribute("primaryFunction", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
//      ), List(), List(), List()),
//
//    "Human" ->
//      MetaEntity("Human", List(
//        MetaAttribute("id"        , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("name"      , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("friends"   , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("appearsIn" , CardSet, "String", None, Nil, None, None, Nil, Nil, List(
//            (
//              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
//            ))),
//        MetaAttribute("homePlanet", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
//      ), List(), List(), List())
//  )
//
//
//  override val attrMap: Map[String, (Card, String, Seq[String])] = Map(
//    "Character.id"          -> (CardOne, "ID"            , List()),
//    "Character.name"        -> (CardOne, "String"        , List()),
//    "Character.friends"     -> (CardSet, "ID"            , List()),
//    "Character.appearsIn"   -> (CardSet, "String"        , List()),
//    "Droid.id"              -> (CardOne, "ID"            , List()),
//    "Droid.name"            -> (CardOne, "String"        , List()),
//    "Droid.friends"         -> (CardSet, "ID"            , List()),
//    "Droid.appearsIn"       -> (CardSet, "String"        , List()),
//    "Droid.primaryFunction" -> (CardOne, "String"        , List()),
//    "Human.id"              -> (CardOne, "ID"            , List()),
//    "Human.name"            -> (CardOne, "String"        , List()),
//    "Human.friends"         -> (CardSet, "ID"            , List()),
//    "Human.appearsIn"       -> (CardSet, "String"        , List()),
//    "Human.homePlanet"      -> (CardOne, "String"        , List())
//  )
//
//
//  override val uniqueAttrs: List[String] = List()
//}