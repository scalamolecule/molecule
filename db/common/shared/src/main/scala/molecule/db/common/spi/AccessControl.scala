package molecule.db.common.spi

import molecule.core.dataModel.{Attr, Element, Nested, OptEntity, OptNested, OptRef, Ref, SubQuery}
import molecule.core.error.ModelError
import molecule.db.common.marshalling.{ConnProxy, EnvMode}

trait AccessControl {

  // Core access checking logic --------------------------------------------------------

  /**
   * Check if a role has permission based on bitmask.
   * Handles public entities (-1) specially: authenticated users must still have the action.
   */
  private def checkPermission(
    permissions: Int,
    roleMask: Int,
    mode: EnvMode,
    roleName: String,
    roleIdx: Int,
    action: String,
    resourceType: String,
    resourceName: String,
    conn: Conn
  ): Unit = {
    if (permissions == -1) {
      // Public entity/attribute (-1 means public)
      // Authenticated users must STILL follow their role's action permissions
      // This prevents privilege escalation where authenticating as Guest (query-only)
      // would allow save/update/delete on public entities
      //
      // Industry standard behavior (AWS, GCP, PostgreSQL, GitHub):
      // - Public + Unauthenticated = Allow all actions (handled before calling this function)
      // - Public + Authenticated = Follow role's action permissions (check here)
      //
      // Check if this role has this specific action by looking at role action bitmasks
      val roleHasAction = action match {
        case a if a.contains("query")  => (conn.proxy.metaDb.roleQueryAction & roleMask) != 0
        case a if a.contains("save")   => (conn.proxy.metaDb.roleSaveAction & roleMask) != 0
        case a if a.contains("insert") => (conn.proxy.metaDb.roleInsertAction & roleMask) != 0
        case a if a.contains("update") => (conn.proxy.metaDb.roleUpdateAction & roleMask) != 0
        case a if a.contains("delete") => (conn.proxy.metaDb.roleDeleteAction & roleMask) != 0
        case _                         => false
      }

      if (!roleHasAction) {
        throw ModelError(formatErrorMessage(
          mode, roleName, roleIdx, roleMask, action, resourceType, resourceName, permissions
        ))
      }
    } else if ((permissions & roleMask) == 0) {
      // Role-restricted entity/attribute - check if role's bit is set
      throw ModelError(formatErrorMessage(
        mode, roleName, roleIdx, roleMask, action, resourceType, resourceName, permissions
      ))
    }
    // else: permission granted (role has action OR role bit is set in bitmask)
  }

  private def getEntityIndexFromElement(element: Element): Option[Int] = element match {
    case a: Attr                                  => Some(a.coord.head)
    case r: Ref if r.coord.nonEmpty               => Some(r.coord.head)
    case OptRef(r: Ref, _) if r.coord.nonEmpty    => Some(r.coord.head)
    case OptEntity(attrs) if attrs.nonEmpty       => Some(attrs.head.coord.head)

    case SubQuery(subElements) if subElements.nonEmpty => getEntityIndexFromElement(subElements.head)

    case Nested(r: Ref, _) if r.coord.nonEmpty    => Some(r.coord.head)
    case OptNested(r: Ref, _) if r.coord.nonEmpty => Some(r.coord.head)
    case _                                        => None
  }

  private def checkAccess(
    elements: List[Element],
    conn: Conn,
    action: String,
    entityAccessGetter: Int => Int,
    attrAccessGetter: Int => Int
  ): Unit = {
    // Skip access control if no roles are defined in the domain
    if (conn.proxy.metaDb.roleIndex.isEmpty) {
      return
    }

    conn.authContext match {
      case None =>
        // No authentication context
        conn.proxy.envMode match {
          case EnvMode.Dev                                   =>
            // Dev mode: Allow access for convenience (local development)
            ()
          case EnvMode.Test | EnvMode.Stage | EnvMode.Prod =>
            // Test/Stage/Prod: Check if accessing non-public resources
            checkUnauthenticatedAccess(elements, entityAccessGetter, attrAccessGetter)
        }

      case Some(authCtx) =>
        checkAuthenticatedAccess(elements, conn, action, authCtx.role, entityAccessGetter, attrAccessGetter)
    }
  }

