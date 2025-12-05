# Molecule Authorization vs. Industry Standards

This document compares Molecule's authorization model with major authorization systems used in production applications.

---

## Quick Comparison Table

| System | Type | Where Defined | Validation | Boilerplate | Learning Curve |
|--------|------|---------------|------------|-------------|----------------|
| **Molecule** | Declarative RBAC | Domain model | Compile-time | Zero | Low |
| **GraphQL Directives** | Declarative | Schema | Runtime | Low | Low |
| **Spring Security** | Annotation-based | Controllers | Runtime | Medium | Medium |
| **Rails Pundit** | Policy objects | Ruby classes | Runtime | High | Medium |
| **Django Permissions** | Model-based | Models + Views | Runtime | Medium | Low |
| **AWS IAM** | Policy documents | JSON files | Runtime | High | High |
| **Casbin** | Policy language | Config files | Runtime | Medium | High |
| **CASL** | Rule-based | JavaScript | Runtime | Medium | Medium |

---

## System-by-System Comparison

### 1. GraphQL with Directives (Apollo, Hasura)

**Approach:** Schema directives for field-level permissions

```graphql
type Post @auth(requires: [MEMBER, ADMIN]) {
  title: String!
  content: String! @auth(requires: [MEMBER, ADMIN])
  secret: String! @auth(requires: [ADMIN])
  tags: [String!]! @auth(requires: [GUEST, MEMBER, ADMIN])
}
```

**Molecule equivalent:**
```scala
trait Post extends Guest with Member with Admin {
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
  val tags = oneString
}
```

**Comparison:**

| Aspect | GraphQL Directives | Molecule |
|--------|-------------------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Type safety** | Schema-level only | Full Scala type system ✓ |
| **Action granularity** | Custom resolver logic | Built-in 5 actions ✓ |
| **Boilerplate** | Middleware per resolver | Zero ✓ |
| **Expressiveness** | Limited to directives | Rich trait composition ✓ |
| **Error messages** | Generic | Specific attribute/entity ✓ |

**Verdict:** Molecule provides compile-time safety with zero boilerplate, while GraphQL requires runtime checks and custom resolver logic.

---

### 2. Spring Security (Java/Kotlin)

**Approach:** Annotation-based method security

```java
@Service
public class PostService {
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public Post getPost(Long id) { ... }

    @PreAuthorize("hasRole('ADMIN')")
    public void updateSecret(Long id, String secret) { ... }

    @PreAuthorize("hasAnyRole('GUEST', 'MEMBER', 'ADMIN')")
    public List<String> getTags(Long id) { ... }
}
```

**Molecule equivalent:**
```scala
trait Post extends Guest with Member with Admin
  with updating[Member]  // Member can update
{
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
  val tags = oneString
}

// Usage - authorization automatic in .transact
Post.title.query.get  // Checks automatically
```

**Comparison:**

| Aspect | Spring Security | Molecule |
|--------|----------------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Location** | Controllers/Services (scattered) | Domain model (centralized) ✓ |
| **Boilerplate** | Annotation per method | Zero ✓ |
| **Consistency** | Manual per endpoint | Automatic ✓ |
| **Testing** | Integration tests required | Type system catches errors ✓ |
| **Field-level** | Manual per getter | Declarative per attribute ✓ |

**Verdict:** Spring Security requires annotations on every method and runtime checks. Molecule centralizes authorization in the domain with compile-time validation.

---

### 3. Rails Pundit (Ruby)

**Approach:** Policy objects per model

```ruby
class PostPolicy < ApplicationPolicy
  def show?
    user.guest? || user.member? || user.admin?
  end

  def update_content?
    user.member? || user.admin?
  end

  def update_secret?
    user.admin?
  end

  def destroy?
    user.member? || user.admin?
  end
end

# In controller
def update
  @post = Post.find(params[:id])
  authorize @post
  # Check each field manually
  if params[:secret]
    authorize @post, :update_secret?
  end
  @post.update(post_params)
end
```

**Molecule equivalent:**
```scala
trait Post extends Guest with Member with Admin
  with updating[Member]
  with deleting[Member]
{
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
}

// Usage - automatic checking
Post(id).content("new").update.transact  // Checks automatically
```

**Comparison:**

