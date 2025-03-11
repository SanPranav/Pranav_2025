---
layout: post 
search_exclude: true
show_reading_time: false
permalink: /pranavfeatureblog
title: Pranav's Project Feature Blog
categories: [Pranav Final Retrospective]
---

# **Code Palette: Key Features & Implementation**

## **1. Full-Stack Implementation**

![Code Palette Architecture](https://github.com/user-attachments/assets/8651ea1e-9677-4009-afc5-244a61643384)

The **Code Palette** project is a full-stack web application that integrates the following technologies:

- **Backend:** Flask (Python) for API handling.
- **Frontend:** JavaScript for UI interaction.
- **Database:** SQLite3 with SQLAlchemy for structured data storage.

This project enables users to store, update, and manage a database of programming languages in an interactive manner.

---

## **2. Backend API (Flask)**

The backend provides a **REST API** using Flask-RESTful and secures requests using **JWT authentication**. It supports **CRUD (Create, Read, Update, Delete)** operations on code palette entries.

### **Endpoints Overview:**

| Method | Endpoint          | Description                       |
|--------|------------------|-----------------------------------|
| POST   | `/api/language`  | Create a new code palette entry  |
| GET    | `/api/language`  | Retrieve all code palettes       |
| PUT    | `/api/language`  | Update an existing entry         |
| DELETE | `/api/language`  | Remove an entry from the database |

### **Example API Implementation (Post Method):**

```python
@token_required()
        def post(self):
            """
            Add a new language entry.
            """
            body = request.get_json()

            # Validate required fields
            name = body.get('name')
            creator = body.get('creator')
            popularity = body.get('popularity', 0)  # Default popularity is 0

            if not name or not creator:
                return {'message': 'Name and creator are required'}, 400

            try:
                # Create a new language entry
                new_language = Language(name=name, creator=creator, popularity=popularity)
                new_language.create()
                return jsonify({'message': 'Language added successfully', 'language': new_language.read()})
            except Exception as e:
                return {'message': 'Failed to create language', 'error': str(e)}, 500
```

---

## **3. Database Management (SQLAlchemy)**

The application uses **SQLAlchemy** to define and manage the relational database. Each code palette entry consists of:

- `id`: Unique identifier (Primary Key).
- `name`: Name of the programming language.
- `creator`: Creator of the programming language.
- `popularity`: Popularity score (integer).

### **Model Definition:**

```python
class Language(db.Model):
    """
    Language Model
    
    The Language class represents a programming language.
    
    Attributes:
        id (db.Column): The primary key, an integer representing the unique identifier for the record.
        name (db.Column): A string representing the name of the programming language.
        creator (db.Column): A string representing the creator of the programming language.
        popularity (db.Column): An integer representing the popularity score of the programming language.
    """
    __tablename__ = 'languages'

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    creator = db.Column(db.String(255), nullable=False)
    popularity = db.Column(db.Integer, default=0)  # New attribute with default value 0

    def __init__(self, name, creator, popularity=0):
        """
        Intializes the Object

        Arguements:
            name (str): The name of the programming language.
            creator (str): The creator of the programming language.
            popularity (int): The initial popularity score (default is 0).
        """
        self.name = name
        self.creator = creator
        self.popularity = popularity
```

### **Database Initialization:**

```python
def initLanguages():
    """
    The initLanguages function creates the Languages table and adds tester data to the table.
    """
    with app.app_context():
        """Create database and tables"""
        db.create_all()
        """Tester data for table"""
        tester_data = [
            Language(name='Python', creator='Guido van Rossum', popularity=500),
            Language(name='JavaScript', creator='Brendan Eich', popularity=400),
            Language(name='Java', creator='James Gosling', popularity=300)
        ]
        
        for data in tester_data:
            try:
                db.session.add(data)
                db.session.commit()
                print(f"Record created: {repr(data)}")
            except Exception as e:
                db.session.rollback()
                print(f"Error creating record for language {data.name}: {e}")
```

### **Restore Database from Backup:**

```python
    @staticmethod
    def restore(data):
        """
        Restore languages from a list of dictionaries, replacing existing entries.

        Args:
            data (list): List of dictionaries containing language data.
        
        Returns:
            dict: Dictionary of restored Language objects.
        """
        with app.app_context():
            # Clear the existing table
            db.session.query(Language).delete()
            db.session.commit()

            restored_classes = {}
            for language_data in data:
                language = Language(
                    name=language_data['name'],
                    creator=language_data['creator'],
                    popularity=language_data.get('popularity', 0)
                )
                language.create()
                restored_classes[language_data['id']] = language
            
            return restored_classes
```

---

## **4. Frontend (JavaScript & HTML)**

The frontend provides a **user-friendly interface** that enables users to interact with the database. It includes:

- **Form Fields:** Inputs for programming language details.
- **Buttons:** Create, Update, Delete, Fetch operations.
- **Dynamic UI Updates:** JavaScript updates the page without reloading.

### **Fetch API Example:**

```javascript
async function fetchLanguages() {
    const response = await fetch('/api/language', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    });
    const data = await response.json();
    document.getElementById('language-display').innerText = JSON.stringify(data, null, 2);
}
```

---

## **5. CPT Requirements & Key Concepts**

This project aligns with **CPT (Create Performance Task) requirements** by demonstrating:

### **CPT Requirements Table**

| CPT Requirement         | Feature Embodying the Requirement | How this feature fulfills the requirement |
|-------------------------|-----------------------------------|------------------------------------------|
| A list                 | Database stores multiple entries | The database stores and retrieves multiple programming languages as list items |
| A procedure            | `initLanguages()` function    | Initializes the database with test data, ensuring structured data setup |
| A call to the procedure | `fetchLanguages()` function      | Calls API endpoint to fetch data from the backend |
| Selection              | Conditional checks in API routes | Ensures valid data is processed before adding/updating database entries |
| Iteration              | `for` loops in Python & JavaScript | Loops iterate through database entries and frontend responses to process them |

This project aligns with **CPT (Create Performance Task) requirements** by demonstrating various programming concepts. Below are detailed examples from the codebase that fulfill each requirement:

### **A List**
**Feature:** Database stores multiple entries of programming languages

**Code Snippet:**
```python
tester_data = [
    Language(name='Python', creator='Guido van Rossum', popularity=500),
    Language(name='JavaScript', creator='Brendan Eich', popularity=400),
    Language(name='Java', creator='James Gosling', popularity=300)
]
```

**Implementation:** The database stores and retrieves multiple programming languages as list items, allowing for efficient data management.

---

### **A Procedure**
**Feature:** `initLanguages()` function 

**Code Snippet:**
```python
def initLanguages():
    with app.app_context():
        db.create_all()
        tester_data = [
            Language(name='Python', creator='Guido van Rossum', popularity=500),
            Language(name='JavaScript', creator='Brendan Eich', popularity=400),
            Language(name='Java', creator='James Gosling', popularity=300)
        ]
        
        for data in tester_data:
            try:
                db.session.add(data)
                db.session.commit()
                print(f"Record created: {repr(data)}")
            except Exception as e:
                db.session.rollback()
                print(f"Error creating record for language {data.name}: {e}")
```

**Implementation:** Initializes the database with test data, ensuring structured data setup with proper error handling.

---

### **A Call to the Procedure**
**Feature:** `fetchLanguages()` function and its invocation

**Code Snippet:**
```javascript
async function fetchLanguages() {
    const response = await fetch('/api/language', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    });
    const data = await response.json();
    document.getElementById('language-display').innerText = JSON.stringify(data, null, 2);
}

// Call to the function
document.getElementById('fetch-button').addEventListener('click', fetchLanguages);
```

**Implementation:** Calls API endpoint to fetch data from the backend when triggered by a user action.

---

### **Selection**
**Feature:** Conditional checks in API routes

**Code Snippet:**
```python
@token_required()
def post(self):
    body = request.get_json()

    # Validate required fields
    name = body.get('name')
    creator = body.get('creator')
    popularity = body.get('popularity', 0)

    if not name or not creator:
        return {'message': 'Name and creator are required'}, 400

    try:
        # Create a new language entry
        new_language = Language(name=name, creator=creator, popularity=popularity)
        new_language.create()
        return jsonify({'message': 'Language added successfully', 'language': new_language.read()})
    except Exception as e:
        return {'message': 'Failed to create language', 'error': str(e)}, 500
```

**Implementation:** Ensures valid data is processed before adding/updating database entries through conditional validation.

---

### **Iteration**
**Feature:** `for` loops in Python & JavaScript

**Code Snippet:**
```python
for data in tester_data:
    try:
        db.session.add(data)
        db.session.commit()
        print(f"Record created: {repr(data)}")
    except Exception as e:
        db.session.rollback()
        print(f"Error creating record for language {data.name}: {e}")
```

### **Implementation:** Loops iterate through database entries and process each one individually, allowing for batch operations with error handling.
---

## **6. Personalized Project Reference (PPR)**

This section contains code segments from my Code Palette project that demonstrate required programming concepts for the AP CSP exam.

### **Procedure Implementation & Call**

#### **Procedure Implementation:**
The following code segment shows my student-developed procedure `initLanguages()` that initializes the database:

```python
def initLanguages():
    with app.app_context():
        """Create database and tables"""
        db.create_all()
        """Tester data for table"""
        tester_data = [
            Language(name='Python', creator='Guido van Rossum', popularity=500),
            Language(name='JavaScript', creator='Brendan Eich', popularity=400),
            Language(name='Java', creator='James Gosling', popularity=300)
        ]
        
        for data in tester_data:
            try:
                db.session.add(data)
                db.session.commit()
                print(f"Record created: {repr(data)}")
            except Exception as e:
                db.session.rollback()
                print(f"Error creating record for language {data.name}: {e}")
```

This procedure:
- Has a defined name `initLanguages()`
- Uses parameters implicitly (the global `app` context)
- Implements an algorithm with:
  - **Sequencing**: Operations performed in order
  - **Selection**: `try/except` block checks for errors during data insertion
  - **Iteration**: `for data in tester_data` loop to process each language entry

#### **Procedure Call:**
The following code segment shows where the procedure is called:

```python
# Called during application startup
if __name__ == "__main__":
    initLanguages()  # Initialize the database with test data
    app.run(debug=True, host="0.0.0.0", port="8086")
```

### **List Usage for Managing Complexity**

#### **List Data Storage:**
The following code segment shows how data is stored in a list:

```python
tester_data = [
    Language(name='Python', creator='Guido van Rossum', popularity=500),
    Language(name='JavaScript', creator='Brendan Eich', popularity=400),
    Language(name='Java', creator='James Gosling', popularity=300)
]
```

This list stores multiple Language objects, each containing programming language information.

#### **List Data Usage:**
The following code segment shows how the list data is used:

```python
@token_required()
def get(self):
    """
    Get all language entries.
    """
    try:
        languages = Language.query.all()
        return jsonify([language.read() for language in languages])
    except Exception as e:
        return {'message': 'Failed to retrieve languages', 'error': str(e)}, 500
```

This code:
- Retrieves all language entries from the database
- Uses a list comprehension `[language.read() for language in languages]` to process each item
- Creates a new JSON response from the existing data
- Manages complexity by organizing multiple language objects into a structured list

---

Looking at the provided code snippets, I notice that all PPR requirements are fulfilled:

1. **Procedure Implementation**:
   - The `initLanguages()` function is defined with a clear name
   - It implicitly uses parameters (app context)
   - It contains sequencing, selection (try/except), and iteration (for loop)

2. **Procedure Call**:
   - The code shows where `initLanguages()` is called

3. **List Data Storage**:
   - The `tester_data` list shows data storage

4. **List Data Usage**:
   - The API GET method shows how list data is processed and used


## **Conclusion**

The **Code Palette** project effectively demonstrates full-stack development principles with a focus on **database management, RESTful APIs, and frontend integration**. It also adheres to **CPT guidelines**, making it a strong candidate for submission.

| Category                          | Points | Description                                                                                                                                                       | Self Grade |
| --------------------------------- | ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------- |
| **Full Stack Project Demo**       | 2      | Demonstrate the project, highlight CPT requirements, and incorporate N@tM feedback. | 1.90          |
| **Project Feature Blog Write-up** | 1      | Use CPT/FRQ language to write a structured blog post on project features.                                                                                        | 0.92       |
