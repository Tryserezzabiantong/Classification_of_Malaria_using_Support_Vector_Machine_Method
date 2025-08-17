# Malaria Classification System

A Java-based application for classifying malaria disease using Support Vector Machine (SVM) algorithm.

## Project Overview

This application is designed to classify malaria disease types using machine learning techniques. It provides a graphical user interface for data input, training, and classification results.

## Prerequisites

### Required Software
- **Java JDK 8** or higher
- **MySQL Server** (XAMPP, MySQL Community Server, etc.)
- **MySQL Connector/J** (JDBC Driver)

### System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 2GB RAM
- **Disk Space**: At least 100MB free space

## Installation & Setup

### 1. Database Setup

#### Start MySQL Server
```bash
# If using XAMPP
# Start XAMPP Control Panel and start MySQL service

# If using MySQL Community Server
sudo systemctl start mysql
```

#### Create Database
```bash
# Connect to MySQL
mysql -u root -p

# Create database
CREATE DATABASE penyakitmalaria1;

# Import data (if you have SQL file)
mysql -u root -p penyakitmalaria1 < penyakitmalaria1.sql
```

### 2. MySQL JDBC Driver Setup

#### Download MySQL Connector/J
1. Visit: https://dev.mysql.com/downloads/connector/j/
2. Select "Platform Independent"
3. Download ZIP archive (should be 2-3 MB)
4. Extract and copy the `.jar` file to `libs/` folder

#### Verify Driver
```bash
# Check file size (should be 2-3 MB, not 554 bytes)
ls -la libs/mysql-connector-java.jar
```

## Running the Application

### Method 1: Command Line

#### Compile the Project
```bash
# Navigate to project directory
cd "PenyakitMalaria_fix 2"

# Compile Java files
javac -cp "libs/*" -d build/classes src/klasifikasimalaria/*.java
```

#### Run the Application
```bash
# Run with MySQL driver
java -cp "build/classes:libs/*" klasifikasimalaria.Main

# Run without MySQL driver (offline mode)
java -cp build/classes klasifikasimalaria.Main
```

### Method 2: Using NetBeans IDE

1. **Open NetBeans IDE**
2. **Open Project**: File → Open Project → Select project folder
3. **Clean and Build**: Right-click project → Clean and Build
4. **Run Project**: Click Run button or F6

### Method 3: Using Build Tools

#### Using Ant (build.xml)
```bash
# Clean and build
ant clean
ant build

# Run
ant run
```

## Project Structure

```
PenyakitMalaria_fix 2/
├── src/
│   └── klasifikasimalaria/
│       ├── Main.java                 # Main entry point
│       ├── Klasifikasi_Malaria.java # Main GUI class
│       ├── koneksidata.java         # Database connection
│       ├── LevelTraining.java       # Training level interface
│       └── *.form                   # NetBeans GUI design files
├── libs/                            # External libraries
├── build/                           # Compiled classes
├── nbproject/                       # NetBeans project files
├── build.xml                        # Ant build file
└── *.sql                           # Database schema files
```

## Configuration

### Database Connection Settings
File: `src/klasifikasimalaria/koneksidata.java`

```java
String url = "jdbc:mysql://localhost/penyakitmalaria1";
String user = "root";
String pass = ""; // Set your MySQL password here
```

### Application Settings
- **Default Port**: 3306 (MySQL default)
- **Database Name**: penyakitmalaria1
- **Username**: root
- **Password**: (empty by default)

## Troubleshooting

### Common Issues

#### 1. MySQL Driver Not Found
```
Exception: ClassNotFoundException: com.mysql.jdbc.Driver
```
**Solution**: 
- Ensure MySQL Connector/J is in `libs/` folder
- Check file size (should be 2-3 MB)
- Verify classpath includes `libs/*`

#### 2. Database Connection Failed
```
Exception: Connection refused
```
**Solution**:
- Verify MySQL server is running
- Check database name and credentials
- Ensure database exists

#### 3. GUI Not Displaying
**Solution**:
- Check Java version compatibility
- Verify all dependencies are loaded
- Check for error messages in console

#### 4. Compilation Errors
**Solution**:
- Ensure Java JDK is properly installed
- Check JAVA_HOME environment variable
- Verify all source files are present

### Error Logs
Check console output for detailed error messages. Common warnings include:
- Font warnings (non-critical)
- Unchecked operations warnings
- Database connection status

## Features

### Main Interface
- **Data Input**: Enter malaria classification parameters
- **Training Data**: Load and manage training datasets
- **SVM Processing**: Execute Support Vector Machine algorithm
- **Results Display**: View classification results and accuracy

### SVM Parameters
- **Kernel Types**: Linear, Polynomial, RBF
- **Complexity**: C parameter for SVM
- **Gamma**: Kernel coefficient
- **Iterations**: Maximum training iterations

## Development

### Building from Source
```bash
# Clone or download project
# Install dependencies
# Compile and run as described above
```

### Modifying the Application
- **GUI Changes**: Use NetBeans GUI Builder
- **Logic Changes**: Edit Java source files
- **Database Changes**: Modify SQL files and connection settings

## Support

### Getting Help
- Check error messages in console
- Verify all prerequisites are met
- Ensure database is properly configured

### Known Issues
- Font warnings on some systems (non-critical)
- Database connection required for full functionality
- Java 8+ compatibility required

## License

This project is for educational and research purposes.

## Author

**Tryse Rezza Biantong**

---

**Note**: This application requires a running MySQL database to function properly. Ensure all prerequisites are met before running.
