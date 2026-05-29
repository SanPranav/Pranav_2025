---
name: CS 113 Final Project & Capstone Progress
about: Track your capstone project progress and evidence for CS 113 college credit alignment
title: "CS 113 Final Project: Capstone Progress & Deliverables"
labels: ["project", "CS113", "final-project", "capstone"]
---

# CS 113 Final Project: Capstone Progress and Deliverables

## Project Overview
This issue documents my progress and deliverables for CS 113, focusing on a comprehensive capstone project that demonstrates growth in Computer Science, JavaScript, Java, and Object-Oriented Programming. Multiple repositories showcase different elements of this work.

**CS 113 Capstone Alignment & Design Documentation:**  
📚 View full alignment details at: https://aadibhat09.github.io/NodCursor/documentation?issue=docs-cs113_capstone-cs113_alignment

---

## Core Competencies & Code Evidence Checklist

### ✅ Data Structures (Collections, Lists, Maps, Sets, Stacks, Queues, Trees, Graphs)

#### Collections & ArrayList Evidence
- **Location:** Open-Coding-Society/spring - [ImportsController.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/backups/ImportsController.java)
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Usage of ArrayList for dynamic data management
ArrayList<> tableNames = new ArrayList<>();
// HashMap for key-value lookup
Map<String, Object> columnMetadata = new HashMap<>();
// HashSet for unique values
Set<String> uniqueIds = new HashSet<>();
```

#### Queue Data Structure Evidence
- **Location:** Open-Coding-Society/spring - [Queue.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/hacks/classDataStruct/Queue.java)
```java
// Generic Queue implementation with FIFO operations
class QueueManager<T> {
    private final Queue<T> queue = new Queue<>();
    private long count = 0;
    
    // Enqueue operation
    public void add(T data) {
        this.queue.add(data);
        this.count++;
    }
    
    // Dequeue operation
    public T delete() {
        T data = this.queue.delete();
        this.count--;
        return data;
    }
    
    // Batch operations for complex data management
    @SafeVarargs
    public final void addList(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                this.add(data);
            }
    }
}
```

---

### ✅ Algorithms (Searching, Sorting, Hashing, Analysis)

#### Sorting with Comparable/Comparator Evidence
- **Location:** Open-Coding-Society/spring - [Person.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L66)
```java
@Entity
@JsonIgnoreProperties({ "submissions", "groups", "faceData" })
public class Person extends Submitter implements Comparable<Person> {
    private String name;
    private String email;
    private String uid;
    
    // Comparable implementation for sorting by name
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
    
    // Usage in initialization with Collections.sort
    public static Person[] init() {
        ArrayList<Person> people = new ArrayList<>();
        // ... populate people list from data ...
        
        // Collections.sort uses compareTo() method
        Collections.sort(people);  // O(n log n) complexity
        
        return people.toArray(new Person[0]);
    }
}
```

#### Heap Sort Algorithm Evidence
- **Location:** Open-Coding-Society/spring - [MediaApiController.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/media/MediaApiController.java#L63-L78)
```java
@RestController
@RequestMapping("/api/media")
public class MediaApiController {
    
    // Heap sort implementation (O(n log n) complexity)
    private static List<Score> heapSort(List<Score> list) {
        int n = list.size();
        
        // Build heap (rearrange array into max-heap structure)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }
        
        // Extract elements from heap and place at end
        for (int i = n - 1; i > 0; i--) {
            Score temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);
            heapify(list, i, 0);  // O(log n) per extraction
        }
        return list;  // Final complexity: O(n log n)
    }
}
```

#### Big-O Complexity Analysis Evidence
- **Location:** SanPranav/Pranav_2025 - [bigo.ipynb](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/_notebooks/CSP/CSSE/2024-12-02-18_bigo.ipynb)
```python
# O(1) - Constant Time
def constant_time_complexity(arr):
    """Fixed operations regardless of input size."""
    return ((arr[0] if len(arr) > 0 else 0) * 
            (arr[-1] if len(arr) > 0 else 0))

# O(log n) - Logarithmic Time
def logarithmic_time_complexity(n):
    """Binary search pattern."""
    count = 0
    i = 1
    while i < n:
        i *= 2
        count += 1
    return count

# O(n) - Linear Time
def linear_time_complexity(arr):
    """Single iteration through array."""
    return sum(arr)

# O(n²) - Quadratic Time
def quadratic_time_complexity(arr):
    """Nested loops for comparisons."""
    count = 0
    for i in range(len(arr)):
        for j in range(len(arr)):
            if arr[i] < arr[j]:
                count += 1
    return count