| Aspect | Rails Pundit | Molecule |
|--------|-------------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Boilerplate** | Policy class + controller checks | Zero ✓ |
| **Consistency** | Manual enforcement | Automatic ✓ |
| **Field-level** | Manual per field | Declarative ✓ |
| **Testing** | Unit + integration tests | Type system ✓ |
| **Centralization** | Separate policy files | Domain model ✓ |
| **DRY principle** | Repetitive checks | Define once ✓ |

**Verdict:** Pundit requires extensive boilerplate in policy classes and manual checks in controllers. Molecule defines authorization once in the domain model.

---

### 4. Django Permissions (Python)

**Approach:** Model permissions + view decorators

```python
class Post(models.Model):
    title = models.CharField(max_length=200)
    content = models.TextField()
    secret = models.TextField()

    class Meta:
        permissions = [
            ("view_post_content", "Can view post content"),
            ("view_post_secret", "Can view post secret"),
            ("change_post_secret", "Can change post secret"),
        ]

# In views
@permission_required('blog.view_post', raise_exception=True)
def post_detail(request, pk):
    post = Post.objects.get(pk=pk)

    # Manual field-level checks
    if request.user.has_perm('blog.view_post_secret'):
        secret = post.secret
    else:
        secret = None

    return render(request, 'post.html', {
        'post': post,
        'secret': secret
    })
```

**Molecule equivalent:**
```scala
trait Post extends Guest with Member with Admin {
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
}

// Usage - automatic
Post.secret.query.get  // Automatically checks
```

**Comparison:**

| Aspect | Django Permissions | Molecule |
|--------|-------------------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Boilerplate** | Decorators + manual checks | Zero ✓ |
| **Field-level** | Completely manual | Declarative ✓ |
| **Consistency** | Template logic varies | Always enforced ✓ |
| **Testing** | Manual tests required | Type system ✓ |
| **DRY principle** | Repetitive across views | Define once ✓ |

**Verdict:** Django requires manual permission checks in views and templates. Molecule handles everything automatically based on domain definition.

---

### 5. AWS IAM (JSON Policies)

**Approach:** JSON policy documents

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "dynamodb:GetItem",
        "dynamodb:Query"
      ],
      "Resource": "arn:aws:dynamodb:*:*:table/Posts",
      "Condition": {
        "StringEquals": {
          "dynamodb:LeadingKeys": ["${aws:userid}"]
        }
      }
    },
    {
      "Effect": "Allow",
      "Action": "dynamodb:UpdateItem",
      "Resource": "arn:aws:dynamodb:*:*:table/Posts",
      "Condition": {
        "ForAllValues:StringEquals": {
          "dynamodb:Attributes": ["title", "content"]
        },
        "StringNotEquals": {
          "dynamodb:Attributes": ["secret"]
        }
      }
    }
  ]
}
```

**Molecule equivalent:**
```scala
trait Post extends Guest with Member with Admin
  with updating[Member]
{
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
}
```

**Comparison:**

| Aspect | AWS IAM | Molecule |
|--------|---------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Readability** | Low (verbose JSON) | High (natural Scala) ✓ |
| **Maintainability** | External files | Integrated domain ✓ |
| **Type safety** | None (JSON strings) | Full type system ✓ |
| **Learning curve** | High (complex conditions) | Low (trait composition) ✓ |
| **Debugging** | CloudTrail logs | Compile errors ✓ |

**Verdict:** AWS IAM is powerful but verbose and complex. Molecule provides equivalent expressiveness with type safety and compile-time validation.

---

### 6. Casbin (Policy Engine)

**Approach:** Policy language in config files

```conf
# model.conf
[request_definition]
r = sub, obj, act

[policy_definition]
p = sub, obj, act

[role_definition]
g = _, _

[policy_effect]
e = some(where (p.eft == allow))

[matchers]
m = g(r.sub, p.sub) && r.obj == p.obj && r.act == p.act

# policy.csv
p, guest, post, read
p, member, post, read
p, member, post, write
p, admin, post, read
p, admin, post, write
p, admin, post.secret, read
p, admin, post.secret, write
```

**Molecule equivalent:**
```scala
trait Guest extends Role with query
trait Member extends Role with query with save
trait Admin extends Role with query with save with insert with update with delete

