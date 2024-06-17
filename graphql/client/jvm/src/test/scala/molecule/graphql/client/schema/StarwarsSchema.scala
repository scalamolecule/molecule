/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit data model file in `molecule.graphql.client.dataModel/`
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.schema

import molecule.base.api.Schema
import molecule.base.ast._


object StarwarsSchema extends Schema
  with StarwarsSchema_Datomic
  with StarwarsSchema_H2
  with StarwarsSchema_MariaDB
  with StarwarsSchema_Mysql
  with StarwarsSchema_PostgreSQL
  with StarwarsSchema_SQlite {

  val metaSchema: MetaSchema =
    MetaSchema("molecule.graphql.client", "Starwars", 6, Seq(
      MetaPart("", Seq(
        MetaNs("Character", Seq(
          MetaAttr("id"       , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("name"     , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("friends"  , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
          MetaAttr("appearsIn", CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            )))
        ), Seq("Character", "Droid", "Human"), Seq(), Seq()),

        MetaNs("Droid", Seq(
          MetaAttr("id"             , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("name"           , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("friends"        , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
          MetaAttr("appearsIn"      , CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            ))),
          MetaAttr("primaryFunction", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
        ), Seq(), Seq(), Seq()),

        MetaNs("Human", Seq(
          MetaAttr("id"        , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("name"      , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
          MetaAttr("friends"   , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
          MetaAttr("appearsIn" , CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            ))),
          MetaAttr("homePlanet", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
        ), Seq(), Seq(), Seq())
      ))
    ))


  val nsMap: Map[String, MetaNs] = Map(
    "Character" -> 
      MetaNs("Character", Seq(
        MetaAttr("id"       , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("name"     , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("friends"  , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("appearsIn", CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            )))
      ), Seq("Character", "Droid", "Human"), Seq(), Seq()),

    "Droid" -> 
      MetaNs("Droid", Seq(
        MetaAttr("id"             , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("name"           , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("friends"        , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("appearsIn"      , CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            ))),
        MetaAttr("primaryFunction", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
      ), Seq(), Seq(), Seq()),

    "Human" -> 
      MetaNs("Human", Seq(
        MetaAttr("id"        , CardOne, "ID"    , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("name"      , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("friends"   , CardSet, "ID"    , Some("Character"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("appearsIn" , CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
            (
              """v => Seq("NEWHOPE", "EMPIRE", "JEDI").contains(v)""",
              """Value `$v` is not one of the allowed values in Seq("NEWHOPE", "EMPIRE", "JEDI")"""
            ))),
        MetaAttr("homePlanet", CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
      ), Seq(), Seq(), Seq())
  )


  val attrMap: Map[String, (Card, String, Seq[String])] = Map(
    "Character.id"          -> (CardOne, "ID"            , Seq()),
    "Character.name"        -> (CardOne, "String"        , Seq()),
    "Character.friends"     -> (CardSet, "ID"            , Seq()),
    "Character.appearsIn"   -> (CardSet, "String"        , Seq()),
    "Droid.id"              -> (CardOne, "ID"            , Seq()),
    "Droid.name"            -> (CardOne, "String"        , Seq()),
    "Droid.friends"         -> (CardSet, "ID"            , Seq()),
    "Droid.appearsIn"       -> (CardSet, "String"        , Seq()),
    "Droid.primaryFunction" -> (CardOne, "String"        , Seq()),
    "Human.id"              -> (CardOne, "ID"            , Seq()),
    "Human.name"            -> (CardOne, "String"        , Seq()),
    "Human.friends"         -> (CardSet, "ID"            , Seq()),
    "Human.appearsIn"       -> (CardSet, "String"        , Seq()),
    "Human.homePlanet"      -> (CardOne, "String"        , Seq())
  )


  val uniqueAttrs: List[String] = List()
}