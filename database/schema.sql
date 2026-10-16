-- Authors
CREATE TABLE IF NOT EXISTS authors (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    birth_date DATE
);

-- Books
CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    publication_date DATE,
    author_id INTEGER NOT NULL,
    category VARCHAR(50),
    CONSTRAINT fk_authors
        FOREIGN KEY (author_id)
        REFERENCES authors(id)
);

-- Members
CREATE TABLE IF NOT EXISTS members (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20)
);

-- Librarians
CREATE TABLE IF NOT EXISTS librarians (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE
);

-- Loans
CREATE TABLE IF NOT EXISTS loans (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    member_id INTEGER NOT NULL,
    book_isbn VARCHAR(20) NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_books
        FOREIGN KEY (book_isbn)
        REFERENCES books(isbn),
    CONSTRAINT fk_members
        FOREIGN KEY (member_id)
        REFERENCES members(id),
    CONSTRAINT loans_status_check
        CHECK (status IN ('ACTIVE', 'RETURNED'))
);