trait Post extends Guest with Member with Admin
  with updating[Member]
{
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
}
```

**Comparison:**

| Aspect | Casbin | Molecule |
|--------|--------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Configuration** | External files | Domain model ✓ |
| **Type safety** | None | Full type system ✓ |
| **Learning curve** | High (policy language) | Low (Scala traits) ✓ |
| **IDE support** | Limited | Full Scala tooling ✓ |
| **Refactoring** | Manual updates | Compiler-assisted ✓ |

**Verdict:** Casbin is flexible but requires learning a policy language and lacks type safety. Molecule leverages Scala's type system for safety and tooling.

---

### 7. CASL (JavaScript/TypeScript)

**Approach:** Ability-based authorization in JavaScript

```typescript
import { defineAbility } from '@casl/ability'

const ability = defineAbility((can, cannot) => {
  // Guest permissions
  if (user.role === 'guest') {
    can('read', 'Post', ['title', 'tags'])
  }

  // Member permissions
  if (user.role === 'member') {
    can('read', 'Post')
    can('create', 'Post')
    can('update', 'Post', ['title', 'content', 'tags'])
  }

  // Admin permissions
  if (user.role === 'admin') {
    can('manage', 'all')
  }
})

// In code - manual checks
if (ability.can('read', post, 'secret')) {
  console.log(post.secret)
}

if (ability.can('update', post, 'content')) {
  post.content = newContent
}
```

**Molecule equivalent:**
```scala
trait Guest extends Role with query
trait Member extends Role with query with save
trait Admin extends Role with query with save with insert with update with delete

trait Post extends Guest with Member with Admin
  with updating[Member]
{
  val title = oneString
  val content = oneString.exclude[Guest]
  val secret = oneString.only[Admin]
  val tags = oneString
}

// Usage - automatic
Post.secret.query.get  // Automatically enforced
Post(id).content("new").update.transact  // Automatically enforced
```

**Comparison:**

| Aspect | CASL | Molecule |
|--------|------|----------|
| **Validation** | Runtime | Compile-time ✓ |
| **Type safety** | TypeScript (limited) | Full Scala type system ✓ |
| **Boilerplate** | Manual checks everywhere | Zero ✓ |
| **Consistency** | Developer must remember | Automatic ✓ |
| **Testing** | Manual mocking | Type system ✓ |
| **Centralization** | Ability definitions separate | Domain model ✓ |

**Verdict:** CASL requires manual checks throughout the codebase. Molecule enforces authorization automatically based on domain definition.

---

## Feature Comparison Matrix

| Feature | Molecule | GraphQL | Spring | Pundit | Django | AWS IAM | Casbin | CASL |
|---------|----------|---------|--------|--------|--------|---------|--------|------|
| **Compile-time validation** | ✓ | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ |
| **Zero boilerplate** | ✓ | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ |
| **Field-level permissions** | ✓ | ✓ | ✗ | ✗ | ✗ | ✓ | ✓ | ✓ |
| **Action granularity** | 6 built-in | Custom | Custom | Custom | 4 built-in | Fine-grained | Custom | Custom |
| **Centralized definition** | ✓ | ✓ | ✗ | ✗ | ✗ | ✗ | ✓ | ✗ |
| **Type safety** | Full | Schema | Partial | None | None | None | None | Partial |
| **Learning curve** | Low | Low | Medium | Medium | Low | High | High | Medium |
| **IDE support** | Full Scala | Full GraphQL | Full Java | Ruby | Python | JSON only | Limited | TypeScript |
| **Automatic enforcement** | ✓ | ✓ | ✗ | ✗ | ✗ | ✓ | ✗ | ✗ |
| **Refactoring support** | Compiler | Schema | IDE | None | None | None | None | IDE |

---

## Code Comparison: Real-World Example

**Scenario:** Blog post with different access levels

### Molecule
```scala
trait Guest extends Role with query
trait Member extends Role with query with save
trait Moderator extends Role with query with save with insert with update with delete
trait Admin extends Role with query with save with insert with update with delete

trait BlogPost extends Guest with Member with Moderator with Admin
  with updating[Member]      // Members can edit their posts
  with deleting[Moderator]   // Moderators can delete
{
  val title = oneString                      // Public
  val content = oneString.exclude[Guest]     // Members+ only
  val draft = oneBoolean.updating[Guest]     // Guest can mark draft
  val flagged = oneBoolean.only[(Moderator, Admin)]  // Mods+ only
  val featured = oneBoolean.only[Admin]   // Admin only
}