  private def checkUnauthenticatedAccess(
    elements: List[Element],
    entityAccessGetter: Int => Int,
    attrAccessGetter: Int => Int
  ): Unit = {
    elements.collect {
      case a: Attr =>
        val List(entityIndex, attrIndex) = a.coord.take(2)

        // Check entity access
        val entityPermissions: Int = entityAccessGetter(entityIndex)
        if (entityPermissions != -1) {
          // Entity requires authentication (not public)
          throw ModelError("Access denied: No authenticated role provided")
        }

        // Check attribute access
        val attrPermissions: Int = attrAccessGetter(attrIndex)
        if (attrPermissions != -1) {
          // Attribute requires authentication (not public)
          throw ModelError("Access denied: No authenticated role provided")
        }
    }
  }

  // Special handling for delete operations which may have no attributes (delete by ID)
  private def checkUnauthenticatedAccessForDelete(
    elements: List[Element],
    conn: Conn,
    entityAccessGetter: Int => Int,
    attrAccessGetter: Int => Int
  ): Unit = {
    // Filter out id attributes - they're only used for targeting
    val attrs = elements.collect { case a: Attr if a.attr != "id" => a }

    if (attrs.nonEmpty) {
      // Has non-id attributes - check them normally
      val elementsToCheck = elements.filter {
        case a: Attr if a.attr == "id" => false
        case _                         => true
      }
      checkUnauthenticatedAccess(elementsToCheck, entityAccessGetter, attrAccessGetter)
    } else {
      // No attributes (delete by ID) - check entity-level access
      elements.headOption.foreach { element =>
        getEntityIndexFromElement(element).foreach { entityIndex =>
          val entityPermissions: Int = entityAccessGetter(entityIndex)
          if (entityPermissions != -1) {
            // Entity itself requires authentication
            throw ModelError("Access denied: No authenticated role provided")
          }
          // If entity is public (-1), still need to check if ANY of its attributes require authentication
          // This prevents circumventing attribute protection by deleting the whole entity
          // Extract entity name from element
          val entityName  = element match {
            case a: Attr              => a.ent
            case r: Ref               => r.ent
            case OptRef(r: Ref, _)    => r.ent
            case OptEntity(attrs)     => attrs.head.ent

            case SubQuery(subElements) if subElements.nonEmpty => subElements.head match {
              case a: Attr => a.ent
              case r: Ref  => r.ent
              case _       => "Unknown"
            }

            case Nested(r: Ref, _)    => r.ent
            case OptNested(r: Ref, _) => r.ent
            case _                    => "Unknown"
          }
          // Scan all attributes in metadata to find those belonging to this entity
          // Exclude 'id' attribute as it's only used for targeting
          val entityAttrs = conn.proxy.metaDb.attrData.keys.filter { key =>
            key.startsWith(s"$entityName.") && !key.endsWith(".id")
          }
          if (entityAttrs.nonEmpty) {
            // Check each attribute's delete permissions
            val attrKeysList = conn.proxy.metaDb.attrData.keys.toList
            entityAttrs.foreach { attrKey =>
              // Find the attribute index by position in attrData keys
              val attrIndex = attrKeysList.indexOf(attrKey)
              if (attrIndex >= 0 && attrIndex < conn.proxy.metaDb.deleteAccessAttributes.length) {
                val attrPermissions = attrAccessGetter(attrIndex)
                if (attrPermissions != -1) {
                  throw ModelError("Access denied: No authenticated role provided")
                }
              }
            }
          }
        }
      }
    }
  }

