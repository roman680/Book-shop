
---

# 📚 BOOKSHOP 📚

Welcome to the Bookshop project! This application has been developed to streamline the operations of an online bookstore, offering a range of functionalities to enhance user experience and facilitate efficient management.

## 🛠️ Functionality

- 📦 **Inventory Management:** Add new books, remove outdated items, and organize books into categories.
- 🛒 **Shopping Cart Operations:** Populate shopping carts and process orders seamlessly.
- 📋 **Order Management (Admin Perspective):** Monitor and manage user orders efficiently.

## 🛠️ Technologies Used

### Core Technologies

- Java ☕
- Maven 📦

### Spring Framework

- Spring Boot 🌱
- Spring Data JPA 📦
- Spring Boot Security 🔒
- Spring Boot Validation ✅
- Spring Boot Docker 🐳

### Database and Persistence

- MySQL 🐬
- Hibernate 🏰
- Liquibase 🧱

### Testing

- JUnit 5 🧬
- Mockito 🃏
- Docker 🐳
- Test Containers 📦

### API Documentation

- Swagger 🚀

### Auxiliary Libraries

- Lombok 🔨
- MapStruct 🗺️

## 📡 Examples of Endpoints

### For Users (🤓)

- `POST: /api/auth/registration` - User registration
- `POST: /api/auth/login` - User login
- `GET: /api/books` - Get all books
- `GET: /api/books/{id}` - Search a specific book by ID
- `POST: /api/orders` - Place orders
- `PATCH: /api/orders/{id}` - Update order status
- `GET: /api/orders/{orderId}/items` - Get order items from an order
- `GET: /api/orders/{orderId}/items/{itemId}` - Get a specific item from an order
- `GET: /api/cart` - Get all items in the shopping cart
- `POST: /api/cart` - Add items to the shopping cart
- `PUT: /api/cart/cart-items/{itemId}` - Update item quantity
- `DELETE: /api/cart/cart-items/{itemId}` - Delete items from the shopping cart

### For Admins (😎)

- `POST: /api/books` - Create a new book
- `PUT: /api/books/{id}` - Update information about a book
- `DELETE: /api/books/{id}` - Delete books
- `POST: /api/categories` - Create a new category
- `PUT: /api/categories/{id}` - Update information about a category
- `DELETE: /api/categories/{id}` - Delete categories

## 💻 How to Run the Project

Follow these steps to run the Bookshop project on your local machine:

### Clone the Repository

1. Open your terminal.

2. Navigate to the directory where you want to clone the repository.

3. Use the following command to clone the repository:

   ```
   git clone https://github.com/your-username/bookshop.git
   ```

   Replace `your-username` with your GitHub username if you're using your own repository. If you're using a different repository, replace the URL with the appropriate one.

### Set Up Environment Variables

1. Once the repository is cloned, navigate to the project directory:

   ```
   cd bookshop
   ```

2. Create a new file named `.env` in the root directory of the project.

3. Define the necessary environment variables in the `.env` file. These variables may include database credentials, API keys, or any other configuration specific to your environment.

### Build and Run the Project

1. Make sure you have Docker installed on your machine. If not, download and install Docker from the official website: [Docker](https://www.docker.com/get-started).

2. After setting up the `.env` file, build the Docker images using the following command:

   ```
   docker-compose build
   ```

3. Once the images are built, start the services using Docker Compose:

   ```
   docker-compose up
   ```

4. The services will start running, and you should see logs indicating the progress. Once everything is set up, you can access the Bookshop application locally.

5. Open your web browser and navigate to `http://localhost:8080` to access the Bookshop application.

### Additional Notes

- If you encounter any issues during the setup or running of the project, refer to the project documentation or reach out to the project maintainers for assistance.

- Make sure to fulfill any additional dependencies or system requirements specified by the project before running the application.

- Remember to keep your environment variables secure and never commit sensitive information to version control.

---
