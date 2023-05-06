package molecule.sql.core.marshalling

import molecule.core.marshalling.MoleculeRpcServer

/**
 * Server app serving http requests/ws from client
 *
 * Run with
 *
 *    sbt datomicJVM/run
 */
object SqlRpcServer extends MoleculeRpcServer(SqlRpcJVM) with App