  private def checkAuthenticatedAccess(
    elements: List[Element],
    conn: Conn,
    action: String,
    roleName: String,
    entityAccessGetter: Int => Int,
    attrAccessGetter: Int => Int
  ): Unit = {
    val roleIdx = conn.proxy.metaDb.roleIndex.getOrElse(
      roleName,
      throw ModelError(s"Unknown role: $roleName")
    )

    if (roleIdx < 0 || roleIdx > 31) {
      throw ModelError(s"Invalid role index: $roleIdx (must be 0-31)")
    }

    val roleMask: Int = 1 << roleIdx

    elements.collect {
      case a: Attr =>
        val List(entityIndex, attrIndex) = a.coord.take(2)

        val attrPermissions  : Int = attrAccessGetter(attrIndex)
        val entityPermissions: Int = entityAccessGetter(entityIndex)

        // Determine if attribute has role override (different from entity permissions)
        val hasAttrOverride = attrPermissions != entityPermissions

        // Check access based on whether attribute has override
        if (hasAttrOverride) {
          // Attribute has its own permissions - check attribute access only
          checkPermission(attrPermissions, roleMask, conn.proxy.envMode, roleName, roleIdx, action, "attribute", s"${a.ent}.${a.attr}", conn)
        } else {
          // Attribute inherits entity permissions - report entity-level error
          checkPermission(entityPermissions, roleMask, conn.proxy.envMode, roleName, roleIdx, action, "entity", a.ent, conn)
        }
    }
  }

  // Special handling for delete operations which may have no attributes (delete by ID)
  private def checkAuthenticatedAccessForDelete(
    elements: List[Element],
    conn: Conn,
    action: String,
    roleName: String,
    entityAccessGetter: Int => Int,
    attrAccessGetter: Int => Int
  ): Unit = {
    // Filter out id attributes - they're only used for targeting
    val attrs = elements.collect { case a: Attr if a.attr != "id" => a }

    if (attrs.nonEmpty) {
      // Has non-id attributes - check them normally
      val elementsToCheck = elements.filter {
        case a: Attr if a.attr == "id" => false
        case _                         => true
      }
      checkAuthenticatedAccess(elementsToCheck, conn, action, roleName, entityAccessGetter, attrAccessGetter)
    } else {
      // No attributes (delete by ID) - check entity-level access
      val roleIdx       = conn.proxy.metaDb.roleIndex.getOrElse(
        roleName,
        throw ModelError(s"Unknown role: $roleName")
      )
      val roleMask: Int = 1 << roleIdx

      elements.headOption.foreach { element =>
        getEntityIndexFromElement(element).foreach { entityIndex =>
          val entityPermissions: Int = entityAccessGetter(entityIndex)
          val entityName             = element match {
            case a: Attr              => a.ent
            case r: Ref               => r.ent
            case OptRef(r: Ref, _)    => r.ent
            case OptEntity(attrs)     => attrs.head.ent

            case SubQuery(subElements) if subElements.nonEmpty => subElements.head match {
              case a: Attr => a.ent
              case r: Ref  => r.ent
              case _       => "Unknown"
            }

            case Nested(r: Ref, _)    => r.ent
            case OptNested(r: Ref, _) => r.ent
            case _                    => "Unknown"
          }

          checkPermission(entityPermissions, roleMask, conn.proxy.envMode, roleName, roleIdx, action, "entity", entityName, conn)
        }
      }
    }
  }

  private def formatErrorMessage(
    mode: EnvMode,
    roleName: String,
    roleIdx: Int,
    roleMask: Int,
    action: String,
    resourceType: String,
    resourceName: String,
    permissions: Int
  ): String = {
    mode match {
      case EnvMode.Dev | EnvMode.Test | EnvMode.Stage =>
        // Verbose error for debugging
        s"Access denied: Role '$roleName' (index $roleIdx, mask ${roleMask.toBinaryString}) " +
          s"cannot $action $resourceType '$resourceName' (permissions ${permissions.toBinaryString})"
      case EnvMode.Prod                                 =>
        // Sanitized error for production
        s"Access denied: Role '$roleName' cannot $action $resourceType '$resourceName'"
    }
  }


  // Public API --------------------------------------------------------

  protected def checkQueryAccess(elements: List[Element], conn: Conn): Unit = {
    checkAccess(
      elements,
      conn,
      "query",
      conn.proxy.metaDb.queryAccessEntities,
      conn.proxy.metaDb.queryAccessAttributes
    )
  }


  protected def checkSaveAccess(elements: List[Element], conn: Conn): Unit = {
    checkAccess(
      elements,
      conn,
      "save",
      conn.proxy.metaDb.saveAccessEntities,
      conn.proxy.metaDb.saveAccessAttributes
    )
  }