```

---

### ✅ Object-Oriented Design (Abstraction, Encapsulation, Inheritance, Polymorphism)

#### Inheritance & Polymorphism Evidence
- **Location:** Open-Coding-Society/spring - [Animal.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/hacks/classDataStruct/Animal.java#L53)
```java
// Base class with abstract methods
public class Animal extends Generics {
    private String name;
    private int age;
    private String color;
    
    // Polymorphic method - overrides parent
    @Override
    public String toString() {
        return String.format("Animal{name='%s', age=%d, color='%s'}", 
                            name, age, color);
    }
    
    // Test data demonstrating use of subclass
    public static Animal[] animalData() {
        return new Animal[]{
            new Animal("Lion", 8, "Gold"),
            new Animal("Pig", 3, "Pink"),
            new Animal("Robin", 7, "Red"),
            new Animal("Cat", 10, "Black")
        };
    }
}
```

#### Encapsulation with Getters/Setters
- **Location:** Open-Coding-Society/spring - [Person.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L1-L50)
```java
@Entity
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@AllArgsConstructor
@NoArgsConstructor
public class Person extends Submitter {
    
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;  // Encapsulated - write-only via JSON
    
    @NotEmpty
    @Email
    @Column(unique = true, nullable = false)
    private String email;  // Encapsulated with validation
    
    @Column(unique = true, nullable = false)
    private String uid;  // Unique constraint enforced
    
    // Custom constructor demonstrating encapsulation
    public Person(String email, String uid, String password, 
                  String sid, String name, String pfp,
                  Boolean kasmServerNeeded, PersonRole role) {
        this.email = email;
        this.uid = uid;
        this.password = password;
        this.sid = sid;
        this.name = name;
        this.pfp = pfp;
        this.kasmServerNeeded = kasmServerNeeded;
        this.roles.add(role);
        this.timeEntries = new Tinkle(this, "");
        this.banks = new Bank(this);  // Association
    }
}
```

#### Abstract Classes & Interfaces
- **Location:** Open-Coding-Society/spring - [Person.java extends Submitter & Comparable](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L66)
```java
public class Person extends Submitter implements Comparable<Person> {
    // Implements Comparable interface - enforces compareTo() implementation
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
    
    // Extends Submitter - inherits submission management
    // One-to-Many relationship demonstrating composition
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<SynergyGrade> grades;
}
```

---

### ✅ RESTful API Development (HTTP Methods, Status Codes, Error Handling)

#### REST Controller with CRUD Operations
- **Location:** Open-Coding-Society/spring - [QuestsController.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/quests/QuestsController.java)
```java
@RestController
@RequestMapping("/api/quests")
public class QuestsController {
    
    @Autowired
    private QuestsRepository repository;
    
    // GET - Retrieve single quest
    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuest(@PathVariable Long id) {
        Optional<Quest> quest = repository.findById(id);
        return ResponseEntity.of(quest);  // 200 OK or 404 NOT FOUND
    }
    
    // POST - Create new quest
    @PostMapping("/create")
    public ResponseEntity<Quest> createQuest(@Valid @RequestBody Quest requestBodyQuest) {
        if (requestBodyQuest.getName() == null || 
            requestBodyQuest.getPermalink() == null ||
            !requestBodyQuest.getPermalink().startsWith("/")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // 400
        }
        
        if (repository.findAll().stream()
            .anyMatch(q -> q.getPermalink().equals(requestBodyQuest.getPermalink()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);  // 409
        }
        
        Quest newQuest = new Quest(
            requestBodyQuest.getName(),
            requestBodyQuest.getDifficulty(),
            requestBodyQuest.getPermalink(),
            requestBodyQuest.getTotalSubmodules(),
            requestBodyQuest.getRewardPoints()
        );
        repository.save(newQuest);
        return new ResponseEntity<>(newQuest, HttpStatus.CREATED);  // 201
    }
    
    // PUT - Update quest
    @PutMapping("/update/{id}")
    public ResponseEntity<Quest> updateQuest(
        @PathVariable Long id,
        @Valid @RequestBody Quest requestBodyQuest) {
        Optional<Quest> existingQuestOpt = repository.findById(id);
        if (!existingQuestOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404
        }
        
        Quest existingQuest = existingQuestOpt.get();
        if (requestBodyQuest.getName() != null) {
            existingQuest.setName(requestBodyQuest.getName());
        }
        if (requestBodyQuest.getDifficulty() != null) {
            existingQuest.setDifficulty(requestBodyQuest.getDifficulty());
        }
        repository.save(existingQuest);
        return new ResponseEntity<>(existingQuest, HttpStatus.OK);  // 200
    }
}
```

#### Batch API with JSON Processing
- **Location:** Open-Coding-Society/spring - [StudentGradeController.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/grades/StudentGradeService.java)
```java
// POST /api/grades/submit
public StudentGradesResponse submitGrades(GradeDataDTO gradeData) {
    Map<String, StudentGradeSummary> studentSummaries = new HashMap<>();
    
    // Organize grades by student name
    for (GradeEntry entry : gradeData.getAllGrades()) {
        String studentName = entry.getStudentName();
        
        StudentGradeSummary summary = studentSummaries.computeIfAbsent(
            studentName,
            k -> new StudentGradeSummary(studentName)
        );
        
        // Group by category and calculate averages
        summary.addGrade(entry.getCategory(), entry.getGrade());
    }
    
    // Save to database
    List<StudentGrade> saved = repository.saveAll(gradesToSave);
    
    return new StudentGradesResponse(
        "Grades successfully saved",
        saved.size(),
        studentSummaries.size(),
        new ArrayList<>(studentSummaries.keySet()),
        studentSummaries
    );  // Returns JSON response
}
```

---

### ✅ Database Integration (JPA/Hibernate, Entity Relationships)

#### JPA Entities with Relationships
- **Location:** Open-Coding-Society/spring - [Person.java](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L169-L208)
```java
@Entity
public class Person extends Submitter {
    
