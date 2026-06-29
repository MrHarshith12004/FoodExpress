# 🍔 FoodExpress - Premium Food Delivery Web Application

FoodExpress is a modern, responsive food delivery web application built using Java, Jakarta Servlet API, MySQL, and styled with a custom premium design system (**Vibrant Cravings**). The project follows the **MVC (Model-View-Controller)** architecture pattern, leveraging JSP for rendering views, Servlets as controllers, and a robust DAO layer for database persistence.

---

## 🚀 Key Features

*   **Secure Authentication**: User registration and login utilizing **BCrypt** password hashing to store credentials securely.
*   **Dynamic Restaurant Directory**: A beautiful home page showcasing active restaurants with ratings, descriptions, addresses, and delivery times.
*   **Categorized Restaurant Menus**: Detailed menus grouped by categories (Pizzas, Burgers, Biryanis, Sides, etc.) with Veg/Non-Veg indicators and pricing.
*   **Asynchronous Cart Management (AJAX)**:
    *   **Side Drawer Cart**: Built using vanilla JS (`fetch` API) to add, update, and remove items with real-time UI/badge updates without full-page reloads.
    *   **Standalone Cart Page**: Fallback/detailed page for reviewing items and pricing.
*   **Order Checkout & Flow**: Delivery address entry, payment method selection, and seamless order creation.
*   **Order History**: A personal panel for users to view past orders, itemized receipts, subtotals, and delivery status.

---

## 🛠️ Technology Stack

*   **Backend**: Java 21, Jakarta EE 10+ (Servlet 6.0 API)
*   **Frontend**: JSP (JavaServer Pages), CSS3 (featuring the *Vibrant Cravings* design system), Vanilla JS (Fetch API/AJAX), FontAwesome 6.4.0 (Icons)
*   **Database**: MySQL 8.0+
*   **External Libraries**:
    *   `mysql-connector-j-9.2.0.jar` (JDBC driver)
    *   `jbcrypt-0.4.jar` (Password hashing)
*   **Deployment Server**: Apache Tomcat 10.1 (required for Jakarta namespace support)
*   **IDE**: Eclipse IDE (Dynamic Web Project)

---

## 📁 Project Structure

```text
FoodExpress/
├── .classpath                  # Eclipse build path config
├── .project                    # Eclipse project config
├── WebContent/                 # Web assets directory
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── foodapp/
        │           ├── controller/   # Jakarta Servlets (Home, Login, Cart, etc.)
        │           ├── dao/          # Database access interfaces
        │           ├── daoimpl/      # MySQL JDBC DAO implementations
        │           ├── model/        # POJOs / Entity model classes
        │           └── util/         # Database connection & connection test tools
        └── webapp/
            ├── WEB-INF/
            │   └── lib/              # Bundled libraries (MySQL Connector, BCrypt)
            ├── jsp/                  # Production JSP views (home, cart, login, etc.)
            ├── js/
            │   └── app.js            # Client-side AJAX & Drawer script
            ├── images/               # Restaurant and Menu images
            ├── schema.sql            # MySQL Database schema & seed data
            └── index.jsp             # Entry point (redirects to /home or /login)
```

> [!NOTE]
> The JSPs inside `/src/main/webapp/jsp/` are the active, production views routed by the Servlets. The JSPs in the root of `/src/main/webapp/` are retained as backup/legacy files.

---

## 💾 Database Setup

1.  **Start MySQL server** on your local machine.
2.  Open your MySQL client (Command Line, Workbench, or DBeaver) and run the `schema.sql` script located in:
    `src/main/webapp/schema.sql`
    
    This will:
    *   Create the `foodexpress` database (if it doesn't exist).
    *   Create all required tables (`users`, `restaurants`, `menu`, `orders`, `order_items`).
    *   Seed the database with sample restaurants and menus (e.g., *The Pizza Bakery*, *Leon Grill*, *Meghana Foods*, etc.).

3.  Configure database credentials in [DBConnection.java](file:///c:/Users/harsh/eclipse-workspace/FoodExpress/src/main/java/com/foodapp/util/DBConnection.java):
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/foodexpress?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // Change if needed
    private static final String PASSWORD = "9632";   // Update with your MySQL password
    ```

4.  *(Optional)* Run [TestConnection.java](file:///c:/Users/harsh/eclipse-workspace/FoodExpress/src/main/java/com/foodapp/util/TestConnection.java) as a Java Application in Eclipse to verify the database connection:
    *   If successful, it outputs `Database Connected Successfully!` in the console.

---

## 🏃 Run & Deploy Instructions

### Prerequisites
*   **Java Development Kit (JDK) 21**
*   **Apache Tomcat 10.1** (Tomcat 9 or below will fail due to `javax` vs `jakarta` package namespace differences)
*   **Eclipse IDE for Enterprise Java and Web Developers**

### Setup in Eclipse
1.  **Import Project**:
    *   Open Eclipse.
    *   Go to **File** -> **Import** -> **General** -> **Existing Projects into Workspace**.
    *   Select the root directory of this repository and click **Finish**.
2.  **Configure Tomcat Server**:
    *   Open the **Servers** view in Eclipse (**Window** -> **Show View** -> **Servers**).
    *   Click to create a new server, choose **Apache** -> **Tomcat v10.1 Server**, and point it to your Tomcat installation path.
3.  **Run on Server**:
    *   Right-click the `FoodExpress` project in the Project Explorer.
    *   Select **Run As** -> **Run on Server**.
    *   Select the **Tomcat v10.1 Server** and click **Finish**.
4.  **Access the Application**:
    *   The browser will automatically open or you can navigate to:
        `http://localhost:8080/FoodExpress/`