// Usage - zero boilerplate
BlogPost.title.query.get  // Automatic
BlogPost(id).content("new").update.transact  // Automatic
```

**Lines of code:** 15 lines (domain definition)

---

### Spring Security (Java)
```java
@Entity
public class BlogPost {
    private String title;
    private String content;
    private Boolean draft;
    private Boolean flagged;
    private Boolean featured;
    // getters/setters omitted for brevity
}

@Service
public class BlogPostService {
    @PreAuthorize("isAuthenticated()")
    public BlogPost getPost(Long id) { ... }

    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public String getContent(Long id) { ... }

    @PreAuthorize("hasRole('MEMBER')")
    public void updateContent(Long id, String content) { ... }

    @PreAuthorize("isAuthenticated()")
    public void updateDraft(Long id, Boolean draft) { ... }

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public Boolean getFlagged(Long id) { ... }

    @PreAuthorize("hasRole('ADMIN')")
    public Boolean getFeatured(Long id) { ... }

    @PreAuthorize("hasRole('MODERATOR')")
    public void deletePost(Long id) { ... }
}

@RestController
public class BlogPostController {
    @GetMapping("/posts/{id}")
    public ResponseEntity<BlogPostDTO> getPost(@PathVariable Long id) {
        BlogPost post = service.getPost(id);

        // Manual field filtering based on role
        BlogPostDTO dto = new BlogPostDTO();
        dto.setTitle(post.getTitle());

        try {
            dto.setContent(service.getContent(id));
        } catch (AccessDeniedException e) {
            dto.setContent(null);
        }

        if (hasRole("MODERATOR") || hasRole("ADMIN")) {
            try {
                dto.setFlagged(service.getFlagged(id));
            } catch (AccessDeniedException e) {}
        }

        if (hasRole("ADMIN")) {
            try {
                dto.setFeatured(service.getFeatured(id));
            } catch (AccessDeniedException e) {}
        }

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                          @RequestBody UpdateDTO dto) {
        if (dto.getContent() != null) {
            try {
                service.updateContent(id, dto.getContent());
            } catch (AccessDeniedException e) {
                return ResponseEntity.status(403).build();
            }
        }

        if (dto.getDraft() != null) {
            try {
                service.updateDraft(id, dto.getDraft());
            } catch (AccessDeniedException e) {
                return ResponseEntity.status(403).build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
```

**Lines of code:** ~80 lines (entity + service + controller, without tests)

---

### Rails Pundit (Ruby)
```ruby
class BlogPost < ApplicationRecord
  # Model definition
end

class BlogPostPolicy < ApplicationPolicy
  def show?
    true  # Everyone can see posts
  end

  def show_content?
    user.member? || user.moderator? || user.admin?
  end

  def show_flagged?
    user.moderator? || user.admin?
  end

  def show_featured?
    user.admin?
  end

  def update?
    user.member? || user.moderator? || user.admin?
  end

  def update_content?
    user.member? || user.moderator? || user.admin?
  end

  def update_draft?
    true  # Anyone can update draft status
  end

  def destroy?
    user.moderator? || user.admin?
  end
end

class BlogPostsController < ApplicationController
  def show
    @post = BlogPost.find(params[:id])
    authorize @post

    @show_content = policy(@post).show_content?
    @show_flagged = policy(@post).show_flagged?
    @show_featured = policy(@post).show_featured?

    render json: @post,
           only: [:id, :title],
           methods: build_methods
  end

  def update
    @post = BlogPost.find(params[:id])
    authorize @post

    if params[:content] && !policy(@post).update_content?
      render json: { error: 'Forbidden' }, status: 403
      return
    end

    if params[:flagged] && !policy(@post).show_flagged?
      render json: { error: 'Forbidden' }, status: 403
      return
    end

    if params[:featured] && !policy(@post).show_featured?
      render json: { error: 'Forbidden' }, status: 403
      return
    end

    @post.update(permitted_params)
    render json: @post
  end

  def destroy
    @post = BlogPost.find(params[:id])
    authorize @post
    @post.destroy
    head :no_content
  end

  private

  def build_methods
    methods = []
    methods << :content if policy(@post).show_content?
    methods << :flagged if policy(@post).show_flagged?
    methods << :featured if policy(@post).show_featured?
    methods
  end

  def permitted_params
    params.permit(permitted_attributes)
  end

  def permitted_attributes
    attrs = [:title]
    attrs << :content if policy(@post).update_content?
    attrs << :draft if policy(@post).update_draft?
    attrs << :flagged if policy(@post).show_flagged?
    attrs << :featured if policy(@post).show_featured?
    attrs
  end
end
```

**Lines of code:** ~90 lines (model + policy + controller, without tests)

---

### Django (Python)
```python
from django.db import models
from django.contrib.auth.models import User

class BlogPost(models.Model):
    title = models.CharField(max_length=200)
    content = models.TextField()
    draft = models.BooleanField(default=False)
    flagged = models.BooleanField(default=False)
    featured = models.BooleanField(default=False)

    class Meta:
        permissions = [
            ('view_content', 'Can view content'),
            ('change_content', 'Can change content'),
            ('view_flagged', 'Can view flagged status'),
            ('view_featured', 'Can view featured status'),
        ]

from django.http import JsonResponse, HttpResponseForbidden
from django.views import View

class BlogPostView(View):
    def get(self, request, post_id):
        post = BlogPost.objects.get(id=post_id)

        data = {
            'id': post.id,
            'title': post.title,
        }

        if request.user.has_perm('blog.view_content'):
            data['content'] = post.content

        if request.user.has_perm('blog.view_flagged'):
            data['flagged'] = post.flagged

        if request.user.has_perm('blog.view_featured'):
            data['featured'] = post.featured

        data['draft'] = post.draft  # Everyone can see

        return JsonResponse(data)

    def put(self, request, post_id):
        post = BlogPost.objects.get(id=post_id)

        if 'content' in request.POST:
            if not request.user.has_perm('blog.change_content'):
                return HttpResponseForbidden()
            post.content = request.POST['content']

        if 'draft' in request.POST:
            # Anyone can update draft
            post.draft = request.POST.get('draft') == 'true'

        if 'flagged' in request.POST:
            if not request.user.has_perm('blog.view_flagged'):
                return HttpResponseForbidden()
            post.flagged = request.POST.get('flagged') == 'true'

        if 'featured' in request.POST:
            if not request.user.has_perm('blog.view_featured'):
                return HttpResponseForbidden()
            post.featured = request.POST.get('featured') == 'true'

        post.save()
        return JsonResponse({'status': 'ok'})

    def delete(self, request, post_id):
        if not (request.user.groups.filter(name='Moderator').exists() or
                request.user.groups.filter(name='Admin').exists()):
            return HttpResponseForbidden()

        post = BlogPost.objects.get(id=post_id)
        post.delete()
        return JsonResponse({'status': 'deleted'})
```

**Lines of code:** ~70 lines (model + views, without tests or templates)

---

## Summary: Why Molecule?

### Advantages Over All Systems

1. **Compile-Time Safety**: Catch authorization errors at build time, not in production
2. **Zero Boilerplate**: No annotations, decorators, or manual checks
3. **Centralized**: All authorization in domain model, not scattered across codebase
4. **Type-Safe**: Full Scala type system backing
5. **Automatic**: Authorization enforced automatically, no manual checks
6. **Refactoring**: Compiler helps with refactoring, not just grep
7. **DRY**: Define authorization once, used everywhere
8. **Natural Syntax**: Reads like English with Scala's `with` keyword
9. **Field-Level**: Declarative field-level permissions
10. **Testable**: Type system validates, reducing test burden

### When Other Systems Might Be Preferable

- **GraphQL systems**: If you're already heavily invested in GraphQL
- **Existing codebases**: Migration cost may be high for mature applications
- **Polyglot teams**: If team doesn't know Scala
- **Dynamic requirements**: If authorization logic changes frequently at runtime (use Layer 5: Validated)

### Molecule's Sweet Spot

- ✓ New Scala projects
- ✓ Domain-driven design
- ✓ Type-safe applications
- ✓ Teams that value compile-time safety
- ✓ Applications where authorization is complex but mostly static
- ✓ Teams that want to minimize boilerplate

---

## Conclusion

Molecule's authorization model is unique in the industry for providing:

1. **Compile-time validation** - catch errors before deployment
2. **Zero boilerplate** - no manual checks, annotations, or decorators
3. **Full type safety** - leverages Scala's type system
4. **Declarative syntax** - natural, readable permission definitions
5. **Automatic enforcement** - no way to forget authorization checks

While other systems require runtime checks, manual enforcement, and scattered authorization logic, Molecule centralizes everything in the domain model with compile-time guarantees.

**The result:** More secure applications with less code and fewer bugs.