    // One-to-One relationship with Bank
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Bank banks;
    
    // One-to-Many relationship with SynergyGrade
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<SynergyGrade> grades;
    
    // Many-to-Many relationship with PersonRole
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<PersonRole> roles = new ArrayList<>();
    
    // Many-to-Many relationship with Groups (bidirectional)
    @ManyToMany(mappedBy = "groupMembers")
    @JsonBackReference
    @JsonIgnore
    private List<Groups> groups = new ArrayList<>();
    
    // JSON column for complex data storage
    @Convert(converter = GradesJsonConverter.class)
    @Column(name = "gradesJson", columnDefinition = "text")
    private List<Map<String, Object>> gradesJson = new ArrayList<>();
    
    // Custom constructor with relationship initialization
    public Person(String email, String uid, String password, String sid,
                  String name, String pfp, Boolean kasmServerNeeded,
                  PersonRole role) {
        this.email = email;
        this.uid = uid;
        this.password = password;
        this.sid = sid;
        this.name = name;
        this.pfp = pfp;
        this.kasmServerNeeded = kasmServerNeeded;
        this.roles.add(role);
        
        // Initialize related entities
        this.timeEntries = new Tinkle(this, "");
        this.banks = new Bank(this);
    }
}
```

#### Repository Pattern
- **Location:** Open-Coding-Society/spring - JPA Repository
```java
public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    // Custom query methods
    Person findByUid(String uid);
    Person findByEmail(String email);
    List<Person> findByRole(String role);
}
```

---

### ✅ Testing & Validation

#### Unit Test Structure
- **Location:** Open-Coding-Society/spring - pom.xml includes testing dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### Input Validation with Annotations
```java
@Entity
public class Person {
    @NotEmpty
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    
    @NotEmpty
    @Size(min = 1)
    @Column(unique = true, nullable = false)
    private String uid;
    
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;
    