  protected def checkInsertAccess(elements: List[Element], conn: Conn): Unit = {
    checkAccess(
      elements,
      conn,
      "insert",
      conn.proxy.metaDb.insertAccessEntities,
      conn.proxy.metaDb.insertAccessAttributes
    )
  }

  protected def checkUpdateAccess(elements: List[Element], conn: Conn): Unit = {
    // Filter out id attributes - they're only used for targeting, not being updated
    val elementsToCheck = elements.filter {
      case a: Attr if a.attr == "id" => false
      case _                         => true
    }
    checkAccess(
      elementsToCheck,
      conn,
      "update",
      conn.proxy.metaDb.updateAccessEntities,
      conn.proxy.metaDb.updateAccessAttributes
    )
  }

  protected def checkDeleteAccess(elements: List[Element], conn: Conn): Unit = {
    // Skip access control if no roles are defined in the domain
    if (conn.proxy.metaDb.roleIndex.isEmpty) {
      return
    }

    conn.authContext match {
      case None =>
        // No authentication context
        conn.proxy.envMode match {
          case EnvMode.Dev                                   =>
            // Dev mode: Allow access for convenience (local development)
            ()
          case EnvMode.Test | EnvMode.Stage | EnvMode.Prod =>
            // Test/Stage/Prod: Check if accessing non-public resources
            checkUnauthenticatedAccessForDelete(
              elements,
              conn,
              conn.proxy.metaDb.deleteAccessEntities,
              conn.proxy.metaDb.deleteAccessAttributes
            )
        }

      case Some(authCtx) =>
        checkAuthenticatedAccessForDelete(
          elements,
          conn,
          "delete",
          authCtx.role,
          conn.proxy.metaDb.deleteAccessEntities,
          conn.proxy.metaDb.deleteAccessAttributes
        )
    }
  }

  protected def checkRawQueryAccess(conn: Conn): Unit = {
    // Skip access control if no roles are defined in the domain
    if (conn.proxy.metaDb.roleIndex.isEmpty) {
      return
    }

    conn.authContext match {
      case None =>
        // No authentication context - raw queries always require authentication
        throw ModelError(
          "Access denied: Raw SQL queries require authentication. " +
            "Please authenticate with a role that has rawQuery access."
        )

      case Some(authCtx) =>
        val roleIndex = conn.proxy.metaDb.roleIndex.get(authCtx.role) match {
          case Some(index) => index
          case None        =>
            throw ModelError(s"Access denied: Unknown role '${authCtx.role}'")
        }

        // Check if role has rawQuery action by checking the role action bitmask
        val roleMask = 1 << roleIndex
        val hasRawQueryAccess = (conn.proxy.metaDb.roleRawQueryAction & roleMask) != 0

        if (!hasRawQueryAccess) {
          throw ModelError(
            s"Access denied: Role '${authCtx.role}' does not have rawQuery access. " +
              "Raw SQL queries require explicit rawQuery permission."
          )
        }
    }
  }

  protected def checkRawTransactAccess(conn: Conn): Unit = {
    // Skip access control if no roles are defined in the domain
    if (conn.proxy.metaDb.roleIndex.isEmpty) {
      return
    }

    conn.authContext match {
      case None =>
        // No authentication context - raw transactions always require authentication
        throw ModelError(
          "Access denied: Raw SQL transactions require authentication. " +
            "Please authenticate with a role that has rawTransact access."
        )

      case Some(authCtx) =>
        val roleIndex = conn.proxy.metaDb.roleIndex.get(authCtx.role) match {
          case Some(index) => index
          case None        =>
            throw ModelError(s"Access denied: Unknown role '${authCtx.role}'")
        }

        // Check if role has rawTransact action by checking the role action bitmask
        val roleMask = 1 << roleIndex
        val hasRawTransactAccess = (conn.proxy.metaDb.roleRawTransactAction & roleMask) != 0

        if (!hasRawTransactAccess) {
          throw ModelError(
            s"Access denied: Role '${authCtx.role}' does not have rawTransact access. " +
              "Raw SQL transactions require explicit rawTransact permission."
          )
        }
    }
  }
}
