package molecule.base.codeGeneration.render

import molecule.base.ast.SchemaAST._


case class Schema(schema: MetaSchema) {

  def get: String =
    s"""|/*
        |* AUTO-GENERATED schema boilerplate code
        |*
        |* To change:
        |* 1. edit data model file in `${schema.pkg}.dataModel/`
        |* 2. `sbt clean compile -Dmolecule=true`
        |*/
        |package ${schema.pkg}.schema
        |import molecule.base.api.SchemaTransaction
        |import molecule.base.ast.SchemaAST._
        |
        |
        |object ${schema.domain}Schema extends SchemaTransaction {
        |
        |  override lazy val metaSchema: MetaSchema =
        |    ${schema.render(2)}
        |
        |
        |  override lazy val nsMap: Map[String, MetaNs] = ${schema.nsMap(1)}
        |
        |
        |  override lazy val attrMap: Map[String, (Cardinality, String)] = ${schema.attrMap(1)}
        |
        |
        |  override lazy val uniqueAttrs: List[String] = ${schema.uniqueAttrs}
        |
        |
        |${Schema_Datomic(schema).get}
        |}""".stripMargin
}