    @Valid
    @RequestBody
    // Validates complex nested objects
}
```

---

### ✅ Deployment (Docker, docker-compose, nginx)

#### Docker Configuration
- **Location:** Open-Coding-Society/spring - [Dockerfile](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/Dockerfile)
```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
RUN apk update && apk upgrade && \
    apk add --no-cache git && \
    rm -rf /var/cache/apk/*
COPY . /app
RUN ./mvnw package
CMD ["java", "-jar", "target/spring-0.0.1-SNAPSHOT.jar"]
EXPOSE 8585 8589
```

#### Docker Compose Configuration
- **Location:** Open-Coding-Society/spring - [docker-compose.yml](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/docker-compose.yml)
```yaml
version: '3'
services:
  web:
    image: java_springv1
    build: .
    ports:
      - "8585:8585"
      - "8589:8589"
    volumes:
      - ./volumes:/app/volumes
    restart: unless-stopped
```

#### Maven Configuration (pom.xml)
- **Location:** Open-Coding-Society/spring - [pom.xml](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/pom.xml)
```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- JPA/Hibernate for database -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- Security for JWT -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <!-- SQLite/MySQL -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

---

### ✅ Documentation & Blog Portfolio

#### Frontend Code Examples
- **Location:** SanPranav/Pranav_2025 - [API Read Documentation](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/hacks/api/read.md)
```javascript
// Fetch API call with error handling
const resultContainer = document.getElementById("result");
const url = "http://localhost:5001/api/data";

const options = {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    },
};

fetch(url, options)
    .then(response => response.json())
    .then(data => {
        // Process response data
        const resultContainer = document.getElementById("result");
        data.forEach(person => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.residence}</td>
            `;
            resultContainer.appendChild(row);
        });
    })
    .catch(error => console.error('Error:', error));
```

#### Jupyter Notebook Documentation
- **Location:** SanPranav/Pranav_2025 - Multiple notebooks including:
  - [Big O Complexity Analysis](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/_notebooks/CSP/CSSE/2024-12-02-18_bigo.ipynb)
  - [Flask API Integration](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/_notebooks/CSP/CSSE/2024-12-02-19_python-flask.ipynb)
  - [JSON Handling](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/_notebooks/JavaScriptLessons/JSON/2026-01-16-json_lesson.ipynb)
  - [AP Exam Study Guide](https://github.com/SanPranav/Pranav_2025/blob/09f86e9610a28b45f922df2a80605ea717b51710/_notebooks/CSP/CSSE/2024-12-02-18_studyguide.ipynb)

---

## Required Deliverables Checklist

- [ ] **Backend Implementation:** Java Spring Boot with JPA/Hibernate ✅ [Evidence](https://github.com/Open-Coding-Society/spring)
- [ ] **Data Structures:** 3+ collections (ArrayList, HashMap, HashSet, Queue) ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/backups/ImportsController.java)
- [ ] **Algorithms:** Sorting (Comparable), Heap Sort, Big-O Analysis ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L313)
- [ ] **OOP Design:** Abstraction, encapsulation, inheritance, polymorphism ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/hacks/classDataStruct/Animal.java)
- [ ] **REST API:** HTTP methods, status codes, error handling ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/quests/QuestsController.java)
- [ ] **Database:** JPA entities, relationships (OneToMany, ManyToMany) ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L169)
- [ ] **Testing:** Input validation, JUnit structure ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/pom.xml#L99)
- [ ] **Docker Deployment:** Dockerfile & docker-compose ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/Dockerfile)
- [ ] **JavaDoc Comments:** All public classes and methods documented ✅ [Evidence](https://github.com/Open-Coding-Society/spring/blob/373d943f6d607f65f72c372c210a935e920a84a8/src/main/java/com/open/spring/mvc/person/Person.java#L50)
- [ ] **Blog Portfolio:** Design docs, code highlights, PR/commit links
- [ ] **Capstone Project:** Solves real-world problem with complete functionality

---

## Related Repositories

| Repository | Purpose | Language | Link |
|------------|---------|----------|------|
| **spring** | Backend API, data structures, OOP, deployment | Java/Spring Boot | [Open-Coding-Society/spring](https://github.com/Open-Coding-Society/spring) |
| **pages** | Frontend UI, HTML/CSS/JavaScript templates | HTML/JavaScript/SCSS | [Open-Coding-Society/pages](https://github.com/Open-Coding-Society/pages) |
| **Pranav_2025** | Personal portfolio, Jupyter notebooks, learning journey | Python/JavaScript/Jupyter | [SanPranav/Pranav_2025](https://github.com/SanPranav/Pranav_2025) |
| **NodCursor** | TypeScript capstone project with CS 113 alignment | TypeScript | [aadibhat09/NodCursor](https://github.com/aadibhat09/NodCursor) |

---

## Sprint Timeline

### Sprint 7 (Weeks 25-29): Data Structures & Passion Project v1.0
- [ ] Exploration and design phase for capstone projects
- [ ] Learn and apply data structures (collections, maps, algorithms)
- [ ] Build initial project prototype (v1.0)
- [ ] Focus on backend architecture and database design
- [ ] Peer teaching sessions on AP FRQs

### Sprint 8 (Weeks 30-33): AP Exam Preparation
- [ ] v1.0 participation and feature completion live reviews
- [ ] Individual checkout on Data Structures competency
- [ ] Continue project development alongside AP study

### Sprint 9 (Weeks 34-36): Passion Project v2.0 & N@tM
- [ ] Refine and enhance project to v2.0 with advanced features
- [ ] Focus on deployment, testing, and documentation
- [ ] Team code reviews and integration
- [ ] Final showcase at N@tM

---

## Grading Scale

- **A (90-100%):** Demonstrates mastery of CS 113 objectives with sophisticated implementation and thorough documentation
- **B (80-89%):** Meets all CS 113 required objectives with solid understanding and complete deliverables
- **C (70-79%):** Meets most CS 113 required objectives with acceptable understanding
- **Below 70%:** Does not meet minimum CS 113 credit requirements

---

## Notes for College Credit Articulation

✅ This issue and the code evidence linked above demonstrate complete alignment with **Mira Costa College CS 113** learning objectives.

✅ All minimum requirements for CS 113 credit have been documented with specific code references.

✅ Design documents, deployment guides, and testing evidence are organized for college review.

---

**Last Updated:** {{ site.time | date: "%Y-%m-%d" }}  
**Assignee:** @SanPranav  
**Related Issues:** N/A  
**Related PRs:** N/A
